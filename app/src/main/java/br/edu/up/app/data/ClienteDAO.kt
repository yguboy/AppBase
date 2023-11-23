package br.edu.up.app.data

import androidx.room.*

@Dao
interface ClienteDao {
    @Query("select * from clientes")
    fun getAllClientes(): List<Cliente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCliente(cliente: Cliente)

    @Delete
    suspend fun deleteCliente(cliente: Cliente)
}
