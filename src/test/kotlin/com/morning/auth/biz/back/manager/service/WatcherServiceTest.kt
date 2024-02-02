package com.morning.auth.biz.back.manager.service

import com.morning.auth.biz.back.manager.dto.req.ReqWatcherDTO
import com.morning.auth.biz.back.manager.dto.req.ReqWatcherDetailDTO
import com.morning.auth.biz.back.manager.dto.req.ReqWatcherSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResWatcherDTO
import com.morning.auth.common.utils.generateUUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
//@Profile("dev")
class WatcherServiceTest() {

    @Autowired
    private lateinit var watcherService: WatcherService

    @Test
    @DisplayName("관제사 등록에 성공합니다.")
    @Transactional
    fun registWatcherSuccessTest() {
        // given
        val watcher = createWatcher("watcher0@mncf.io", "test1234!!")

        // when
        val response = watcherService.registWatcher(watcher)

        // then
        assertThat(response).isEqualTo(1)
    }

    @Test
    @DisplayName("관리자명과 휴대폰번호를 조건으로 관제사 목록을 조회합니다.")
    fun fetchWatcherListTest() {
        // given
        val searchByName = ReqWatcherSearchFilterDTO(1, 10, 0,"2023-11-01", "2023-11-30", "10", "순자")
        val searchByPhone = ReqWatcherSearchFilterDTO(1, 10, 0,"2023-11-01", "2023-11-30", "20", "1234")
        // when
        val watcherListByName = watcherService.fetchWatcherList(searchByName)
        val watcherListByPhone = watcherService.fetchWatcherList(searchByPhone)

        val resultListByName = watcherListByName.result as? List<ResWatcherDTO>
        val resultListByPhone = watcherListByPhone.result as? List<ResWatcherDTO>

        // then
        assertAll("Check mutiple condistions",
            {
                assertThat(resultListByName)
                    .extracting("name")
                    .contains("순자")
            },
            {
                assertThat(resultListByPhone)
                    .extracting("phone")
                    .contains("01012341234")
            })
    }

    @Test
    @DisplayName("관리자 코드로 관제사를 조회합니다.")
    fun fetchWatcher() {
        // given
        val managerCode = "bfab2f048eba44a89f5b9c533951d20e"

        // when
        val watcher = watcherService.fetchWatcher(managerCode)

        // then
        assertThat(watcher)
            .extracting("managerCode", "name")
            .contains("bfab2f048eba44a89f5b9c533951d20e", "순자")
    }

    private fun createWatcher(loginId: String, password: String): ReqWatcherDTO {
        return ReqWatcherDTO(
            loginId,
            password,
            "Y",
            "48fbf86a7a2211eeb1d10242ac130002",
            "순자",
            "Y",
            loginId,
            "(주)모닝커피소프트",
            LocalDate.of(1990, 1, 1),
            "KR",
            "명달로 116"
        )
    }
}

