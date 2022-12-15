package com.example.integradora

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.integradora.Destinos.*

@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = Pantalla1.ruta){
        composable(Pantalla1.ruta){
            Perfil()
        }
        composable(Pantalla2.ruta){
            Configuracion()
        }
        composable(Pantalla3.ruta){
            Aparcamiento()
        }
        composable(Pantalla4.ruta){
            Vip()
        }
        composable(Pantalla5.ruta){
            Ayuda()
        }
    }
}