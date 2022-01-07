package com.ljmtecnologica.mercadolivro.service

import com.ljmtecnologica.mercadolivro.model.enums.CustomerStatusEnum
import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import com.ljmtecnologica.mercadolivro.model.enums.RolesEnum
import com.ljmtecnologica.mercadolivro.exception.NotFoundException
import com.ljmtecnologica.mercadolivro.model.Customer
import com.ljmtecnologica.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun findAll(name : String?) : List<Customer>{
        name?.let {
            return this.customerRepository.findByNameContaining(it)
        }
        return this.customerRepository.findAll().toList()
    }

    fun findById(id : Int) : Customer{
        return this.customerRepository.findById(id).orElseThrow{NotFoundException(ErrorEnum.ML0101.message.format(id), ErrorEnum.ML0101.internalCode)}
    }

    fun insert(customer: Customer) : Customer{
        val customerCopy = customer.copy(
            roles = setOf(RolesEnum.COSTUMER),
            password = this.bCryptPasswordEncoder.encode(customer.password)
        )
        return this.customerRepository.save(customerCopy)
    }

    fun update(customer: Customer) : Customer{
        if(this.customerRepository.existsById(customer.id!!)){
            return this.customerRepository.save(customer)
        }
        throw NotFoundException(ErrorEnum.ML0101.message.format(customer.id), ErrorEnum.ML0101.internalCode)
    }

    fun delete(id : Int){
        if(this.customerRepository.existsById(id)){
            val customer = this.findById(id)
            customer.status = CustomerStatusEnum.INATIVO
            this.customerRepository.save(customer)
            this.bookService.deleteByCustomer(customer)
            return
        }
        throw NotFoundException(ErrorEnum.ML0101.message.format(id), ErrorEnum.ML0101.internalCode)
    }

    fun emailAvailable(email: String): Boolean{
        return !this.customerRepository.existsByEmail(email)
    }

}