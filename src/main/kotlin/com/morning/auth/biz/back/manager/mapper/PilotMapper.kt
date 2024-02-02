package com.morning.auth.biz.back.manager.mapper

import com.morning.auth.biz.back.manager.dto.req.ReqPilotDTO
import com.morning.auth.biz.back.manager.dto.req.ReqPilotDetailDTO
import com.morning.auth.biz.back.manager.dto.req.ReqPilotSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResPilotDTO
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.SelectProvider

@Mapper
interface PilotMapper {
    @InsertProvider(type = PilotSqlProvider::class, method = "registPilot")
    fun registPilot(reqPilot: ReqPilotDTO): Int

    @InsertProvider(type = PilotSqlProvider::class, method = "registPilotDetail")
    fun registPilotDetail(reqPilotDetailDTO: ReqPilotDetailDTO): Int

    @SelectProvider(type = PilotSqlProvider::class, method = "fetchPilotList")
    fun fetchPilotList(
        @Param("offset") offset: Int,
        @Param("size") size: Int,
        @Param("searchFilter") searchFilter: ReqPilotSearchFilterDTO
    ): List<ResPilotDTO>

    @SelectProvider(type = PilotSqlProvider::class, method = "fetchTotalCount")
    fun fetchTotalCount(): Int

    @SelectProvider(type = PilotSqlProvider::class, method = "fetchSearchCount")
    fun fetchSearchCount(@Param("searchFilter") searchFilter: ReqPilotSearchFilterDTO): Int

    @SelectProvider(type = PilotSqlProvider::class, method = "fetchPilot")
    fun fetchPilot(@Param("managerCode") managerCode: String): ResPilotDTO
}
