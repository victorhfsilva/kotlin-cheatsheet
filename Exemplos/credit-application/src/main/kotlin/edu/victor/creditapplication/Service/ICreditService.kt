package edu.victor.creditapplication.Service

import edu.victor.creditapplication.Model.Credit
import java.util.*

interface ICreditService {

    fun save(credit: Credit): Credit

    fun findAllByCustomer(customerId: Long): List<Credit>

    fun findByCreditCode(creditCode: UUID, customerId: Long): Credit


}