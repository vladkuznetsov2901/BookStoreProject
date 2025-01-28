package com.example.bookstoreproject.presentation.views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstoreproject.R
import com.example.bookstoreproject.databinding.FragmentCartBinding
import com.example.bookstoreproject.databinding.FragmentHomeBinding
import com.example.bookstoreproject.presentation.adapters.ProductsAdapter
import com.example.bookstoreproject.presentation.viewmodels.MainViewModel
import com.example.bookstoreproject.presentation.viewmodels.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var homeViewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { homeViewModelFactory }

    private lateinit var productsAdapter: ProductsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter = ProductsAdapter(
            onAddToCartClick = { book ->
                lifecycleScope.launch {
                    viewModel.addNewProduct(book)
                    productsAdapter.notifyDataSetChanged()

                }
            },
            isBookInCart = { bookId ->
                viewModel.isBookInCart(bookId)
            }
        )

        binding.booksRecycler.layoutManager =
            GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false)

        binding.booksRecycler.adapter = productsAdapter

        lifecycleScope.launch {
            viewModel.getAllProductsFromAPI()
            viewModel.allProducts.collect {
                productsAdapter.submitList(it)
            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}