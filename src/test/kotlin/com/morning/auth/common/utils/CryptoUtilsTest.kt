package com.morning.auth.common.utils

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import javax.crypto.BadPaddingException

@SpringBootTest
class CryptoUtilsTest {

    @Test
    @DisplayName("암호화된 원문이 주어진 문자열과 같습니다.")
    fun encryptText() {
        // given
        val encryptText = CryptoUtils.encryptText("test1234")

        // when + then
        assertThat(encryptText contentEquals "QQ1fkIrvlxETsr2Xmt0vIQ==").isTrue()
    }

    @Test
    @DisplayName("암호화된 원문을 복호화한 문자열과 입력한 평문이 같습니다.")
    fun decryptText() {
        // given
        val encryptText = CryptoUtils.encryptText("test1234")

        // when
        val decryptText = CryptoUtils.decryptText(encryptText)

        // then
        assertThat(decryptText contentEquals "test1234").isTrue()
    }

    @Test
    @DisplayName("단방향 암호화를 복호화하면 예외를 발생시킵니다.")
    fun digest() {
        // given
        val digestText = CryptoUtils.digestText("test1234")

        // when + then
        assertThatThrownBy {
            val error = CryptoUtils.decryptText(digestText)
        }
            .isExactlyInstanceOf(BadPaddingException::class.java)
            .isInstanceOf(Exception::class.java)
    }
}
