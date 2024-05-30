package com.example.hilt_mvvm_retrofit.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hilt_mvvm_retrofit.databinding.ActivityMainBinding
import com.example.hilt_mvvm_retrofit.models.Product
import com.example.hilt_mvvm_retrofit.utils.interfaces.ProductListener
import com.example.hilt_mvvm_retrofit.view.activity.adapters.ProductAdapter
import com.example.hilt_mvvm_retrofit.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class] }
    private lateinit var binding:ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setObserver()


    }

    private fun setAdapter() {
        productAdapter = ProductAdapter(object : ProductListener{
            override fun onProductClick(product: Product) {
                println(product)
            }

        })
        binding.productRv.adapter = productAdapter
    }

    private fun setObserver() {
        viewModel.productsLiveData.observe(this){
                productAdapter.setData(it)

        }
    }
}