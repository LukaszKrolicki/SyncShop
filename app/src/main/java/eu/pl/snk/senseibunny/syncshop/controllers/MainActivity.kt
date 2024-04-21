package eu.pl.snk.senseibunny.syncshop.controllers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.pl.snk.senseibunny.syncshop.controllers.UserControllers.ShoppingListActivity
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityMainBinding
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

        Thread {
            try {
                Model.getInstance(this)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()

        runBlocking {
            withContext(Dispatchers.IO) {
                Model.getInstanceWC().login("lk13", "lk13")
                System.out.println(Model.getInstanceWC().client.imie)
                Model.getInstanceWC().dataBaseDriver.getProtectedRoute()
                Model.getInstanceWC().dataBaseDriver.getProtectedRoute2()
            }
        }

        binding.loginButton.setOnClickListener{
            val intent = Intent(this, ShoppingListActivity::class.java)
            startActivity(intent)
        }
    }
}