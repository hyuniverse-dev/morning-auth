package com.morning.auth.biz.back.manager.service

import com.morning.auth.biz.back.manager.dto.req.ReqLoginDTO
import com.morning.auth.biz.back.manager.dto.req.ReqRoleDTO
import com.morning.auth.biz.back.manager.dto.res.ResRuleDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.regex.Pattern.compile

@SpringBootTest
//@Profile("dev")
class ManagerServiceTest() {

    @Autowired
    private lateinit var managerService: ManagerService

    @Autowired
    private lateinit var resRuleDTO: ResRuleDTO

    @Test
    @DisplayName("운영자 로그인에 성공하면 운영자 정보를 반환합니다.")
    fun loginOperatorSuccessTest() {
        // given
        val operator = ReqLoginDTO("master@mncf.io", "test1234!")

        // when
        val result = managerService.login(operator)

        // then
        assertThat(result)
            .extracting("loginId", "managerCode")
            .contains("master@mncf.io", "e3697962871846e79d552a0fa2c78e08")
    }

    @Test
    @DisplayName("로그인 아이디가 존재하지 않으면 예외가 발생합니다.")
    fun loginOperatorFailLoginIdTest() {
        // given
        val operator = ReqLoginDTO("unknown@mncf.io", "test1234!!")

        // when + then
        val exception = assertThrows<IllegalArgumentException> {
            managerService.login(operator)
        }

        assertThat(exception.message == "존재하지 않는 아이디입니다.")
    }

    @Test
    @DisplayName("입력한 로그인 아이디의 비밀번호와 맞지 않으면 예외가 발생합니다.")
    fun loginOperatorFailPassworddTest() {
        // given
        val operator = ReqLoginDTO("master@mncf.io", "1234test")

        // when + then
        val exception = assertThrows<IllegalArgumentException> {
            managerService.login(operator)
        }

        assertThat(exception.message == "올바르지 않은 비밀번호 입니다.")
    }


    @Test
    fun passwordRuleSuccessTest() {
        // given
        val normalText = "test1234!!"
        val patterns = listOf(
            compile(resRuleDTO.minPattern),
            compile(resRuleDTO.maxPattern),
            compile(resRuleDTO.numberPattern),
            compile(resRuleDTO.specialPattern),
            compile(resRuleDTO.characterPattern)
        )
        val matchers = patterns.map { it.matcher(normalText).matches() }

        // when
        val isMatched = matchers.all { it }

        // then
        assertThat(isMatched).isTrue()
    }

    @Test
    @Transactional
    fun registRoleTest() {
        // given
        val role = ReqRoleDTO("운영자 권한-팀장", menus = setOf(1, 2, 2))

        // when
        val result = managerService.registRole(role)

        // then
        assertThat(result).isEqualTo(1)
    }
}
