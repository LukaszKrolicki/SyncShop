package eu.pl.snk.senseibunny.syncshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.databinding.CellFriendListBinding
import eu.pl.snk.senseibunny.syncshop.models.Client

class SearchFriendAdapter(private val clientList: ArrayList<Client>) : RecyclerView.Adapter<SearchFriendAdapter.SearchFriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFriendViewHolder {
        val view = CellFriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchFriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFriendViewHolder, position: Int) {
        val friend = clientList[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    inner class SearchFriendViewHolder(private val itemBinding: CellFriendListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(client: Client) {
            // Bind your data to views here
            titleTextView.text = client.username // Assuming there's a name property in Client
            // You can bind other views similarly
        }
    }
}
