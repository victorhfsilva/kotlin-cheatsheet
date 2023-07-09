package com.example.AdressApi.Service.Implementation

import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.OwnerRepository
import com.example.AdressApi.Service.IOwnerService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Component
class OwnerService(
    private val ownerRepository: OwnerRepository
): IOwnerService {

    override fun save (owner: Owner): Owner = this.ownerRepository.save(owner)

    override fun findByCpf(cpf: String): Owner? =
        this.ownerRepository.findByCpf(cpf)

    override fun findById(id: Long): Owner? =
        this.ownerRepository.findById(id).orElse(null)

    override fun deleteByCpf(cpf: String): Owner? {
        val owner = findByCpf(cpf)
        owner?.let{
            this.ownerRepository.delete(it)
        }
        return owner
    }

    override fun findByAddressId(addressId: Long): Owner? =
        ownerRepository.findByAddressId(addressId)

}