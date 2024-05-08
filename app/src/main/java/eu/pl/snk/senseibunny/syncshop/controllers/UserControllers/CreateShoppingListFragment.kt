package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import eu.pl.snk.senseibunny.syncshop.adapters.AddFriendToListAdapter
import eu.pl.snk.senseibunny.syncshop.databinding.CustomListCreatedPopupBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentCreateShoppingListBinding
import eu.pl.snk.senseibunny.syncshop.models.Client
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.Calendar


class CreateShoppingListFragment : Fragment() {

    private lateinit var binding: FragmentCreateShoppingListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateShoppingListBinding.inflate(inflater, container, false)
        // Initialize a new instance of DatePickerDialog for start date
        val calendar = Calendar.getInstance()
        val startDatePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Display the selected date in start date TextView
                binding.startDate.text = "$dayOfMonth/${month + 1}/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        Model.getInstanceWC().clearClientsIdToAddToNewList()

        // Initialize a new instance of DatePickerDialog for end date
        val endDatePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Display the selected date in end date TextView
                binding.endDate.text = "$dayOfMonth/${month + 1}/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Show the DatePickerDialog when the start button is clicked
        binding.startBtn.setOnClickListener {
            activity?.runOnUiThread {
                startDatePickerDialog.show()
            }
        }

        // Show the DatePickerDialog when the end button is clicked
        binding.endBtn.setOnClickListener {
            activity?.runOnUiThread {
                endDatePickerDialog.show()
            }
        }

        var adapter: AddFriendToListAdapter? = null;
        var friends: ArrayList<Client>
        runBlocking{
            withContext(Dispatchers.IO){
                friends = Model.getInstanceWC().getFriendsM(Model.getInstanceWC().client.idKlienta)
                activity?.runOnUiThread {
                    adapter= AddFriendToListAdapter(friends)
                    binding.addFriendsRV.layoutManager = LinearLayoutManager(requireContext()) // Set the layout manager
                    binding.addFriendsRV.adapter=adapter
                }
            }
        }
        binding.CreateListButton.setOnClickListener {
            runBlocking {
                withContext(Dispatchers.IO) {
                    val listName = binding.sListNameEditText.text.toString()
                    val userId = Model.getInstanceWC().client.idKlienta
                    val dataPocz = binding.startDate.text.toString()
                    val dataKon = binding.endDate.text.toString()
                    if(listName.isEmpty()){
                        activity?.runOnUiThread {
                            binding.error.setText("Please fill all fields")
                        }
                    }
                    else{
                        try{
                            val x = Model.getInstanceWC().createList(userId, listName, dataPocz, dataKon)
                            println("sid $x")
                            for(i in Model.getInstanceWC().clientsIdToAddToNewList){
                                Model.getInstanceWC().createListBindM(i, x)
                            }
                            clearFields()
                            Model.getInstanceWC().clearClientsIdToAddToNewList()

                            activity?.runOnUiThread {
                                showPopup()
//                                System.out.println(friends)
//                                adapter?.updateData(friends);
                            }

                        }
                        catch(Exeption: Exception){
                            activity?.runOnUiThread {
                                binding.error.setText("List creation failed")
                            }

                        }
                    }
                }
            }
        }
        return binding.root
    }
    private fun clearFields() {
        binding.sListNameEditText.text.clear()
        binding.startDate.text = ""
        binding.endDate.text = ""
    }
    fun showPopup() {
        val popupBinding: CustomListCreatedPopupBinding = CustomListCreatedPopupBinding.inflate(LayoutInflater.from(binding.root.context))
        val popupView: View = popupBinding.root
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)


        // Set the focusable property to true
        popupWindow.isFocusable = true

        // Set an OnTouchListener to consume touch events
        popupView.setOnTouchListener { _, _ -> true }


        // Show the popup window in the center of the screen
        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }


}