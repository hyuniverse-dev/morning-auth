package com.morning.auth.biz.back.manager.mapper

import com.morning.auth.biz.back.manager.dto.req.ReqRoleDTO
import com.morning.auth.biz.back.manager.dto.req.ReqRoleSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResGradeDTO
import com.morning.auth.biz.back.manager.dto.res.ResMenuDTO
import com.morning.auth.biz.back.manager.dto.res.ResRoleDTO
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.SelectProvider

@Mapper
interface ManagerMapper {

    @InsertProvider(type = ManagerSqlProvider::class, method = "registRole")
    fun registRole(reqRoleDTO: ReqRoleDTO): Int

    @SelectProvider(type = ManagerSqlProvider::class, method = "fetchRoleList")
    fun fetchRoleList(
        @Param("offset") offset: Int,
        @Param("size") size: Int,
        @Param("searchFilter") searchFilter: ReqRoleSearchFilterDTO
    ): List<ResRoleDTO>

    @SelectProvider(type = ManagerSqlProvider::class, method = "fetchRole")
    fun fetchRole(): List<ResRoleDTO>

    @SelectProvider(type = ManagerSqlProvider::class, method = "fetchRoleTotalCount")
    fun fetchRoleTotalCount(): Int

    @SelectProvider(type = ManagerSqlProvider::class, method = "fetchRoleSearchCount")
    fun fetchRoleSearchCount(@Param("searchFilter") searchFilter: ReqRoleSearchFilterDTO): Int

    @SelectProvider(type = ManagerSqlProvider::class, method = "fetchMenuList")
    fun fetchMenuList(): List<ResMenuDTO>

    @InsertProvider(type = ManagerSqlProvider::class, method = "registRoleMenu")
    fun registRoleMenu(reqRoleDTO: ReqRoleDTO): Int

    @SelectProvider(type = ManagerSqlProvider::class, method = "fetchGradeList")
    fun fetchGradeList(): List<ResGradeDTO>


}
