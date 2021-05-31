package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.ActivityMainBinding
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTitleBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        binding.playButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        // Tell Android that the TitleFragment has a menu:
        setHasOptionsMenu(true)
        return binding.root
    }

    // Inflate the menu resource:
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    // This function is to connect it to the NavigationUI
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // If the navigationUI doesn't handle the selection, so call super on optionsItemSelected
        // which allows the app to directly handle the menu item without navigating
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}