package com.geramotos.scheduler.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import java.time.Duration
import java.time.Instant

data class AppointmentDTO (
    @field:NotBlank(message = "userAccountId must not be blank")
    val userAccountId: String? = null,

    @field:NotBlank(message = "vehicleId must not be blank")
    val vehicleId: String? = null,

    @field:NotBlank(message = "description must not be blank")
    val description: String? = null,

    @field:NotNull(message = "scheduledTo must not be blank")
    @field:FutureOrPresent(message = "scheduledTo must not be in the past")
    val scheduledTo: Instant? = null,

    @field:NotNull(message = "scheduledFrom must not be blank")
    @field:FutureOrPresent(message = "scheduledTo must not be in the past")
    val scheduledFrom: Instant? = null,

    @field:PositiveOrZero(message = "value must be positive")
    val value: Float? = 0f,

    @field:Valid
    val parts: List<PartDTO>?
){
    @AssertTrue(message = "The difference between scheduledFrom and scheduledTo must be at least 1 hour")
    fun isDurationValid(): Boolean {
        if (scheduledFrom == null || scheduledTo == null) return true
        val duration = Duration.between(scheduledFrom, scheduledTo)
        return !duration.isNegative && duration.toHours() >= 1
    }
}