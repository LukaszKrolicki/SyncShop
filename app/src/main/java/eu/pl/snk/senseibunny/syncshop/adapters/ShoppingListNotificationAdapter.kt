package eu.pl.snk.senseibunny.syncshop.adapters

import android.app.Activity
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.CellFriendListBinding
import eu.pl.snk.senseibunny.syncshop.databinding.CellShoppinListNotifyBinding
import eu.pl.snk.senseibunny.syncshop.models.Client
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.ShoppingInvitation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ShoppingListNotificationAdapter(private val clientList: ArrayList<ShoppingInvitation>, private val activity: Activity) : RecyclerView.Adapter< ShoppingListNotificationAdapter.ShoppingListNotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListNotificationViewHolder {
        val view = CellShoppinListNotifyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListNotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListNotificationViewHolder, position: Int) {
        val friend = clientList[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    inner class ShoppingListNotificationViewHolder(private val itemBinding: CellShoppinListNotifyBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(shopItem: ShoppingInvitation) {
            var name = Model.getInstanceWC().getFriendName(shopItem.idZapraszajacego)
            var listname = Model.getInstanceWC().getListName(shopItem.idListy)
            titleTextView.text = "Your friend "+name+" added you to his shopping list "+listname ;

            itemBinding.delete.setOnClickListener{
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().deleteListNotificationM(shopItem.idZapraszajacego, shopItem.idListy)
                    }
                }

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clientList.removeAt(position)
                    notifyItemRemoved(position)

                }
            }

        }
    }
}
