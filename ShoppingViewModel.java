package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShoppingViewModel extends AndroidViewModel {
    private ShoppingListRepository mShoppingListRepository;
    private ItemsRepository mItemsRepository;

    public ShoppingViewModel(@NonNull Application application) {
        super(application);
        mShoppingListRepository = new ShoppingListRepository(application);
        mItemsRepository = new ItemsRepository(application);
    }

    public LiveData<List<ShoppingLists>> getShoppingLists(){
        return mShoppingListRepository.getAllShoppingLists();
    }

    public LiveData<List<ShoppingLists>> getShoppingListsbyId(Integer shoppingListId){
        return mShoppingListRepository.getShoppingListById(shoppingListId);
    }

    public void insertShoppingList(ShoppingLists shoppingLists){
        mShoppingListRepository.insert(shoppingLists);
    }

    public LiveData<List<Items>> getItems(){
        return mItemsRepository.getAllItems();
    }

    public LiveData<List<Items>> getItemsbyID(Integer ListId){
        return mItemsRepository.getItemsByListId(ListId);
    }

    public void insertItems(Items items){
        mItemsRepository.insert(items);
    }


}
