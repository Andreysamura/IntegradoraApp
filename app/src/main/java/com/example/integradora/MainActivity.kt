package com.example.integradora


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.integradora.Destinos.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaPricipal()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PantallaPricipal() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navevegationItems = listOf(
        Pantalla1,
        Pantalla2,
        Pantalla3,
        Pantalla4,
        Pantalla5
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope, scaffoldState)},
        drawerContent = {Drawer(
            scope,
            scaffoldState,
            navController,
            menu_items = navevegationItems)}
    ) {
        NavigationHost(navController)
    }
}

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
){
    TopAppBar(
        title = {Text(text = "Menu")},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Iconos de menu")
            }
        }
    )
}

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menu_items : List<Destinos>){
    /*val menu_items = listOf(
        "Perfil",
        "Configuracion",
        "Busca Aparcamiento",
        "VIP",
        "Ayuda"
    )*/
    Column(){
        Image(
            painterResource(id = R.drawable.parking),
            contentDescription = "Menu de opciones",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        val currentRoute = currentRoute(navController)
        menu_items.forEach{item ->
            DrawerItem(item = item,
                selected = currentRoute == item.ruta
                ){
                navController.navigate(item.ruta){
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}

@Composable
fun DrawerItem(item : Destinos,
               selected: Boolean,
onItemClick: (Destinos)->Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(if (selected) MaterialTheme.colors.primaryVariant.copy(alpha = 0.25f)
                        else Color.Transparent)
            .padding(8.dp)
            .clickable { onItemClick(item) },

    ){
        Image(painterResource(id = item.icon),
        contentDescription = item.title,
        modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = item.title,
        style = MaterialTheme.typography.body1,
            color = if (selected) MaterialTheme.colors.secondaryVariant
        else MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PantallaPricipal()
}