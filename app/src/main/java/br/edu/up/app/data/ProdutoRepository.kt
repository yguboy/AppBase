package br.edu.up.app.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ProdutoRepository {

    val produtos: Flow<List<Produto>>
    suspend fun salvar(produto: Produto)
    suspend fun excluir(produto: Produto)
    suspend fun excluirTodos()

}