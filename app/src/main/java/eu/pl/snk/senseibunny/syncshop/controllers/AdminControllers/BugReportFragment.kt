package eu.pl.snk.senseibunny.syncshop.controllers.AdminControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.syncshop.R
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentBugReportBinding
import eu.pl.snk.senseibunny.syncshop.databinding.FragmentUserListBinding


class BugReportFragment : Fragment() {
    private lateinit var binding: FragmentBugReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBugReportBinding.inflate(inflater, container, false)
        return binding.root

    }

}