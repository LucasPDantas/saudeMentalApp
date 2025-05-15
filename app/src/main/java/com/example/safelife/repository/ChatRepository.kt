package com.example.safelife.repository

import com.example.safelife.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FieldValue

class ChatRepository {
    private val db: FirebaseFirestore = Firebase.firestore  // Instância do Firestore

    // Obtém ou cria um ID de chat para dois usuários
    suspend fun getOrCreateChatId(user1: String, user2: String): String {
        // Primeiro verifica se já existe um chat entre esses usuários
        val snapshot = db.collection("chats")
            .whereArrayContains("participants", user1)
            .get()
            .await()

        // Verifica se já existe um chat com ambos os participantes
        for (doc in snapshot.documents) {
            val participants = doc.get("participants") as? List<*>
            if (participants != null && participants.contains(user2)) {
                return doc.id
            }
        }

        // Se não existir, cria um novo chat
        val newChat = hashMapOf(
            "participants" to listOf(user1, user2),
            "createdAt" to System.currentTimeMillis()
        )

        val docRef = db.collection("chats").add(newChat).await()
        return docRef.id
    }

    // Observa mensagens em tempo real usando Flow
    fun observeMessages(chatId: String): Flow<List<Message>> = callbackFlow {
        val listener = db.collection("chats/$chatId/messages")
            .orderBy("timestamp")  // Ordena por timestamp
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)  // Encerra o Flow em caso de erro
                    return@addSnapshotListener
                }

                // Converte os documentos para objetos Message
                val messages = snapshot?.documents?.mapNotNull { doc ->
                    try {
                        val msg = doc.toObject(Message::class.java)
                        if (msg?.timestamp != null) msg else null
                    } catch (e: Exception) {
                        null
                    }
                } ?: emptyList()


                trySend(messages)  // Envia as mensagens pelo Flow
            }

        // Remove o listener quando o Flow é cancelado
        awaitClose { listener.remove() }
    }

    // Envia uma mensagem para o Firestore
    suspend fun sendMessage(chatId: String, message: Message) {
        val firebaseMessage = hashMapOf(
            "senderId" to message.senderId,
            "receiverId" to message.receiverId,
            "text" to message.text,
            "timestamp" to FieldValue.serverTimestamp(), // ✅ aqui o timestamp será definido pelo Firestore
            "read" to message.read
        )

        db.collection("chats/$chatId/messages")
            .add(firebaseMessage)
            .await()
        }
    }
