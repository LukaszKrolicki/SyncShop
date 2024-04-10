package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityShoppingListBinding

class ShoppingListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListBinding;
    private lateinit var fragmentManager: FragmentManager;
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)!!
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_list_planned -> {
                    openFragment(ListPlannedFragment())
                    true
                }

                R.id.nav_list_reserved -> {
                    openFragment(ReservedListFragment())
                    true
                }

                R.id.nav_list_bought -> {
                    openFragment(ListBoughtFragment())
                    true
                }

                else -> {
                    openFragment(ListPlannedFragment());
                    true
                }
            }
        }
        setSupportActionBar(binding.toolbar)

        fragmentManager = supportFragmentManager

        openFragment(ListPlannedFragment());
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}