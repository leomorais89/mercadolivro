package com.ljmtecnologica.mercadolivro.security

import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import com.ljmtecnologica.mercadolivro.exception.AuthenticationException
import com.ljmtecnologica.mercadolivro.repository.CustomerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsSecurityService(
    private val customerRepository: CustomerRepository
): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val customer = this.customerRepository.findByEmail(email)
            .orElseThrow{AuthenticationException(ErrorEnum.ML0002.message.format(email), ErrorEnum.ML0002.internalCode)}
        return UserDetailsSecurity(customer)
    }

}