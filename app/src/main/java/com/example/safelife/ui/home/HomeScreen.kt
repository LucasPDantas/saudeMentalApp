package com.example.safelife.ui.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navigateToChat: () -> Unit, navigateToConsultas: () -> Unit, navigateToForum: () -> Unit, navigateToLogin: () -> Unit) {
    // Obtém a instância do FirebaseAuth para controle de autenticação
    val auth = FirebaseAuth.getInstance()

    // Layout da tela principal do aplicativo
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SafeLife", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // Botão para acessar o Chat de Suporte
        Button(onClick = {
            Log.d("HomeScreen", "Usuário acessou a tela de Chat")
            navigateToChat()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Chat de Suporte")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botão para acessar o Agendamento de Consultas
        Button(onClick = {
            Log.d("HomeScreen", "Usuário acessou a tela de Agendamento de Consultas")
            navigateToConsultas()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Agendar Consulta")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botão para acessar o Fórum de Apoio
        Button(onClick = {
            Log.d("HomeScreen", "Usuário acessou a tela do Fórum")
            navigateToForum()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Fórum de Apoio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de Logout para sair da conta
        Button(
            onClick = {
                Log.d("HomeScreen", "Usuário fez logout")
                auth.signOut()
                navigateToLogin()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Sair")
        }
    }
}
