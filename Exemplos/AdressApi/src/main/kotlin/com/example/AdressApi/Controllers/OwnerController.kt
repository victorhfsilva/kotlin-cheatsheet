package com.example.AdressApi.Controllers

import com.example.AdressApi.DTOs.OwnerDTO
import com.example.AdressApi.Service.Implementation.OwnerService
import com.example.AdressApi.Views.OwnerView
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/owner")
class OwnerController(
    private val ownerService: OwnerService
){

    @PostMapping
    fun saveOwner(@RequestBody @Valid ownerDTO: OwnerDTO): ResponseEntity<OwnerView> {
        val savedOwner = this.ownerService.save(ownerDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(OwnerView(savedOwner))
    }

    @GetMapping("/cpf/{cpf}")
    fun findByCpf(@PathVariable cpf: String): ResponseEntity<OwnerView> {
        val owner = this.ownerService.findByCpf(cpf)
        if (owner == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(OwnerView(owner))
        }
    }

    @GetMapping("/address/id/{id}")
    fun findByAddress(@PathVariable id: Long): ResponseEntity<OwnerView> {
        val owner = this.ownerService.findByAddress(id)
        if (owner == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(OwnerView(owner))
        }
    }

    @PutMapping("/cpf/{ownerCpf}")
    fun updateOwner (@RequestParam(value = "ownerCpf") cpf: String,
                     @RequestBody @Valid ownerDTO: OwnerDTO): ResponseEntity<OwnerView> {
        val existingOwner = this.ownerService.findByCpf(cpf) ?: return ResponseEntity.notFound().build()
        existingOwner.apply {
            this.firstName = ownerDTO.firstName
            this.lastName = ownerDTO.lasttName
            this.cpf = ownerDTO.cpf
            this.identidade = ownerDTO.identidade
        }
        val updatedOwner = this.ownerService.save(existingOwner)
        return ResponseEntity.status(HttpStatus.OK).body(OwnerView(updatedOwner))
    }

    @DeleteMapping("/cpf/{cpf}")
    fun deleteByCpf(@PathVariable cpf: String): ResponseEntity<Unit> {
        val deletedOwner = ownerService.deleteByCpf(cpf)
        return if (deletedOwner != null) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/address/id/{id}")
    fun deleteByAddress(@PathVariable id: Long): ResponseEntity<Unit> {
        val deletedOwner = ownerService.deleteByAddress(id)
        return if (deletedOwner != null) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}