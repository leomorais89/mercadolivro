package com.ljmtecnologica.mercadolivro.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admins")
class AdminController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun repost(): String {
        return "This is a reporte, only admins can access!"
    }

}