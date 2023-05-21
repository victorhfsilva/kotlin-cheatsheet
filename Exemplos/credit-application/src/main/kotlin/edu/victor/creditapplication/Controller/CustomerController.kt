package edu.victor.creditapplication.Controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import edu.victor.creditapplication.DTO.CustomerDto
import edu.victor.creditapplication.Service.implementation.CustomerService
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/api/customers")
class CustomerController(
        private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): String{
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return "Customer ${savedCustomer.email} saved."
    }
}