package com.ramiroc.shoppinglist.data.ui

import androidx.lifecycle.ViewModel
import com.ramiroc.shoppinglist.data.db.entities.ShoppingItem
import com.ramiroc.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {
    fun upsert(item: ShoppingItem) = kotlinx.coroutines.GlobalScope.launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllItems() = repository.getAllItems()
}