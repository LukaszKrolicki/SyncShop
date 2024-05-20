package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.adapters.BoughtProductAdapter
import eu.pl.snk.senseibunny.syncshop.adapters.ReservedProductsAdapter
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentReservedListBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ReservedListFragment : Fragment() {
    private lateinit var binding: FragmentReservedListBinding
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnableCode: Runnable
    private lateinit var productReserved: ArrayList<Product>
    private lateinit var adapter: ReservedProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservedListBinding.inflate(inflater, container, false)
        productReserved = ArrayList()
        adapter = ReservedProductsAdapter(productReserved, requireActivity())
        binding.recyclerView.adapter = adapter

        runnableCode = object : Runnable {
            override fun run() {
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().setShoppingProducts(Model.getInstanceWC().currentList.idListy, "reserved")
                        val newProductReserved = Model.getInstanceWC().currentListReservedProducts
                        if (newProductReserved != productReserved) {
                            productReserved.clear()
                            productReserved.addAll(newProductReserved)
                            activity?.runOnUiThread {
                                adapter.notifyDataSetChanged()
                            }
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