package uk.ac.le.co2103.part2;

import android.content.ClipData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemsDao {
    @Query("SELECT * FROM shopping_items WHERE shopping_list_id = :shoppingListId")
    LiveData<List<Items>> getByShoppingListId(int shoppingListId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Items item);

    @Query("SELECT * FROM shopping_items")
    LiveData<List<Items>> getAll();

    @Query("DELETE FROM shopping_items")
    void DeleteAll();
}