package uk.ac.le.co2103.part2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ShoppingLists.class, Items.class}, version = 4, exportSchema = false)
@TypeConverters(UriTypeConverter.class)
public abstract class TheDatabase extends RoomDatabase {

    public abstract ItemsDao itemsDao();
    public abstract ShoppingListsDao shoppingListsDao();

    private static volatile TheDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TheDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TheDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TheDatabase.class, "the_database")
                            .addCallback(sTheDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sTheDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {

                ShoppingListsDao sl_dao = INSTANCE.shoppingListsDao();
                ItemsDao i_dao = INSTANCE.itemsDao();
                sl_dao.DeleteAll();
                i_dao.DeleteAll();
            });
        }
    };
}
