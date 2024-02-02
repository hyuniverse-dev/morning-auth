package com.morning.auth.biz.back.manager.service

import com.morning.auth.biz.back.manager.dto.req.ReqOperatorDTO
import com.morning.auth.biz.back.manager.dto.req.ReqOperatorSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResOperatorDTO
import com.morning.auth.biz.back.manager.mapper.OperatorMapper
import com.morning.auth.common.model.PaginationInfo
import com.morning.auth.common.model.PagingResultVO
import com.morning.auth.common.utils.ValidUtils
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OperatorService(private val operatorMapper: OperatorMapper) {
    @Transactional
    fun registOperator(reqOperatorDTO: ReqOperatorDTO): Int {
        val resultOperator: Int

        require(reqOperatorDTO.getPasswordResult()) { "비밀번호는 10~32자 이내여야 합니다." }

        try {
            resultOperator = operatorMapper.registOperator(reqOperatorDTO)
        } catch (e: DuplicateKeyException) {
            throw DuplicateKeyException("이미 사용중인 로그인 아이디 입니다")
        }

        return resultOperator
    }

    @Transactional(readOnly = true)
    fun fetchOperatorList(searchFilter: ReqOperatorSearchFilterDTO): PagingResultVO {
        val (page, size, offset, fromDate, toDate) = searchFilter
        ValidUtils.validBetweenDate(fromDate!!, toDate!!)
        val operatorList = operatorMapper.fetchOperatorList(offset, size, searchFilter)
        val totalCount = operatorMapper.fetchTotalCount()
        val searchCount = operatorMapper.fetchSearchCount(searchFilter)

        return PagingResultVO(PaginationInfo(page, size, totalCount, searchCount), operatorList)
    }

    @Transactional(readOnly = true)
    fun fetchOperator(managerCode: String): ResOperatorDTO {
        return operatorMapper.fetchOperator(managerCode)
    }

    @Transactional(readOnly = true)
    fun fetchDuplicateLoginId(loginId: String): Boolean {
        return operatorMapper.fetchOperatorById(loginId) != null
    }
}
