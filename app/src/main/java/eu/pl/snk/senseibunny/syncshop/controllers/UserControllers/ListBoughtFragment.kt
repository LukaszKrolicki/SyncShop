package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.adapters.BoughtProductAdapter
import eu.pl.snk.senseibunny.syncshop.adapters.ReservedProductsAdapter
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentListBoughtBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentReservedListBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class ListBoughtFragment : Fragment() {

    private lateinit var binding: FragmentListBoughtBinding

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnableCode: Runnable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBoughtBinding.inflate(inflater, container, false)
        var productBought: ArrayList<Product> = arrayListOf()
        runnableCode = object : Runnable {
            override fun run() {
                runBlocking{
                    withContext(Dispatchers.IO){
                        Model.getInstanceWC().setShoppingProducts(Model.getInstanceWC().currentList.idListy, "bought")
                        productBought = Model.getInstanceWC().currentListBoughtProducts
                    }

                    activity?.runOnUiThread {
                        val adapter= BoughtProductAdapter(productBought,requireActivity())
                        binding.recyclerView.adapter=adapter
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