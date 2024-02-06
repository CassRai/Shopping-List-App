package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class ShoppingListRepository {

    private ShoppingListsDao mShoppingListsDao;
    private LiveData<List<ShoppingLists>> mAllShoppingLists;

    ShoppingListRepository(Application application) {
        TheDatabase db = TheDatabase.getDatabase(application);
        mShoppingListsDao = db.shoppingListsDao();
        mAllShoppingLists = mShoppingListsDao.getAll();
    }

    LiveData<List<ShoppingLists>> getAllShoppingLists() {
        return mAllShoppingLists;
    }

    LiveData<List<ShoppingLists>> getShoppingListById(int shoppingListId){
        return mShoppingListsDao.getShoppingListsByID(shoppingListId);
    }

    void insert(ShoppingLists shoppingLists) {
        TheDatabase.databaseWriteExecutor.execute(() -> {
            mShoppingListsDao.insert(shoppingLists);
        });
    }
}
