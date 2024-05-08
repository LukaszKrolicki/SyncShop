package eu.pl.snk.senseibunny.syncshop.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.controllers.UserControllers.ShoppingListActivity
import eu.pl.snk.senseibunny.syncshop.controllers.UserControllers.UserActivity
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityMainBinding
import eu.pl.snk.senseibunny.syncshop.databinding.CustomRegistrationCompletedPopupBinding
import eu.pl.snk.senseibunny.syncshop.models.ApiDatabaseDriver
import eu.pl.snk.senseibunny.syncshop.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: ApiDatabaseDriver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, "You've already sent an invitation to that person", Toast.LENGTH_SHORT).show()

        Thread {
            try {
                Model.getInstance(this)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()



        binding.loginButton.setOnClickListener{
            runBlocking {
                withContext(Dispatchers.IO) {
                    //Model.getInstanceWC().login("lk13", "lk13")
                    if(binding.usernameEditText.text.toString().equals("aaa")){
                        Model.getInstanceWC().login("aaa", "123")
                    }
                    else if (binding.usernameEditText.text.toString().equals("bbb")){
                        Model.getInstanceWC().login("bbb", "123")
                    }
                    else if (binding.usernameEditText.text.toString().equals("ccc")){
                        Model.getInstanceWC().login("ccc", "123")
                    }
                    else if(binding.usernameEditText.text.toString().equals("ddd")){
                        Model.getInstanceWC().login("ddd", "123")
                    }
                    else if(binding.usernameEditText.text.toString().equals("eee")){
                        Model.getInstanceWC().login("eee", "123")
                    }
                    else if(binding.usernameEditText.text.toString().equals("fff")){
                        Model.getInstanceWC().login("fff", "123")
                    }
                    else if(binding.usernameEditText.text.toString().equals("ggg")){
                        Model.getInstanceWC().login("ggg", "123")
                    }
                    else if(binding.usernameEditText.text.toString().equals("hhh")){
                        Model.getInstanceWC().login("hhh", "123")
                    }
                    else if(binding.usernameEditText.text.toString().equals("iii")){
                        Model.getInstanceWC().login("iii", "123")
                    }
                    else if(binding.usernameEditText.text.toString().equals("jjj")){
                        Model.getInstanceWC().login("jjj", "123")
                    }
                    else if(binding.usernameEditText.text.toString().equals("yyy")){
                        Model.getInstanceWC().login("yyy", "123")
                    }
                    else{
                        Model.getInstanceWC().login("lk13", "lk13")

                    }

                    System.out.println(Model.getInstanceWC().client.imie)
                    Model.getInstanceWC().dataBaseDriver.getProtectedRoute()
                    Model.getInstanceWC().dataBaseDriver.getProtectedRoute2()
                }
            }
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        binding.CreateButton.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                showPopup()
            }
        }
    }

    fun showPopup() {
        val popupBinding: CustomRegistrationCompletedPopupBinding = CustomRegistrationCompletedPopupBinding.inflate(LayoutInflater.from(binding.root.context))
        val popupView: View = popupBinding.root
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)


        // Set the focusable property to true
        popupWindow.isFocusable = true

        // Set an OnTouchListener to consume touch events
        popupView.setOnTouchListener { _, _ -> true }



        // Show the popup window in the center of the screen
        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }
}
