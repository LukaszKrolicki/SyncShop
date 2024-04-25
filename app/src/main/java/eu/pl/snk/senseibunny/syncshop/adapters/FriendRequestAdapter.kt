package eu.pl.snk.senseibunny.syncshop.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.databinding.CellFriendNotifyBinding
import eu.pl.snk.senseibunny.syncshop.models.Invitation
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FriendRequestAdapter(private val clientList: ArrayList<Invitation>, private val activity: Activity) : RecyclerView.Adapter<FriendRequestAdapter.FriendRequstViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequstViewHolder {
        val view = CellFriendNotifyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendRequstViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendRequstViewHolder, position: Int) {
        val friend = clientList[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    inner class FriendRequstViewHolder(private val itemBinding: CellFriendNotifyBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(client: Invitation) {
            itemBinding.confirmButton.setOnClickListener{

                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().updateInvitationM(client.idZapraszajacego, client.idZaproszonego,"accepted")
                    }
                }

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clientList.removeAt(position)
                    notifyItemRemoved(position)
                }

                activity.runOnUiThread {
                    Toast.makeText(activity.applicationContext, "Friend request accepted :)", Toast.LENGTH_SHORT).show()
                }
            }

            itemBinding.delete.setOnClickListener{
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().updateInvitationM(client.idZapraszajacego, client.idZaproszonego,"rejected")
                    }
                }

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clientList.removeAt(position)
                    notifyItemRemoved(position)

                }
                activity.runOnUiThread {
                    Toast.makeText(activity.applicationContext, "Friend request deleted :(", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}

