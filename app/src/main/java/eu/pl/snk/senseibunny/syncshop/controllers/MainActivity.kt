package eu.pl.snk.senseibunny.syncshop.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.controllers.AdminControllers.AdminActivity
import eu.pl.snk.senseibunny.syncshop.controllers.UserControllers.UserActivity
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }
}