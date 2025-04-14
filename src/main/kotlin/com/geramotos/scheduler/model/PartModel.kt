package com.geramotos.scheduler.model

import org.springframework.data.annotation.Id

data class PartModel(
    @Id
    val id: String,
    val price: Float,
    val quantity: Int
)