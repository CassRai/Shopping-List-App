package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemsRepository {
    private ItemsDao mItemsDao;
    private LiveData<List<Items>> mAllItems;

    ItemsRepository(Application application) {
        TheDatabase db = TheDatabase.getDatabase(application);
        mItemsDao = db.itemsDao();
        mAllItems = mItemsDao.getAll();
    }

    LiveData<List<Items>> getAllItems() {
        return mAllItems;
    }

    LiveData<List<Items>> getItemsByListId(Integer ListId) {
        return mItemsDao.getByShoppingListId(ListId) ;
    }

    void insert(Items items) {
        TheDatabase.databaseWriteExecutor.execute(() -> {
            mItemsDao.insert(items);
        });
    }
}
