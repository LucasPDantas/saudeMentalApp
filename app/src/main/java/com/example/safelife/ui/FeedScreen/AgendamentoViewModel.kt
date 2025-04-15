package com.safelife.ui.agendamento

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AgendamentoViewModel : ViewModel() {

    private val _estado = MutableStateFlow(AgendamentoState())
    val estado: StateFlow<AgendamentoState> = _estado

    private val db = FirebaseFirestore.getInstance()

    fun selecionarProfissional(nome: String) {
        _estado.value = _estado.value.copy(profissionalSelecionado = nome)
    }

    fun selecionarData(data: String) {
        _estado.value = _estado.value.copy(dataSelecionada = data)
    }

    fun selecionarHorario(horario: String) {
        _estado.value = _estado.value.copy(horarioSelecionado = horario)
    }

    fun confirmarAgendamento(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        _estado.value = _estado.value.copy(isConfirmando = true)

        val dados = hashMapOf(
            "profissional" to _estado.value.profissionalSelecionado,
            "data" to _estado.value.dataSelecionada,
            "horario" to _estado.value.horarioSelecionado
        )

        db.collection("agendamentos")
            .add(dados)
            .addOnSuccessListener {
                _estado.value = AgendamentoState()
                onSuccess()
            }
            .addOnFailureListener {
                _estado.value = _estado.value.copy(
                    isConfirmando = false,
                    erro = it.localizedMessage
                )
                onError(it.localizedMessage ?: "Erro ao agendar")
            }
    }
}


