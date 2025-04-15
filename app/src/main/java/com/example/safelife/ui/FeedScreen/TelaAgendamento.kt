package com.safelife.ui.agendamento

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun TelaAgendamento(
    navController: NavController,
    viewModel: AgendamentoViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Agendamento", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = estado.profissionalSelecionado,
            onValueChange = viewModel::selecionarProfissional,
            label = { Text("Profissional") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.dataSelecionada,
            onValueChange = viewModel::selecionarData,
            label = { Text("Data (dd/mm/yyyy)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.horarioSelecionado,
            onValueChange = viewModel::selecionarHorario,
            label = { Text("Horário (ex: 14:00)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.confirmarAgendamento(
                    onSuccess = { navController.navigate("home") },
                    onError = { }
                )
            },
            enabled = !estado.isConfirmando
        ) {
            Text(if (estado.isConfirmando) "Aguarde..." else "Agendar")
        }

        estado.erro?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}


