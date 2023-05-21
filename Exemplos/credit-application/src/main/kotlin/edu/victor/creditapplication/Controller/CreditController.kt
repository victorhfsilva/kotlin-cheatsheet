package edu.victor.creditapplication.Controller

import edu.victor.creditapplication.DTO.CreditDto
import edu.victor.creditapplication.DTO.CreditViewList
import edu.victor.creditapplication.DTO.CreditView
import edu.victor.creditapplication.Model.Credit
import edu.victor.creditapplication.Service.implementation.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credit")
class CreditController (
        private val creditService: CreditService
){

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved.")
    }

    @GetMapping("/{customerId}")
    fun findAllByCustomerId(@PathVariable customerId: Long): ResponseEntity<List<CreditViewList>> {
        val creditViewList = this.creditService.findAllByCustomer(customerId).stream().map {
            credit: Credit -> CreditViewList(credit)
        }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @RequestParam(value = "creditCode") creditCode: UUID): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(creditCode, customerId)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }

}