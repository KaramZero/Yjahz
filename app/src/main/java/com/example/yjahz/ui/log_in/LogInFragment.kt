package com.example.yjahz.ui.log_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.yjahz.databinding.FragmentLogInBinding
import com.example.yjahz.model.Status.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[LogInViewModel::class.java]


        binding.logInButton.setOnClickListener {
            viewModel.logIn(
                binding.editTextTextEmailAddress.text.toString(),
                binding.editTextTextPassword.text.toString()
            )

        }


        binding.signUpText.setOnClickListener {
            navigateToSignUpFragment(view)
        }

        viewModel.logInStatus.observe(viewLifecycleOwner) {
            when (it) {
                LOADING -> {
                    binding.logInButton.isEnabled = false
                    binding.progressBar.visibility = View.VISIBLE
                }
                ERROR -> {
                    binding.editTextTextPassword.error = "wrong"
                    binding.editTextTextEmailAddress.error = "wrong"
                    binding.logInButton.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                }
                DONE -> {

                    //preparing Client Info to be sent to The Home Fragment
                    val name = viewModel.user.name ?: "no name"
                    var address = "null"
                    if (viewModel.user.addresses.size > 0) {
                        address = viewModel.user.addresses[0].address ?: "null"
                    }

                    navigateToHomeFragment(view, name, address)

                }
                else -> {
                    binding.logInButton.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }

    private fun navigateToHomeFragment(
        view: View,
        name: String,
        address: String
    ) {
        view.findNavController()
            .navigate(
                LogInFragmentDirections.actionLogInFragmentToHomeFragment(
                    name,
                    address
                )
            )
    }

    private fun navigateToSignUpFragment(view: View) {
        view.findNavController()
            .navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
    }

}