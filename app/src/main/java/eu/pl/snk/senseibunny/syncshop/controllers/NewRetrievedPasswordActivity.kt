package eu.pl.snk.senseibunny.syncshop.controllers

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityNewRetrievedPasswordBinding
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityRegisterBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class NewRetrievedPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewRetrievedPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewRetrievedPasswordBinding.inflate(layoutInflater)
        binding.AcceptButton.setOnClickListener {
//            try {
                if (binding.passEditText.text.toString().equals(binding.passEditText2.text.toString())){
                    runBlocking {
                        withContext(Dispatchers.IO){
                            Model.getInstanceWC().updateUserPassRetrieveM(binding.passEditText.text.toString(),Model.getInstanceWC().emailR)
                        }
                    }

                    setResult(Activity.RESULT_OK)
                    finish()
                }
                else{
                    runOnUiThread {
                        binding.error.setText("Passwords do not match")
                    }
                }
//            }
//            catch (Exeption: Exception){
                runOnUiThread {
                    binding.error.setText("Password not changed")
                }
            //}
        }
        binding.BackButton.setOnClickListener{
            finish()
        }
        setContentView(binding.root)
    }
}