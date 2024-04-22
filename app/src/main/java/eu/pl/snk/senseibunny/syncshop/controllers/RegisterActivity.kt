package eu.pl.snk.senseibunny.syncshop.controllers

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityMainBinding
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityRegisterBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.CreateButton.setOnClickListener {
            runBlocking {
                withContext(Dispatchers.IO) {
                    val name = binding.nameEditText.text.toString()
                    val familyName = binding.FamilyNameEditText.text.toString()
                    val email = binding.emailEditText.text.toString()
                    val username = binding.usernameEditText.text.toString()
                    val password = binding.passwordEditText.text.toString()
                    val password2 = binding.passwordEditText2.text.toString()

                    if (password != password2) {
                        runOnUiThread {
                            binding.error.setText("Passwords do not match")
                        }
                    }
                    else if (username.isEmpty() || password.isEmpty() || password2.isEmpty() || email.isEmpty()) {
                        runOnUiThread {
                            binding.error.setText("Please fill all fields")
                        }

                    }
                    else{
                        try{
                            Model.getInstanceWC().register(name, familyName, email, username, password)
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                        catch(Exeption: Exception){
                            runOnUiThread {
                                binding.error.setText("User creation failed")
                            }
                        }
                    }
                }
            };
        }

    }
}