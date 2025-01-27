package com.example.bookstoreproject.presentation.views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bookstoreproject.R
import com.example.bookstoreproject.databinding.FragmentAuthBinding
import com.example.bookstoreproject.presentation.viewmodels.MainViewModel
import com.example.bookstoreproject.presentation.viewmodels.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = requireNotNull(_binding)

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var homeViewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { homeViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.createNewUser("admin", "admin123")
            viewModel.createNewUser("test_user", "test123")
        }

        binding.signInButton.setOnClickListener {
            if (binding.editTextLogin.text.isNullOrEmpty() || binding.editTextPassword.text.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            } else {

                lifecycleScope.launch {
                    viewModel.authUser(
                        binding.editTextLogin.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
                    viewModel.isUserAuth.collect {
                        if (it) findNavController().navigate(R.id.action_authFragment_to_homeFragment)

                    }
                }


            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}