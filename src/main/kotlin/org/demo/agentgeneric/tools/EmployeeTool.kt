package org.demo.agentgeneric.tools

import org.demo.agentgeneric.entity.Employee
import org.demo.agentgeneric.repo.EmployeeRepository
import org.springframework.ai.tool.annotation.Tool
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class EmployeeTool( val employeeRepository: EmployeeRepository) {

   @Tool(description = "Find employee with email and tell me his first name")
    fun findByEmail(email: String): Employee? {
        return employeeRepository.findEmployeesByEmail(email)
    }
}