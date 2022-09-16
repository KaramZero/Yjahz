package com.example.yjahz.ui.log_in

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.yjahz.databinding.FragmentLogInBinding
import com.example.yjahz.model.Status.*


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        viewModel.sharedPreferences =
            this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)


        binding.logInButton.setOnClickListener {
            viewModel.logIn(
                binding.editTextTextEmailAddress.text.toString(),
                binding.editTextTextPassword.text.toString()
            )

        }
        binding.signUpText.setOnClickListener {
            view.findNavController()
                .navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
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
                    val name = viewModel.user.name ?: "no name"
                    var address = "null"
                    if (viewModel.user.addresses.size > 0) {
                        address = viewModel.user.addresses[0].address ?: "null"
                    }

                    view.findNavController()
                        .navigate(
                            LogInFragmentDirections.actionLogInFragmentToHomeFragment(
                                name,
                                address
                            )
                        )
                }
                else -> {
                    binding.logInButton.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }

}