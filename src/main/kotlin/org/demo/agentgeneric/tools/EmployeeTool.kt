package org.demo.agentgeneric.tools

import org.demo.agentgeneric.repo.EmployeeRepository
import org.springframework.ai.tool.annotation.Tool
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class EmployeeTool( val employeeRepository: EmployeeRepository) {

   @Tool(description = "Find employee from company_db.employee table by email address ")
    fun findByEmail(email: String): String {
        return employeeRepository.findEmployeesByEmail(email)?.firstName.toString()
    }
}