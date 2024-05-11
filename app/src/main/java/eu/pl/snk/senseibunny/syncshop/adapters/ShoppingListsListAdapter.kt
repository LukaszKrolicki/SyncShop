package eu.pl.snk.senseibunny.syncshop.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.databinding.CellShoppingListBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.ShoppingList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ShoppingListsListAdapter(private val shoppingLists: ArrayList<ShoppingList>, private val activity: Activity) : RecyclerView.Adapter<ShoppingListsListAdapter.ShoppingListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val view = CellShoppingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val friend = shoppingLists[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return shoppingLists.size
    }

    inner class ShoppingListViewHolder(private val itemBinding: CellShoppingListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(shoppingList: ShoppingList) {
            titleTextView.setText("#"+shoppingList.nazwa)

            itemBinding.delete.setOnClickListener{
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().deleteList(Model.getInstanceWC().client.idKlienta, shoppingList.idListy)
                    }
                }

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    shoppingLists.removeAt(position)
                    notifyItemRemoved(position)

                }
                activity.runOnUiThread {
                    Toast.makeText(activity.applicationContext, "List deleted :(", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
