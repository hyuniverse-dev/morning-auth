package com.morning.auth.biz.front.manager.controller

import com.morning.auth.biz.front.manager.dto.req.ReqLoginDTO
import com.morning.auth.biz.front.manager.dto.res.ResManagerDTO
import com.morning.auth.biz.front.manager.service.MonitorService
import com.morning.auth.common.annotation.CommonApiResponses
import com.morning.auth.common.response.SuccessResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/front/monitor")
@Tag(name = "관제시스템 관리자", description = "관제시스템 관리자 API 입니다")
@Validated
@CommonApiResponses
class MonitorController {

    @Autowired
    lateinit var monitorService: MonitorService

    @PostMapping("/login")
    @Operation(summary = "관리자 로그인", description = "관제시스템에서 관리자 계정으로 로그인합니다.")
    fun loginManager(
        @RequestBody @Valid
        reqLoginDTO: ReqLoginDTO
    ): SuccessResponse<ResManagerDTO> {
        val manager = monitorService.loginManager(reqLoginDTO)
        return SuccessResponse(manager)
    }

    @GetMapping("/{managerCode}")
    @Operation(summary = "관리자 정보", description = "관제시스템 관리자의 정보를 조회합니다.")
    @Parameters(
        Parameter(
            name = "managerCode",
            description = "관리자 코드",
            schema = Schema(type = "string"),
            `in` = ParameterIn.PATH,
        )
    )
    fun fetchManager(
        @PathVariable(name = "managerCode")
        @NotBlank(message = "{managerCode.not.blank}")
        managerCode: String,
    ): SuccessResponse<ResManagerDTO> {
        val result = monitorService.fetchManagerByCode(managerCode)
        return SuccessResponse(result)
    }
}
