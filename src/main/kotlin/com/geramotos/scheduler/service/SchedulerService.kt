package com.geramotos.scheduler.service

import com.geramotos.scheduler.dto.AppointmentDTO
import com.geramotos.scheduler.model.AppointmentModel
import com.geramotos.scheduler.model.AppointmentStatus
import com.geramotos.scheduler.repository.SchedulerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.*

@Service
class SchedulerService (private val repository: SchedulerRepository){
    fun findAppointmentById(id:String): Optional<AppointmentModel> {
        return repository.findById(id)
    }

    fun createAppointment(appointment: AppointmentDTO): AppointmentModel {
        val now = Instant.now()

        val scheduledTo = appointment.scheduledTo!!
        val scheduledFrom = appointment.scheduledFrom!!
        val vehicleId = appointment.vehicleId!!
        val userAccountId = appointment.userAccountId!!
        val description = appointment.description!!
        val value = appointment.value!!
        val parts = appointment.parts?.map { it.toModel() } ?: emptyList()

        if (isOverlapExists(scheduledFrom, scheduledTo)) {
            // TODO after standardize this
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "The interval between scheduledFrom and scheduledTo must be at least 1 hour"
            )
        }

        val toSave = AppointmentModel(
            id = UUID.randomUUID().toString(),
            vehicleId = vehicleId,
            userAccountId = userAccountId,
            scheduledTo = scheduledTo,
            scheduledFrom = scheduledFrom,
            description = description,
            parts = parts,
            value = value,
            status = AppointmentStatus.SCHEDULED,
            createdAt = now,
            updatedAt = now
        )


        return repository.save(toSave)
    }

    fun updateAppointment(appointment: AppointmentDTO){
        return
    }

    fun deleteAppointment(id: String){
        return repository.deleteById(id)
    }

    private fun isOverlapExists(scheduledFrom: Instant, scheduledTo: Instant): Boolean {
        val overlappingAppointments = repository.findAllByScheduledFromBeforeAndScheduledToAfter(
            scheduledTo, scheduledFrom
        )

        return overlappingAppointments.isNotEmpty()
    }
}