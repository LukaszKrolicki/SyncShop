package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentAddFriendBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentNotificationsBinding
import eu.pl.snk.senseibunny.syncshop.models.Client
import eu.pl.snk.senseibunny.syncshop.models.Invitation
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private  var invitations: ArrayList<Invitation> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)


        runBlocking {
            withContext(Dispatchers.IO) {
                try{

                    invitations= Model.getInstanceWC().getInvitations(Model.getInstanceWC().client.idKlienta)
                }
                catch (
                    e: Exception
                ){
                    println(e.message)
                }
                activity?.runOnUiThread {
//                    val adapter= SearchFriendAdapter(users,requireActivity())
//                    binding.recyclerView.adapter=adapter
                }


            }

        }


        return binding.root
    }

}