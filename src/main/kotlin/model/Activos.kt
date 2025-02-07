package model

import kotlinx.serialization.SerialName

data class Activos(
    @SerialName("nombre") var nombre: String,
    @SerialName("descripcion") var descripcion: String
)
