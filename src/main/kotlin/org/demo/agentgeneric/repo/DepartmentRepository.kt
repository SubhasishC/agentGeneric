package org.demo.agentgeneric.repo

import org.demo.agentgeneric.entity.Department
import org.demo.agentgeneric.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department, Int> {
    fun findDepartmentsByDeptName(departmentName: String): Department?
}