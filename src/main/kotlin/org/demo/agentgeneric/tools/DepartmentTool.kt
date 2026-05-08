package org.demo.agentgeneric.tools

import org.demo.agentgeneric.repo.DepartmentRepository
import org.springframework.ai.tool.annotation.Tool
import org.springframework.stereotype.Component

@Component
class DepartmentTool(val departmentRepository: DepartmentRepository) {

   @Tool(description = "Find all departments from company_db.department table")
    fun findAllDepartments(): String {
        return departmentRepository.findAll().toString();
    }
}