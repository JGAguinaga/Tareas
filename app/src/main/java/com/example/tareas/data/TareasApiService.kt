package com.example.tareas.data

import retrofit2.http.GET

interface TareasApiService {

    @GET("todos")
    suspend fun obtenerTareas(): List<TareaDto>
}