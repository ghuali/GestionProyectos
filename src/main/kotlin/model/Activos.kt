package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Activos(
    @SerialName("nombre") var nombre: String,
    @SerialName("descripcion") var descripcion: String
)
