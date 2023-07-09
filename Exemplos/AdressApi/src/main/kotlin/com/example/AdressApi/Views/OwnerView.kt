package com.example.AdressApi.Views

import com.example.AdressApi.Models.Owner

data class OwnerView(
    val firstName: String,
    val lastName: String
){
    constructor(owner: Owner): this(
        firstName = owner.firstName,
        lastName = owner.lastName
    )
}
