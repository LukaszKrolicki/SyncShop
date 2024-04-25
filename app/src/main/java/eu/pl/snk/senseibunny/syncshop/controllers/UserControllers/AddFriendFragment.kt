package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import SearchFriendAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import eu.pl.snk.senseibunny.syncshop.databinding.FragmentAddFriendBinding
import eu.pl.snk.senseibunny.syncshop.models.Client
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class AddFriendFragment : Fragment() {

    private lateinit var binding: FragmentAddFriendBinding
    private  var users: ArrayList<Client> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentAddFriendBinding.inflate(inflater, container, false)
        Toast.makeText(context, "You've already sent an invitation to that person", Toast.LENGTH_SHORT).show()

        binding.searchButton.setOnClickListener {
            runBlocking {
                withContext(Dispatchers.IO) {
                    try{
                        val searchUsername = binding.searchBar.text.toString()
                        users=Model.getInstanceWC().searchUserM(searchUsername)
                        println(users.get(0));
                    }
                    catch (
                        e: Exception
                    ){
                        println(e.message)
                    }
                    activity?.runOnUiThread {
                        val adapter= SearchFriendAdapter(users,requireActivity())
                        binding.recyclerView.adapter=adapter
                    }


                }

            }
        }


        return binding.root
    }
}