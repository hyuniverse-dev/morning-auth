package com.morning.auth.biz.sample.repository

import com.morning.auth.biz.sample.entity.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long> {
}