package com.ljmtecnologica.mercadolivro.controller

import com.ljmtecnologica.mercadolivro.mapper.PurchaseMapper
import com.ljmtecnologica.mercadolivro.controller.request.PostPurchase
import com.ljmtecnologica.mercadolivro.model.Purchase
import com.ljmtecnologica.mercadolivro.security.annotation.UserCanOnlyAccessTheirOwnResource
import com.ljmtecnologica.mercadolivro.service.PurchaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/purchases")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @UserCanOnlyAccessTheirOwnResource
    fun findByCustomerId(@PathVariable id: Int, @PageableDefault(page = 0, size = 10) pageable: Pageable): Page<Purchase>{
        return this.purchaseService.findByCustomerId(id, pageable)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun insert(@RequestBody @Valid postPurchase: PostPurchase): Purchase{
        return this.purchaseService.insert(this.purchaseMapper.toPurchase(postPurchase))
    }

}