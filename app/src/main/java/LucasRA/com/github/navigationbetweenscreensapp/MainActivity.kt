package LucasRA.com.github.navigationbetweenscreensapp

import LucasRA.com.github.navigationbetweenscreensapp.screens.LoginScreen
import LucasRA.com.github.navigationbetweenscreensapp.screens.MenuScreen
import LucasRA.com.github.navigationbetweenscreensapp.screens.PedidosScreen
import LucasRA.com.github.navigationbetweenscreensapp.screens.PerfilScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import LucasRA.com.github.navigationbetweenscreensapp.ui.theme.NavigationbetweenscreensappTheme
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() { // Bundle - quem cuida dos estados das activities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //pegou o construtor da classe pai
        enableEdgeToEdge() // vem de Component Activity - habilita a fronteira (habilita todo o espaço do app)
        setContent { //configurando conteúdo
            NavigationbetweenscreensappTheme { //tema criado com o nome do projeto (cores, contexto, etc)
//                essa Activity usa toda a borda do dispositivo
//                tem tudo o que precisa para ficar responsivo
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    chamamos a tela de login - tiramos pois não precisa mais - perdeu o sentido pois já criamos a rota
//                    LoginScreen(modifier = Modifier.padding(innerPadding))
//                    criamos a variável que vai lembrar do conteúdo da função sempre que clicarmos
                    val navController = rememberNavController()

                    NavHost(
//                        parâmetro
                        navController = navController,
//                        primeira página - onde inicia o destino
                        startDestination = "login"
                    ) {
                        composable(route = "login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "pedidos?cliente={cliente}",
                            arguments = listOf(navArgument("cliente"){
                                defaultValue = "Cliente Genérico"
                            })
                            ) {
                            PedidosScreen(modifier = Modifier.padding(innerPadding), navController, it.arguments?.getString("cliente"))
                        }
                        composable(route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument("nome") { type = NavType.StringType},
                                navArgument("idade") { type = NavType.IntType}
                            )
                            ) {
                            val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
                            val idade: Int? = it.arguments?.getInt("idade", 0)
                            PerfilScreen(modifier = Modifier.padding(innerPadding), navController, nome!!, idade!!)
                        }
                    }
                }
            }
        }
    }
}


