package edu.victor.creditapplication.Model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "customer")
data class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(nullable = false)
        var firstName: String = "",

        @Column(nullable = false)
        var lastName: String = "",

        @Column(nullable = false, unique = true)
        var cpf: String = "",

        @Column(nullable = false, unique = true)
        var email: String = "",

        @Column(nullable = false)
        var password: String = "",

        @Column(nullable = false)
        var income: BigDecimal = BigDecimal.ZERO,

        @Embedded
        @Column(nullable = false)
        var address: Address = Address(),

        @Column(nullable = false)
        @OneToMany(fetch = FetchType.LAZY,
                cascade = [CascadeType.REMOVE, CascadeType.PERSIST],
                mappedBy = "customer")
        var credits: List<Credit> = mutableListOf<Credit>()
)