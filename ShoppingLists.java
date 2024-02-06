package uk.ac.le.co2103.part2;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "shopping_lists",indices = {@Index(value = "name", unique = true)})
@TypeConverters(UriTypeConverter.class)
public class ShoppingLists {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "listId")
    private int listId;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image")
    private Uri image;

    public ShoppingLists(String name, @Nullable Uri image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
