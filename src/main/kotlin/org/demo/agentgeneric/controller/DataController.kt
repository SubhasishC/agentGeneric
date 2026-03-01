package org.demo.agentgeneric.controller

import org.demo.agentgeneric.repo.EmployeeRepository
import org.demo.agentgeneric.service.AiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class DataController(
    private val aiService: AiService,
    private val employeeRepository: EmployeeRepository
) {
    @GetMapping("/departments")
    fun getData2(): Mono<String>  {
        return aiService.getAiStreamResponse("List out all departments").map { it.result?.output?.text ?: "" }.collectList().map { it.joinToString("") }
    }

    @GetMapping("/user/{emailAddress}")
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
