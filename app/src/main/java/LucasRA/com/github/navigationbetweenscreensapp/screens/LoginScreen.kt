package LucasRA.com.github.navigationbetweenscreensapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
//se não passarmos o parâmetro, passamos um Modifier com tudo que tem direito - valor default (padrão)
fun LoginScreen(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
        .fillMaxSize()
        .background(Color(0xFFED145B))
        .padding(32.dp)
    ) {
//        escopo - objeto text e button >> Tudo jetpack Compose
        Text(
            text = "LOGIN",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
//        é um objeto compose > Tem parâmetro (onClick) e Escopo
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "ENTRAR",
                fontSize = 20.sp,
                color = Color.Blue
            )
        }

    }
}