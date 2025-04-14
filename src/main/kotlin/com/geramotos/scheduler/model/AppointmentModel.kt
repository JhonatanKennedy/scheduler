package com.geramotos.scheduler.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "appointments")
data class AppointmentModel(
    @Id
    val id: String,
    val userAccountId: String,
    val vehicleId: String,
    val description: String,
    val scheduledFrom: Instant,
    val scheduledTo: Instant,
    val status: AppointmentStatus,
    val value: Float,
    val parts: List<PartModel>,
    val createdAt: Instant,
    val updatedAt: Instant
)

enum class AppointmentStatus(val value: Int) {
    SCHEDULED(0),
    IN_PROGRESS(1),
    COMPLETED(2);
}