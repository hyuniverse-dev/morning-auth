package com.morning.auth.biz.back.manager.service

import com.morning.auth.biz.back.manager.dto.req.ReqPilotDTO
import com.morning.auth.biz.back.manager.dto.req.ReqPilotSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResPilotDTO
import com.morning.auth.biz.back.manager.mapper.PilotMapper
import com.morning.auth.common.model.PaginationInfo
import com.morning.auth.common.model.PagingResultVO
import com.morning.auth.common.utils.ValidUtils
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PilotService(private val pilotMapper: PilotMapper) {
    @Transactional
    fun registPilot(reqPilotDTO: ReqPilotDTO): Int {
        val resultPilot: Int
        val resultPilotDetail: Int

        require(reqPilotDTO.getPasswordResult()) { "비밀번호는 10~32자 이내여야 합니다." }

        try {
            resultPilot = pilotMapper.registPilot(reqPilotDTO)
        } catch (e: DuplicateKeyException) {
            throw DuplicateKeyException("이미 사용중인 로그인 아이디 입니다")
        }
        try {
            resultPilotDetail = pilotMapper.registPilotDetail(reqPilotDTO.pilotDetail)
        } catch (e: DuplicateKeyException) {
            throw DuplicateKeyException("이미 사용중인 이메일 주소 입니다")
        }

        return if (resultPilot == 1 && resultPilotDetail == 1) 1 else 0
    }

    @Transactional(readOnly = true)
    fun fetchPilotList(searchFilter: ReqPilotSearchFilterDTO): PagingResultVO {
        val (page, size, offset, fromDate, toDate) = searchFilter
        ValidUtils.validBetweenDate(fromDate!!, toDate!!)
        val pilotList = pilotMapper.fetchPilotList(offset, size, searchFilter)
        val totalCount = pilotMapper.fetchTotalCount()
        val searchCount = pilotMapper.fetchSearchCount(searchFilter)

        return PagingResultVO(PaginationInfo(page, size, totalCount, searchCount), pilotList)
    }

    @Transactional(readOnly = true)
    fun fetchPilot(managerCode: String): ResPilotDTO {
        return pilotMapper.fetchPilot(managerCode)
    }
}
