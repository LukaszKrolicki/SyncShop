package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentAddFriendBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentCreateShoppingListBinding


class CreateShoppingListFragment : Fragment() {

    private lateinit var binding: FragmentCreateShoppingListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateShoppingListBinding.inflate(inflater, container, false)
        return binding.root
    }


}