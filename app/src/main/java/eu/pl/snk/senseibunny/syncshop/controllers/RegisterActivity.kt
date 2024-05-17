package eu.pl.snk.senseibunny.syncshop.controllers

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityMainBinding
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityRegisterBinding
import eu.pl.snk.senseibunny.syncshop.databinding.PopupActivityCodeBinding
import eu.pl.snk.senseibunny.syncshop.databinding.PopupProductInfoBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import eu.pl.snk.senseibunny.syncshop.models.Product
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

                         runBlocking {
                             withContext(Dispatchers.IO){
                                 Model.getInstanceWC().sendEmailM(username,binding.emailEditText.text.toString())
                             }
                         }

                            runOnUiThread {
                                showPopup()
                            }

                       }
                       catch(Exeption: Exception){
                            runOnUiThread {
                                binding.error.setText("User creation failed, change email or username")
                            }
                        }
                    }
        }

    }

    fun showPopup(){
        val popupBinding: PopupActivityCodeBinding = PopupActivityCodeBinding.inflate(LayoutInflater.from(binding.root.context))
        val popupView: View = popupBinding.root
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

        // Set the focusable property to true
        popupWindow.isFocusable = true

        // Set an OnTouchListener to consume touch events
        popupView.setOnTouchListener { _, _ -> true }



        popupBinding.addBtn.setOnClickListener {
            try {
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().register(binding.nameEditText.text.toString(), binding.FamilyNameEditText.text.toString(), binding.emailEditText.text.toString(), binding.usernameEditText.text.toString(), binding.passwordEditText.text.toString(), popupBinding.codeEt.text.toString().toInt())
                    }
                }
                popupWindow.dismiss()
                setResult(Activity.RESULT_OK)
                finish()
            }
            catch(e: Exception){
                runOnUiThread {
                    popupBinding.error.setText("Invalid code")
                }
            }

        }

        // Show the popup window
        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }
}
