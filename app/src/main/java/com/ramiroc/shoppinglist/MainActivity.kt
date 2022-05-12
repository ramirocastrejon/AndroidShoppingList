package com.ramiroc.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramiroc.shoppinglist.data.db.ShoppingDatabase
import com.ramiroc.shoppinglist.data.db.entities.ShoppingItem
import com.ramiroc.shoppinglist.data.repositories.ShoppingRepository
import com.ramiroc.shoppinglist.data.ui.AddDialogListener
import com.ramiroc.shoppinglist.data.ui.AddItemDialog
import com.ramiroc.shoppinglist.data.ui.ShoppingViewModel
import com.ramiroc.shoppinglist.data.ui.ShoppingViewModelFactory
import com.ramiroc.shoppinglist.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val database = ShoppingDatabase(this)
        //val repository = ShoppingRepository(database)

        //val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener{
            AddItemDialog(this,
            object : AddDialogListener {
                override fun onAddButtonClick(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}