package network

import io.ktor.client.request.*
import model.Historial

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend fun getHistorialProyectos(): List<Historial> {
    val url = "http://127.0.0.1:5000/proyecto/historialProyectos"
    return try {
        val response: HttpResponse = NetworkUtils.httpClient.get(url)

        if (response.status == HttpStatusCode.OK) {
            response.body<List<Historial>>()  // ✅ Usa body() en lugar de get<T>()
        } else {
            println("Error: ${response.status}")
            emptyList()
        }
    } catch (e: Exception) {
        println("Error fetching historial: ${e.message}")
        emptyList()
    }
}
