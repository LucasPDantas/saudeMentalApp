package com.example.safelife.ui.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safelife.viewModel.AuthViewModel
import androidx.compose.ui.text.input.ImeAction


@Composable
fun LoginScreen(navigateToHome: () -> Unit, navigateToSignup: () -> Unit) {
    // Obtém a instância do ViewModel de autenticação
    val viewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    // Estados para armazenar email e senha digitados pelo usuário
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Layout da tela de login
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Título e Subtítulo
            Text(
                text = "Seja Bem-Vindo",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFFFFC107) // ✅ Cor amarela conforme imagem
            )

            Text(
                text = "O Mente livre conecta você a profissionais de saúde mental e grupos de apoio.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Faça seu Login",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
            )

            // Botão de "Esqueceu a senha?"
            TextButton(onClick = {
                if (email.isNotEmpty()) {
                    viewModel.resetPassword(email) { success, message ->
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Digite seu e-mail antes de redefinir a senha", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Esqueceu a senha?")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    Log.d("LoginScreen", "Tentativa de login com email: $email")
                    viewModel.login(email, password, onSuccess = {
                        Log.d("LoginScreen", "Login bem-sucedido para email: $email")
                        navigateToHome()
                    }, onError = {
                        Log.e("LoginScreen", "Erro ao fazer login: $it")
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    })
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text(text = "Entrar")
            }

            TextButton(onClick = navigateToSignup) {
                Text("Não tem uma conta? Cadastre-se")
            }
        } // **Fechamento correto da Column**
    } // **Fechamento correto da Box**
}
