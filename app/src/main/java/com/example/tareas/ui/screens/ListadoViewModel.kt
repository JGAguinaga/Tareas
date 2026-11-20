package com.example.tareas.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class ListadoUiState(
    val cargando: Boolean = false,
    val mensaje: String = "Iniciando..."
)

class ListadoViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ListadoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        cargarDatos()
    }

    fun cargarDatos(){
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, mensaje = "Cargando datos") }
            delay(5.seconds)
            _uiState.update { it.copy(cargando = false, mensaje = "Datos cargados!") }
        }

    }
}