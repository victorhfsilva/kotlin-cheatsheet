package com.example.AdressApi.Repositories

import com.example.AdressApi.Models.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository: JpaRepository<Address, Long> {

    fun findByCep(cep: String): List<Address>

    fun findByLogradouro(lograudoro: String): List<Address>

    fun findByBairro(bairro: String): List<Address>

    fun findByLocalidade(cidade: String): List<Address>

    @Query("SELECT a FROM Address a WHERE a.owner.id = :ownerId")
    fun findByOwner(@Param("ownerId") ownerId: Long): List<Address>

}