package eu.pl.snk.senseibunny.syncshop.adapters

import eu.pl.snk.senseibunny.syncshop.databinding.CellProductBoughtBinding


import eu.pl.snk.senseibunny.syncshop.databinding.CellProductReservedBinding


import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class BoughtProductAdapter(private val productList: ArrayList<Product>, private val activity: Activity) : RecyclerView.Adapter<BoughtProductAdapter.BoughtProductViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoughtProductViewHolder {
        val view =
            CellProductBoughtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoughtProductViewHolder(view)
    }

    override fun onBindViewHolder(holder:BoughtProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class BoughtProductViewHolder(private val itemBinding: CellProductBoughtBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product) {
            itemBinding.nameTextView.setText("#" + product.nazwa)



        }

    }
}