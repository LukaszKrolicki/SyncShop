package eu.pl.snk.senseibunny.syncshop.controllers.UserControllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.controllers.AdminControllers.BugReportFragment
import eu.pl.snk.senseibunny.syncshop.controllers.AdminControllers.UserListFragment
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityAdminBinding
import eu.pl.snk.senseibunny.syncshop.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityUserBinding
    private  lateinit var fragmentManage: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val toogle = ActionBarDrawerToggle(this,binding.drawerLayout, binding.toolbar,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        // Remove the title
        if(getSupportActionBar() != null){
            getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        }

        fragmentManage = supportFragmentManager
        openFragment(NotificationsFragment())
        binding.navigationDrawer.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.notifications->openFragment(NotificationsFragment())
            R.id.create_shopping_list->openFragment(CreateShoppingListFragment())
            R.id.your_shopping_lists->openFragment(YourShoppinListFragment())
            R.id.add_friend->openFragment(AddFriendFragment())
            R.id.friend_list->openFragment(FriendListFragment())
            R.id.profileSettings->openFragment(SettingsFragment())
            R.id.report->openFragment(ReportFragment())
            R.id.logout->finish()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManage.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }


}