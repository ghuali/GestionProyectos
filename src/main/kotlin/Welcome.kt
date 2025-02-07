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
import kotlinx.coroutines.launch
import model.Historial
import model.User

import network.getHistorialProyectos

class WelcomeScreen(val usuario: User) : Screen {
    @Composable
    override fun Content() {
        var historial by remember { mutableStateOf<List<Historial>>(emptyList()) }
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            getHistorialProyectos { proyectos ->
                historial = proyectos
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
                    text = "Nombre de Empresa",
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
                    text = "Bienvenido ${usuario.nombre}",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Text(
                    text = "Rol: Gestor",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Proyectos Activos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(modifier = Modifier.height(200.dp)) {
                    items(listOf("Proyecto 1", "Proyecto 2", "Proyecto 3")) { project ->
                        ProjectItem(project)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Historial de Proyectos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                if (historial.isEmpty()) {
                    Text("No hay historial disponible", fontSize = 16.sp, fontStyle = FontStyle.Italic)
                } else {
                    LazyColumn(modifier = Modifier.height(200.dp)) {
                        items(historial) { proyecto ->
                            ProjectItem(proyecto.nombre, proyecto.descripcion)
                        }
                    }
                }
                Button(
                    onClick = { navigator?.pop() },
                    modifier = Modifier
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFD32F2F))
                ) {
                    Text("Desconectar", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    }

    @Composable
    fun ProjectItem(nombre: String, descripcion: String = "") {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = nombre, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                if (descripcion.isNotEmpty()) {
                    Text(text = descripcion, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
    }
}


