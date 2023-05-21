package edu.victor.creditapplication.Model

import jakarta.persistence.Embeddable

@Embeddable
data class Address (
        var zipCode: String = "",
        var street: String = ""
){
}