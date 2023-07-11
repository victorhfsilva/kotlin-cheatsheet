package com.example.AdressApi.Models

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "addresses")
data class Address (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var cep: String,

    @Column(nullable = false)
    var logradouro: String,

    @Column(nullable = false)
    var numero: String,

    @Column(nullable = false)
    var bairro: String,

    @Column(nullable = false)
    var complemento: String = "",

    @Column(nullable = false)
    var localidade: String,

    @Column(nullable = false)
    var uf: String,

    @ManyToOne
    @JoinColumn(name = "owner_id")
    var owner: Owner? = null,

    @Column(nullable = false)
    var creationDate : LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var active: Boolean = true
){

    // Override the toString() method excluding the owner property
    override fun toString(): String {
        return "Address(id=$id, cep='$cep', logradouro='$logradouro', numero='$numero', bairro='$bairro', complemento='$complemento', localidade='$localidade', uf='$uf', creationDate=$creationDate, active=$active)"
    }

}