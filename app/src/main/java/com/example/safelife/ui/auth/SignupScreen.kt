package com.example.safelife.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safelife.viewModel.AuthViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Color

@Composable
fun SignupScreen(navigateToLogin: () -> Unit) {
    val viewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("Paciente") }
    var crp by remember { mutableStateOf("") }
    val isProfessional = userType == "Profissional"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()) // ✅ Permite rolagem se necessário
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Cadastro", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome *") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail *") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Telefone *") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha *") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar Senha *") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
            )

            Text("Selecione o tipo de usuário:")
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = userType == "Paciente", onClick = { userType = "Paciente" })
                Text("Paciente")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(selected = userType == "Profissional", onClick = { userType = "Profissional" })
                Text("Profissional")
            }

            if (isProfessional) {
                OutlinedTextField(
                    value = crp,
                    onValueChange = { crp = it },
                    label = { Text("CRP *") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // ✅ Evita sobreposição

            // O botão agora sempre será visível
            Button(
                onClick = {
                    if (password != confirmPassword) {
                        Toast.makeText(context, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
                    } else if (isProfessional && crp.isBlank()) {
                        Toast.makeText(context, "Profissionais precisam informar o CRP", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.signup(name, email, phoneNumber, userType, crp, password, onSuccess = {
                            navigateToLogin()
                        }, onError = { error ->
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                        })
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), // ✅ Mantém o tamanho adequado
                shape = RoundedCornerShape(12.dp), // 🔹 Mantém um leve arredondamento
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFED474A)) // 🔥 Botão agora está vermelho
            ) {
                Text("CONFIRMAR", color = Color.White) // ✅ Texto branco para melhor contraste
            }

//            TextButton(onClick = navigateToLogin) {
//                Text("Já tem uma conta? Faça login")
//            }
        }
    }
}

