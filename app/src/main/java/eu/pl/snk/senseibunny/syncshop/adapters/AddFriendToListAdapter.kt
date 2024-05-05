package eu.pl.snk.senseibunny.syncshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.databinding.CellFriendAddtolistBinding
import eu.pl.snk.senseibunny.syncshop.models.Client


class AddFriendToListAdapter(private val clientList: ArrayList<Client>) : RecyclerView.Adapter<AddFriendToListAdapter.AddFriendToListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendToListViewHolder {
        val view = CellFriendAddtolistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddFriendToListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddFriendToListViewHolder, position: Int) {
        val friend = clientList[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    inner class AddFriendToListViewHolder(private val itemBinding: CellFriendAddtolistBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(client: Client) {
            itemBinding.nameTextView.setText("#"+client.username)

        }

    }
}
