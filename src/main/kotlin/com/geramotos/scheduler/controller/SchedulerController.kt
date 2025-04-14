package com.geramotos.scheduler.controller

import com.geramotos.scheduler.service.SchedulerService
import com.geramotos.scheduler.dto.AppointmentDTO
import com.geramotos.scheduler.model.AppointmentModel
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@RequestMapping("/")
class SchedulerController (private val service: SchedulerService){
    @GetMapping("{id}")
    fun getAppointment(@PathVariable id:String): Optional<AppointmentModel>{
        return service.findAppointmentById(id)
    }

    @PostMapping
    fun postAppointment(@Valid @RequestBody appointment: AppointmentDTO): ResponseEntity<AppointmentModel>{
        val result = service.createAppointment(appointment)
        return ResponseEntity.ok(result)
    }

    @PatchMapping
    fun updateAppointment(@RequestBody appointment: AppointmentDTO){
        return service.updateAppointment(appointment)
    }

    @DeleteMapping("{id}")
    fun deleteAppointment(@PathVariable id:String){
        return service.deleteAppointment(id)
    }
}