package com.morning.auth.biz.back.manager.controller

import com.morning.auth.biz.back.manager.dto.req.ReqLoginDTO
import com.morning.auth.biz.back.manager.dto.req.ReqRoleDTO
import com.morning.auth.biz.back.manager.dto.req.ReqRoleSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.*
import com.morning.auth.biz.back.manager.service.ManagerService
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
@RequestMapping("/api/back/manager")
@Tag(name = "관리자페이지", description = "관리자페이지 API 입니다")
@Validated
@CommonApiResponses
class ManagerController {

    @Autowired
    lateinit var managerService: ManagerService

    @PostMapping("/login")
    @Operation(summary = "관리자 로그인", description = "관리자페이지에 운영자 계정으로 로그인합니다")
    fun login(
        @RequestBody @Valid
        reqLoginDTO: ReqLoginDTO
    ): SuccessResponse<ResOperatorDTO> {
        val result = managerService.login(reqLoginDTO)
        return SuccessResponse(result)
    }

    @PostMapping("/role")
    @Operation(summary = "접근 권한 등록", description = "신규 접근 권한을 등록합니다.")
    fun registRole(
        @RequestBody @Valid
        reqRoleDTO: ReqRoleDTO
    ): SuccessResponse<Int> {
        val result = managerService.registRole(reqRoleDTO)
        return SuccessResponse(result)
    }

    @GetMapping("/roles")
    @Operation(summary = "접근 권한 목록 조회", description = "운영자의 접근 권한 목록을 조회합니다.")
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
            description = "검색조건 : ['']전체 ['10']권한명, ['20']등록자",
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
    fun fetchRoleList(@Parameter(hidden = true) @Valid searchFilter: ReqRoleSearchFilterDTO): SuccessResponse<PagingResultVO> {
        val result = managerService.fetchRoleList(searchFilter)
        return SuccessResponse(result)
    }

    @GetMapping("/role")
    @Operation(summary = "접근 권한 조회", description = "접근 권한을 조회합니다.")
    fun fetchRole(): SuccessResponse<List<ResRoleDTO>> {
        val result = managerService.fetchRole()
        return SuccessResponse(result)
    }


    @GetMapping("/rule")
    @Operation(summary = "규칙 조회", description = "현재 서비스의 규칙 정규표현식을 조회합니다.")
    fun fetchRule(): SuccessResponse<ResRuleDTO> {
        val result = managerService.fetchRule()
        return SuccessResponse(result)
    }

    @GetMapping("/grade")
    @Operation(summary = "자격증 등급 조회", description = "자격증 등급을 조회합니다.")
    fun fetchGradeList(): SuccessResponse<List<ResGradeDTO>> {
        val result = managerService.fetchGradeList()
        return SuccessResponse(result)
    }

    @GetMapping("/menu")
    @Operation(summary = "메뉴 조회", description = "메뉴 전체 목록을 조회합니다.")
    fun fetchMenuList(): SuccessResponse<List<ResMenuDTO>> {
        val result = managerService.fetchMenuList();
        return SuccessResponse(result)
    }
}
