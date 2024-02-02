package com.morning.auth.biz.front.manager.mapper

import com.morning.auth.biz.front.manager.dto.res.ResManagerDTO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.SelectProvider

@Mapper
interface MonitorMapper {
    @SelectProvider(type = MonitorSqlProvider::class, method = "fetchPassword")
    fun fetchPassword(loginId: String): String

    @SelectProvider(type = MonitorSqlProvider::class, method = "fetchManagerById")
    fun fetchManagerById(loginId: String): ResManagerDTO

    @SelectProvider(type = MonitorSqlProvider::class, method = "fetchManagerByCode")
    fun fetchManagerByCode(managerCode: String): ResManagerDTO
}
