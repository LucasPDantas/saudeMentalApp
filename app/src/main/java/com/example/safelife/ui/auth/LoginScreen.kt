package com.example.safelife.ui.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safelife.viewModel.AuthViewModel


@Composable
fun LoginScreen(navigateToHome: () -> Unit, navigateToSignup: () -> Unit) {
    // Obtém a instância do ViewModel de autenticação
    val viewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    // Estados para armazenar email e senha digitados pelo usuário
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Layout da tela de login
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineSmall)

        // Campo de entrada para o email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Campo de entrada para a senha
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de login
        Button(
            onClick = {
                Log.d("LoginScreen", "Tentativa de login com email: $email") // Log do email digitado
                viewModel.login(email, password, onSuccess = {
                    Log.d("LoginScreen", "Login bem-sucedido para email: $email") // Log de sucesso
                    navigateToHome()
                }, onError = {
                    Log.e("LoginScreen", "Erro ao fazer login: $it") // Log de erro
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Entrar")
        }

        // Botão para navegar até a tela de cadastro
        TextButton(onClick = navigateToSignup) {
            Text("Não tem uma conta? Cadastre-se")
        }
    }
}
