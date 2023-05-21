package edu.victor.creditapplication.Service.implementation

import edu.victor.creditapplication.Exception.BusinessException
import edu.victor.creditapplication.Model.Customer
import edu.victor.creditapplication.Repository.CustomerRepository
import edu.victor.creditapplication.Service.ICustomerService
import org.springframework.stereotype.Component

@Component
class CustomerService(
        private val customerRepository: CustomerRepository
): ICustomerService {

    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)


    override fun findByID(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow{
            throw BusinessException("Id $id not found.")
        }


    override fun delete(id: Long) =
        this.customerRepository.deleteById(id)

}