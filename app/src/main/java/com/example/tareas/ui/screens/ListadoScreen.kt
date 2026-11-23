package com.example.tareas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(
                items = uiState.tareas,
                key = { tarea -> tarea.id }
            ){ todo ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                    Row(modifier = Modifier.padding(5.dp)) {
                        Text(text = "${todo.id}. ${todo.title}", modifier = Modifier.weight(1f))
                        Checkbox(checked = todo.completed, onCheckedChange = null)
                    }
                }

            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { listadoViewModel.cargarDatos() }) {
                Text(text = "Recargar")
            }

            OutlinedButton(onClick = { listadoViewModel.cancelar() }) {
                Text(text = "Cancelar")
            }
        }
    }
}