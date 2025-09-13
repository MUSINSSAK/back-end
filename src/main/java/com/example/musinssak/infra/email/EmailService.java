package com.example.musinssak.infra.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// @Service: 이 클래스가 비즈니스 로직을 처리하는 서비스 계층의 컴포넌트임을 스프링에게 알려줍니다.
// 스프링이 이 클래스의 객체를 알아서 생성하고 관리해줍니다.
@Service
// @RequiredArgsConstructor: final 키워드가 붙은 필드를 포함하는 생성자를 자동으로 만들어줍니다.
// 덕분에 @Autowired 없이도 의존성 주입이 가능합니다. (생성자 주입 방식)
@RequiredArgsConstructor
public class EmailService {

    // JavaMailSender: 스프링에서 제공하는 이메일 발송용 인터페이스입니다.
    // application.yml(또는 properties)에 설정한 이메일 서버 정보를 바탕으로 이메일을 보냅니다.
    private final JavaMailSender mailSender;

    // 이메일을 보내는 메소드
    // to: 받는 사람 이메일 주소, subject: 이메일 제목, text: 이메일 본문 내용
    public void sendEmail(String to, String subject, String text) {
        // SimpleMailMessage: 간단한 텍스트 이메일을 보낼 때 사용하는 클래스
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to); // 받는 사람 설정
        message.setSubject(subject); // 제목 설정
        message.setText(text); // 내용 설정
        // message.setFrom("보내는사람@example.com"); // 보내는 사람 설정 (yml 설정으로 대체 가능)

        // mailSender.send() 메소드를 호출하여 실제로 이메일을 발송합니다.
        mailSender.send(message);
    }
}