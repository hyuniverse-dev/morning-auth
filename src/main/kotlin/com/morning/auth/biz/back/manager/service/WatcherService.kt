package com.morning.auth.biz.back.manager.service

import com.morning.auth.biz.back.manager.dto.req.ReqWatcherDTO
import com.morning.auth.biz.back.manager.dto.req.ReqWatcherSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResWatcherDTO
import com.morning.auth.biz.back.manager.mapper.WatcherMapper
import com.morning.auth.common.model.PaginationInfo
import com.morning.auth.common.model.PagingResultVO
import com.morning.auth.common.utils.ValidUtils
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WatcherService(private val watcherMapper: WatcherMapper) {
    @Transactional
    fun registWatcher(reqWatcherDTO: ReqWatcherDTO): Int {
        val resultWatcher: Int
        val resultWatcherDetail: Int

        require(reqWatcherDTO.getPasswordResult()) { "비밀번호는 10~32자 이내여야 합니다." }

        try {
            resultWatcher = watcherMapper.registWatcher(reqWatcherDTO)
        } catch (e: DuplicateKeyException) {
            throw DuplicateKeyException("이미 사용중인 로그인 아이디 입니다")
        }
        try {
            resultWatcherDetail = watcherMapper.registWatcherDetail(reqWatcherDTO.watcherDetail)
        } catch (e: DuplicateKeyException) {
            throw DuplicateKeyException("이미 사용중인 이메일 주소 입니다")
        }

        return if (resultWatcher == 1 && resultWatcherDetail == 1) 1 else 0
    }

    @Transactional(readOnly = true)
    fun fetchWatcherList(searchFilter: ReqWatcherSearchFilterDTO): PagingResultVO {
        val (page, size, offset, fromDate, toDate) = searchFilter
        ValidUtils.validBetweenDate(fromDate!!, toDate!!)
        val watcherList = watcherMapper.fetchWatcherList(offset, size, searchFilter)
        val totalCount = watcherMapper.fetchTotalCount()
        val searchCount = watcherMapper.fetchSearchCount(searchFilter)
        return PagingResultVO(PaginationInfo(page, size, totalCount, searchCount), watcherList)
    }

    @Transactional(readOnly = true)
    fun fetchWatcher(managerCode: String): ResWatcherDTO {
        return watcherMapper.fetchWatcher(managerCode)
    }
}
