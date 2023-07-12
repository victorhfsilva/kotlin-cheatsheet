package com.example.AdressApi.Controller

import com.example.AdressApi.DTOs.OwnerDTO
import com.example.AdressApi.Models.Address
import com.example.AdressApi.Models.Owner
import com.example.AdressApi.Repositories.OwnerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
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

@SpringBootTest
@ExtendWith(MockKExtension::class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class OwnerControllerTest {

    @MockK
    private lateinit var ownerRepository: OwnerRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    lateinit var owner: Owner
    lateinit var address1: Address
    lateinit var address2: Address
    lateinit var addresses: List<Address>

    companion object {
        const val URL: String = "/owner"
    }

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

    private fun buildOwnerDTO(
        firstName: String = "John",
        lastName: String = "Doe",
        cpf: String = "68710381910",
        identidade: String = "ID123"
    ): OwnerDTO = OwnerDTO(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        identidade = identidade
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
    fun setup() {
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
    fun `should save owner and return 201 status`() {
        // Given
        val ownerDTO = buildOwnerDTO()
        val valueAsString = objectMapper.writeValueAsString(ownerDTO)

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find owner by CPF and return 200 status`() {
        // Given
        val cpf = owner.cpf

        every { ownerRepository.findByCpf(cpf) } returns owner

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/cpf/{cpf}", cpf)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find owner by invalid CPF and return 404 status`() {
        // Given
        val cpf = "12345678900"
        every { ownerRepository.findByCpf(cpf) } returns null

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/cpf/{cpf}", cpf)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find owner by address ID and return 200 status`() {
        // Given
        val addressId = owner.addresses.first().id!!
        every { ownerRepository.findByAddressId(addressId) } returns owner

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/address/{addressId}", addressId)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find owner by invalid address ID and return 404 status`() {
        // Given
        val addressId = 1L
        every { ownerRepository.findByAddressId(addressId) } returns null

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/address/{addressId}", addressId)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should update owner by CPF and return 200 status`() {
        // Given
        val cpf = owner.cpf
        val existingOwner = owner
        val updatedOwnerDTO = buildOwnerDTO()
        val valueAsString = objectMapper.writeValueAsString(updatedOwnerDTO)
        every { ownerRepository.findByCpf(cpf) } returns existingOwner
        every { ownerRepository.save(any()) } returns existingOwner

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/cpf/{cpf}", cpf)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
                .param("cpf", cpf)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not update owner with invalid CPF and return 404 status`() {
        // Given
        val cpf = "12345678900"
        val updatedOwnerDTO = buildOwnerDTO()
        val valueAsString = objectMapper.writeValueAsString(updatedOwnerDTO)
        every { ownerRepository.findByCpf(cpf) } returns null

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/cpf/{cpf}", cpf)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
                .param("cpf", cpf)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should delete owner by CPF and return 204 status`() {
        // Given
        val cpf = owner.cpf
        every { ownerRepository.findByCpf(cpf) } returns owner

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/cpf/{cpf}", cpf)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not delete owner with invalid CPF and return 404 status`() {
        // Given
        val cpf = "12345678900"
        every { ownerRepository.findByCpf(cpf) } returns null

        // When
        // Then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/cpf/{cpf}", cpf)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andDo(MockMvcResultHandlers.print())
    }
}
