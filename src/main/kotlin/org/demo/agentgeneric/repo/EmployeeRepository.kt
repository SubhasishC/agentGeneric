package org.demo.agentgeneric.repo

import org.demo.agentgeneric.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Int> {
    fun findEmployeesByEmail(email: String): Employee?
}