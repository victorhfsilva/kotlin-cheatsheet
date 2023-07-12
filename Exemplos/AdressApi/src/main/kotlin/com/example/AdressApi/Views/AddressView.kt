package com.example.AdressApi.Views

import com.example.AdressApi.Models.Address

data class AddressView (
    val cep: String,
    val logradouro: String,
    val numero: Int,
    val bairro: String,
    val complemento: String,
    val localidade: String,
    val uf: String,
    val ownerName: String
) {
    constructor(address: Address): this(
        cep = address.cep,
        logradouro = address.logradouro,
        numero = address.numero,
        bairro = address.bairro,
        complemento = address.complemento,
        localidade = address.localidade,
        uf = address.uf,
        ownerName = "${address.owner?.firstName} ${address.owner?.lastName}"
    )
}