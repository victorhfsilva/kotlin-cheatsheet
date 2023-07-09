package com.example.AdressApi.Controllers

import com.example.AdressApi.DTOs.AddressDTO
import com.example.AdressApi.Service.Implementation.AddressService
import com.example.AdressApi.Service.Implementation.OwnerService
import com.example.AdressApi.Views.AddressView
import com.example.AdressApi.Views.OwnerView
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/address")
class AddressController (
    private val ownerService: OwnerService,
    private val addressService: AddressService
    ) {

    @PostMapping
    fun saveOwner(@RequestBody @Valid addressDTO: AddressDTO): ResponseEntity<AddressView> {
        val savedAddress = this.addressService.save(addressDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(AddressView(savedAddress))
    }


    @GetMapping("/cep/{cep}")
    fun findByCep(@PathVariable cep: String): ResponseEntity<List<AddressView>> {
        val addresses = this.addressService.findByCep(cep)
        if (addresses.isEmpty()) {
            return ResponseEntity.notFound().build()
        } else {
            val addressViews = addresses.map { AddressView(it) }
            return ResponseEntity.ok(addressViews)
        }
    }

    @GetMapping("/logradouro/{logradouro}")
    fun findByLogradouro(@PathVariable logradouro: String): ResponseEntity<List<AddressView>> {
        val addresses = this.addressService.findByLograudoro(logradouro)
        if (addresses.isEmpty()) {
            return ResponseEntity.notFound().build()
        } else {
            val addressViews = addresses.map { AddressView(it) }
            return ResponseEntity.ok(addressViews)
        }
    }

    @GetMapping("/bairro/{bairro}")
    fun findByBairro(@PathVariable bairro: String): ResponseEntity<List<AddressView>> {
        val addresses = this.addressService.findByBairro(bairro)
        if (addresses.isEmpty()) {
            return ResponseEntity.notFound().build()
        } else {
            val addressViews = addresses.map { AddressView(it) }
            return ResponseEntity.ok(addressViews)
        }
    }

    @GetMapping("/cidade/{cidade}")
    fun findByCidade(@PathVariable cidade: String): ResponseEntity<List<AddressView>> {
        val addresses = this.addressService.findByCidade(cidade)
        if (addresses.isEmpty()) {
            return ResponseEntity.notFound().build()
        } else {
            val addressViews = addresses.map { AddressView(it) }
            return ResponseEntity.ok(addressViews)
        }
    }

    @GetMapping("/owner/{ownerId}")
    fun findByOwner(@PathVariable ownerId: Long): ResponseEntity<List<AddressView>> {
        val addresses = this.addressService.findByOwner(ownerId)
        if (addresses.isEmpty()) {
            return ResponseEntity.notFound().build()
        } else {
            val addressViews = addresses.map { AddressView(it) }
            return ResponseEntity.ok(addressViews)
        }
    }

    @DeleteMapping("/id/{id}")
    fun deleteAddress(@PathVariable id: Long): ResponseEntity<Unit> {
        val deletedAddress = this.addressService.deleteById(id)
        return if (deletedAddress != null) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updateAddress(
        @PathVariable id: Long,
        @RequestBody @Valid addressDTO: AddressDTO
    ): ResponseEntity<AddressView> {
        val existingAddress = addressService.findById(id) ?: return ResponseEntity.notFound().build()

        // Update the existing address with the values from addressDTO
        existingAddress.apply {
            cep = addressDTO.cep
            logradouro = addressDTO.logradouro
            numero = addressDTO.numero
            bairro = addressDTO.bairro
            complemento = addressDTO.complemento
            localidade = addressDTO.localidade
            uf = addressDTO.uf
            owner = addressDTO.owner
        }

        val updatedAddress = addressService.save(existingAddress)
        return ResponseEntity.ok(AddressView(updatedAddress))
    }

}
