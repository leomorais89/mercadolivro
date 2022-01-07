package com.ljmtecnologica.mercadolivro.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.ljmtecnologica.mercadolivro.model.enums.CustomerStatusEnum
import com.ljmtecnologica.mercadolivro.model.enums.RolesEnum
import javax.persistence.*

@Entity(name = "customer")
@JsonIgnoreProperties(value = ["password", "roles"])
data class Customer(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,

    @Column(name = "name")
    val name : String,

    @Column(name = "email")
    val email : String,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status : CustomerStatusEnum,

    @Column(name = "password")
    val password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RolesEnum::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id", referencedColumnName = "id")])
    val roles: Set<RolesEnum> = setOf()
)