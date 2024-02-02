package com.morning.auth.biz.back.manager.service

import com.morning.auth.biz.back.manager.dto.req.ReqPilotDTO
import com.morning.auth.biz.back.manager.dto.req.ReqPilotDetailDTO
import com.morning.auth.biz.back.manager.dto.req.ReqPilotSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResPilotDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.dao.DuplicateKeyException
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
//@Profile("dev")
class PilotServiceTest {

    @Autowired
    private lateinit var pilotService: PilotService

    @Test
    @DisplayName("신규 파일럿 등록에 성공하면 정수 1을 반환합니다.")
    @Transactional
    fun registPilotSuccessTest() {
        // given
        val pilot = createPilot("testing@mncf.io", "test1234!!")

        // when
        val response = pilotService.registPilot(pilot)

        // then
        assertThat(response).isEqualTo(1)
    }

    @Test
    @DisplayName("파일럿 등록에 중복된 로그인아이디가 존재하면 예외를 발생합니다.")
    @Transactional
    fun registPilotFailTest() {
        // given
        val pilotA = createPilot("testing@mncf.io", "test1234!!")
        pilotService.registPilot(pilotA)

        // when
        val pilotB = createPilot("testing@mncf.io", "test1234!!")
        val exception = assertThrows<DuplicateKeyException> {
            pilotService.registPilot(pilotB)
        }

        // then
        assertThat(exception.message == "이미 사용중인 로그인 아이디 입니다").isTrue()
    }

    @Test
    @DisplayName("검색조건을 이름과 소속명으로 파일럿 목록을 조회면 각 조건에 해당하는 데이터를 반환합니다.")
    fun fetchPilotListTest() {
        // given
        val searchByName = ReqPilotSearchFilterDTO(1, 10, 0, "2023-11-01", "2023-11-30", "", "10", "순자")
        val searchByPhone = ReqPilotSearchFilterDTO(1, 10, 0, "2023-11-01", "2023-11-30", "", "20", "모닝")

        // when
        val pilotListByName = pilotService.fetchPilotList(searchByName)
        val pilotListByPhone = pilotService.fetchPilotList(searchByPhone)

        val resultByName = pilotListByName.result as? List<ResPilotDTO>
        val resultByPhone = pilotListByPhone.result as? List<ResPilotDTO>

        // then
        assertAll("Check multiple conditions",
            {
                assertThat(resultByName)
                    .extracting("name")
                    .contains("순자")
            },
            {
                assertThat(resultByPhone)
                    .extracting("groupName")
                    .contains("(주)모닝커피소프트")
            }
        )
    }

    @Test
    @DisplayName("관리자코드로 파일럿을 조회합니다.")
    fun fetchPilotTest() {
        // given
        val manager_code = "5340990558fb4a67a08571f74d5c4cc7"

        // when
        val pilot = pilotService.fetchPilot(manager_code)

        // then
        assertThat(pilot)
            .extracting("loginId", "name")
            .contains("pilot@mncf.io", "순자")
    }

    private fun createPilot(loginId: String, password: String): ReqPilotDTO {
        return ReqPilotDTO(
            loginId,
            password,
            "Y",
            "5c2563d57a2211eeb1d10242ac130002",
            "순자",
            "010-1234-1234",
            loginId,
            "(주)모닝커피소프트",
            LocalDate.of(1990, 1, 1),
            "KR",
            "명달로 116",
            ReqPilotDetailDTO(
                "특기사항",
                "자격 번호",
                "DAP",
                "TP1",
                "Y",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )
        )
    }
}
