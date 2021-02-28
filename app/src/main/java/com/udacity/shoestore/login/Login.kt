package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentLoginBinding


class Login : Fragment(){
    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private val shoeStoreViewModel: ShoeStoreViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        findNavController().previousBackStackEntry?.let {
            savedStateHandle = it.savedStateHandle
            savedStateHandle.set(LOGIN_SUCCESSFUL, false)
        }

        binding.shoeStoreViewModel = shoeStoreViewModel

        shoeStoreViewModel.loggedIn.observe(viewLifecycleOwner, Observer { isLoggedIn ->
            if (isLoggedIn){
                if (::savedStateHandle.isInitialized)
                    savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                // findNavController().popBackStack()
                findNavController().navigate(LoginDirections.actionLoginToWelcome())
            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}