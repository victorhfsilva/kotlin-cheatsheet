package com.example.AdressApi.Controller

import com.example.AdressApi.DTOs.AddressDTO
import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.AddressRepository
import com.example.AdressApi.Repositories.OwnerRepository
import com.example.AdressApi.Service.Implementation.AddressService
import com.example.AdressApi.Service.Implementation.OwnerService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@SpringBootTest
@ExtendWith(MockKExtension::class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class AddressControllerTest {

    @MockK
    private lateinit var addressRepository: AddressRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    lateinit var address: Address
    lateinit var owner: Owner
    lateinit var addressDTO: AddressDTO

    companion object {
        const val URL: String = "/address"
    }

    private fun buildAddress(
        id: Long = 1L,
        cep: String = "88138570",
        logradouro: String = "Street A",
        numero: Int = 14,
        bairro: String = "Neighborhood A",
        localidade: String = "City 1",
        uf: String = "State 1",
        owner: Owner? = null
    ) = Address(
        id = id,
        cep = cep,
        logradouro = logradouro,
        numero = numero,
        bairro = bairro,
        localidade = localidade,
        uf = uf,
        owner = owner
    )

    private fun buildAddressDTO(
        cep: String = "88138570",
        logradouro: String = "Street A",
        numero: Int = 14,
        bairro: String = "Neighborhood A",
        complemento: String = "",
        localidade: String = "City 1",
        uf: String = "State 1",
        ownerId: Long? = null
    ): AddressDTO = AddressDTO(
        cep = cep,
        logradouro = logradouro,
        numero = numero,
        bairro = bairro,
        complemento = complemento,
        localidade = localidade,
        uf = uf,
        ownerId = ownerId
    )
    
    private fun buildOwner(
        id: Long = 1L,
        firstName: String = "John",
        lastName: String = "Doe",
        cpf: String = "68710381910",
        identidade: String = "ID123",
        addresses: List<Address> = emptyList()
    ) = Owner(
        id = id,
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        identidade = identidade,
        addresses = addresses
    )

    @BeforeEach
    fun setup() {
        owner = buildOwner()
        address = buildAddress(owner = owner)
        addressDTO = buildAddressDTO(ownerId = owner.id)
    }

    @Test
    fun `should save address and return 201 status`() {
        // Given
        val valueAsString = objectMapper.writeValueAsString(addressDTO)

        every { addressRepository.save(any()) } returns address

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value("64608435"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Street A"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.numero").value(14))
            .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value("Neighborhood A"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.complemento").value(""))
            .andExpect(MockMvcResultMatchers.jsonPath("$.localidade").value("City 1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.uf").value("State 1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.ownerName").value("John Doe"))
            .andDo(MockMvcResultHandlers.print())
    }

/*    @Test
    fun `should find addresses by CEP and return 200 status`() {
        // Given
        val cep = "64608435"

        every { addressRepository.findByCep(cep) } returns listOf(address)

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/cep/{cep}", cep)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].cep").value("64608435"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find addresses by invalid CEP and return 404 status`() {
        // Given
        val cep = "12345678"

        every { addressRepository.findByCep(cep) } returns emptyList()

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/cep/{cep}", cep)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find addresses by logradouro and return 200 status`() {
        // Given
        val logradouro = "Street A"

        every { addressRepository.findByLogradouro(logradouro) } returns listOf(address)

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/logradouro/{logradouro}", logradouro)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].logradouro").value("Street A"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find addresses by invalid logradouro and return 404 status`() {
        // Given
        val logradouro = "Nonexistent Street"

        every { addressRepository.findByLogradouro(logradouro) } returns emptyList()

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/logradouro/{logradouro}", logradouro)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find addresses by bairro and return 200 status`() {
        // Given
        val bairro = "Neighborhood A"

        every { addressRepository.findByBairro(bairro) } returns listOf(address)

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/bairro/{bairro}", bairro)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].bairro").value("Neighborhood A"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find addresses by invalid bairro and return 404 status`() {
        // Given
        val bairro = "Nonexistent Neighborhood"

        every { addressRepository.findByBairro(bairro) } returns emptyList()

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/bairro/{bairro}", bairro)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find addresses by cidade and return 200 status`() {
        // Given
        val cidade = "City 1"

        every { addressRepository.findByLocalidade(cidade) } returns listOf(address)

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/cidade/{cidade}", cidade)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].localidade").value("City 1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].localidade").value("City 1"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find addresses by invalid cidade and return 404 status`() {
        // Given
        val cidade = "Nonexistent City"

        every { addressRepository.findByLocalidade(cidade) } returns emptyList()

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/cidade/{cidade}", cidade)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find addresses by owner ID and return 200 status`() {
        // Given
        val ownerId = 1L

        every { addressRepository.findByOwner(ownerId) } returns listOf(address)

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/owner/{ownerId}", ownerId)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].cep").value("64608435"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].cep").value("55004072"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find addresses by invalid owner ID and return 404 status`() {
        // Given
        val ownerId = 999L

        every { addressRepository.findByOwner(ownerId) } returns emptyList()

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/owner/{ownerId}", ownerId)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should delete address by ID and return 204 status`() {
        // Given
        val id = 1L

        every { addressRepository.deleteById(id) }

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/id/{id}", id)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not delete address with invalid ID and return 404 status`() {
        // Given
        val id = 999L

        every { addressRepository.deleteById(id) }

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/id/{id}", id)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should update address by ID and return 200 status`() {
        // Given
        val id = 1L
        val updatedAddressDTO = buildAddressDTO(ownerId = owner.id)
        val valueAsString = objectMapper.writeValueAsString(updatedAddressDTO)
        val existingAddress = address.copy()

        every { addressRepository.findById(id) } returns Optional.of(existingAddress)
        every { addressRepository.save(any()) } returns existingAddress

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value("64608435"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Street A"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not update address with invalid ID and return 404 status`() {
        // Given
        val id = 999L
        val updatedAddressDTO = buildAddressDTO(ownerId = owner.id)
        val valueAsString = objectMapper.writeValueAsString(updatedAddressDTO)

        every { addressRepository.findById(id) }

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }*/
}
