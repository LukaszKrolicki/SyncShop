package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservedListBinding.inflate(inflater, container, false)
        var productReserved: ArrayList<Product> = arrayListOf()
        runBlocking{
            withContext(Dispatchers.IO){
                productReserved = Model.getInstanceWC().currentListReservedProducts
                activity?.runOnUiThread {
                    val adapter= ReservedProductsAdapter(productReserved,requireActivity())
                    binding.recyclerView.adapter=adapter
                }
            }
        }
        return binding.root
    }


}