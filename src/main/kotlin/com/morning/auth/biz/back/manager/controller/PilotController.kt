package com.morning.auth.biz.back.manager.controller

import com.morning.auth.biz.back.manager.dto.req.ReqPilotDTO
import com.morning.auth.biz.back.manager.dto.req.ReqPilotSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResPilotDTO
import com.morning.auth.biz.back.manager.service.PilotService
import com.morning.auth.common.annotation.CommonApiResponses
import com.morning.auth.common.model.PagingResultVO
import com.morning.auth.common.response.SuccessResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/back/pilot")
@Tag(name = "관리자페이지 파일럿", description = "파일럿 API 입니다")
@Validated
@CommonApiResponses
class PilotController {

    @Autowired
    lateinit var pilotService: PilotService

    @PostMapping("")
    @Operation(summary = "파일럿 등록", description = "관리자페이지에서 파일럿을 등록합니다")
    fun registPilot(
        @RequestBody @Valid
        reqPilotDTO: ReqPilotDTO,
    ): SuccessResponse<Int> {
        val result = pilotService.registPilot(reqPilotDTO)
        return SuccessResponse(result)
    }

    @GetMapping("")
    @Operation(summary = "파일럿 목록 조회", description = "선택한 검색 조건에 맞는 파일럿 목록을 조회합니다")
    @Parameters(
        Parameter(
            name = "fromDate",
            description = "검색시작일 (YYYY-MM-DD)",
            schema = Schema(type = "string"),
            `in` = ParameterIn.QUERY
        ),
        Parameter(
            name = "toDate",
            description = "검색종료일 (YYYY-MM-DD)",
            schema = Schema(type = "string"),
            `in` = ParameterIn.QUERY
        ),
        Parameter(
            name = "licenseGrade",
            description = "자격증 등급 : ['']전체, ['TP1']1종, ['TP2']2종, ['TP3']3종, ['TP4']4종",
            schema = Schema(type = "string", allowableValues = ["", "TP1", "TP2", "TP3", "TP4"]),
            `in` = ParameterIn.QUERY
        ),
        Parameter(
            name = "searchCode",
            description = "검색조건 : ['']전체 ['10']파일럿명, ['20']업체명",
            schema = Schema(type = "string", allowableValues = ["", "10", "20"]),
            `in` = ParameterIn.QUERY
        ),
        Parameter(
            name = "searchKeyword",
            description = "검색어",
            schema = Schema(type = "string"),
            `in` = ParameterIn.QUERY
        ),
        Parameter(
            name = "page",
            description = "페이지번호",
            schema = Schema(type = "integer"),
            `in` = ParameterIn.QUERY
        ),
        Parameter(
            name = "size",
            description = "페이지당 행 개수",
            schema = Schema(type = "integer"),
            `in` = ParameterIn.QUERY
        )
    )
    fun fetchPilotList(@Parameter(hidden = true) @Valid searchFilter: ReqPilotSearchFilterDTO): SuccessResponse<PagingResultVO> {
        val result = pilotService.fetchPilotList(searchFilter)
        return SuccessResponse(result)
    }

    @GetMapping("/{managerCode}")
    @Operation(summary = "파일럿 조회", description = "관리자 코드로 파일럿을 조회합니다.")
    @Parameters(
        Parameter(
            name = "managerCode",
            description = "관리자 코드",
            schema = Schema(type = "string"),
            `in` = ParameterIn.PATH
        )
    )
    fun fetchPilot(@PathVariable managerCode: String): SuccessResponse<ResPilotDTO> {
        val result = pilotService.fetchPilot(managerCode)
        return SuccessResponse(result)
    }
}
