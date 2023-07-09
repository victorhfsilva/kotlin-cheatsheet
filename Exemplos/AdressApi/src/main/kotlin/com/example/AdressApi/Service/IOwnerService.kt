package com.example.AdressApi.Service

import com.example.AdressApi.Models.Owner
import java.util.*

interface IOwnerService {

    fun save (owner: Owner): Owner
    fun findByCpf (cpf: String): Owner?
    fun findByAddress (addressId: Long): Owner?
    fun deleteByCpf (cpf: String): Owner?
    fun deleteByAddress (addressId: Long): Owner?

}