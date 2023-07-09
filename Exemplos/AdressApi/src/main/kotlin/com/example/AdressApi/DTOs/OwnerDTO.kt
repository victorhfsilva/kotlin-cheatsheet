package com.example.AdressApi.DTOs

import com.example.AdressApi.Models.Owner
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF

data class OwnerDTO (

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    val firstName: String,

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    val lastName: String,

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    @field:CPF(message = "Invalid CPF.")
    val cpf: String,

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    val identidade: String,

    ){
    fun toEntity(): Owner = Owner(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        identidade = this.identidade
    )
}