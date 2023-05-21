package edu.victor.creditapplication.DTO

import edu.victor.creditapplication.Model.Credit
import edu.victor.creditapplication.Model.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
        @field:NotNull(message = "Invalid Field.") val creditValue: BigDecimal,
        @field:Future(message = "Invalid Date.") val dayFirstInstallment: LocalDate,
        @field:Positive(message = "Invalid Field.") val numberOfInstallments: Int,
        @field:NotNull(message = "Invalid Field.") val customerId: Long
) {

    fun toEntity(): Credit = Credit(
            creditValue = this.creditValue,
            dayFirstInstallment = this.dayFirstInstallment,
            numberofInstallments = this.numberOfInstallments,
            customer = Customer(id = this.customerId)
    )

}