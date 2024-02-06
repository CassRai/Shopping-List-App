package uk.ac.le.co2103.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShoppingListsDao {

    @Query("SELECT * FROM shopping_lists ORDER BY name ASC")
    LiveData<List<ShoppingLists>> getAll();

    @Query("SELECT * FROM shopping_lists WHERE listId = :shoppingListId")
    LiveData<List<ShoppingLists>> getShoppingListsByID(int shoppingListId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ShoppingLists shoppingLists);

    @Query("DELETE FROM shopping_lists")
    void DeleteAll();
}
