package edu.victor.creditapplication.Model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "credit")
data class Credit(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false, unique = true)
        var creditCode : UUID = UUID.randomUUID(),

        @Column(nullable = false)
        val creditValue: BigDecimal = BigDecimal.ZERO,

        @Column(nullable = false)
        val dayFirstInstallment: LocalDate,

        @Column(nullable = false)
        val numberofInstallments: Int = 0,

        @Enumerated
        val status: Status = Status.IN_PROGRESS,

        @ManyToOne
        var customer: Customer? = null
)