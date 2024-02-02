package com.morning.auth.biz.back.manager.controller

import com.morning.auth.biz.back.manager.dto.req.ReqOperatorDTO
import com.morning.auth.biz.back.manager.dto.req.ReqOperatorSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResOperatorDTO
import com.morning.auth.biz.back.manager.service.OperatorService
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
import jakarta.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/back/operator")
@Tag(name = "관리자페이지 운영자", description = "관리자페이지의 운영자 API 입니다")
@Validated
@CommonApiResponses
class OperatorController {

    @Autowired
    lateinit var operatorService: OperatorService

    @PostMapping("")
    @Operation(summary = "운영자 등록", description = "관리자페이지에서 신규 운영자 등록합니다")
    fun registOperator(
        @RequestBody @Valid
        reqOperatorDTO: ReqOperatorDTO,
    ): SuccessResponse<Int> {
        val result = operatorService.registOperator(reqOperatorDTO)
        return SuccessResponse(result)
    }

    @GetMapping("")
    @Operation(summary = "운영자 목록 조회", description = "선택한 검색 조건에 맞는 운영자 목록을 조회합니다")
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
            name = "searchCode",
            description = "검색조건 : ['']전체 ['10']운영자명, ['20']휴대폰번호",
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
    fun fetchOperatorList(@Parameter(hidden = true) @Valid searchFilter: ReqOperatorSearchFilterDTO): SuccessResponse<PagingResultVO> {
        val result = operatorService.fetchOperatorList(searchFilter)
        return SuccessResponse(result)
    }

    @GetMapping("/{managerCode}")
    @Operation(summary = "운영자 조회", description = "관리자 코드로 운영자를 조회합니다.")
    @Parameters(
        Parameter(
            name = "managerCode",
            description = "관리자 코드",
            schema = Schema(type = "string"),
            `in` = ParameterIn.PATH
        )
    )
    fun fetchOperator(
        @PathVariable(name = "managerCode")
        @NotBlank(message = "{managerCode.not.blank}")
        managerCode: String
    ): SuccessResponse<ResOperatorDTO> {
        val result = operatorService.fetchOperator(managerCode)
        return SuccessResponse(result)
    }

    @GetMapping("/duplicate/{loginId}")
    @Operation(summary = "운영자 아이디 중복조회", description = "[중복(True), 희소(False)] : 입력한 운영자 아이디의 사용가능 여부를 검증합니다")
    @Parameters(
        Parameter(
            name = "loginId",
            description = "로그인아이디",
            schema = Schema(type = "string"),
            `in` = ParameterIn.PATH,
        )
    )
    fun fetchDuplicateLoginId(
        @PathVariable(name = "loginId")
        @NotBlank(message = "{loginId.not.blank}")
        loginId: String
    ): SuccessResponse<Boolean> {
        val result = operatorService.fetchDuplicateLoginId(loginId)
        return SuccessResponse(result)
    }
}
