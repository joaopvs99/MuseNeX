package model

import java.io.Serializable
import java.util.Date

class User (name: String, surname: String, adress: String):
    Serializable {

    var name = name
    var surname = surname
    var adress = adress
}