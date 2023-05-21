package edu.victor.creditapplication.DTO

import edu.victor.creditapplication.Model.Address
import edu.victor.creditapplication.Model.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto (
        @field:NotEmpty(message = "Empty field.") val firstName: String,
        @field:NotEmpty(message = "Empty field.") val lastName: String,
        @field:CPF(message = "Invalid CPF.") val cpf: String,
        @field:NotNull(message = "Invalid Field.") val income: BigDecimal,
        @field:Email(message = "Invalid Email.") val email: String,
        @field:NotEmpty(message = "Empty field.") val password: String,
        @field:NotEmpty(message = "Empty field.") val zipCode: String,
        @field:NotEmpty(message = "Empty field.") val street: String
){
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}