package com.example.android3_pmdm

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment

class FragmentLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_layout)
    }

    class TitlesFragment : ListFragment() {
        private var dualPane: Boolean = false
        private var curCheckPosition = 0

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            // Populate list with our static array of titles.
            listAdapter = ArrayAdapter<String>(
                requireActivity(),
                android.R.layout.simple_list_item_activated_1,
                Campeones.NOMBRES
            )

            // Check to see if we have a frame in which to embed the details
            // fragment directly in the containing UI.
            val detailsFrame: View? = activity?.findViewById(R.id.details)
            dualPane = detailsFrame?.visibility == View.VISIBLE

            curCheckPosition = savedInstanceState?.getInt("curChoice", 0) ?: 0

            if (dualPane) {
                // In dual-pane mode, the list view highlights the selected item.
                listView.choiceMode = ListView.CHOICE_MODE_SINGLE
                // Make sure our UI is in the correct state.
                showDetails(curCheckPosition)
            }
        }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            outState.putInt("curChoice", curCheckPosition)
        }

        override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
            showDetails(position)
        }

        /**
         * Helper function to show the details of a selected item, either by
         * displaying a fragment in-place in the current UI, or starting a
         * whole new activity in which it is displayed.
         */
        private fun showDetails(index: Int) {
            curCheckPosition = index

            if (dualPane) {
                // We can display everything in-place with fragments, so update
                // the list to highlight the selected item and show the data.
                listView.setItemChecked(index, true)

                // Check what fragment is currently shown, replace if needed.
                var details = fragmentManager?.findFragmentById(R.id.details) as? DetailsFragment
                if (details?.shownIndex != index) {
                    // Make new fragment to show this selection.
                    details = DetailsFragment.newInstance(index)

                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    fragmentManager?.beginTransaction()?.apply {
                        if (index == 0) {
                            replace(R.id.details, details)
                        } else {
                            replace(R.id.details, details)
                        }
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        commit()
                    }
                }

            } else {
                // Otherwise we need to launch a new activity to display
                // the dialog fragment with selected text.
                val intent = Intent().apply {
                    setClass(requireActivity(), DetailsActivity::class.java)
                    putExtra("index", index)
                }
                startActivity(intent)
            }

            Toast.makeText(context, "Mostrando nuevos datos...", Toast.LENGTH_SHORT).show()
            Log.i("TitlesFragment.showDetails()", "Se ha hecho clic en un título.")
        }

    }

    class DetailsFragment : Fragment() {

        val shownIndex: Int by lazy {
            arguments?.getInt("index", 0) ?: 0
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            if (container == null) {
                // We have different layouts, and in one of them this
                // fragment's containing frame doesn't exist. The fragment
                // may still be created from its saved state, but there is
                // no reason to try to create its view hierarchy because it
                // isn't displayed. Note this isn't needed -- we could just
                // run the code below, where we would create and return the
                // view hierarchy; it would just never be used.
                return null
            }

            val text = TextView(activity).apply {
                val padding: Int = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    4f,
                    activity?.resources?.displayMetrics
                ).toInt()
                setPadding(padding, padding, padding, padding)
                text = Campeones.HISTORIA[shownIndex]
            }
            return ScrollView(activity).apply {
                addView(text)
            }
        }

        companion object {
            /**
             * Create a new instance of DetailsFragment, initialized to
             * show the text at 'index'.
             */
            fun newInstance(index: Int): DetailsFragment {
                val f = DetailsFragment()

                // Supply index input as an argument.
                val args = Bundle()
                args.putInt("index", index)
                f.arguments = args

                return f
            }
        }
    }

    class DetailsActivity : FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // If the screen is now in landscape mode, we can show the
                // dialog in-line with the list so we don't need this activity.
                finish()
                return
            }

            if (savedInstanceState == null) {
                // During initial setup, plug in the details fragment.
                val details = DetailsFragment().apply {
                    arguments = intent.extras
                }
                supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, details)
                    .commit()
            }
        }
    }

}
