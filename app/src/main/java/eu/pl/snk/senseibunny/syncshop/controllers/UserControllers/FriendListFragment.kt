package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.adapters.FriendListAdapter
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentAddFriendBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentCreateShoppingListBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentFriendListBinding
import eu.pl.snk.senseibunny.syncshop.models.Client
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class FriendListFragment : Fragment() {

    private lateinit var binding: FragmentFriendListBinding
    private  var friends: ArrayList<Client> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendListBinding.inflate(inflater, container, false)
        runBlocking{
            withContext(Dispatchers.IO){
                val friends = Model.getInstanceWC().getFriendsM(Model.getInstanceWC().client.idKlienta)
                activity?.runOnUiThread {
                    val adapter= FriendListAdapter(friends,requireActivity())
                    binding.recyclerView.adapter=adapter
                }
            }
        }
        return binding.root
    }
}