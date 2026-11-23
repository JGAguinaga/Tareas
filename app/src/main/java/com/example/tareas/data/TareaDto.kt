package com.example.tareas.data

import kotlinx.serialization.Serializable


@Serializable
data class TareaDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
