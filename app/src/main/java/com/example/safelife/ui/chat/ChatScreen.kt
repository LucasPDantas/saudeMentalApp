//package com.example.safelife.ui.chat
//
//@Composable
//fun ChatScreen(
//    viewModel: ChatViewModel = viewModel(factory = ChatViewModelFactory(currentUserId, otherUserId))
//) {
//    val messages = viewModel.messages
//    var messageText by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        // Lista de mensagens
//        LazyColumn(
//            modifier = Modifier.weight(1f)
//        ) {
//            items(messages) { message ->
//                MessageBubble(message, isCurrentUser = message.senderId == currentUserId)
//            }
//        }
//
//        // Campo de texto e botão de enviar
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            TextField(
//                value = messageText,
//                onValueChange = { messageText = it },
//                modifier = Modifier.weight(1f),
//                placeholder = { Text("Digite uma mensagem...") }
//            )
//
//            IconButton(
//                onClick = {
//                    viewModel.sendMessage(messageText)
//                    messageText = ""
//                },
//                enabled = messageText.isNotBlank()
//            ) {
//                Icon(Icons.Default.Send, contentDescription = "Enviar")
//            }
//        }
//    }
//}
//
//@Composable
//fun MessageBubble(message: Message, isCurrentUser: Boolean) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        contentAlignment = if (isCurrentUser) Alignment.CenterEnd else Alignment.CenterStart
//    ) {
//        Card(
//            backgroundColor = if (isCurrentUser) Color.Blue else Color.LightGray,
//            contentColor = if (isCurrentUser) Color.White else Color.Black
//        ) {
//            Text(
//                text = message.text,
//                modifier = Modifier.padding(16.dp)
//            )
//        }
//    }
//}