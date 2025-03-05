package com.example.safelife.ui.auth

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
fun SignupScreen(navigateToLogin: () -> Unit) {
    val viewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    // Estados para armazenar os dados do usuário
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("Paciente") } // Padrão: Paciente
    var crp by remember { mutableStateOf("") }
    val isProfessional = userType == "Profissional"

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cadastro", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Nome
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // E-mail
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Telefone
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Telefone *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Senha
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha *") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Confirmar Senha
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Senha *") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Seleção de tipo de usuário
        Text("Selecione o tipo de usuário:")
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = userType == "Paciente", onClick = { userType = "Paciente" })
            Text("Paciente")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(selected = userType == "Profissional", onClick = { userType = "Profissional" })
            Text("Profissional")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Campo CRP (aparece apenas se for Profissional)
        if (isProfessional) {
            OutlinedTextField(
                value = crp,
                onValueChange = { crp = it },
                label = { Text("CRP *") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        // Botão de Cadastro
        Button(
            onClick = {
                if (password != confirmPassword) {
                    Toast.makeText(context, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
                } else if (isProfessional && crp.isBlank()) {
                    Toast.makeText(context, "Profissionais precisam informar o CRP", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.signup(name, email, phoneNumber, userType, crp, password, onSuccess = {
                        Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        navigateToLogin()
                    }, onError = { error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    })
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text(text = "CONFIRMAR")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Link para login
        TextButton(onClick = navigateToLogin) {
            Text("Já tem uma conta? Faça login")
        }
    }
}
