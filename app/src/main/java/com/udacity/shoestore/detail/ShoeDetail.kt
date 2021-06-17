package com.udacity.shoestore.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetail : Fragment() {
    private val viewModel : ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        // Initialize default shoe
        binding.shoe = Shoe("", 0.0, "", "", mutableListOf())

        binding.buttonCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.buttonSave.setOnClickListener {
            val shoe = binding.shoe
            viewModel.addShoe(shoe)
        }

        viewModel.navigateToShoeList.observe(viewLifecycleOwner, Observer { navigateToShoeList ->
            if (navigateToShoeList){
                findNavController().navigate(ShoeDetailDirections.actionShoeDetailToShoeList())
                viewModel.navigateToShoeListComplete()
            }
        })

        return binding.root
    }

}