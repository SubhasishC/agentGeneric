package org.demo.agentgeneric.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.*

@Table
data class Employee(

    @Id
    val empId: Int = 0, // Maps to emp_id

    val firstName: String?, // Maps to first_name

    val lastName: String?,  // Maps to last_name

    val email: String?, // Maps to email

    val salary: BigDecimal?, // Maps to salary

    val hireDate: Date?, // Maps to hire_date

    val department: Department? // Maps to dept_id (foreign key relation)

)