package com.example.yjahz.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.yjahz.databinding.FragmentWelcomeBinding
import com.example.yjahz.model.Status.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]

        viewModel.getClient()

        //Observing getting Client Profile status
        viewModel.clientStatus.observe(viewLifecycleOwner) {
            when (it) {
                LOADING -> binding.progressBar.visibility = View.VISIBLE

                ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    navigateToLogIn(view)
                }

                DONE -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    navigateToHome(viewModel, view)
                }

                else -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    navigateToLogIn(view)
                }
            }
        }

    }

    private fun navigateToLogIn(view: View) {
        view.findNavController()
            .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLogInFragment())
    }

    private fun navigateToHome(
        viewModel: WelcomeViewModel,
        view: View
    ) {

        //preparing Client Info to be sent to The Home Fragment
        val name = viewModel.user.name ?: "no name"
        var address = "null"
        if (viewModel.user.addresses.size > 0) {
            address = viewModel.user.addresses[0].address ?: "null"
        }

        view.findNavController()
            .navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment(
                    name,
                    address
                )
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}