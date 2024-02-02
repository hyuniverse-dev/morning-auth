package com.morning.auth.biz.front.manager.service

import com.morning.auth.biz.front.manager.dto.req.ReqLoginDTO
import com.morning.auth.biz.front.manager.dto.res.ResManagerDTO
import com.morning.auth.biz.front.manager.mapper.MonitorMapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MonitorService(private val monitorMapper: MonitorMapper) {
    @Transactional
    fun loginManager(reqLoginDTO: ReqLoginDTO): ResManagerDTO {
        val loginId = reqLoginDTO.loginId
        val inputPassword = reqLoginDTO.password
        val fetchPassword = monitorMapper.fetchPassword(loginId)

        if (fetchPassword != null) {
            if (inputPassword.equals(fetchPassword)) {
                val result = monitorMapper.fetchManagerById(loginId)
                return result
            } else {
                throw IllegalArgumentException("올바르지 않은 비밀번호 입니다")
            }
        } else {
            throw IllegalArgumentException("존재하지 않는 아이디입니다")
        }
    }

    fun fetchManagerByCode(managerCode: String): ResManagerDTO {
        return monitorMapper.fetchManagerByCode(managerCode)
    }
}
