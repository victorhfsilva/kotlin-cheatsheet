package com.example.AdressApi.Models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "owners")
data class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(nullable = false, unique = true)
    var cpf: String,

    @Column(nullable = false, unique = true)
    var identidade: String,

    @OneToMany(fetch = FetchType.LAZY,
        cascade = [CascadeType.REMOVE, CascadeType.PERSIST],
        mappedBy = "owner")
    var addresses: List<Address> = mutableListOf<Address>(),

    @Column(nullable = false)
    var creationDate : LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var active: Boolean = true

)
