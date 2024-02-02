package com.morning.auth.biz.back.manager.mapper

import com.morning.auth.biz.back.manager.dto.req.ReqOperatorDTO
import com.morning.auth.biz.back.manager.dto.req.ReqOperatorSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResOperatorDTO
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.SelectProvider

@Mapper
interface OperatorMapper {

    @SelectProvider(type = OperatorSqlProvider::class, method = "fetchOperatorList")
    fun fetchOperatorList(
        @Param("offset") offset: Int,
        @Param("size") size: Int,
        @Param("searchFilter") searchFilter: ReqOperatorSearchFilterDTO
    ): List<ResOperatorDTO>

    @SelectProvider(type = OperatorSqlProvider::class, method = "fetchTotalCount")
    fun fetchTotalCount(): Int

    @SelectProvider(type = OperatorSqlProvider::class, method = "fetchSearchCount")
    fun fetchSearchCount(@Param("searchFilter") searchFilter: ReqOperatorSearchFilterDTO): Int

    @SelectProvider(type = OperatorSqlProvider::class, method = "fetchOperator")
    fun fetchOperator(@Param("managerCode") managerCode: String): ResOperatorDTO

    @SelectProvider(type = OperatorSqlProvider::class, method = "fetchOperatorById")
    fun fetchOperatorById(@Param("loginId") loginId: String): ResOperatorDTO

    @InsertProvider(type = OperatorSqlProvider::class, method = "registOperator")
    fun registOperator(reqOperatorDTO: ReqOperatorDTO): Int

    @SelectProvider(type = OperatorSqlProvider::class, method = "fetchPasswordById")
    fun fetchPasswordById(@Param("loginId") loginId: String): String
}
