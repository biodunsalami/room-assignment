package com.biodun.roomassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.biodun.roomassignment.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  val adapter = ContactAdapter()

    private var categoryTitle : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val i = intent
//        val title = i.getStringExtra("toolbarTitle")

        categoryTitle =intent.getStringExtra("categoryToolbarTitle")
        binding.contactTb.title = categoryTitle

        setUpData(binding)
    }

    private fun setUpData(binding: ActivityMainBinding){
        binding.contactsRv.adapter = adapter
        binding.contactsRv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))




        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextInputEditText>(R.id.nameEt)
        val no = view.findViewById<TextInputEditText>(R.id.numberEt)
        val saveBtn = view.findViewById<MaterialButton>(R.id.saveBt)


        no.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveBtn.isEnabled = s?.length == 11
            }

        })


        val alertDialog = builder.create()


        saveBtn.setOnClickListener{
            val contact = Contact(name.text.toString(), no.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContacts(contacts)
            alertDialog.dismiss()
        }



        binding.fab.setOnClickListener {
            alertDialog.show()
        }
    }
}
