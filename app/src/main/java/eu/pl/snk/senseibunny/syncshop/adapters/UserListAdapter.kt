package eu.pl.snk.senseibunny.syncshop.adapters

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.databinding.CellUserListBinding
import eu.pl.snk.senseibunny.syncshop.databinding.PopupUserEditBinding
import eu.pl.snk.senseibunny.syncshop.models.Client
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class UserListAdapter(private val clientList: ArrayList<Client>, private val activity: Activity) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = CellUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val friend = clientList[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    inner class UserListViewHolder(private val itemBinding: CellUserListBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(client: Client) {
            itemBinding.nameTextView.text = client.imie
            itemBinding.secondNameTextView.text = client.nazwisko
            itemBinding.userId.text = client.username

            itemBinding.detailsIcon.setOnClickListener {
                showPopup(client)
            }
        }

            fun showPopup(client: Client){
                val popupBinding: PopupUserEditBinding = PopupUserEditBinding.inflate(LayoutInflater.from(itemBinding.root.context))
                val popupView: View = popupBinding.root
                val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

                // Set the focusable property to true
                popupWindow.isFocusable = true

                // Set an OnTouchListener to consume touch events
                popupView.setOnTouchListener { _, _ -> true }

                // Set the text fields
                popupBinding.nameEt.setText(client.username)
                popupBinding.periodEt.setText(client.imie)
                popupBinding.topicEt.setText(client.nazwisko)
                popupBinding.authorEt.setText(client.email)

                popupBinding.closeButton.setOnClickListener {
                    popupWindow.dismiss()
                }
                popupBinding.deleteButton.setOnClickListener {
                    runBlocking {
                        withContext(Dispatchers.IO) {
                            Model.getInstanceWC().deleteUserM(client.idKlienta)
                        }
                    }

                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        clientList.removeAt(position)
                        notifyItemRemoved(position)

                    }
                    activity.runOnUiThread {
                        Toast.makeText(activity.applicationContext, "User deleted :(", Toast.LENGTH_SHORT).show()
                    }
                }

                // Show the popup window
                popupWindow.showAtLocation(itemBinding.root, Gravity.CENTER, 0, 0)
            }
        }

}