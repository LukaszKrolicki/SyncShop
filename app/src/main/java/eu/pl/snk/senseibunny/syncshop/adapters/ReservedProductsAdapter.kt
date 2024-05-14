package eu.pl.snk.senseibunny.syncshop.adapters

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

class ReservedProductsAdapter(private val productList: ArrayList<Product>, private val activity: Activity) : RecyclerView.Adapter<ReservedProductsAdapter.ReservedProductViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservedProductViewHolder {
        val view =
            CellProductReservedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservedProductViewHolder(view)
    }

    override fun onBindViewHolder(holder:ReservedProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ReservedProductViewHolder(private val itemBinding: CellProductReservedBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product) {
            itemBinding.nameTextView.setText("#" + product.nazwa)

            itemBinding.reserve.setOnClickListener{
                runBlocking {
                    withContext(Dispatchers.IO){
                        Model.getInstanceWC().updateProductM(product.idListy,product.idProduktu,"-", "dodane")
                        Model.getInstanceWC().addToCurrentListAddedProducts(product);
                    }


                }

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    productList.removeAt(position)
                    notifyItemRemoved(position)

                }
            }

            itemBinding.success.setOnClickListener{
                runBlocking {
                    withContext(Dispatchers.IO){
                        Model.getInstanceWC().updateProductM(product.idListy,product.idProduktu,Model.getInstanceWC().client.username, "bought")
                        Model.getInstanceWC().addToCurrentListBoughtProducts(product);

                    }

                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        productList.removeAt(position)
                        notifyItemRemoved(position)

                    }
                }
            }

            itemBinding.delete.setOnClickListener{
                runBlocking {
                    withContext(Dispatchers.IO){
                        Model.getInstanceWC().deleteProductM(product.idListy,product.idProduktu)
                    }
                }

                AlertDialog.Builder(activity)
                    .setTitle("Delete Confirmation")
                    .setMessage("Are you sure you want to delete this product? This product will be permanently removed from this list.")
                    .setPositiveButton("Yes") {_,_->
                        runBlocking {
                            withContext(Dispatchers.IO) {
                                Model.getInstanceWC().deleteProductM(product.idProduktu, product.idListy)
                            }
                        }
                        productList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        }

    }
}