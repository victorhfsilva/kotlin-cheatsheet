package com.example.AdressApi.Service

import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.AddressRepository
import com.example.AdressApi.Repositories.OwnerRepository
import com.example.AdressApi.Service.Implementation.AddressService
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
class AddressServiceTest {
    @MockK
    lateinit var ownerRepository: OwnerRepository

    @MockK
    lateinit var addressRepository: AddressRepository

    @InjectMockKs
    lateinit var addressService: AddressService

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
        uf: String = "State 1"
    ) = Address (
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
        addresses = listOf(address1, address2)
        owner = buildOwner(addresses = addresses)
    }

    @Test
    fun `should save address`() {
        // Mock the repository behavior
        every { addressRepository.save(any()) } returns address1

        // When
        val savedAddress = addressService.save(address1)

        // Then
        Assertions.assertThat(savedAddress).isNotNull
        Assertions.assertThat(savedAddress).isSameAs(address1)

        // Ensure that the save method of the addressRepository is called exactly once with
        // the address object as an argument.
        verify(exactly = 1) { addressRepository.save(address1) }
    }

    @Test
    fun `should find address by ID`() {
        // Given
        val id = address1.id!!

        every { addressRepository.findById(id) } returns Optional.of(address1)

        // When
        val foundAddress = addressService.findById(id)

        // Then
        Assertions.assertThat(foundAddress).isNotNull
        Assertions.assertThat(foundAddress).isSameAs(address1)
        verify(exactly = 1) { addressRepository.findById(id) }
    }

    @Test
    fun `should find addresses by CEP`() {
        // Given
        val cep = address1.cep

        every { addressRepository.findByCep(cep) } returns listOf(address1)

        // When
        val foundAddresses = addressService.findByCep(cep)

        // Then
        Assertions.assertThat(foundAddresses).isNotEmpty
        Assertions.assertThat(foundAddresses).contains(address1)
        verify(exactly = 1) { addressRepository.findByCep(cep) }
    }

    @Test
    fun `should find addresses by logradouro`() {
        // Given
        val logradouro = address1.logradouro

        every { addressRepository.findByLogradouro(logradouro) } returns listOf(address1)

        // When
        val foundAddresses = addressService.findByLogradouro(logradouro)

        // Then
        Assertions.assertThat(foundAddresses).isNotEmpty
        Assertions.assertThat(foundAddresses).contains(address1)
        verify(exactly = 1) { addressRepository.findByLogradouro(logradouro) }
    }

    @Test
    fun `should find addresses by bairro`() {
        // Given
        val bairro = address1.bairro

        every { addressRepository.findByBairro(bairro) } returns listOf(address1)

        // When
        val foundAddresses = addressService.findByBairro(bairro)

        // Then
        Assertions.assertThat(foundAddresses).isNotEmpty
        Assertions.assertThat(foundAddresses).contains(address1)
        verify(exactly = 1) { addressRepository.findByBairro(bairro) }
    }

    @Test
    fun `should find addresses by localidade`() {
        // Given
        val localidade = address1.localidade

        every { addressRepository.findByLocalidade(localidade) } returns listOf(address1)

        // When
        val foundAddresses = addressService.findByLocalidade(localidade)

        // Then
        Assertions.assertThat(foundAddresses).isNotEmpty
        Assertions.assertThat(foundAddresses).contains(address1)
        Assertions.assertThat(foundAddresses).contains(address2)
        verify(exactly = 1) { addressRepository.findByLocalidade(localidade) }
    }

    @Test
    fun `should delete address by ID`() {
        // Given
        val id = address1.id!!

        every { addressRepository.findById(id) } returns Optional.of(address1)
        every { addressRepository.deleteById(id) } just runs

        // When
        val deletedAddress = addressService.deleteById(id)

        // Then
        Assertions.assertThat(deletedAddress).isNotNull
        Assertions.assertThat(deletedAddress).isSameAs(address1)
        verify(exactly = 1) { addressRepository.findById(id) }
        verify(exactly = 1) { addressRepository.deleteById(id) }
    }

    @Test
    fun `should find addresses by owner ID`() {
        // Given
        val ownerId = owner.id!!

        every { addressRepository.findByOwner(ownerId) } returns listOf(address1, address2)

        // When
        val foundAddresses = addressService.findByOwner(ownerId)

        // Then
        Assertions.assertThat(foundAddresses).isNotEmpty
        Assertions.assertThat(foundAddresses).contains(address1, address2)
        verify(exactly = 1) { addressRepository.findByOwner(ownerId) }
    }
}