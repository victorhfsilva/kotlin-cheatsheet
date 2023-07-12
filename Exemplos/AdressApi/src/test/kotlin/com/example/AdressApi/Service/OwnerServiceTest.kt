package com.example.AdressApi.Service

import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.OwnerRepository
import com.example.AdressApi.Service.Implementation.OwnerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class OwnerServiceTest {
    @MockK
    lateinit var ownerRepository: OwnerRepository

    @InjectMockKs
    lateinit var ownerService: OwnerService


    lateinit var owner: Owner
    lateinit var address1: Address
    lateinit var address2: Address
    lateinit var addresses: List<Address>

    private fun buildOwner(
        id: Long = 1L,
        firstName: String = "John",
        lastName: String = "Doe",
        cpf: String = "68710381910",
        identidade: String = "ID123",
        addresses: List<Address> = emptyList()
    ) = Owner (
        id = id,
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        identidade = identidade,
        addresses = addresses
    )

    private fun buildAddress (
        id: Long = 1L,
        cep: String = "64608435",
        logradouro: String = "Street A",
        numero: Int = 14,
        bairro: String = "Neighborhood A",
        localidade: String = "City 1",
        uf: String = "State 1") = Address (
        id = id,
        cep = cep,
        logradouro = logradouro,
        numero = numero,
        bairro = bairro,
        localidade = localidade,
        uf = uf
    )

    @BeforeEach
    fun setup(){
        address1 = buildAddress()
        address2 = buildAddress(
            id = 2L,
            cep = "55004072",
            logradouro = "Street B",
            numero = 25,
            bairro = "Neighborhood B",
            localidade = "City 1",
            uf = "State 1"
        )
        addresses = listOf(address1,address2)
        owner = buildOwner(addresses = addresses)
    }


    @Test
    fun `should save owner`() {
        //Mock the repository behaviour
        every { ownerRepository.save(any()) } returns owner

        // When
        val savedOwner = ownerService.save(owner)

        // Then
        Assertions.assertThat(savedOwner).isNotNull
        Assertions.assertThat(savedOwner).isSameAs(owner)

        //Ensure that the save method of the ownerRepository is called exactly once with
        //the owner object as an argument.
        verify(exactly = 1) { ownerRepository.save(owner) }
    }

    @Test
    fun `should find owner by CPF`() {
        //Given
        val cpf = owner.cpf

        every { ownerRepository.findByCpf(cpf) } returns owner

        // When
        val foundOwner = ownerService.findByCpf(cpf)

        // Then
        Assertions.assertThat(foundOwner).isNotNull
        Assertions.assertThat(foundOwner).isSameAs(owner)
        verify(exactly = 1) { ownerRepository.findByCpf(cpf) }
    }

    @Test
    fun `should find owner by ID`() {
        // Given
        val id = owner.id!!

        every { ownerRepository.findById(id) } returns Optional.of(owner)

        // When
        val foundOwner = ownerService.findById(id)

        // Then
        Assertions.assertThat(foundOwner).isNotNull
        Assertions.assertThat(foundOwner).isSameAs(owner)
        verify(exactly = 1) { ownerRepository.findById(id) }
    }

    @Test
    fun `should delete owner by CPF`() {
        // Given
        val cpf = owner.cpf

        every { ownerRepository.findByCpf(cpf) } returns owner
        every { ownerRepository.delete(owner) } just runs

        // When
        val deletedOwner = ownerService.deleteByCpf(cpf)

        // Then
        Assertions.assertThat(deletedOwner).isNotNull
        Assertions.assertThat(deletedOwner).isSameAs(owner)
        verify(exactly = 1) { ownerRepository.findByCpf(cpf) }
        verify(exactly = 1) { ownerRepository.delete(owner) }
    }

    @Test
    fun `should find owner by address ID`() {
        val addressId1 = owner.addresses.first().id!!
        val addressId2 = owner.addresses[1].id!!

        every { ownerRepository.findByAddressId(addressId1) } returns owner
        every { ownerRepository.findByAddressId(addressId2) } returns owner

        // When
        val foundOwner1 = ownerService.findByAddressId(addressId1)
        val foundOwner2 = ownerService.findByAddressId(addressId2)

        // Then
        Assertions.assertThat(foundOwner1).isNotNull
        Assertions.assertThat(foundOwner2).isNotNull
        Assertions.assertThat(foundOwner1).isSameAs(owner)
        Assertions.assertThat(foundOwner2).isSameAs(owner)
        verify(exactly = 1) { ownerRepository.findByAddressId(addressId1) }
        verify(exactly = 1) { ownerRepository.findByAddressId(addressId2) }
    }

}
