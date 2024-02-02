package com.morning.auth.biz.back.manager.controller

import com.morning.auth.biz.back.manager.dto.req.ReqWatcherDTO
import com.morning.auth.biz.back.manager.dto.req.ReqWatcherSearchFilterDTO
import com.morning.auth.biz.back.manager.dto.res.ResWatcherDTO
import com.morning.auth.biz.back.manager.service.WatcherService
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
@RequestMapping("/api/back/watcher")
@Tag(name = "관리자페이지 관제사", description = "관제사 API 입니다")
@Validated
@CommonApiResponses
class WatcherController {

    @Autowired
    lateinit var watcherService: WatcherService

    @PostMapping("")
    @Operation(summary = "관제사 등록", description = "관리자페이지에서 신규 관제사를 등록합니다")
    fun registWatcher(
        @RequestBody @Valid
        reqWatcherDTO: ReqWatcherDTO,
    ): SuccessResponse<Int> {
        val result = watcherService.registWatcher(reqWatcherDTO)
        return SuccessResponse(result)
    }

    @GetMapping("")
    @Operation(summary = "관제사 목록 조회", description = "선택한 검색 조건에 맞는 관제사 목록을 조회합니다")
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
            description = "검색조건 : ['']전체 ['10']관제사명, ['20']휴대폰번호",
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
    fun fetchWatcherList(@Parameter(hidden = true) @Valid searchFilter: ReqWatcherSearchFilterDTO): SuccessResponse<PagingResultVO> {
        val result = watcherService.fetchWatcherList(searchFilter)
        return SuccessResponse(result)
    }

    @GetMapping("/{managerCode}")
    @Operation(summary = "관제사 조회", description = "관리자 코드로 관제사를 조회합니다.")
    @Parameters(
        Parameter(
            name = "managerCode",
            description = "관리자 코드",
            schema = Schema(type = "string"),
            `in` = ParameterIn.PATH
        )
    )
    fun fetchWatcher(@PathVariable managerCode: String): SuccessResponse<ResWatcherDTO> {
        val result = watcherService.fetchWatcher(managerCode)
        return SuccessResponse(result)
    }
}
