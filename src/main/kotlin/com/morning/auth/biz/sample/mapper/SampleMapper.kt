package com.morning.auth.biz.sample.mapper

import com.morning.auth.biz.sample.dto.req.ReqSampleDTO
import com.morning.auth.biz.sample.dto.req.ReqSampleSearchFilterDTO
import com.morning.auth.biz.sample.dto.res.ResSampleDTO
import org.apache.ibatis.annotations.*

@Mapper
interface SampleMapper {
    /**
     * 등록
     */
    @InsertProvider(type = SampleSQLProvider::class, method = "registSample")
    fun registSample(@Param("reqSample") reqSample: ReqSampleDTO)

    /**
     * 목록 조회
     */
    @SelectProvider(type = SampleSQLProvider::class, method = "fetchSampleList")
    fun fetchSampleList(
        @Param("offset") offset: Int,
        @Param("size") size: Int,
        @Param("searchFilterDTO") searchFilterDTO: ReqSampleSearchFilterDTO
    ): List<ResSampleDTO>

    /**
     * 조회 - 샘플 번호
     */
    @SelectProvider(type = SampleSQLProvider::class, method = "fetchSample")
    fun fetchSample(@Param("sampleNo") sampleNo: Long): ResSampleDTO

    /**
     * 조회 - reqSampleDTO
     */
    @SelectProvider(type = SampleSQLProvider::class, method = "fetchSampleByDTO")
    fun fetchSampleByDTO(@Param("reqSample") reqSample: ReqSampleDTO): List<ResSampleDTO>

    /**
     * 수정
     */
    @UpdateProvider(type = SampleSQLProvider::class, method = "modifySample")
    fun modifySample(@Param("sampleNo") sampleNo: Long, @Param("reqSample") reqSample: ReqSampleDTO)

    /**
     * 삭제
     */
    @DeleteProvider(type = SampleSQLProvider::class, method = "removeSample")
    fun removeSample(@Param("sampleNo") sampleNo: Long)
}
