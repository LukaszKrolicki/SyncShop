package eu.pl.snk.senseibunny.syncshop.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.controllers.UserControllers.UserActivity
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityNewRetrievedPasswordBinding
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityRetrievePasswordBinding
import eu.pl.snk.senseibunny.syncshop.databinding.PopupActivityCodeBinding
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RetrievePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrievePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRetrievePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmButton.setOnClickListener{
            try{
                runBlocking {
                    withContext(Dispatchers.IO){
                        Model.getInstanceWC().emailR=binding.mailEditText.text.toString()
                        Model.getInstanceWC().sendEmailRetriveM(binding.mailEditText.text.toString())
                    }
                }

                runOnUiThread {
                    showPopup(this)
                }

            }
            catch(Exeption: Exception){
                runOnUiThread {
                    binding.error.setText("Email not found")
                }
            }
        }

        binding.BackButton.setOnClickListener{
            finish()
        }


    }

    fun showPopup(context: Context){
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
                        Model.getInstanceWC().checkRetrieveCodeM(popupBinding.codeEt.text.toString().toInt(),binding.mailEditText.text.toString());
                    }
                }
//                finish()
                val intent = Intent(context, NewRetrievedPasswordActivity::class.java)
                startActivityForResult(intent, 1)
                popupWindow.dismiss()

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}