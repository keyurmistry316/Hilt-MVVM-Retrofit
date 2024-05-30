package com.example.hilt_mvvm_retrofit.view.activity.adapters

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.hilt_mvvm_retrofit.databinding.ProductLayoutBinding
import com.example.hilt_mvvm_retrofit.models.Product
import com.example.hilt_mvvm_retrofit.utils.interfaces.ProductListener

class ProductAdapter( val productListener: ProductListener) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var mutableProductList = mutableListOf<Product>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ProductLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mutableProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        (holder as ProductViewHolder).bind(mutableProductList[position])
    }

    fun setData(productList: List<Product>){
        mutableProductList.clear()
        mutableProductList.addAll(productList)
        notifyDataSetChanged()

    }

    inner class ProductViewHolder(private val binding: ProductLayoutBinding):ViewHolder(binding.root) {

        fun bind(product: Product) {

            binding.productTitleTv.text = product.title
            binding.productRating.rating = (product.rating?.rate?.toFloat() ?: 0.0).toFloat()
            Glide.with(binding.root.context)
                .load(product.image)
                .into(binding.productImg)

            binding.root.setOnClickListener {
                    productListener.onProductClick(product)
            }
        }
    }
}