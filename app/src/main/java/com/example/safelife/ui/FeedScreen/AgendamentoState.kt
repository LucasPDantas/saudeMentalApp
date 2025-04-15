package com.safelife.ui.agendamento

data class AgendamentoState(
    val profissionalSelecionado: String = "",
    val dataSelecionada: String = "",
    val horarioSelecionado: String = "",
    val isConfirmando: Boolean = false,
    val erro: String? = null
)
