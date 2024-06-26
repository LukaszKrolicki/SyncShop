package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.adapters.FriendRequestAdapter
import eu.pl.snk.senseibunny.syncshop.adapters.ShoppingListNotificationAdapter
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentNotificationsBinding
import eu.pl.snk.senseibunny.syncshop.models.Invitation
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.ShoppingInvitation
import eu.pl.snk.senseibunny.syncshop.models.ShoppingList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private  var invitations: ArrayList<Invitation> = ArrayList()

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnableCode: Runnable

    private  var shoppingInvitations: ArrayList<ShoppingInvitation> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)


        runnableCode = object : Runnable {
            override fun run() {
                runBlocking {
                    withContext(Dispatchers.IO) {
                        try{
                            invitations= Model.getInstanceWC().getInvitations(Model.getInstanceWC().client.idKlienta)
                            shoppingInvitations=Model.getInstanceWC().getShoppingInvitations(Model.getInstanceWC().client.idKlienta)
                        }
                        catch (e: Exception){
                            println(e.message)
                        }
                        activity?.runOnUiThread {
                            val adapter= FriendRequestAdapter(invitations,requireActivity())
                            binding.recyclerViewFriends.adapter=adapter

                            val adapter2 = ShoppingListNotificationAdapter(shoppingInvitations, requireActivity())
                            binding.recyclerViewShoppingList.adapter=adapter2
                        }
                    }
                }
                handler.postDelayed(this, 30000)
            }
        }
        handler.post(runnableCode)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnableCode)
    }

}