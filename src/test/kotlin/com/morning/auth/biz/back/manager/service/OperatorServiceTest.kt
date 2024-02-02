package com.morning.auth.biz.back.manager.service

import com.morning.auth.biz.back.manager.dto.req.ReqOperatorDTO
import com.morning.auth.biz.back.manager.dto.req.ReqOperatorSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResOperatorDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.dao.DuplicateKeyException
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.regex.Pattern

@SpringBootTest
//@Profile("dev")
class OperatorServiceTest() {
    @Autowired
    private lateinit var operatorService: OperatorService

    @Test
    @DisplayName("검색조건을 이름과 전화번호로 운영자 목록을 조회면 각 조건에 해당하는 데이터를 반환합니다.")
    fun fetchOperatorListTest() {
        // given
        val searchByName = ReqOperatorSearchFilterDTO(1, 10, 0, "2023-11-01", "2023-11-30", "10", "영자")
        val searchByPhone = ReqOperatorSearchFilterDTO(1, 10, 0, "2023-11-01", "2023-11-30", "20", "1234")

        // when
        val operatorListByName = operatorService.fetchOperatorList(searchByName)
        val operatorListByPhone = operatorService.fetchOperatorList((searchByPhone))

        val responseListByName = operatorListByName.result as? List<ResOperatorDTO>
        val responseListByPhone = operatorListByPhone.result as? List<ResOperatorDTO>


        // then
        assertAll("Check multiple conditions",
            {
                assertThat(responseListByName)
                    .extracting("name")
                    .contains("영자")
            },
            {
                assertThat(responseListByPhone)
                    .extracting("phone")
                    .contains("01012341234")
            })
    }

    @Test
    @DisplayName("관리자코드로 운영자를 조회합니다.")
    fun fetchOperatorTest() {
        // given
        val managerCode = "e3697962871846e79d552a0fa2c78e08"

        // when
        val operator = operatorService.fetchOperator(managerCode)

        // then
        assertThat(operator)
            .extracting("name", "email")
            .contains("옥순", "master@mncf.io")
    }

    @Test
    @DisplayName("신규 운영자 등록에 성공하면 정수 1을 반환합니다.")
    @Transactional
    fun registOperatorSuccessTest() {
        // given
        val operator = createOperator("manager@mncf.io", "test1234!!")

        // when
        val response = operatorService.registOperator(operator)

        // then
        assertThat(response).isEqualTo(1)
    }

    @Test
    @DisplayName("운영자 등록에 중복된 로그인아이디가 존재하면 예외를 발생합니다.")
    @Transactional
    fun registOperatorFailTest() {
        // given
        val operatorA = createOperator("testing@mncf.io", "test1234!!")
        operatorService.registOperator(operatorA)

        // when + then
        val operatorB = createOperator("testing@mncf.io", "test1234!!")
        val exception = assertThrows<DuplicateKeyException> {
            operatorService.registOperator(operatorB)
        }

        assertThat(exception.message == "이미 사용중인 로그인 아이디 입니다").isTrue()

    }

    @Test
    @DisplayName("입력한 로그인 아이디가 중복이면 True 를 반환합니다.")
    fun fetchDuplicateLoginIdTest() {
        // given
        val loginId = "master@mncf.io"

        // when
        val isDuplicate = operatorService.fetchDuplicateLoginId(loginId)

        // then
        assertThat(isDuplicate).isTrue()
    }

    private fun createOperator(loginId: String, password: String): ReqOperatorDTO {
        return ReqOperatorDTO(
            loginId,
            password,
            "Y",
            "5c2563d57a2211eeb1d10242ac130002",
            "순자",
            "010-1234-1234",
            loginId,
            LocalDate.of(1990, 1, 1),
            "(주)모닝커피소프트",
            "KR",
            "명달로 116",
        )
    }
}
