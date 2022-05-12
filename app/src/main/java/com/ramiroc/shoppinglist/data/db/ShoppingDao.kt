package com.ramiroc.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramiroc.shoppinglist.data.db.entities.ShoppingItem


@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)
    @Delete
    suspend fun delete(item: ShoppingItem)
    @Query("SELECT * FROM  shopping_items")
    fun getAllItems(): LiveData<List<ShoppingItem>>
}