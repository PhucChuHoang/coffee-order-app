package com.example.coffee_order_app.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.editName.setText(Globals.user.name)
        binding.editPhoneNumber.setText(Globals.user.phone_number)
        binding.editEmail.setText(Globals.user.email)
        binding.editAddress.setText(Globals.user.address)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val clickListener = View.OnClickListener { v ->
            when (v) {
                binding.buttonEditName -> {
                    toggleEditText(binding.editName)
                }
                binding.buttonEditPhoneNumber -> {
                    toggleEditText(binding.editPhoneNumber)
                }
                binding.buttonEditEmail -> {
                    toggleEditText(binding.editEmail)
                }
                binding.buttonEditAddress -> {
                    toggleEditText(binding.editAddress)
                }
                binding.layoutProfile -> {
                    if (binding.editName.isEnabled) {
                        toggleEditText(binding.editName)
                    } else if (binding.editPhoneNumber.isEnabled) {
                        toggleEditText(binding.editPhoneNumber)
                    } else if (binding.editEmail.isEnabled) {
                        toggleEditText(binding.editEmail)
                    } else if (binding.editAddress.isEnabled) {
                        toggleEditText(binding.editAddress)
                    }
                }
            }
        }

        binding.buttonEditName.setOnClickListener(clickListener)
        binding.buttonEditPhoneNumber.setOnClickListener(clickListener)
        binding.buttonEditEmail.setOnClickListener(clickListener)
        binding.buttonEditAddress.setOnClickListener(clickListener)
        binding.layoutProfile.setOnClickListener(clickListener)
    }

    private fun toggleEditText(editText: EditText) {
        if (editText.isEnabled) {
            editText.isEnabled = false
            // Update the corresponding value in the Globals.user object
            when (editText) {
                binding.editName -> Globals.user.name = editText.text.toString()
                binding.editPhoneNumber -> Globals.user.phone_number = editText.text.toString()
                binding.editEmail -> Globals.user.email = editText.text.toString()
                binding.editAddress -> Globals.user.address = editText.text.toString()
            }
        } else {
            editText.isEnabled = true
            editText.requestFocus()
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}