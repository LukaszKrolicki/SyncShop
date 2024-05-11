package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.adapters.FriendListAdapter
import eu.pl.snk.senseibunny.syncshop.adapters.ShoppingListsListAdapter
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentAddFriendBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentFriendListBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentYourShoppinListBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.ShoppingList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class YourShoppinListFragment : Fragment() {
    private lateinit var binding: FragmentYourShoppinListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYourShoppinListBinding.inflate(inflater, container, false)
        runBlocking{
            withContext(Dispatchers.IO){
                val lists = Model.getInstanceWC().getLists(Model.getInstanceWC().client.idKlienta)
                activity?.runOnUiThread {
                    val adapter= ShoppingListsListAdapter(lists,requireActivity())
                    binding.recyclerView.adapter=adapter
                }
            }
        }
        return binding.root
    }

}