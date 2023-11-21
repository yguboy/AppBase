package br.edu.up.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produtos")
data class Produto(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var docId: String,
    var nome: String,
    var descricao: String = String(),
    var preco: Double = 0.0,
    var peso: Int = 0,
    var foto: String = "semfoto.jpg",
    var categoria: Int = 0
) {
    constructor() : this(0,"","","",0.0,0,"semfoto.jpg",0)
}