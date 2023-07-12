package com.example.AdressApi.DTOs

import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.OwnerRepository
import com.example.AdressApi.Service.Implementation.OwnerService
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class AddressDTO(

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    var cep: String,

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    var logradouro: String,

    @field:NotNull(message = "Field can not be null")
    var numero: Int,

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    var bairro: String,

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    var complemento: String = "",

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    var localidade: String,

    @field:NotEmpty(message = "Empty Field.")
    @field:NotNull(message = "Field can not be null")
    var uf: String,

    @field:NotNull(message = "Field can not be null")
    var ownerId: Long?,
) {

    fun toEntity(ownerService: OwnerService): Address {
        val owner = ownerService.findById(ownerId!!)

        return Address(
            cep = this.cep,
            logradouro = this.logradouro,
            numero = this.numero,
            bairro = this.bairro,
            complemento = this.complemento,
            localidade = this.localidade,
            uf = this.uf,
            owner = owner
        )
    }
}
