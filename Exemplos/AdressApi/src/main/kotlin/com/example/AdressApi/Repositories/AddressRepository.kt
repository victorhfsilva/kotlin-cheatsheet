package com.example.AdressApi.Repositories

import com.example.AdressApi.Models.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AddressRepository: JpaRepository<Address, Long> {

    fun findByCep(cep: String): List<Address>

    fun findByLograudoro(lograudoro: String): List<Address>

    fun findByBairro(bairro: String): List<Address>

    fun findByCidade(cidade: String): List<Address>

    @Query(value = "SELECT * FROM addresses WHERE owner_id = :ownerId")
    fun findByOwner(@Param("ownerId") ownerId: Long): List<Address>
}