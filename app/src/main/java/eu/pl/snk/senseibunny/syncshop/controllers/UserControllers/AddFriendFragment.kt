package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentAddFriendBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class AddFriendFragment : Fragment() {

    private lateinit var binding: FragmentAddFriendBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentAddFriendBinding.inflate(inflater, container, false)

        binding.searchButton.setOnClickListener {
            runBlocking {
                withContext(Dispatchers.IO) {
                    val searchUsername = binding.searchBar.text.toString()
                    println(Model.getInstanceWC().searchUser(searchUsername))
                }
            }
        }
        return binding.root
    }
}