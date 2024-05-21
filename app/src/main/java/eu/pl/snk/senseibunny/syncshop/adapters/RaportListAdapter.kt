package eu.pl.snk.senseibunny.syncshop.adapters

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.databinding.CellReportListBinding
import eu.pl.snk.senseibunny.syncshop.databinding.PopupRaportBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.Raport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RaportListAdapter(private val raportList: ArrayList<Raport>, private val activity: Activity) : RecyclerView.Adapter<RaportListAdapter.RaportListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaportListViewHolder {
        val view = CellReportListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RaportListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RaportListViewHolder, position: Int) {
        val raport = raportList[position]
        holder.bind(raport)
    }

    override fun getItemCount(): Int {
        return raportList.size
    }

    inner class RaportListViewHolder(private val itemBinding: CellReportListBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(raport: Raport) {
            itemBinding.nameTextView.text = raport.username

            itemBinding.otherIcon.setOnClickListener {
                showPopup(raport)
            }
        }

        fun showPopup(raport: Raport){
            val popupBinding: PopupRaportBinding = PopupRaportBinding.inflate(LayoutInflater.from(itemBinding.root.context))
            val popupView: View = popupBinding.root
            val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            // Set the focusable property to true
            popupWindow.isFocusable = true

            // Set an OnTouchListener to consume touch events
            popupView.setOnTouchListener { _, _ -> true }

            // Set the text fields
            popupBinding.nameTextView.setText(raport.username)
            popupBinding.authorEt.setText(raport.opis)

            popupBinding.closeButton.setOnClickListener {
                popupWindow.dismiss()
            }
            popupBinding.deleteButton.setOnClickListener {
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().deleteRaportM(raport.idRaportu)
                    }
                }

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    raportList.removeAt(position)
                    notifyItemRemoved(position)

                }
                activity.runOnUiThread {
                    Toast.makeText(activity.applicationContext, "Raport deleted", Toast.LENGTH_SHORT).show()
                }
            }

            // Show the popup window
            popupWindow.showAtLocation(itemBinding.root, Gravity.CENTER, 0, 0)
        }
    }

}