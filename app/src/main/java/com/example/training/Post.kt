package com.example.training

class Post {
    var name: String? = null
    var realname: String? = null
    var team: String? = null
    var firstapperance: String? = null
    var createdby: String? = null
    var publisher: String? = null
    var imageurl: String? = null
    var bioo: String? = null
    constructor(): super() {}
    constructor(Name: String, RealName: String, Team: String,Firstapperance: String, Createdby: String, Publisher: String, Imageurl: String, Bio: String): super() {
        this.name = Name
        this.realname = RealName
        this.team = Team
        this.firstapperance = Firstapperance
        this.createdby = Createdby
        this.publisher = Publisher
        this.imageurl = Imageurl
        this.bioo = Bio
    }
}