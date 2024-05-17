package eu.pl.snk.senseibunny.syncshop.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityNewRetrievedPasswordBinding
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityRegisterBinding

class NewRetrievedPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewRetrievedPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewRetrievedPasswordBinding.inflate(layoutInflater)
        binding.BackButton.setOnClickListener{
            finish()
        }
        setContentView(binding.root)
    }
}