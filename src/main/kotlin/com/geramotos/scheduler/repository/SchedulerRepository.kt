package com.geramotos.scheduler.repository

import com.geramotos.scheduler.model.AppointmentModel
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.Instant

interface SchedulerRepository: MongoRepository<AppointmentModel, String>{
    fun findAllByScheduledFromBeforeAndScheduledToAfter(scheduledTo: Instant, scheduledFrom: Instant): List<AppointmentModel>
}