package com.example.bookstoreproject.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstoreproject.R
import com.example.bookstoreproject.databinding.FragmentCartBinding
import com.example.bookstoreproject.databinding.FragmentHomeBinding
import com.example.bookstoreproject.presentation.adapters.ProductsAdapter
import com.example.bookstoreproject.presentation.adapters.ProductsCartAdapter
import com.example.bookstoreproject.presentation.viewmodels.MainViewModel
import com.example.bookstoreproject.presentation.viewmodels.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = requireNotNull(_binding)

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var homeViewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { homeViewModelFactory }

    private lateinit var productsAdapter: ProductsCartAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter = ProductsCartAdapter { product ->
            lifecycleScope.launch {
                viewModel.removeProductFromCart(product)
            }

        }

        binding.booksRecycler.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        binding.booksRecycler.adapter = productsAdapter

        lifecycleScope.launch {
            viewModel.getAllProductsFromDB()
            viewModel.allProductsCart.collect {
                productsAdapter.submitList(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}