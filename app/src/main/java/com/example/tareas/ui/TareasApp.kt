package com.example.tareas.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tareas.ui.icons.arrow_back
import com.example.tareas.ui.screens.ListadoScreen
import com.example.tareas.ui.theme.TareasTheme

enum class TareasScreens(val title: String) {
    Listado(title = "Listado tareas"),
    Detalle(title = "Detalle tarea")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasAppBar(
    currentScreen: TareasScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                Icon(
                    imageVector = arrow_back,
                    contentDescription = null,
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        navigateUp()
                    })
                )
            }
        }
    )
}

@Composable
fun TareasApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TareasScreens.valueOf(backStackEntry?.destination?.route ?: TareasScreens.Listado.name)

    TareasTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TareasAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { innerPadding ->
            NavHost(navController, startDestination = TareasScreens.Detalle.name) {
                composable(route = TareasScreens.Listado.name) {
                    ListadoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}