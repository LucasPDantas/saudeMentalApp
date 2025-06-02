package com.example.safelife.viewModel.agendamento.profissional

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AgendaProfissionalViewModel : ViewModel() {

    // Armazena disponibilidade: dia ("Seg") -> horários (ex: "08:00", "09:00")
    private val _disponibilidade = MutableStateFlow<Map<String, Set<String>>>(emptyMap())
    val disponibilidade: StateFlow<Map<String, Set<String>>> = _disponibilidade

    // Simula o salvamento de horários
    fun salvarHorarios(dia: String, horarios: Set<String>) {
        val atual = _disponibilidade.value.toMutableMap()
        atual[dia] = horarios
        _disponibilidade.value = atual
    }

    fun obterHorariosParaDia(dia: String): Set<String> {
        return _disponibilidade.value[dia] ?: emptySet()
    }

    // Simula agendamentos já existentes
    private val agendamentosFicticios = listOf(
        Pair("Seg", "10:00"),
        Pair("Ter", "13:00")
    )

    // Verifica se um horário pode ser removido
    fun podeRemover(dia: String, horario: String): Boolean {
        return !agendamentosFicticios.contains(Pair(dia, horario))
    }

}
