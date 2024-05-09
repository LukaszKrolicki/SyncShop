package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentAddFriendBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentSettingsBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class SettingsFragment : Fragment() {

    private lateinit var binding : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.emailEt.setText(Model.getInstanceWC().client.email)
        binding.nameEt.setText(Model.getInstanceWC().client.imie)
        binding.surnameEt.setText(Model.getInstanceWC().client.nazwisko)

        binding.saveChanges.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val name= binding.nameEt.text.toString()
            val surname = binding.surnameEt.text.toString()

            val trueEmail = Model.getInstanceWC().client.email
            val trueName = Model.getInstanceWC().client.imie
            val trueSurname= Model.getInstanceWC().client.nazwisko

            if(email.equals(trueEmail) && name.equals(trueName) && surname.equals(trueSurname)){
                binding.error.setTextColor(Color.RED)
                binding.error.setText("Nothing changed");
            }
            else if(email.isEmpty()){
                binding.error.setTextColor(Color.RED)
                binding.error.setText("Empty email field");
            }
            else{
                runBlocking {
                    withContext(Dispatchers.IO){
                        Model.getInstanceWC().updateUserM(Model.getInstanceWC().client.idKlienta, email, name, surname)
                        Model.getInstanceWC().client.email = email;
                        Model.getInstanceWC().client.nazwisko=surname;
                        Model.getInstanceWC().client.imie=name;

                        activity?.runOnUiThread {
                            binding.error.setTextColor(Color.GREEN)
                            binding.error.setText("Peronal changes saved");
                        }
                    }
                }
            }
        }

        binding.changePass.setOnClickListener {
            val usern = Model.getInstanceWC().client.username
            println(usern)
            var isTheSame = false
            runBlocking {
                withContext(Dispatchers.IO) {
                    isTheSame =
                        Model.getInstanceWC().checkUserPassM(usern, binding.oldPass.text.toString())
                }
                if (!binding.newPass1.text.toString().equals(binding.newPass2.text.toString())) {
                    println(binding.newPass1.toString() + " + " + binding.newPass2.toString())
                    binding.error.setTextColor(Color.RED)
                    binding.error.setText("Passwords are not the same");
                } else if (binding.newPass1.text.toString().isEmpty()) {
                    binding.error.setTextColor(Color.RED)
                    binding.error.setText("Empty password field");
                } else if (isTheSame) {
                    runBlocking {
                        withContext(Dispatchers.IO) {
                            Model.getInstanceWC().updateUserPassM(
                                Model.getInstanceWC().client.idKlienta,
                                binding.newPass1.text.toString()
                            )
                            activity?.runOnUiThread {
                                binding.error.setTextColor(Color.GREEN)
                                binding.error.setText("Password changed");
                            }
                        }
                    }
                } else {
                    binding.error.setTextColor(Color.RED)
                    binding.error.setText("Wrong current password");
                }
            }
        }
        return binding.root
    }


}