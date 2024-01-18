package model

import java.io.Serializable
import java.util.Date

class Piece(pieceId: String, name: String, description: String, authorId : String, museumId: String, categoryId: String, audio_url: String, foto_url: String, engDescription: String):
    Serializable {
    var pieceId = pieceId
    var name = name
    var description = description
    var authorId = authorId
    var museumId = museumId
    var categoryId = categoryId
    var categoryName = ""
    var audio_url = audio_url
    var foto_url = foto_url
    var engDescription = engDescription
}