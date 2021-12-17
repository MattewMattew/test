package com.example.training

//data class Card(var name: String,var amount: String)
class Card {
    var name: String? = null
    var url: String? = null
    var amount: String? = null
    constructor(): super()
    constructor(NameCard: String, Amount: String, Url: String): super(){
        this.name = NameCard
        this.amount = Amount
        this.url = Url
    }
}