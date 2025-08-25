package com.example.musinssak.domain.user.service;

import com.example.musinssak.api.user.dto.AddressResponse;
import com.example.musinssak.api.user.dto.CreateAddressRequest;
import com.example.musinssak.api.user.dto.DefaultAddressUpdateResponse;
import com.example.musinssak.api.user.dto.UpdateAddressRequest;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.user.entity.Address;
import com.example.musinssak.domain.user.entity.User;
import com.example.musinssak.domain.user.repository.AddressRepository;
import com.example.musinssak.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    private static final int MAX_ADDRESS_COUNT = 5;

    @Override
    public List<AddressResponse> getMyAddresses(Long userId) {
        // 요구사항: 사용자 없으면 404 USER_NOT_FOUND
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return addressRepository.findByUserIdOrderByIsDefaultDescIdDesc(user.getId())
                .stream().map(AddressResponse::from).toList();
    }

    @Transactional
    @Override
    public DefaultAddressUpdateResponse setDefaultAddress(Long userId, Long addressId) {
        // 1) 사용자 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 2) 대상 주소 확인 (404)
        Address target = addressRepository.findById(addressId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));

        // 3) 권한 체크 (403) - 내 주소인지
        if (!target.getUser().getId().equals(user.getId())) {
            // ErrorCode에 FORBIDDEN 또는 AUTH_FORBIDDEN 중 하나를 사용하세요.
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        // 4) 단일성 보장: 내 모든 기본배송지 해제 → 대상만 true
        addressRepository.clearDefaultByUserId(userId);
        int changed = addressRepository.setDefaultById(addressId);
        if (changed == 0) {
            // 이 경우는 논리적으로 거의 없지만, 방어적으로 404 처리
            throw new BusinessException(ErrorCode.ADDRESS_NOT_FOUND);
        }

        // 5) 변경 후 목록 재조회하여 응답 구성 (정렬: 기본배송지 우선, 최신순)
        var after = addressRepository.findByUserIdOrderByIsDefaultDescIdDesc(userId);
        return DefaultAddressUpdateResponse.from(after);
    }

    @Override
    @Transactional
    public void createAddress(Long userId, CreateAddressRequest request) {
        // 1) 사용자 확인 (없으면 404)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 2) 최대 5개 제한
        long count = addressRepository.countByUserId(userId);
        if (count >= MAX_ADDRESS_COUNT) {
            throw new BusinessException(ErrorCode.ADDRESS_LIMIT_EXCEEDED);
        }

        // 3) 기본 설정 여부 결정
        //   - 첫 주소면 자동 기본
        //   - 요청 isDefault=true면 기존 기본 해제 후 신규를 기본으로
        boolean makeDefault = (count == 0) || request.isDefault();
        if (makeDefault) {
            addressRepository.clearDefaultByUserId(userId); // 기존 기본 해제
        }

        // 4) 저장
        Address address = Address.builder()
                .user(user)
                .label(request.getLabel())
                .recipient(request.getRecipient())
                .phone(request.getPhone())
                .postalCode(request.getPostalCode())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .isDefault(makeDefault)
                .build();

        addressRepository.save(address);
    }

    @Override
    @Transactional
    public void updateAddress(Long userId, Long addressId, UpdateAddressRequest req) {
        // [1] 사용자/주소 존재 여부 확인 -------------------------
        // - 사용자 없으면 404(USER_NOT_FOUND)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // - 수정할 주소가 없으면 404(ADDRESS_NOT_FOUND)
        Address target = addressRepository.findById(addressId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));

        // - 이 주소의 소유자가 '나'인지 확인 (RBAC)
        //   아니면 403(FORBIDDEN)
        if (!target.getUser().getId().equals(user.getId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        // [2] 내용 수정 -----------------------------------------
        // - 엔티티 도메인 메서드로 필드값 변경
        // - 영속 상태라서 트랜잭션 커밋 시점에 자동으로 UPDATE 쿼리가 나감
        target.update(
                req.getLabel(),
                req.getRecipient(),
                req.getPhone(),
                req.getAddress(),
                req.getDetailAddress(),
                req.getPostalCode()
        );

        // [3] 기본 배송지 규칙 처리 ------------------------------
        // - 내 주소 개수 조회 (1개뿐이면 무조건 기본)
        long count = addressRepository.countByUserId(userId);

        // makeDefault = (주소가 1개뿐) 또는 (요청에서 isDefault=true)
        boolean makeDefault = (count == 1) || req.isDefault();
        if (makeDefault) {
            // 3-1) 기존 기본 해제 → 대상만 기본으로 설정
            //  - 아래 두 메서드는 JPQL "벌크 UPDATE"라서 엔티티를 거치지 않고 DB를 직접 갱신함
            //  - Repository 쿼리에 @Modifying(flushAutomatically=true, clearAutomatically=true)를
            //    걸어두었기 때문에,
            //    (a) 먼저 영속성 컨텍스트의 변경분을 DB에 flush하고
            //    (b) 벌크 UPDATE 실행 직후 1차 캐시를 비워 상태 불일치를 방지함
            addressRepository.clearDefaultByUserId(userId);          // 내 기존 기본들 false
            addressRepository.setDefaultByIdAndUserId(addressId, userId); // 대상만 true
        } else {
            // 3-2) 요청이 isDefault=false 인데, 현재 대상이 기본이고,
            //      다른 주소가 1개 이상 있을 때 → 기본 해제
            if (target.isDefault() && count > 1) {
                // 엔티티 플래그만 내려도 됨 (영속 상태 → 커밋 시 UPDATE)
                target.setDefault(false);
            }
        }
        // ※ 이 메서드는 별도 반환값이 없고, 컨트롤러에서 "성공 메시지"만 내려줌
    }

    @Override
    @Transactional
    public void deleteAddress(Long userId, Long addressId) {
        // [1] 사용자/주소 존재 및 소유권 확인 -------------------------
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Address target = addressRepository.findById(addressId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));

        if (!target.getUser().getId().equals(user.getId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN); // 내 주소가 아니면 삭제 불가
        }

        // [2] 삭제 전 개수 파악 ------------------------------------
        // - 삭제 정책은 "삭제 이후 남은 개수"에 따라 달라짐
        // - delete() 전에 미리 개수를 구해 두면 flush 타이밍에 덜 민감
        long beforeCount = addressRepository.countByUserId(userId);

        // 남은 주소가 1개가 될 상황인지 미리 판단 (삭제 전 개수가 2라면, 삭제 후 1개)
        boolean willRemainExactlyOne = (beforeCount == 2);

        // (선택) 남은 1개가 될 경우를 대비해 "살아남을 주소"의 id를 미리 조회
        //  - flush 타이밍 이슈 없이 바로 기본으로 지정 가능
        Long survivorId = null;
        if (willRemainExactlyOne) {
            survivorId = addressRepository
                    .findFirstByUserIdAndIdNotOrderByIdAsc(userId, addressId)
                    .map(Address::getId)
                    .orElse(null); // 이 null은 논리상 거의 발생하지 않음
        }

        // [3] 실제 삭제 -------------------------------------------
        addressRepository.delete(target); // 영속 상태 엔티티 삭제 → 커밋 시점에 DELETE

        // [4] 삭제 후 정책 적용 ------------------------------------
        if (willRemainExactlyOne && survivorId != null) {
            // 삭제 후 1개만 남음 → 그 주소를 "자동 기본"으로 설정
            // 벌크 UPDATE 사용: 빠르고, userId 조건까지 걸어 안전성 ↑
            addressRepository.setDefaultByIdAndUserId(survivorId, userId);
            // (2개 이상 남는 케이스는 기본 배송지 없음 상태도 허용하므로 추가 작업 없음)
        }

        // ※ 삭제 후 남은 주소가 0개인 경우: 설정할 기본이 없으므로 아무 작업도 하지 않음
    }
}
