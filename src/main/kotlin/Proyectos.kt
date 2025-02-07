
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import model.Activos
import model.Historial
import model.User

import network.getHistorialProyectos
import network.getProyectosActivos

class ProyectosScreen() : Screen {
    @Composable
    override fun Content() {
        var Activos by remember { mutableStateOf<List<Activos>>(emptyList()) }
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {

            getProyectosActivos { proyectos ->
                Activos = proyectos
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

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navigator?.push(ProyectoScreen()) },
                    colors = ButtonDefaults.buttonColors(Color(0xFF1976D2))) {
                    Text("Entrar a Proyectos",color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))

                    if (Activos.isEmpty()) {
                        Text("No hay proyectos Activos", fontSize = 16.sp, fontStyle = FontStyle.Italic)
                    } else {
                        LazyColumn(modifier = Modifier.height(200.dp)) {
                            items(Activos) { proyecto ->
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
    }
}
private fun ProjectItem(nombre: String, descripcion: String = "") {
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