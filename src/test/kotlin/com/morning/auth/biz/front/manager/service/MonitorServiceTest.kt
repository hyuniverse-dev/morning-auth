package com.morning.auth.biz.front.manager.service

import com.morning.auth.biz.front.manager.dto.req.ReqLoginDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@SpringBootTest
//@Profile("dev")
class MonitorServiceTest {
    @Autowired
    private lateinit var monitorService: MonitorService

    @Test
    @DisplayName("관제시스템 관리자 로그인에 성공하면 관리자 정보를 반환합니다.")
    fun loginManagerSuccessTest() {
        // given
        val manager = ReqLoginDTO("master@mncf.io", "test1234!")

        // when
        val result = monitorService.loginManager(manager)

        // then
        assertThat(result)
            .extracting("loginId", "managerCode")
            .contains("master@mncf.io", "e3697962871846e79d552a0fa2c78e08")
    }

    @Test
    @DisplayName("관제시스템 로그인 아이디가 존재하지 않으면 예외가 발생합니다.")
    fun loginManagerFailLoginIdTest() {
        // given
        val manager = ReqLoginDTO("unknown@mncf.io", "test1234!")

        // when + then
        val exception = assertThrows<IllegalArgumentException> {
            monitorService.loginManager(manager)
        }

        assertThat(exception.message == "존재하지 않는 아이디입니다.")
    }

    @Test
    @DisplayName("입력한 로그인 아이디의 비밀번호와 맞지 않으면 예외가 발생합니다.")
    fun loginManagerFailPassworddTest() {
        // given
        val manager = ReqLoginDTO("master@mncf.io", "1234test")

        // when + then
        val exception = assertThrows<IllegalArgumentException> {
            monitorService.loginManager(manager)
        }

        assertThat(exception.message == "올바르지 않은 비밀번호 입니다.")
    }

    @Test
    @DisplayName("입력한 관리자 코드로 조회한 관리자명과 저장 되어있는 관리자명이 동일합니다.")
    fun fetchManagerByCode() {
        // given
        val result = monitorService.fetchManagerByCode("e3697962871846e79d552a0fa2c78e08")

        // when + then
        assertThat(result)
            .extracting("managerCode", "name")
            .contains("e3697962871846e79d552a0fa2c78e08", "옥순")
    }
}

