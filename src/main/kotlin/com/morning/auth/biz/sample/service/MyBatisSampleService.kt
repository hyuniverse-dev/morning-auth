package com.morning.auth.biz.sample.service

import com.morning.auth.biz.sample.dto.req.ReqSampleDTO
import com.morning.auth.biz.sample.dto.req.ReqSampleSearchFilterDTO
import com.morning.auth.biz.sample.dto.res.ResSampleDTO
import com.morning.auth.biz.sample.mapper.SampleMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class MyBatisSampleService(
    private val sampleMapper: SampleMapper
) {
    /**
     * 등록
     */
    @Transactional
    fun registSample(sampleDTO: ReqSampleDTO) {
        return sampleMapper.registSample(sampleDTO)
    }

    /**
     * 목록 조회
     */
    @Transactional(readOnly = true)
    fun fetchSampleList(searchFilterDTO: ReqSampleSearchFilterDTO): List<ResSampleDTO> {
        val page = searchFilterDTO.page
        val size = searchFilterDTO.size
        val offset = (page - 1) * size
        return sampleMapper.fetchSampleList(offset, size, searchFilterDTO)
    }

    /**
     * 조회
     * Parameters : sampleNo
     */
    @Transactional(readOnly = true)
    fun fetchSample(sampleNo: Long): ResSampleDTO {
        return sampleMapper.fetchSample(sampleNo)
    }

    /**
     * 조회
     * Parameters : reqSampleDTO
     */
    fun fetchSampleByDTO(reqSampleDTO: ReqSampleDTO): List<ResSampleDTO> {
        return sampleMapper.fetchSampleByDTO(reqSampleDTO)
    }

    /**
     * 수정
     */
    @Transactional
    fun modifySample(sampleNo: Long, sampleDTO: ReqSampleDTO) {
        return sampleMapper.modifySample(sampleNo, sampleDTO)
    }

    /**
     * 삭제
     */
    @Transactional
    fun removeSample(sampleNo: Long) {
        return sampleMapper.removeSample(sampleNo)
    }

    /**
     * 트랜잭션
     */
    @Transactional
    fun transactionSample() {
        val sample = ReqSampleDTO(sampleNo = 0, sampleName = "트랙잭션 샘플")
        sampleMapper.registSample(sample)

        sample.sampleName = "샘플이름 변경"
        sampleMapper.modifySample(sample.sampleNo!!, sample)
    }


}
