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

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun tareaView() {
    var expandedProgramadores by remember { mutableStateOf(false) }
    var selectedProgramador by remember { mutableStateOf("Seleccionar programador") }
    val programadores = listOf("Programador 1", "Programador 2", "Programador 3")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Bloque superior con nombre de la empresa
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
                text = "Tarea",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(text = "Mostrar datos (campos) de la tarea", fontSize = 20.sp, modifier = Modifier.padding(vertical = 8.dp))

            // Dropdown para asignar programador
            Text(text = "Asignar programador a la tarea", fontSize = 20.sp, modifier = Modifier.padding(vertical = 8.dp))
            Box {
                Button(onClick = { expandedProgramadores = true }) {
                    Text(selectedProgramador)
                }
                DropdownMenu(
                    expanded = expandedProgramadores,
                    onDismissRequest = { expandedProgramadores = false }
                ) {
                    programadores.forEach { programador ->
                        DropdownMenuItem(onClick = {
                            selectedProgramador = programador
                            expandedProgramadores = false
                        }) {
                            Text(programador)
                        }
                    }
                }
            }
        }


        Button(
            onClick = { },
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text("Volver", fontSize = 20.sp, color = Color.White)
        }
    }
}
