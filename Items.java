package uk.ac.le.co2103.part2;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_items",indices = {@Index(value = "name", unique = true)},foreignKeys = @ForeignKey(entity = ShoppingLists.class,
        parentColumns = "listId",
        childColumns = "shopping_list_id",
        onDelete = CASCADE))
public class Items {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "quantity")
    private Integer quantity;

    @ColumnInfo(name = "unit")
    private String unit;

    @ColumnInfo(name = "shopping_list_id")
    private @NonNull Integer shoppingListId;

    public Items(String name, Integer quantity, String unit, @NonNull Integer shoppingListId){
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Integer shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
