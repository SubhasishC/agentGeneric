package org.demo.agentgeneric.tools

import org.demo.agentgeneric.repo.EmployeeRepository
import org.springframework.ai.tool.annotation.Tool
import org.springframework.stereotype.Component

@Component
class EmployeeTool( val employeeRepository: EmployeeRepository) {

   @Tool(description = "Find employee with email and tell me his first name")
    fun findByEmail(email: String): String {
        return employeeRepository.findByEmail(email).map { it -> it.firstName.toString() }.toString()
    }
}