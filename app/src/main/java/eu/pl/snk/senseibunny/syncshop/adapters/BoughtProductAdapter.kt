package eu.pl.snk.senseibunny.syncshop.adapters

import eu.pl.snk.senseibunny.syncshop.databinding.CellProductBoughtBinding


import eu.pl.snk.senseibunny.syncshop.databinding.CellProductReservedBinding


import android.app.Activity
import android.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.databinding.PopupProductInfoBinding
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

            itemBinding.details.setOnClickListener{
                showPopup(product)
            }

        }
        fun showPopup(product: Product){
            val popupBinding: PopupProductInfoBinding = PopupProductInfoBinding.inflate(LayoutInflater.from(itemBinding.root.context))
            val popupView: View = popupBinding.root
            val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            // Set the focusable property to true
            popupWindow.isFocusable = true

            // Set an OnTouchListener to consume touch events
            popupView.setOnTouchListener { _, _ -> true }

            // Set the text fields
            popupBinding.pNameEt.text = product.nazwa
            popupBinding.quantityEt.text = product.ilosc
            popupBinding.shopEt.text = product.sklep
            popupBinding.noteEt.text = product.notatka
            popupBinding.createdEt.text = product.nazwaTworzacego
            popupBinding.reservedEt.text = product.nazwaRezerwujacego
            popupBinding.boughtEt.text = product.nazwaKupujacego

            popupBinding.closeBtn.setOnClickListener {
                popupWindow.dismiss()
            }

            // Show the popup window
            popupWindow.showAtLocation(itemBinding.root, Gravity.CENTER, 0, 0)
        }
        }

}
