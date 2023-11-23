package br.edu.up.app.ui.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.edu.up.app.R
import br.edu.up.app.databinding.FragmentClienteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClienteFragment : Fragment() {

    private var _binding: FragmentClienteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClienteViewModel by activityViewModels()

    private val adapter = ClientesAdapter(viewModel)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClienteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupViews()
        observeViewModel()

        return root
    }

    private fun setupViews() {
        binding.btnAddCliente.setOnClickListener {
            viewModel.novo()
        }

        binding.recyclerViewClientes.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.clientes.observe(viewLifecycleOwner) { clientes ->
            adapter.clientes = clientes
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
