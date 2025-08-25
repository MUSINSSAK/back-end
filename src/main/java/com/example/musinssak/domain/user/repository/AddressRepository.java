package com.example.musinssak.domain.user.repository;

import com.example.musinssak.domain.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    // @ManyToOne User를 타고 들어가지만, 키만 쓰면 심플합니다.
    // 기본배송지 우선 → 이후 최신(id desc)
    List<Address> findByUserIdOrderByIsDefaultDescIdDesc(Long userId);

    boolean existsByUserIdAndIsDefaultTrue(Long userId);

    /**
     * [벌크 UPDATE]
     * - 특정 사용자(userId)의 '현재 기본 배송지들'을 모두 기본 해제(false)한다.
     * - 일반적으로 0~1건이 변경된다(정상 설계면 기본은 1개뿐).
     *
     * 왜 벌크 UPDATE?
     * - 엔티티를 하나하나 조회해 setDefault(false) 하는 것보다 DB에 직접 쿼리 한 번이 빠름.
     *
     * 주의: 벌크 UPDATE는 1차 캐시(영속성 컨텍스트)를 건너뛰므로
     *       clearAutomatically=true로 실행 직후 캐시를 비워서 상태 불일치를 막는다.
            *       flushAutomatically=true로 실행 전 변경분을 DB에 먼저 반영한다.
     *
             * @return 업데이트된 행 수(영향받은 row 수)
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Address a set a.isDefault = false where a.user.id = :userId and a.isDefault = true")
    int clearDefaultByUserId(@Param("userId") Long userId);

    /**
     * [벌크 UPDATE]
     * - 특정 주소 한 건을 기본 배송지(true)로 설정한다.
     * - 권한 체크(내 소유인지) 는 서비스 계층에서 별도 검증(RBAC)하고 오도록 한다.
     *
     * 팁: 안전성을 더 높이려면 userId 조건까지 포함한 쿼리로 바꿀 수도 있음
     *     (setDefaultByIdAndUserId 참고).
     *
     * @return 업데이트된 행 수(영향받은 row 수). 0이면 대상이 없다는 뜻.
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Address a set a.isDefault = true where a.id = :addressId")
    int setDefaultById(@Param("addressId") Long addressId);

    long countByUserId(Long userId);


    /**
     * [벌크 UPDATE] 대상 주소 한 건을 '기본 배송지(true)'로 설정한다.
     *
     * 왜 userId 조건까지 포함할까?
     * - 단순히 id만 조건으로 두면, 실수로 다른 사용자의 주소를 기본으로 만들 위험이 있다.
     * - id + userId를 함께 걸어 '내 주소'만 변경되도록 안전하게 보장한다.
     *
     * @Modifying 옵션 설명
     * - flushAutomatically = true:
     *     이 벌크 UPDATE를 실행하기 전에, 같은 트랜잭션에서 대기 중인 변경분을 DB로 먼저 밀어넣는다.
     *     (예: 위 서비스에서 target.update(...) 한 내용이 먼저 flush됨)
     * - clearAutomatically = true:
     *     벌크 UPDATE는 영속성 컨텍스트를 거치지 않고 DB만 갱신하므로
     *     실행 직후 1차 캐시를 비워서(영속성 컨텍스트 clear) 캐시-DB 불일치를 예방한다.
     *
     * 반환값(int)
     * - 영향을 받은 행(row) 수를 반환한다.
     * - 0이면 조건에 맞는 행이 없다는 뜻(잘못된 addressId이거나 소유자 불일치).
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Address a set a.isDefault = true " +
            "where a.id = :addressId and a.user.id = :userId")
    int setDefaultByIdAndUserId(@Param("addressId") Long addressId,
                                @Param("userId") Long userId);

    /**
     * [삭제 보조용 조회]
     * - 특정 사용자(userId)의 주소들 중에서,
     *   현재 삭제 대상 addressId를 "제외"하고
     *   id 오름차순으로 가장 앞(가장 작은 id) 한 건을 가져온다.
     *
     * 사용 시나리오:
     * - 삭제 전에 내 주소 개수가 2개였을 때 → 삭제 후 정확히 1개가 남는다.
     * - 그 "남는 한 건"을 자동 기본으로 설정하기 위해 미리 찾아두는 용도.
     *
     * 반환:
     * - Optional<Address> (이론상 거의 항상 1건 존재)
     * - 서비스에서 .map(Address::getId)로 id만 뽑아 사용
     */
    Optional<Address> findFirstByUserIdAndIdNotOrderByIdAsc(Long userId, Long addressId);
}
