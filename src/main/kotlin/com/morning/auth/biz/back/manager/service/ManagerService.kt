package com.morning.auth.biz.back.manager.service

import com.morning.auth.biz.back.manager.dto.req.ReqLoginDTO
import com.morning.auth.biz.back.manager.dto.req.ReqRoleDTO
import com.morning.auth.biz.back.manager.dto.req.ReqRoleSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.*
import com.morning.auth.biz.back.manager.mapper.ManagerMapper
import com.morning.auth.biz.back.manager.mapper.OperatorMapper
import com.morning.auth.common.model.PaginationInfo
import com.morning.auth.common.model.PagingResultVO
import com.morning.auth.common.utils.ValidUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManagerService(
    private val operatorMapper: OperatorMapper,
    private val managerMapper: ManagerMapper,
    private val resRuleDTO: ResRuleDTO
) {
    @Transactional(readOnly = true)
    fun login(reqLoginDTO: ReqLoginDTO): ResOperatorDTO {
        val loginId = reqLoginDTO.loginId
        val inputPassword = reqLoginDTO.password
        val fetchPassword = operatorMapper.fetchPasswordById(loginId)

        if (fetchPassword != null) {
            if (inputPassword.equals(fetchPassword)) {
                val result = operatorMapper.fetchOperatorById(loginId)
                return result
            } else {
                throw IllegalArgumentException("올바르지 않은 비밀번호 입니다")
            }
        } else {
            throw IllegalArgumentException("존재하지 않는 아이디입니다")
        }
    }

    @Transactional
    fun registRole(reqRoleDTO: ReqRoleDTO): Int {
        managerMapper.registRole(reqRoleDTO)
        val menus = managerMapper.fetchMenuList().map { it.menuNo }

        // Check invalid menu numbers
        val invalidMenus = reqRoleDTO.menus.filterNot { it in menus }
        require(invalidMenus.isEmpty()) { "${invalidMenus.joinToString()}은 유효하지 않는 메뉴 번호입니다." }

        // Regist new roles
        reqRoleDTO.menus.forEach { menu ->
            reqRoleDTO.menuNo = menu
            managerMapper.registRoleMenu(reqRoleDTO)
        }

        return 1
    }

    @Transactional(readOnly = true)
    fun fetchRoleList(searchFilter: ReqRoleSearchFilterDTO): PagingResultVO {
        val (page, size, offset, fromDate, toDate) = searchFilter
        ValidUtils.validBetweenDate(fromDate!!, toDate!!)
        val roleList = managerMapper.fetchRoleList(offset, size, searchFilter)
        val totalCount = managerMapper.fetchRoleTotalCount()
        val searchCount = managerMapper.fetchRoleSearchCount(searchFilter)
        return PagingResultVO(PaginationInfo(page, size, totalCount, searchCount), roleList)
    }

    @Transactional(readOnly = true)
    fun fetchRole(): List<ResRoleDTO> {
        return managerMapper.fetchRole()
    }

    @Transactional(readOnly = true)
    fun fetchRule(): ResRuleDTO {
        return resRuleDTO
    }

    @Transactional(readOnly = true)
    fun fetchGradeList(): List<ResGradeDTO> {
        return managerMapper.fetchGradeList()
    }

    @Transactional(readOnly = true)
    fun fetchMenuList(): List<ResMenuDTO> {
        return managerMapper.fetchMenuList()
    }


}
