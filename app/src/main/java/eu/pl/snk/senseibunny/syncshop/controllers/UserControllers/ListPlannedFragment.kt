package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.google.android.material.floatingactionbutton.FloatingActionButton
import eu.pl.snk.senseibunny.syncshop.R


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

            // Set an OnClickListener on the close button
            closeButton.setOnClickListener {
                // Dismiss the popup when the close button is clicked
                popupWindow.dismiss()
            }
        }

        return view
    }
}