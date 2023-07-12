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

    private lateinit var address1: Address
    private lateinit var address2: Address

    private lateinit var addresses: List<Address>

    @BeforeEach
    fun setup() {
        addressRepository.deleteAll()
        ownerRepository.deleteAll()

        address1 = testEntityManager.persist(buildAddress())
        address2 = testEntityManager.persist(buildAddress(
            cep = "55004072",
            logradouro = "Street B",
            numero = 25,
            bairro = "Neighborhood B",
            localidade = "City 1",
            uf = "State 1"
        ))

        addresses = listOf(address1, address2)

        owner1 = testEntityManager.persist(buildOwner(addresses = addresses)).also {
            it.addresses.forEach { address: Address -> address.owner = it }
        }

    }

    private fun buildOwner(
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

    private fun buildAddress (
    cep: String = "64608435",
    logradouro: String = "Street A",
    numero: Int = 14,
    bairro: String = "Neighborhood A",
    localidade: String = "City 1",
    uf: String = "State 1") = Address (
        cep = cep,
        logradouro = logradouro,
        numero = numero,
        bairro = bairro,
        localidade = localidade,
        uf = uf
    )

    @Test
    fun `should find owner by CPF`() {
        // Given
        val cpf1 = owner1.cpf
        val cpf2 = "27127772550"


        // When
        val fakeOwner1: Owner? = ownerRepository.findByCpf(cpf1)
        val fakeOwner2: Owner? = ownerRepository.findByCpf(cpf2)

        // Then
        Assertions.assertThat(fakeOwner1).isNotNull
        Assertions.assertThat(fakeOwner2).isNull()
        Assertions.assertThat(fakeOwner1).isSameAs(owner1)
        Assertions.assertThat(fakeOwner2).isNotSameAs(owner1)
    }

    @Test
    fun `should find owner by address id`() {
        // When
        val fakeOwner1: Owner? = ownerRepository.findByAddressId(address1.id!!)
        val fakeOwner2: Owner? = ownerRepository.findByAddressId(address1.id!!+1)

        // Then
        Assertions.assertThat(fakeOwner1).isNotNull
        Assertions.assertThat(fakeOwner2).isNull()
        Assertions.assertThat(fakeOwner1).isSameAs(owner1)
        Assertions.assertThat(fakeOwner2).isNotSameAs(owner1)
    }

}