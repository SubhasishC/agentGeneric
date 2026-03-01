package org.demo.agentgeneric.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


//import javax.persistence.*

@Table
data class Department(

    @Id
    val deptId: Int = 0, // Maps to dept_id

    val deptName: String, // Maps to dept_name

)