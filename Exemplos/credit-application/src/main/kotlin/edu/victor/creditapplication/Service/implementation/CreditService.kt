package edu.victor.creditapplication.Service.implementation

import edu.victor.creditapplication.Model.Credit
import edu.victor.creditapplication.Repository.CreditRepository
import edu.victor.creditapplication.Service.ICreditService
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreditService(
        private val creditRepository: CreditRepository,
        private val customerService: CustomerService
        ): ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findByID(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomerId(customerId)


    override fun findByCreditCode(creditCode: UUID, customerId: Long): Credit {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode) ?: throw RuntimeException("Credit $creditCode not found.")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin.")
    }

}