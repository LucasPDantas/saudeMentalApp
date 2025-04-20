//package com.example.safelife.ui.chat
//
//class ChatViewModel(
//    private val currentUserId: String,   // ID do usuário atual
//    private val otherUserId: String,    // ID do outro usuário
//    private val chatRepository: ChatRepository = ChatRepository()
//) : ViewModel() {
//
//    // Lista observável de mensagens
//    private val _messages = mutableStateListOf<Message>()
//    val messages: List<Message> = _messages
//
//    private var chatId: String = ""  // ID da conversa atual
//
//    init {
//        setupChat()  // Inicia a configuração do chat quando o ViewModel é criado
//    }
//
//    private fun setupChat() {
//        viewModelScope.launch {
//            // Obtém ou cria um ID de chat para estes dois usuários
//            chatId = chatRepository.getOrCreateChatId(currentUserId, otherUserId)
//
//            // Observa as mensagens em tempo real
//            chatRepository.observeMessages(chatId).collect { newMessages ->
//                _messages.clear()
//                _messages.addAll(newMessages)  // Atualiza a lista local
//            }
//        }
//    }
//
//    // Envia uma nova mensagem
//    fun sendMessage(text: String) {
//        if (text.isBlank()) return  // Não envia mensagens vazias
//
//        viewModelScope.launch {
//            val message = Message(
//                senderId = currentUserId,
//                receiverId = otherUserId,
//                text = text,
//                timestamp = System.currentTimeMillis(),  // Usa o tempo atual
//                read = false  // Inicialmente não lida
//            )
//            chatRepository.sendMessage(chatId, message)  // Persiste no Firestore
//        }
//    }
//}