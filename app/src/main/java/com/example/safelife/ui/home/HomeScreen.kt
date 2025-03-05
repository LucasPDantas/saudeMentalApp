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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Adiciona margem para evitar cortes na tela
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp) // Espaçamento uniforme entre botões
        ) {
            Text(text = "SafeLife", style = MaterialTheme.typography.headlineMedium)

            // Botão para acessar o Chat de Suporte
            Button(
                onClick = {
                    Log.d("HomeScreen", "Usuário acessou a tela de Chat")
                    navigateToChat()
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Chat de Suporte")
            }

            // Botão para acessar o Agendamento de Consultas
            Button(
                onClick = {
                    Log.d("HomeScreen", "Usuário acessou a tela de Agendamento de Consultas")
                    navigateToConsultas()
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Agendar Consulta")
            }

            // Botão para acessar o Fórum de Apoio
            Button(
                onClick = {
                    Log.d("HomeScreen", "Usuário acessou a tela do Fórum")
                    navigateToForum()
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
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
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Sair")
            }
        }
    }
}
