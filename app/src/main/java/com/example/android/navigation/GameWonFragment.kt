/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false
        )

        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
//        Toast.makeText(
//            context,
//            "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
//            Toast.LENGTH_LONG
//        ).show()

        // Tells Android that there's a menu:
        setHasOptionsMenu(true)
        return binding.root
    }

    // Create a getShareIntent method and move our GameWonFragmentArgs.fromBundle there.
    // Use shareCompat to create our share Implicit intent.
    // Call ShareCompat.IntentBuilder.from(activity), set our text, and then set the MIME type,
    // finishing off by building our intent.
    private fun getShareIntent(): Intent {
        var args = GameWonFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.share_success_text, args.numCorrect, args.numQuestions)
            )
        return shareIntent
    }

    // Inflate the menu and hide the sharing menu item if the sharing intent doesn't resolve to an Activity
    // Showing the share menu Item Dynamically
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)

        // Check if the activity resolves:
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // Hide the menu item if it doesn't resolve
            menu?.findItem(R.id.share)?.isVisible = false
        }
    }

    // Method, which gets the Intent from getShareIntent and calls startActivity to begin sharing.
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    // To link the menu to the shareSuccess action
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // When the menuitem id matches R.id.share, call the shareSuccess method.
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}