package org.demo.agentgeneric.service

import org.demo.agentgeneric.repo.DepartmentRepository
import org.demo.agentgeneric.repo.EmployeeRepository
import org.demo.agentgeneric.tools.DepartmentTool
import org.demo.agentgeneric.tools.EmployeeTool
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class AiService(chatClientBuilder: ChatClient.Builder, val employeeRepository: EmployeeRepository, val departmentRepository: DepartmentRepository) {

    // Build the chat client once during initialization
    private val chatClient: ChatClient = chatClientBuilder.build()

    fun getAiStreamResponse(userPrompt: String): Flux<ChatResponse> {
        // Validate the user prompt to ensure it's not empty or null
        require(userPrompt.isNotBlank()) { "User prompt must not be blank" }

        // Log the user prompt for debugging purposes
        println("Received user prompt: $userPrompt")

        return chatClient.prompt()
            .user(userPrompt)
            .tools(EmployeeTool(employeeRepository), DepartmentTool(departmentRepository))
            .stream()           // Execute the request
            .chatResponse()     // Extract the text response
            .doOnError { error ->
                // Log any errors that occur during the streaming process
                println("Error occurred while processing AI stream response: ${error.message}")
            }
            .doOnComplete {
                // Log completion of the streaming process
                println("AI stream response processing completed")
            }
    }
}
