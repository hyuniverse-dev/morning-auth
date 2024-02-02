package com.morning.auth.biz.sample.service

import com.morning.auth.biz.sample.dto.req.ReqSampleDTO
import com.morning.auth.biz.sample.dto.req.ReqSampleSearchFilterDTO
import com.morning.auth.common.utils.calculate
import com.morning.auth.common.utils.sumNumbers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class MyBatisSampleServiceTest() {

    @Autowired
    private lateinit var myBatisSampleService: MyBatisSampleService

    @Test
    @Transactional
    @DisplayName("신규 샘플을 등록합니다.")
    fun registSampleTest() {
        // given
        val sample = ReqSampleDTO(null, "영철")

        // when + // then
        myBatisSampleService.registSample(sample)
    }

    @Test
    @DisplayName("샘플 번호로 조회합니다.")
    fun fetchSampleByNoTest() {
        // given
        val sampleNo = 28L

        // when
        val result = myBatisSampleService.fetchSample(sampleNo)

        // then (단정문을 통하여 결과를 확인하는 것이 맞지만 이 테스트 코드는 출력 결과를 확인하는 용도로 작성되었습니다)
        print(result)
    }

    @Test
    @DisplayName("RequestDTO로 조회합니다.")
    fun fetchSampleByDTOTest() {
        // given
        val requests = ReqSampleDTO(31, "샘플")

        // when
        val result = myBatisSampleService.fetchSampleByDTO(requests)

        // then (단정문을 통하여 결과를 확인하는 것이 맞지만 이 테스트 코드는 출력 결과를 확인하는 용도로 작성되었습니다)
        print(result)
    }

    @Test
    @DisplayName("RequestDTO로 동적쿼리를 사용하여 조회합니다.")
    fun fetchSampleByDynamicQueryTest() {
        // given
        val requests = ReqSampleSearchFilterDTO(null, 1, 10, "2023-10-19", "2023-10-19")
        // when
        val result = myBatisSampleService.fetchSampleList(requests)

        // then (단정문을 통하여 결과를 확인하는 것이 맞지만 이 테스트 코드는 출력 결과를 확인하는 용도로 작성되었습니다)
        print(result)
    }

    @Test
    @DisplayName("샘플번호로 조회된 샘플을 수정합니다.")
    fun modifySampleTest() {
        // given
        val requests = ReqSampleDTO(null, "개명한 영철이")
        val sampleNo = 34L

        // when + then
        myBatisSampleService.modifySample(sampleNo, requests)
    }

    @Test
    @DisplayName("샘플번호로 조회된 샘플을 삭제합니다.")
    fun removeSampleTest() {
        // given
        val sampleNo = 23L

        // when + then
        myBatisSampleService.removeSample(sampleNo)
    }
}
