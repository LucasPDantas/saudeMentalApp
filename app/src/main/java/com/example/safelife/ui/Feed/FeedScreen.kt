package com.example.safelife.ui.Feed

package com.safelife.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.safelife.model.Postagem
import com.safelife.ui.components.TopBar
import com.safelife.ui.theme.SafeLifeTheme

@Composable
fun FeedScreen(
    feedViewModel: FeedViewModel = viewModel()
) {
    val postagens by feedViewModel.postagens.collectAsState()

    SafeLifeTheme {
        Scaffold(
            topBar = { TopBar(titulo = "Comunidade") }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF0F0F0)),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(postagens) { postagem ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = postagem.autor,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF37474F)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = postagem.conteudo,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = postagem.dataFormatada,
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}
