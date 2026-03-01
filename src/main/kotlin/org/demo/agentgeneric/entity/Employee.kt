package org.demo.agentgeneric.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Id
import java.math.BigDecimal
import java.util.*

@Entity
data class Employee(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val empId: Int = 0, // Maps to emp_id

    val firstName: String?, // Maps to first_name

    val lastName: String?,  // Maps to last_name

    val email: String?, // Maps to email

    val salary: BigDecimal?, // Maps to salary

    val hireDate: Date?, // Maps to hire_date

    @ManyToOne
    @JoinColumn(name = "dept_id")
    val department: Department? // Maps to dept_id (foreign key relation)

)