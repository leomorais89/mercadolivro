package com.ljmtecnologica.mercadolivro.controller

import com.ljmtecnologica.mercadolivro.controller.request.PostCustomer
import com.ljmtecnologica.mercadolivro.controller.request.PutCustomer
import com.ljmtecnologica.mercadolivro.extension.toCustomer
import com.ljmtecnologica.mercadolivro.model.Customer
import com.ljmtecnologica.mercadolivro.security.annotation.UserCanOnlyAccessTheirOwnResource
import com.ljmtecnologica.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(@RequestParam name : String?) : List<Customer>{
        return this.customerService.findAll(name)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @UserCanOnlyAccessTheirOwnResource
    fun findById(@PathVariable id : Int) : Customer{
        return this.customerService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun insert(@RequestBody @Valid postCustomer: PostCustomer) : Customer{
        return this.customerService.insert(postCustomer.toCustomer())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @UserCanOnlyAccessTheirOwnResource
    fun update(@PathVariable id: Int, @RequestBody putCustomer: PutCustomer) : Customer{
        val customer = this.customerService.findById(id)
        return this.customerService.update(putCustomer.toCustomer(customer))
    }

    @DeleteMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id : Int){
        this.customerService.delete(id)
    }

}