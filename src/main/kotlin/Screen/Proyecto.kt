package Screen

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import model.Proyecto
import model.Tarea
import network.obtenerTareas

class ProyectoScreen(val proyecto: Proyecto) : Screen {
    @Composable
    override fun Content() {
        var tareas by remember { mutableStateOf<List<Tarea>>(emptyList()) } // Estado para las tareas
        val navigator = LocalNavigator.current

        // Obtener tareas del proyecto al cargar la pantalla
        LaunchedEffect(proyecto.id) {
            obtenerTareas(proyecto.id) { listaTareas ->
                tareas = listaTareas
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color(0xFF1976D2)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = proyecto.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Descripción: ${proyecto.descripcion}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text("Fecha de creación: ${proyecto.fecha_creacion}", fontSize = 16.sp)
                Text("Fecha de inicio: ${proyecto.fecha_inicio}", fontSize = 16.sp)
                Text("Cliente ID: ${proyecto.cliente}", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(16.dp))
                Text("Tareas del Proyecto", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                // Mostrar tareas obtenidas desde la API
                if (tareas.isEmpty()) {
                    Text("No hay tareas asignadas", fontSize = 16.sp, fontStyle = FontStyle.Italic)
                } else {
                    LazyColumn(modifier = Modifier.height(200.dp)) {
                        items(tareas) { tarea ->
                            TaskItem(tarea)
                        }
                    }
                }
            }

            Button(
                onClick = { navigator?.pop() },
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("Volver", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun TaskItem(tarea: Tarea) {
    val navigator = LocalNavigator.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(tarea.nombre, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navigator?.push(TareaScreen()) }) {
                Text("Entrar a la Tarea")
            }
        }
    }
}


