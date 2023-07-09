package com.example.AdressApi.Service.Implementation

import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.AddressRepository
import com.example.AdressApi.Repositories.OwnerRepository
import com.example.AdressApi.Service.IAddressService
import org.springframework.stereotype.Component

@Component
class AddressService(
    private val ownerRepository: OwnerRepository,
    private val addressRepository: AddressRepository
): IAddressService {

    override fun save(address: Address): Address {
        val savedAddress = addressRepository.save(address)
        return savedAddress
    }

    override fun findById(id: Long): Address? {
        return addressRepository.findById(id).orElse(null)
    }

    override fun findByCep(cep: String): List<Address> =
        addressRepository.findByCep(cep)


    override fun findByLogradouro(lograudoro: String): List<Address> =
        addressRepository.findByLogradouro(lograudoro)


    override fun findByBairro(bairro: String): List<Address> =
        addressRepository.findByBairro(bairro)


    override fun findByLocalidade(cidade: String): List<Address> =
        addressRepository.findByLocalidade(cidade)


    override fun deleteById(id: Long): Address? {
        val existingAddress = addressRepository.findById(id!!).orElse(null)
        existingAddress?.let {
            this.addressRepository.deleteById(id)
        }
        return existingAddress
    }

    override fun findByOwner(ownerId: Long): List<Address> {
        return addressRepository.findByOwner(ownerId)
    }


}