package com.example.integradora

sealed class Destinos(
    val icon : Int,
    val title : String,
    val ruta : String
){
    object Pantalla1 : Destinos(R.drawable.usuario, "perfil", "Pantalla1")
    object Pantalla2 : Destinos(R.drawable.ajustes, "configuracion", "Pantalla2")
    object Pantalla3 : Destinos(R.drawable.lupa, "aparcamiento", "Pantalla3")
    object Pantalla4 : Destinos(R.drawable.vip, "vip", "Pantalla4")
    object Pantalla5 : Destinos(R.drawable.informacion, "ayuda", "Pantalla5")
}
