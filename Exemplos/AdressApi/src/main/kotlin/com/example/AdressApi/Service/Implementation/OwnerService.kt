package com.example.AdressApi.Service.Implementation

import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.OwnerRepository
import com.example.AdressApi.Service.IOwnerService
import java.lang.Exception
import java.util.*

class OwnerService(
    private val ownerRepository: OwnerRepository
): IOwnerService {

    override fun save (owner: Owner): Owner = this.ownerRepository.save(owner)

    override fun findByCpf(cpf: String): Owner? =
        this.ownerRepository.findByCpf(cpf)

    override fun findByAddress(addressId: Long): Owner?  =
        this.ownerRepository.findByAddress(addressId)

    override fun deleteByCpf(cpf: String): Owner? {
        val owner = findByCpf(cpf)
        owner?.let{
            this.ownerRepository.delete(it)
        }
        return owner
    }

    override fun deleteByAddress(addressId: Long): Owner? {
        val owner = findByAddress(addressId)
        owner?.let{
            this.ownerRepository.delete(it)
        }
        return owner
    }

}