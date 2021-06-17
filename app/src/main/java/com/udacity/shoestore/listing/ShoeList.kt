package com.udacity.shoestore.listing

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.IndividualShoeLayoutBinding

class ShoeList : Fragment() {
    private val shoeStoreViewModel: ShoeStoreViewModel by activityViewModels()
    private lateinit var shoesLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        shoesLayout = binding.shoesLayout

        setHasOptionsMenu(true)

        binding.fabAddShoe.setOnClickListener {
            findNavController().navigate(ShoeListDirections.actionShoeListToShoeDetail())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoeStoreViewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach {
                val layout = IndividualShoeLayoutBinding.inflate(layoutInflater)
                layout.shoe = it

                val view = layout.root
                val params = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )

                // Set lower margin for cards
                params.setMargins(0, 0, 0, 16)
                view.layoutParams = params

                shoesLayout.addView(view)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.sign_out -> {
                // handle sign out here
                shoeStoreViewModel.logOut()

                view?.findNavController()?.navigate(ShoeListDirections.actionShoeListToLogin())
                return true
            }
        }

        return false
    }
}