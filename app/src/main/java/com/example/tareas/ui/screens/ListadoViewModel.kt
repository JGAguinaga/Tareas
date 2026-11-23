package com.example.tareas.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tareas.data.TareaDto
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class ListadoUiState(
    val cargando: Boolean = false,
    val mensaje: String = "Iniciando...",
    val tareas: List<TareaDto> = emptyList()
)

class ListadoViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ListadoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        cargarDatos()
    }

    var corrutina: Job? = null

    fun cargarDatos(){
        corrutina = viewModelScope.launch {

            try {
                _uiState.update { it.copy(cargando = true, mensaje = "Cargando datos") }
                delay(5.seconds)
                _uiState.update { it.copy(cargando = false, mensaje = "Datos cargados!") }
            }
            catch (e: CancellationException){
                _uiState.update { it.copy(cargando = false, mensaje = "Cancelado") }
            }
        }
    }

    fun cancelar(){
        corrutina?.cancel()
    }
}