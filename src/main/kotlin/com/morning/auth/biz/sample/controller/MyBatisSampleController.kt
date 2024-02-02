package com.morning.auth.biz.sample.controller

import com.morning.auth.biz.sample.dto.req.ReqSampleDTO
import com.morning.auth.biz.sample.dto.req.ReqSampleSearchFilterDTO
import com.morning.auth.biz.sample.dto.res.ResSampleDTO
import com.morning.auth.biz.sample.service.MyBatisSampleService
import com.morning.auth.common.annotation.CommonApiResponses
import com.morning.auth.common.response.SuccessResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/mybatis/sample")
@Tag(name = "MyBatis 샘플", description = "기본 샘플 API")
@Validated
@CommonApiResponses
class MyBatisSampleController {
    @Autowired
    lateinit var sampleService: MyBatisSampleService

    @GetMapping("")
    @Parameters(
        Parameter(
            name = "sampleName",
            description = "샘플명",
            schema = Schema(type = "string"),
            `in` = ParameterIn.QUERY,
        ),
        Parameter(
            name = "page",
            description = "페이지 번호",
            schema = Schema(type = "integer", defaultValue = "1"),
            `in` = ParameterIn.QUERY,
        ),
        Parameter(
            name = "size",
            description = "페이지당 행 개수",
            schema = Schema(type = "integer", defaultValue = "10"),
            `in` = ParameterIn.QUERY,
        ),
    )
    @Operation(summary = "전체조회", description = "모든 샘플조회을 조회")
    fun fetchSampleList(@Parameter(hidden = true) @Valid reqSampleSearchFilterDTO: ReqSampleSearchFilterDTO): SuccessResponse<List<ResSampleDTO>> {
        val sampleList = sampleService.fetchSampleList(reqSampleSearchFilterDTO)
        return SuccessResponse(sampleList)
    }

    @GetMapping("/{sampleNo}")
    @Parameters(
        Parameter(
            name = "sampleNo",
            description = "샘플번호",
            schema = Schema(type = "integer"),
            `in` = ParameterIn.PATH,
        ),
    )
    @Operation(summary = "샘플조회", description = "특정 샘플을 조회")
    fun fetchSample(
        @PathVariable
        sampleNo: Long,
    ): SuccessResponse<ResSampleDTO> {
        val sample = sampleService.fetchSample(sampleNo)
        return SuccessResponse(sample)
    }

    @PostMapping("")
    @Operation(summary = "샘플등록", description = "샘플을 등록")
    fun registSample(
        @RequestBody @Valid
        sampleDTO: ReqSampleDTO,
    ): SuccessResponse<Unit> {
        sampleService.registSample(sampleDTO)
        return SuccessResponse(Unit)
    }

    @PutMapping("/{sampleNo}")
    @Parameters(
        Parameter(
            name = "sampleNo",
            description = "샘플번호",
            schema = Schema(type = "integer"),
            `in` = ParameterIn.PATH,
        ),
    )
    @Operation(summary = "샘플수정", description = "샘플을 갱신")
    fun modifySample(
        @PathVariable
        @Min(value = 1, message = "{sampleNo.min}")
        sampleNo: Long,
        @RequestBody @Valid
        sampleDTO: ReqSampleDTO,
    ): SuccessResponse<Unit> {
        sampleService.modifySample(sampleNo, sampleDTO)
        return SuccessResponse(Unit)
    }

    @DeleteMapping("/{sampleNo}")
    @Parameters(
        Parameter(
            name = "sampleNo",
            description = "샘플번호",
            schema = Schema(type = "integer"),
            `in` = ParameterIn.PATH,
        ),
    )
    @Operation(summary = "샘플삭제", description = "샘플을 삭제")
    fun removeSample(
        @PathVariable
        @Min(
            value = 1,
            message = "{sampleNo.min}",
        )
        sampleNo: Long,
    ): SuccessResponse<Unit> {
        sampleService.removeSample(sampleNo)
        return SuccessResponse(Unit)
    }

    @PostMapping("/transaction")
    @Operation(summary = "트랙잭션", description = "트랙잭션 샘플을 제공")
    fun transactionSample(): SuccessResponse<Unit> {
        sampleService.transactionSample()
        return SuccessResponse(Unit)
    }
}
