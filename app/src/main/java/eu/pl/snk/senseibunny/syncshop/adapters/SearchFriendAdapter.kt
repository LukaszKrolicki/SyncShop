import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.CellFriendListBinding
import eu.pl.snk.senseibunny.syncshop.models.Client
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class SearchFriendAdapter(private val clientList: ArrayList<Client>, private val activity: Activity) : RecyclerView.Adapter<SearchFriendAdapter.FriendRequstViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequstViewHolder {
        val view = CellFriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendRequstViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendRequstViewHolder, position: Int) {
        val friend = clientList[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    inner class FriendRequstViewHolder(private val itemBinding: CellFriendListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(client: Client) {
            // Bind your data to views here
            titleTextView.text = "#"+client.username // Assuming there's a name property in Client
            itemBinding.add.setOnClickListener {
                // Change background
                itemBinding.add.setBackgroundResource(R.drawable.item_approve_background)

                // Change icon
                itemBinding.addIcon.setImageResource(R.drawable.baseline_check_24)
                runBlocking {
                    withContext(Dispatchers.IO) {
                        try {
                            Model.getInstanceWC().createInviteM(Model.getInstanceWC().client.idKlienta, client.idKlienta,Model.getInstanceWC().client.username)
                        } catch (x: IllegalStateException) {
                            x.printStackTrace()
                            activity.runOnUiThread {
                                System.out.println("xd")
                                Toast.makeText(activity.applicationContext, "You've already sent an invitation to that person", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            activity.runOnUiThread {
                                Toast.makeText(itemBinding.root.context, "Error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

        }
    }
}
