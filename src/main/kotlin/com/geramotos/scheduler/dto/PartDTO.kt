package com.geramotos.scheduler.dto

import com.geramotos.scheduler.model.PartModel
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class PartDTO(
    @field:NotBlank(message = "id must no be blank")
    val id: String? = null,

    @field:NotNull(message = "price must not be empty")
    @field:Positive(message = "price must be greater then zero")
    val price: Float? = null,

    @field:NotNull(message = "quantity must not be empty")
    @field:Positive(message = "quantity must be greater then zero")
    val quantity: Int? = null
){
    fun toModel(): PartModel {
        return PartModel(
            id = id!!,
            price = price!!,
            quantity = quantity!!
        )
    }
}