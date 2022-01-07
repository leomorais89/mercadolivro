package com.ljmtecnologica.mercadolivro.security

import com.ljmtecnologica.mercadolivro.model.enums.CustomerStatusEnum
import com.ljmtecnologica.mercadolivro.model.Customer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsSecurity(
    val customer: Customer
): UserDetails {
    val id: Int? = customer.id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.customer.roles.map { SimpleGrantedAuthority(it.description) }.toMutableList()
    }

    override fun getPassword(): String {
        return this.customer.password
    }

    override fun getUsername(): String {
        return customer.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return customer.status == CustomerStatusEnum.ATIVO
    }
}