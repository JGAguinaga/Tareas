package com.example.tareas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListadoScreen(
    modifier: Modifier = Modifier,
    listadoViewModel: ListadoViewModel = viewModel()
){
    val uiState by listadoViewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = uiState.mensaje)

        if(uiState.cargando){
            CircularProgressIndicator()
        }

        Button(onClick = { listadoViewModel.cargarDatos() }) {
            Text(text = "Recargar")
        }
    }
}