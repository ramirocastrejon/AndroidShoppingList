package com.ramiroc.shoppinglist.data.ui

import com.ramiroc.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClick(item: ShoppingItem)
}