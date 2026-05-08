package org.demo.agentgeneric.controller

import org.apache.logging.log4j.message.Message
import org.demo.agentgeneric.repo.EmployeeRepository
import org.demo.agentgeneric.service.AiService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.awt.PageAttributes

@Controller
class DataController(
    private val aiService: AiService,
    private val employeeRepository: EmployeeRepository
) {

    @GetMapping("/")
    fun index(): Mono<String>  {return Mono.just("index");}

    @GetMapping("/departments")
    fun getData2(): Mono<String>  {
        return aiService.getAiStreamResponse("List out all departments").map { it.result?.output?.text ?: "" }.collectList().map { it.joinToString("") }
    }

    @PostMapping("/ask", consumes = [MediaType.ALL_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sendPrompt(serverWebExchange: ServerWebExchange): Mono<String>  {

        return serverWebExchange.formData.flatMap { formData ->
            val message = formData.getFirst("message") ?: ""

        aiService.getAiStreamResponse(message.toString()).map { it.result?.output?.text ?: "" }.collectList().map { it.joinToString("") } }
    }

    @GetMapping("/user/{emailAddress}")
    @ResponseBody
    fun getEmployeeData(@PathVariable("emailAddress") emailAddress: String): Flux<String> {
        return aiService.getAiStreamResponse("Find employee with email ${emailAddress} and tell me his first name").map { it.result?.output?.text ?: "" }
    }

    @GetMapping("/test4/{emailAddress}")
    fun getEmployeeData3(@PathVariable("emailAddress") emailAddress: String): String {
        val employee = employeeRepository.findByEmail(emailAddress)
        return if (employee != null) {
            employee.map { it -> it.firstName.toString() }.toString()
        } else {
            "No employee found with the email address: $emailAddress"
        }
    }
}
