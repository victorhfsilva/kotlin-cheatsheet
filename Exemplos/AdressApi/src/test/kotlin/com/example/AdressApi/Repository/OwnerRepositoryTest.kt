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
class OwnerRepositoryTest {

    @Autowired
    lateinit var ownerRepository: OwnerRepository

    @Autowired
    lateinit var addressRepository: AddressRepository

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
    fun `should find owner by CPF`() {
        // Given
        val cpf1 = "68710381910"
        val cpf2 = "27127772550"
        owner1.cpf = cpf1
        owner2.cpf = cpf2

        // When
        val fakeOwner1: Owner? = ownerRepository.findByCpf(cpf1)
        val fakeOwner2: Owner? = ownerRepository.findByCpf(cpf2)

        // Then
        Assertions.assertThat(fakeOwner1).isNotNull
        Assertions.assertThat(fakeOwner2).isNotNull
        Assertions.assertThat(fakeOwner1).isSameAs(owner1)
        Assertions.assertThat(fakeOwner2).isSameAs(owner2)
    }

    @Test
    fun `should find owner by address id`() {
        // When
        val fakeOwner1: Owner? = ownerRepository.findByAddressId(address1.id!!)
        val fakeOwner2: Owner? = ownerRepository.findByAddressId(address2.id!!)

        // Then
        Assertions.assertThat(fakeOwner1).isNotNull
        Assertions.assertThat(fakeOwner2).isNotNull
        Assertions.assertThat(fakeOwner1).isSameAs(owner1)
        Assertions.assertThat(fakeOwner2).isSameAs(owner2)
    }

}