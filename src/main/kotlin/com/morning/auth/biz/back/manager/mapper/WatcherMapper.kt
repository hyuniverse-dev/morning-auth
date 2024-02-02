package com.morning.auth.biz.back.manager.mapper

import com.morning.auth.biz.back.manager.dto.req.ReqWatcherDTO
import com.morning.auth.biz.back.manager.dto.req.ReqWatcherDetailDTO
import com.morning.auth.biz.back.manager.dto.req.ReqWatcherSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResWatcherDTO
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.SelectProvider

@Mapper
interface WatcherMapper {
    @InsertProvider(type = WatcherSqlProvider::class, method = "registWatcher")
    fun registWatcher(reqWatcherDTO: ReqWatcherDTO): Int

    @InsertProvider(type = WatcherSqlProvider::class, method = "registWatcherDetail")
    fun registWatcherDetail(reqWatcherDetailDTO: ReqWatcherDetailDTO): Int

    @SelectProvider(type = WatcherSqlProvider::class, method = "fetchWatcherList")
    fun fetchWatcherList(
        @Param("offset") offset: Int,
        @Param("size") size: Int,
        @Param("searchFilter") searchFilter: ReqWatcherSearchFilterDTO
    ): List<ResWatcherDTO>

    @SelectProvider(type = WatcherSqlProvider::class, method = "fetchTotalCount")
    fun fetchTotalCount(): Int

    @SelectProvider(type = WatcherSqlProvider::class, method = "fetchSearchCount")
    fun fetchSearchCount(@Param("searchFilter") searchFilter: ReqWatcherSearchFilterDTO): Int

    @SelectProvider(type = WatcherSqlProvider::class, method = "fetchWatcher")
    fun fetchWatcher(@Param("managerCode") managerCode: String): ResWatcherDTO
}
