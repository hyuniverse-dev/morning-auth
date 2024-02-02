package com.morning.auth.biz.sample.service

import com.morning.auth.biz.sample.dto.req.ReqSampleDTO
import com.morning.auth.biz.sample.entity.Sample
import com.morning.auth.biz.sample.mapper.SampleMapper
import com.morning.auth.biz.sample.repository.SampleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class JPASampleService(@Autowired private val sampleMapper: SampleMapper) {
    @Autowired
    lateinit var sampleRepository: SampleRepository

    @Transactional(readOnly = true)
    fun fetchSampleList(): List<Sample> {
        return sampleRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun fetchSample(sampleNo:Long): Sample? {
        return sampleRepository.findByIdOrNull(sampleNo)
    }

    @Transactional
    fun registSample(sampleDTO: ReqSampleDTO): Sample {
        val sample = Sample(sampleName = sampleDTO.sampleName)
        return sampleRepository.save(sample)
    }

    @Transactional
    fun modifySample(sampleNo: Long, sampleDTO: ReqSampleDTO): Sample {
        val sample = sampleRepository.findById(sampleNo).orElseThrow { IllegalArgumentException("존재하지 않는 샘플입니다.") }
        return sampleRepository.save(sample)
    }

    @Transactional
    fun removeSample(sampleNo:Long) {
        return sampleRepository.deleteById(sampleNo)
    }

    @Transactional
    fun transactionSample() {
        val sample = Sample(sampleName = "트랙잭션 샘플")
        sampleRepository.save(sample)

        sample.sampleName = "샘플이름 변경"
        sampleRepository.save(sample)
    }
}