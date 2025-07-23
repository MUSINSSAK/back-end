package com.example.musinssak.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    @Test
    @DisplayName("비밀번호는 최소 4자 이상이고 짧으면 예외 발생한다")
    void shouldThrowExceptionWhenPasswordIsTooShort() {
        User user = new User("email@test.com", "1234", "신범준", LocalDateTime.now());

        assertThatThrownBy(() -> user.changePassword("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 최소 4자 이상이어야 합니다.");
    }
}
