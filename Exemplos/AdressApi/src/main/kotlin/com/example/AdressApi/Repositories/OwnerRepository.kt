package com.example.AdressApi.Repositories

import com.example.AdressApi.Models.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OwnerRepository: JpaRepository<Owner, Long> {

    fun findByCpf(cpf: String): Owner?

    @Query("SELECT o FROM Owner o JOIN o.addresses a WHERE a.id = :addressId")
    fun findByAddressId(@Param("addressId") addressId: Long): Owner?
}