package org.demo.agentgeneric.contract

import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.model.StreamingModel
import reactor.core.publisher.Flux

interface StreamingChatModel: StreamingModel<Prompt, ChatResponse> {

    fun stream(message: String): Flux<String> {
        // TODO: implement default behavior
        throw NotImplementedError("Not yet implemented")
    }


    override fun stream(prompt: Prompt): Flux<ChatResponse>
}