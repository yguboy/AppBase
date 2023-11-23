package br.edu.up.app.ui.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.app.data.Cliente
import br.edu.up.app.data.ClienteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel
@Inject constructor(private val repository: ClienteRepository) : ViewModel() {

    var cliente: Cliente = Cliente()

    private var _clientes = MutableStateFlow(listOf<Cliente>())
    val clientes: Flow<List<Cliente>> = _clientes

    init {
        viewModelScope.launch {
            repository.clientes.collect { clientes ->
                _clientes.value = clientes
            }
        }
    }

    fun novo() {
        this.cliente = Cliente()
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(cliente)
    }

    fun editar(itemCliente: Cliente) = viewModelScope.launch {
        repository.editar(cliente)
    }

    fun excluir(itemCliente: Cliente) = viewModelScope.launch {
        val clienteParaExcluir = cliente
        novo()
        repository.excluir(clienteParaExcluir)
    }
}
