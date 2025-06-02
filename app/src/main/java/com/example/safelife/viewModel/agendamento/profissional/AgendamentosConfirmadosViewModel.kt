package com.example.safelife.viewModel.agendamento.profissional

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Agendamento(
    val nomePaciente: String,
    val emailPaciente: String,
    val telefonePaciente: String,
    val pacienteId: String,
    val data: String,
    val horario: String,
    val status: String
)

class AgendamentosConfirmadosViewModel : ViewModel() {

    private val _agendamentos = MutableStateFlow(
        listOf(
            Agendamento(
                nomePaciente = "Pedro Henrique",
                emailPaciente = "pedro@gmail.com",
                telefonePaciente = "11366563869",
                pacienteId = "44mcBQ3wvKc1UmAZ6iR3ddPv8Ib2",
                data = "2025-06-03",
                horario = "10:00",
                status = "confirmado"
            ),
            Agendamento(
                nomePaciente = "Carla Lima",
                emailPaciente = "carla@gmail.com",
                telefonePaciente = "11999887766",
                pacienteId = "7pm3iW1b5Rc9Fejrt59jXUWEro42",
                data = "2025-06-04",
                horario = "14:30",
                status = "confirmado"
            ),
            Agendamento(
                nomePaciente = "Lucas Pereira",
                emailPaciente = "lucas@exemplo.com",
                telefonePaciente = "11911112222",
                pacienteId = "1mpAqfOqHWTzXPvhuQx6NrqHjH2",
                data = "2025-06-04",
                horario = "16:00",
                status = "confirmado"
            )
        )
    )

    val agendamentos: StateFlow<List<Agendamento>> = _agendamentos
}
