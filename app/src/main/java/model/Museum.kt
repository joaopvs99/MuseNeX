package model

class Museum(museumId: String, name: String, location: String, description: String, contact: Int, categoryId: String, galery: Array<String>) {
    var museumId = museumId
    var name = name
    var location = location
    var description = description
    var contact = contact
    var categoryId = categoryId
    var galery = galery
    var categoryName = ""
}

