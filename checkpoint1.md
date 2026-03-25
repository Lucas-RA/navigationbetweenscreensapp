# Navegação entre Telas com Jetpack Compose

## Este projeto demonstra como implementar a navegação entre múltiplas telas em um aplicativo Android utilizando Jetpack Compose e Navigation Compose.

# Funcionamento do Código

------------------------------------------------------------------------

##  Commit 1 - Passagem de parâmetros obrigatórios na tela de Perfil

### Na MainActivity

Começamos marcando a rota para esperar um valor dinâmico (nome), no qual
esse parâmetro será recebido pela tela

``` kotlin
composable(route = "perfil/{nome}") {
```

Depois pegamos o parâmetro e configuramos um valor padrão para a String:
"Usuário Genérico", sendo ele recuperado via arguments e passado para a
PerfilScreen

``` kotlin
val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
```

Depois marcamos o PerfilScreen para receber o nome e marcamos com o nome
com !! para forçar um valor não nulo, ou seja, um parâmetro obrigatório

``` kotlin
PerfilScreen(modifier = Modifier.padding(innerPadding), navController, nome!!)
```

Na criação da variável nome usamos para passar para ela um valor
informado, e mercamos o valor padrão caso não seja fornecido

------------------------------------------------------------------------

### No MenuScreen

Marcamos no onclick para que ao clicar no botão o nome será enviado, ou
seja, fazemos o envio do parâmetro nome

``` kotlin
Button(
    onClick = { navController.navigate("perfil/Fulano de Tal") }
)
```

------------------------------------------------------------------------

### No PerfilScreen

Configuramos a tela para receber o nome

``` kotlin
fun PerfilScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    nome: String
)
```

E inserimos o nome informado (ou o genérico) na tela, assim deixando ela
dinâmica

``` kotlin
Text(
    text = "PERFIL - $nome"
)
```

------------------------------------------------------------------------

##  Commit 2 - Passagem de parâmetros opcionais na tela de Pedidos

### Na MainActivity

Configuramos a tela para aceitar um parâmetro, sendo assim ele um
parâmetro opcional, depois definimos o argumento indicando o nome do
parâmetro e seu valor padrão (Default)

``` kotlin
composable(
    route = "pedidos?cliente={cliente}",
    arguments = listOf(navArgument("cliente"){
        defaultValue = "Cliente Genérico"
    })
) {
```

Depois coletamos o valor enviado (se for enviado) e fazemos o envio do
cliente para a tela de pedidos

``` kotlin
PedidosScreen(
    modifier = Modifier.padding(innerPadding),
    navController,
    it.arguments?.getString("cliente")
)
```

------------------------------------------------------------------------

### Na PedidoScreen

Configuramos a tela para receber o cliente, mas por ser opcional
adicionamos o ? que vai indicar que ele pode ser nulo. Isso para
permitir a navegação com ou sem envio do valor

``` kotlin
fun PedidosScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    cliente: String?
)
```

Depois repetimos o processo de mostrar o cliente na tela deixando-a
dinâmica

``` kotlin
Text(
    text = "PEDIDOS - $cliente"
)
```

------------------------------------------------------------------------

##  Commit 3 - Inserindo valor ao parâmetro opcional na tela de Pedidos

### Na MenuScreen

Colocamos que ao apertar o botão, vamos enviar o parâmetro opcional
configurado (cliente), quando navegarmos para a tela de Pedidos, por
meio do navController fazendo uma "query".

``` kotlin
Button(
    onClick = { navController.navigate("pedidos?cliente=Cliente XPTO") }
)
```

Definimos que pode receber um cliente, se não receber configuramos um
valor genérico, na MenuScreen vamos enviar o valor que será recebido
pela PedidoScreen e será possível visualizar no texto da tela

------------------------------------------------------------------------

##  Commit 4 - Passagem de múltiplos parâmetros

### Na MainActivity

Na rota para o perfil adicionamos o outro parâmetro -- idade, ficando
assim com 2 parâmetros, pedindo o nome e a idade

``` kotlin
composable(route = "perfil/{nome}/{idade}",
    arguments = listOf(
        navArgument("nome") { type = NavType.StringType},
        navArgument("idade") { type = NavType.IntType}
    )
)
```

Depois informamos o tipo de dado da idade e fazemos a coleta dele, para
isso usamos o navArgument e NavType

``` kotlin
val idade: Int? = it.arguments?.getInt("idade", 0)
```

Depois ajustamos para informar que a tela Perfil vai receber a idade
também (não será mais só o nome)

E marcamos a idade como um valor não nulo

``` kotlin
PerfilScreen(
    modifier = Modifier.padding(innerPadding),
    navController,
    nome!!,
    idade!!
)
```

------------------------------------------------------------------------

### Na MenuScreen

Ajustamos a configuração para enviarmos 2 parâmetros -- adicionamos a
idade

``` kotlin
Button(
    onClick = { navController.navigate("perfil/Fulano de Tal/27") }
)
```

------------------------------------------------------------------------

### Na PerfilScreen

Ajustamos a tela para receber a idade também e inserimos no mesmo text
que o nome a idade que foi passada, fazendo uma interpolação

``` kotlin
fun PerfilScreen(modifier: Modifier = Modifier, navController: NavController, nome: String, idade: Int)
```

``` kotlin
Text(
    text = "PERFIL - $nome tem $idade anos"
)
```

