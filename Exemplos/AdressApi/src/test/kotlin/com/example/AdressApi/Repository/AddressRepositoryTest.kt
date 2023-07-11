package com.example.AdressApi.Repository

import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.AddressRepository
import com.example.AdressApi.Repositories.OwnerRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AddressRepositoryTest {

    @Autowired
    lateinit var addressRepository: AddressRepository

    @Autowired
    lateinit var ownerRepository: OwnerRepository

    @Autowired
    lateinit var testEntityManager: TestEntityManager

    private lateinit var owner1: Owner
    private lateinit var owner2: Owner

    private lateinit var address1: Address
    private lateinit var address2: Address

    @BeforeEach
    fun setup() {
        addressRepository.deleteAll()
        ownerRepository.deleteAll()

        address1 = Address(
            cep = "64608435",
            logradouro = "Street B",
            numero = "2",
            bairro = "Neighborhood B",
            localidade = "City 2",
            uf = "State 2")

        address2 = Address(
            cep = "55004072",
            logradouro = "Street A",
            numero = "1",
            bairro = "Neighborhood A",
            localidade = "City 1",
            uf = "State 1")

        owner1 = testEntityManager.persist(buildFirstOwner(addresses = listOf(address1)))
        owner2 = testEntityManager.persist(buildSecondOwner(addresses = listOf(address2)))

        owner1.addresses[0].owner = owner1
        owner2.addresses[0].owner = owner2
    }

    private fun buildFirstOwner(
        firstName: String = "John",
        lastName: String = "Doe",
        cpf: String = "68710381910",
        identidade: String = "ID123",
        addresses: List<Address> = emptyList()
    ) = Owner (
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        identidade = identidade,
        addresses = addresses
    )

    private fun buildSecondOwner(
        firstName: String = "Janice",
        lastName: String = "Crystal",
        cpf: String = "27127772550",
        identidade: String = "ID321",
        addresses: List<Address> = emptyList()
    ) = Owner (
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        identidade = identidade,
        addresses = addresses
    )

    @Test
    fun `should find addresses by CEP`() {
        // Given
        val cep1 = "64608435"
        val cep2 = "55004072"

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByCep(cep1)
        val fakeAddresses2: List<Address> = addressRepository.findByCep(cep2)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isNotEmpty
        Assertions.assertThat(fakeAddresses1[0].owner).isSameAs(owner1)
        Assertions.assertThat(fakeAddresses2[0].owner).isSameAs(owner2)
    }

    @Test
    fun `should find addresses by logradouro`() {
        // Given
        val logradouro1 = "Street B"
        val logradouro2 = "Street A"

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByLogradouro(logradouro1)
        val fakeAddresses2: List<Address> = addressRepository.findByLogradouro(logradouro2)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isNotEmpty
        Assertions.assertThat(fakeAddresses1[0].owner).isSameAs(owner1)
        Assertions.assertThat(fakeAddresses2[0].owner).isSameAs(owner2)
    }

    @Test
    fun `should find addresses by bairro`() {
        // Given
        val bairro1 = "Neighborhood B"
        val bairro2 = "Neighborhood C"

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByBairro(bairro1)
        val fakeAddresses2: List<Address> = addressRepository.findByBairro(bairro2)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isEmpty()
        Assertions.assertThat(fakeAddresses1[0].owner).isSameAs(owner1)
    }

    @Test
    fun `should find addresses by localidade`() {
        // Given
        val localidade1 = "City 2"
        val localidade2 = "City 3"

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByLocalidade(localidade1)
        val fakeAddresses2: List<Address> = addressRepository.findByLocalidade(localidade2)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isEmpty()
        Assertions.assertThat(fakeAddresses1[0].owner).isSameAs(owner1)
    }

    @Test
    fun `should find addresses by owner`() {
        // Given
        val ownerId1 = owner1.id!!
        val ownerId2 = owner2.id!!

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByOwner(ownerId1)
        val fakeAddresses2: List<Address> = addressRepository.findByOwner(ownerId2)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isNotEmpty
        Assertions.assertThat(fakeAddresses1[0].owner).isSameAs(owner1)
        Assertions.assertThat(fakeAddresses2[0].owner).isSameAs(owner2)
    }
}