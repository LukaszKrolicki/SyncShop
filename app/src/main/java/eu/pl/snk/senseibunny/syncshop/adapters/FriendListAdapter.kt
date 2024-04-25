package eu.pl.snk.senseibunny.syncshop.adapters

import eu.pl.snk.senseibunny.syncshop.databinding.CellFriendList2Binding
import eu.pl.snk.senseibunny.syncshop.models.Client
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FriendListAdapter(private val clientList: ArrayList<Client>, private val activity: Activity) : RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListViewHolder {
        val view = CellFriendList2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
        val friend = clientList[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    inner class FriendListViewHolder(private val itemBinding: CellFriendList2Binding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(client: Client) {
            titleTextView.setText("#"+client.username  )

            itemBinding.delete.setOnClickListener{
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().deleteFriendM(client.idKlienta, Model.getInstanceWC().client.idKlienta)
                    }
                }

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clientList.removeAt(position)
                    notifyItemRemoved(position)

                }
                activity.runOnUiThread {
                    Toast.makeText(activity.applicationContext, "Friend deleted :(", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}

