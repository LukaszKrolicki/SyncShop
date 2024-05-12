package eu.pl.snk.senseibunny.syncshop.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.controllers.UserControllers.ShoppingListActivity
import eu.pl.snk.senseibunny.syncshop.controllers.UserControllers.UserActivity
import eu.pl.snk.senseibunny.syncshop.databinding.CellOwnedShoppingListBinding
import eu.pl.snk.senseibunny.syncshop.databinding.CellShoppingListBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.ShoppingList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ShoppingListsListAdapter(private val shoppingLists: ArrayList<ShoppingList>, private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_ONE = 1
        private const val VIEW_TYPE_TWO = 2
    }
    override fun getItemViewType(position: Int): Int {
        return if (shoppingLists[position].idTworcy.equals(Model.getInstanceWC().client.idKlienta)) {
            VIEW_TYPE_ONE
        } else {
            VIEW_TYPE_TWO
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ONE -> {
                val view = CellOwnedShoppingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ShoppingListOwnedViewHolder(view)
            }
            VIEW_TYPE_TWO -> {
                val view = CellShoppingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
               ShoppingListViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shoppingList = shoppingLists[position]
        when (holder) {
            is ShoppingListOwnedViewHolder -> holder.bind(shoppingList)
            is ShoppingListViewHolder -> holder.bind(shoppingList)
        }
    }
    override fun getItemCount(): Int {
        return shoppingLists.size
    }

    inner class ShoppingListViewHolder(private val itemBinding: CellShoppingListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(shoppingList: ShoppingList) {
            titleTextView.setText("#"+shoppingList.nazwa)

            itemBinding.delete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    AlertDialog.Builder(activity)
                        .setTitle("Delete Confirmation")
                        .setMessage("Are you sure you want to delete this list? You will be permanently removed from this list.")
                        .setPositiveButton("Yes") {_,_->
                            runBlocking {
                                withContext(Dispatchers.IO) {
                                    Model.getInstanceWC().deleteList(Model.getInstanceWC().client.idKlienta, shoppingList.idListy)
                                }
                            }
                            shoppingLists.removeAt(position)
                            notifyItemRemoved(position)
                            activity.runOnUiThread {
                                Toast.makeText(activity.applicationContext, "List deleted :(", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .setNegativeButton("No", null)
                        .show()

                }

                itemBinding.reserve.setOnClickListener{
                    Model.getInstanceWC().currentList=shoppingList
                    // Create an Intent to open ReserveActivity
                    val intent = Intent(activity, ShoppingListActivity::class.java)

                    activity.startActivity(intent)
                }
            }
        }

    }

    inner class ShoppingListOwnedViewHolder(private val itemBinding: CellOwnedShoppingListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(shoppingList: ShoppingList) {
            titleTextView.setText("#"+shoppingList.nazwa)

            itemBinding.delete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    AlertDialog.Builder(activity)
                        .setTitle("Delete Confirmation")
                        .setMessage("Are you sure you want to delete this list? You and your friends will be permanently removed from this list.")
                        .setPositiveButton("Yes") {_,_->
                            runBlocking {
                                withContext(Dispatchers.IO) {
                                    Model.getInstanceWC().deleteList(Model.getInstanceWC().client.idKlienta, shoppingList.idListy)
                                }
                            }
                            shoppingLists.removeAt(position)
                            notifyItemRemoved(position)
                            activity.runOnUiThread {
                                Toast.makeText(activity.applicationContext, "List deleted :(", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .setNegativeButton("No", null)
                        .show()

                }
            }
        }

    }
}
