package com.morning.auth.biz.sample.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "샘플번호", nullable = false)
    val sampleNo: Long? = null,

    @Column(nullable = false)
    @Schema(description = "샘플명", nullable = false, example = "이것은 샘플입니다.")
    var sampleName: String? = null
)