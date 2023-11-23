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
class ClienteViewModel @Inject constructor(private val repository: ClienteRepository) : ViewModel() {

    private var _clientes = MutableStateFlow<List<Cliente>>(emptyList())
    val clientes: Flow<List<Cliente>> = _clientes

    fun novo() {
        _clientes.value = _clientes.value + Cliente()
    }

    fun salvar(cliente: Cliente) = viewModelScope.launch {
        repository.salvar(cliente)
    }

    fun editar(cliente: Cliente) = viewModelScope.launch {
        repository.editar(cliente)
    }

    fun excluir(cliente: Cliente) = viewModelScope.launch {
        repository.excluir(cliente)
        _clientes.value = _clientes.value - cliente
    }
}
