package org.demo.agentgeneric.controller

import org.demo.agentgeneric.service.AiService
import org.demo.agentgeneric.tools.EmployeeTool
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class TestDataController(
    private val aiService: AiService,
    private val employeeTool: EmployeeTool
) {

   @GetMapping("/test2", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun getData2(): Flux<String>  {

        return aiService.getAiStreamResponse("Explain Apache kafka in funny way")

    }

    @GetMapping("/test3/{emailAddress}")
    fun getEmployeeData(@PathVariable("emailAddress") emailAddress: String): String {
        return aiService.getAiStreamResponse2("Find employee with email $emailAddress and tell me his first name").blockFirst() ?: "No response"
    }
}