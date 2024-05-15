package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentNotificationsBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentReportBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class ReportFragment : Fragment() {
    private lateinit var binding: FragmentReportBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener{
            if(binding.editText.text.isNotEmpty()){
                runBlocking {
                    withContext(Dispatchers.IO){
                        var text = binding.editText.text.toString()
                        Model.getInstanceWC().createReportM(Model.getInstanceWC().client.idKlienta,text,Model.getInstanceWC().client.username)
                    }
                }

                binding.error.setText("Report sent :)");
            }
        }
        return binding.root
    }


}