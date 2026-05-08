package org.demo.agentgeneric.repo

import org.demo.agentgeneric.entity.Department
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : ReactiveCrudRepository<Department, Int> {
    fun findDepartmentsByDeptName(departmentName: String): Department?
}