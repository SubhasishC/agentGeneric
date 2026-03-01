package org.demo.agentgeneric.repo

import org.demo.agentgeneric.entity.Employee
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface EmployeeRepository : ReactiveCrudRepository<Employee, Int> {
    fun findByEmail(email: String): Mono<Employee>
}