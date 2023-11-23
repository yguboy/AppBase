package br.edu.up.app.ui.cliente

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import br.edu.up.app.data.Cliente
import br.edu.up.app.databinding.FragmentClienteBinding

class ClientesAdapter(
    private val viewModel: ClienteViewModel
) : RecyclerView.Adapter<ClientesAdapter.ViewHolder>() {

    var clientes: List<Cliente> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentClienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemCliente = clientes[position]

        holder.txtNome.text = itemCliente.nome
        holder.txtDescricao.text = itemCliente.descricao
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(itemCliente)
            val action = ClienteFragmentDirections.actionClienteFragmentToClienteEditFragment()
            view.findNavController().navigate(action)
        }

        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("Tem certeza que deseja excluir?")
                .setPositiveButton("Confirmar") { _, _ ->
                    viewModel.excluir(itemCliente)
                }
                .setNegativeButton("CANCELAR") { _, _ ->
                }
                .create()
                .show()
            true
        }
    }

    override fun getItemCount(): Int = clientes.size

    inner class ViewHolder(binding: FragmentClienteBinding) : RecyclerView.ViewHolder(binding.root) {
        val txtNome: TextView = binding.txtNome
        val txtDescricao: TextView = binding.txtDescricao
    }
}
