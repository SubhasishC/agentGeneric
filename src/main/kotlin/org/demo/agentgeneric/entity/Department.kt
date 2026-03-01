package org.demo.agentgeneric.entity

import jakarta.persistence.*

//import javax.persistence.*

@Entity
data class Department(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val deptId: Int = 0, // Maps to dept_id

    val deptName: String, // Maps to dept_name

)