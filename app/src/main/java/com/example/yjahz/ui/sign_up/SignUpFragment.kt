package com.example.yjahz.ui.sign_up

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.yjahz.databinding.FragmentSignUpBinding
import com.example.yjahz.model.pojo.InputStatus
import com.example.yjahz.model.pojo.InputStatus.*
import com.example.yjahz.model.pojo.Status
import com.example.yjahz.model.pojo.Status.*
import com.example.yjahz.ui.log_in.LogInFragmentDirections

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        viewModel.sharedPreferences =
            this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)


        viewModel.inputStatus.observe(viewLifecycleOwner) {
            when (it) {
                NAME -> binding.editTextTextPersonName.error = "not valid name"
                EMAIL -> binding.editTextTextEmailAddress.error = "not valid email"
                PHONE -> binding.editTextPhone.error = "not valid phone"
                PASSWORD -> binding.editTextTextPassword.error = "not valid password"
                CONFIRM_PASSWORD -> binding.editTextTextConfirmPassword.error =
                    "not the same as password"
                null -> {}
            }
        }

        viewModel.signUpStatus.observe(viewLifecycleOwner) {
            when (it) {
                LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.signUpButton.isEnabled = false
                }
                ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.signUpButton.isEnabled = true
                }
                DONE -> {
                    navigateToHome(viewModel, view)
                }
                null -> {
                    binding.progressBar.visibility = View.GONE
                    binding.signUpButton.isEnabled = true
                }
            }
        }

        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        binding.signUpButton.setOnClickListener {
            viewModel.signUp(
                name = binding.editTextTextPersonName.text.toString(),
                email = binding.editTextTextEmailAddress.text.toString(),
                password = binding.editTextTextPassword.text.toString(),
                confirmPassword = binding.editTextTextConfirmPassword.text.toString(),
                phone = binding.editTextPhone.text.toString()
            )
        }
        binding.logInTextView.setOnClickListener {
            view.findNavController()
                .navigate(
                    SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
                )
        }

    }

    private fun navigateToHome(
        viewModel: SignUpViewModel,
        view: View
    ) {
        val name = viewModel.user.name ?: "no name"
        var address = "null"
        if (viewModel.user.addresses.size > 0) {
            address = viewModel.user.addresses[0].address ?: "null"
        }

        view.findNavController()
            .navigate(
                SignUpFragmentDirections.actionSignUpFragmentToHomeFragment(
                    name,
                    address
                )
            )
    }

}
