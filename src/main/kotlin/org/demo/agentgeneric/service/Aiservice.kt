package org.demo.agentgeneric.service

import org.demo.agentgeneric.entity.Employee
import org.demo.agentgeneric.repo.EmployeeRepository
import org.demo.agentgeneric.tools.EmployeeTool
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class AiService(chatClientBuilder: ChatClient.Builder, val employeeRepository: EmployeeRepository) {

    // Build the chat client once during initialization
    private val chatClient: ChatClient = chatClientBuilder.build()

    fun getAiStreamResponse(userPrompt: String): Flux<String> {
        return chatClient.prompt()
            .user(userPrompt)
            .stream()           // Execute the request
            .content()   // Extract the text response
    }

    fun getAiStreamResponse2(userPrompt: String): Flux<String> {
        return chatClient.prompt()
            .user(userPrompt)
            .tools(EmployeeTool(employeeRepository))
            .stream()           // Execute the request
            .content()   // Extract the text response
    }


//    fun getAiResponse(userPrompt: String): String {
//        return chatClient.prompt()
//            .user(userPrompt)
//            .call()
//            .content().toString()
//    }
}
