package com.example.AdressApi.Service.Implementation

import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.AddressRepository
import com.example.AdressApi.Repositories.OwnerRepository
import com.example.AdressApi.Service.IAddressService

class AddressService(
    private val ownerRepository: OwnerRepository,
    private val addressRepository: AddressRepository
): IAddressService {

    override fun save(address: Address): Address {
        val savedAddress = addressRepository.save(address)
        return savedAddress
    }

    override fun save(address: Address, owner: Owner): Address {
        val existingOwner = ownerRepository.findById(owner.id!!).orElse(null)
        if (existingOwner != null) {
            address.owner = existingOwner
            val savedAddress = addressRepository.save(address)
            return savedAddress
        }
        throw IllegalArgumentException("Owner not found")
    }

    override fun findById(id: Long): Address? {
        return addressRepository.findById(id).orElse(null)
    }

    override fun findByCep(cep: String): List<Address> =
        addressRepository.findByCep(cep)


    override fun findByLograudoro(lograudoro: String): List<Address> =
        addressRepository.findByLograudoro(lograudoro)


    override fun findByBairro(bairro: String): List<Address> =
        addressRepository.findByBairro(bairro)


    override fun findByCidade(cidade: String): List<Address> =
        addressRepository.findByCidade(cidade)


    override fun findByOwner(ownerId: Long): List<Address> =
        addressRepository.findByOwner(ownerId)


    override fun deleteById(id: Long): Address? {
        val existingAddress = addressRepository.findById(id!!).orElse(null)
        existingAddress?.let {
            this.addressRepository.deleteById(id)
        }
        return existingAddress
    }

}