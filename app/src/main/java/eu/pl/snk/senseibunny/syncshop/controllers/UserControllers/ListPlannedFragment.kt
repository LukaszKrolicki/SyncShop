package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.w3c.dom.Text


class ListPlannedFragment : Fragment() {
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_planned, container, false)

        fab = view.findViewById(R.id.fab)

        fab.setOnClickListener {
            // Inflate the popup_add_product layout
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.popup_add_product, null)

            // Create a new PopupWindow instance
            val width = LinearLayout.LayoutParams.MATCH_PARENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val popupWindow = PopupWindow(popupView, width, height, true)

            // Show the PopupWindow
            popupWindow.showAtLocation(fab, Gravity.CENTER, 0, 0)
            // Get a reference to the close button in the popup layout
            val closeButton = popupView.findViewById<Button>(R.id.closeBtn)
            val addButton = popupView.findViewById<Button>(R.id.addBtn)
            // Set an OnClickListener on the close button
            closeButton.setOnClickListener {
                // Dismiss the popup when the close button is clicked
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {
                val name = popupView.findViewById<EditText>(R.id.pName_et).text.toString()
                val price = popupView.findViewById<EditText>(R.id.price_et).text.toString()
                val quantity = popupView.findViewById<EditText>(R.id.quantity_et).text.toString()
                val shop=popupView.findViewById<EditText>(R.id.shop_et).text.toString()
                val note = popupView.findViewById<EditText>(R.id.note_et).text.toString()
                val idList = Model.getInstanceWC().currentList.idListy
                val idClient = Model.getInstanceWC().client.idKlienta
                val username= Model.getInstanceWC().client.username
                val status = "dodane"

                if (name.isNotEmpty()){
                    runBlocking {
                        withContext(Dispatchers.IO) {
                            Model.getInstanceWC().addProductM(idList, idClient,username,name,price,quantity,note,shop,status)
                        }
                    }
                    popupView.findViewById<EditText>(R.id.pName_et).text.clear()
                    popupView.findViewById<EditText>(R.id.price_et).text.clear()
                    popupView.findViewById<EditText>(R.id.quantity_et).text.clear()
                    popupView.findViewById<EditText>(R.id.shop_et).text.clear()
                    popupView.findViewById<EditText>(R.id.note_et).text.clear()
                    popupWindow.dismiss()
                }
                else{
                    popupView.findViewById<TextView>(R.id.error).text="Product name cannot be empty"
                }


            }
        }

        return view
    }
}