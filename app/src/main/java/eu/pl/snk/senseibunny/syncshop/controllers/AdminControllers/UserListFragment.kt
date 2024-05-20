package eu.pl.snk.senseibunny.syncshop.controllers.AdminControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.adapters.UserListAdapter
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentUserListBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        runBlocking{
            withContext(Dispatchers.IO){
                val users = Model.getInstanceWC().getUsersL()
                activity?.runOnUiThread {
                    val adapter = UserListAdapter(users,requireActivity())
                    binding.recyclerView.adapter = adapter
                }
            }
        }
        return binding.root
    }

}