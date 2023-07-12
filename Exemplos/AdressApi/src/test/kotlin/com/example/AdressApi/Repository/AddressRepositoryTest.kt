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
    fun `should find addresses by CEP`() {
        // Given
        val cep1 = address1.cep
        val cep2 = address2.cep
        val cep3 = "89026791"

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByCep(cep1)
        val fakeAddresses2: List<Address> = addressRepository.findByCep(cep2)
        val fakeAddresses3: List<Address> = addressRepository.findByCep(cep3)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isNotEmpty
        Assertions.assertThat(fakeAddresses3).isEmpty()

        Assertions.assertThat(fakeAddresses1.first()).isSameAs(address1)
        Assertions.assertThat(fakeAddresses2.first()).isSameAs(address2)
        Assertions.assertThat(fakeAddresses1.first()).isNotSameAs(fakeAddresses2.first())
    }

    @Test
    fun `should find addresses by logradouro`() {
        // Given
        val logradouro1 = address1.logradouro
        val logradouro2 = address2.logradouro
        val logradouro3 = "Street C"

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByLogradouro(logradouro1)
        val fakeAddresses2: List<Address> = addressRepository.findByLogradouro(logradouro2)
        val fakeAddresses3: List<Address> = addressRepository.findByLogradouro(logradouro3)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isNotEmpty
        Assertions.assertThat(fakeAddresses3).isEmpty()

        Assertions.assertThat(fakeAddresses1.first()).isSameAs(address1)
        Assertions.assertThat(fakeAddresses2.first()).isSameAs(address2)
        Assertions.assertThat(fakeAddresses1.first()).isNotSameAs(fakeAddresses2.first())
    }

    @Test
    fun `should find addresses by bairro`() {
        // Given
        val bairro1 = address1.bairro
        val bairro2 = address2.bairro
        val bairro3 = "Neighborhood C"

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByBairro(bairro1)
        val fakeAddresses2: List<Address> = addressRepository.findByBairro(bairro2)
        val fakeAddresses3: List<Address> = addressRepository.findByBairro(bairro3)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isNotEmpty
        Assertions.assertThat(fakeAddresses3).isEmpty()

        Assertions.assertThat(fakeAddresses1.first()).isSameAs(address1)
        Assertions.assertThat(fakeAddresses2.first()).isSameAs(address2)
        Assertions.assertThat(fakeAddresses1.first()).isNotSameAs(address2)
    }

    @Test
    fun `should find addresses by localidade`() {
        // Given
        val localidade1 = "City 1"
        val localidade2 = "City 2"

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByLocalidade(localidade1)
        val fakeAddresses2: List<Address> = addressRepository.findByLocalidade(localidade2)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isEmpty()

        Assertions.assertThat(fakeAddresses1).contains(address1)
        Assertions.assertThat(fakeAddresses1).contains(address2)
    }

    @Test
    fun `should find addresses by owner`() {
        // Given
        val ownerId1 = owner1.id!!
        val ownerId2 = owner1.id!! + 1

        // When
        val fakeAddresses1: List<Address> = addressRepository.findByOwner(ownerId1)
        val fakeAddresses2: List<Address> = addressRepository.findByOwner(ownerId2)

        // Then
        Assertions.assertThat(fakeAddresses1).isNotEmpty
        Assertions.assertThat(fakeAddresses2).isEmpty()

        Assertions.assertThat(fakeAddresses1).contains(address1)
        Assertions.assertThat(fakeAddresses1).contains(address2)
    }
}