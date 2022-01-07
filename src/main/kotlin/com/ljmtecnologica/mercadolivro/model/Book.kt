package com.ljmtecnologica.mercadolivro.model

import com.ljmtecnologica.mercadolivro.model.enums.BookStatusEnum
import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import com.ljmtecnologica.mercadolivro.exception.BadRequestException
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "book")
data class Book(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,

    @Column(name = "name")
    val name : String,

    @Column(name = "price")
    val price : BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", updatable = false)
    val customer : Customer? = null
) {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status : BookStatusEnum? = null
        set(value) {
            if(field != BookStatusEnum.ATIVO){
                throw BadRequestException(ErrorEnum.ML0202.message.format(field), ErrorEnum.ML0202.internalCode)
            }
            field = value
        }

    constructor(
        id: Int? = null,
        name : String,
        price : BigDecimal,
        customer : Customer? = null,
        status : BookStatusEnum?
    ) : this(id, name, price, customer) {
        this.status = status
    }

}