package edu.victor.creditapplication.DTO

import edu.victor.creditapplication.Model.Credit
import edu.victor.creditapplication.Model.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView(
        val creditCode: UUID,
        val creditValue: BigDecimal,
        val numberOfInstallments: Int,
        val status: Status,
        val emailCustomer: String?,
        val incomeCustomer: BigDecimal?
) {
    constructor(credit: Credit): this(
            creditCode = credit.creditCode,
            creditValue = credit.creditValue,
            numberOfInstallments = credit.numberofInstallments,
            status = credit.status,
            emailCustomer = credit.customer?.email,
            incomeCustomer = credit.customer?.income
    )
}