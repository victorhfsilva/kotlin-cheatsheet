package edu.victor.creditapplication.Service

import edu.victor.creditapplication.Model.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findByID(id: Long): Customer

    fun delete(id: Long)
}