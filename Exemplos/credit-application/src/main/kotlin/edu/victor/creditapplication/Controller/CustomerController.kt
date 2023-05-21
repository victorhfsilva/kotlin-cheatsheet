package edu.victor.creditapplication.Controller

import edu.victor.creditapplication.DTO.CustomerDto
import edu.victor.creditapplication.DTO.CustomerUpdateDto
import edu.victor.creditapplication.DTO.CustomerView
import edu.victor.creditapplication.Model.Customer
import edu.victor.creditapplication.Service.implementation.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customer")
class CustomerController(
        private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved.")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findByID(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer (@RequestParam(value = "customerId") id: Long,
                        @RequestBody @Valid customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findByID(id)
        val customerToUpdate:Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }

}