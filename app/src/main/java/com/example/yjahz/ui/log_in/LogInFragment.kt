package com.example.yjahz.ui.log_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.yjahz.R
import com.example.yjahz.databinding.FragmentLogInBinding
import com.example.yjahz.model.InputStatus.*
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
                    Toast.makeText(
                        context,
                        getString(R.string.wrong_emal_or_password),
                        Toast.LENGTH_LONG
                    ).show()
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

        viewModel.inputStatus.observe(viewLifecycleOwner) {
            when (it) {
                EMAIL -> binding.editTextTextEmailAddress.error = getString(R.string.invalid_email)
                PASSWORD -> binding.editTextTextPassword.error =
                    getString(R.string.invalid_password)
                else -> {}
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