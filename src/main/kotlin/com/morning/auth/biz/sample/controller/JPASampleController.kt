package com.morning.auth.biz.sample.controller

import com.morning.auth.biz.sample.dto.req.ReqSampleDTO
import com.morning.auth.biz.sample.entity.Sample
import com.morning.auth.biz.sample.service.JPASampleService
import com.morning.auth.common.annotation.CommonApiResponses
import com.morning.auth.common.response.SuccessResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

// ktlint-disable no-wildcard-imports

// @Validated
@RestController
@RequestMapping("/api/jpa/sample")
@Tag(name = "JPA 샘플", description = "기본 샘플 API")
class JPASampleController {
    @Autowired
    lateinit var sampleService: JPASampleService

    @GetMapping("")
    @Operation(summary = "전체조회", description = "모든 샘플조회을 조회")
    @CommonApiResponses
    fun fetchSampleList(): SuccessResponse<List<Sample>> {
        val sampleList = sampleService.fetchSampleList()
        return SuccessResponse(sampleList)
    }

    @GetMapping("/{sampleNo}")
    @Operation(summary = "샘플조회", description = "특정 샘플을 조회")
    @CommonApiResponses
    fun fetchSample(
        @Parameter(name = "sampleNo", description = "샘플번호", `in` = ParameterIn.PATH)
        @PathVariable
        @NotBlank(message = "샘플번호는 필수입니다.")
        sampleNo: Long,
    ): SuccessResponse<Sample?> {
        val sample = sampleService.fetchSample(sampleNo)
        return SuccessResponse(sample)
    }

    @PostMapping("")
    @Operation(summary = "샘플등록", description = "샘플을 등록")
    @CommonApiResponses
    fun registSample(
        @RequestBody @Valid
        sampleDTO: ReqSampleDTO,
    ): SuccessResponse<Unit> {
        sampleService.registSample(sampleDTO)
        return SuccessResponse(Unit)
    }

    @PutMapping("/{sampleNo}")
    @Operation(summary = "샘플수정", description = "샘플을 갱신")
    @CommonApiResponses
    fun modifySample(
        @Parameter(name = "sampleNo", description = "샘플번호", `in` = ParameterIn.PATH)
        @PathVariable
        @NotBlank(message = "샘플번호는 필수입니다.")
        sampleNo: Long,
        @RequestBody @Valid
        sampleDTO: ReqSampleDTO,
    ): SuccessResponse<Unit> {
        sampleService.modifySample(sampleNo, sampleDTO)
        return SuccessResponse(Unit)
    }

    @DeleteMapping("/{sampleNo}")
    @Operation(summary = "샘플삭제", description = "샘플을 삭제")
    @CommonApiResponses
    fun removeSample(
        @Parameter(name = "sampleNo", description = "샘플번호", `in` = ParameterIn.PATH)
        @PathVariable
        @NotBlank(message = "샘플번호는 필수입니다.")
        sampleNo: Long,
    ): SuccessResponse<Unit> {
        sampleService.removeSample(sampleNo)
        return SuccessResponse(Unit)
    }

    @PostMapping("/transaction")
    @Operation(summary = "트랙잭션", description = "트랙잭션 샘플을 제공")
    @CommonApiResponses
    fun transactionSample(): SuccessResponse<Unit> {
        sampleService.transactionSample()
        return SuccessResponse(Unit)
    }
}
