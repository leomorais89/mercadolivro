package com.ljmtecnologica.mercadolivro.model

import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "purchase")
data class Purchase(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "nfe")
    val nfe: String? = null,

    @Column(name = "price")
    val price: BigDecimal,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", updatable = false)
    val customer: Customer,

    @ManyToMany
    @JoinTable(name = "purchase_book",
        joinColumns = [JoinColumn(name = "purchase_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")])
    val books: MutableList<Book>

)