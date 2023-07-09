package com.example.AdressApi.Service

import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import org.springframework.data.repository.query.Param

interface IAddressService {

    fun save(address: Address): Address
    fun save(address: Address, owner: Owner): Address
    fun findById(id: Long): Address?
    fun findByCep(cep: String): List<Address>
    fun findByLograudoro(lograudoro: String): List<Address>
    fun findByBairro(bairro: String): List<Address>
    fun findByCidade(cidade: String): List<Address>
    fun findByOwner(ownerId: Long): List<Address>
    fun deleteById(id: Long): Address?

}