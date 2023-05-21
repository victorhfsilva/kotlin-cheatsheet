package edu.victor.creditapplication.DTO

import edu.victor.creditapplication.Model.Credit
import java.math.BigDecimal
import java.util.UUID

data class CreditViewList(
        val creditCode: UUID,
        val creditValue: BigDecimal,
        val numberOfInstallments: Int
) {
    constructor(credit: Credit): this(
            creditCode = credit.creditCode,
            creditValue = credit.creditValue,
            numberOfInstallments = credit.numberofInstallments
    )
}