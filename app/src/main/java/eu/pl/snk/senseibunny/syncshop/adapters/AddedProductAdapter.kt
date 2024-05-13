package eu.pl.snk.senseibunny.syncshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.databinding.CellFriendAddtolistBinding
import eu.pl.snk.senseibunny.syncshop.databinding.CellProductBoughtBinding
import eu.pl.snk.senseibunny.syncshop.databinding.CellProductPlannedBinding
import eu.pl.snk.senseibunny.syncshop.models.Client
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AddedProductAdapter(private val productList: ArrayList<Product>) : RecyclerView.Adapter<AddedProductAdapter.AddedProductViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddedProductViewHolder {
        val view =
            CellProductPlannedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddedProductViewHolder(view)
    }

    override fun onBindViewHolder(holder:AddedProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class AddedProductViewHolder(private val itemBinding: CellProductPlannedBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product) {
            itemBinding.nameTextView.setText("#" + product.nazwa)

            itemBinding.reserve.setOnClickListener{
                runBlocking {
                    withContext(Dispatchers.IO){
                        Model.getInstanceWC().updateProductM(product.idListy,product.idProduktu,Model.getInstanceWC().client.username, "reserved")

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
                    }

                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        productList.removeAt(position)
                        notifyItemRemoved(position)

                    }
                }
            }
        }

    }
}