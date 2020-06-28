
package com.kuke.common.database;


import android.content.Context;

import com.kuke.common.database.login.LoginModeDao;
import com.kuke.common.database.login.LoginTable;
import com.kuke.common.database.login.LoginUserConverter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


/**
 * @date: 2020-04-03
 * @version: 1.0
 * @author: wangchenxing
 */
@Database(
        entities = {LoginTable.class},
        version = KuKeDatabase.DATABASEVERSION,
        exportSchema = false)
@TypeConverters({
        LoginUserConverter.class
})
public abstract class KuKeDatabase extends RoomDatabase {
    public static final int DATABASEVERSION = 1;
    private static String databaseName = "KuKe.db";
    private static volatile KuKeDatabase kuKeDatabase;

    public static KuKeDatabase getDatabase(Context context) {
        if (kuKeDatabase == null) {
            synchronized (KuKeDatabase.class) {
                if (kuKeDatabase == null) {
                    kuKeDatabase = Room.databaseBuilder(
                            context,
                            KuKeDatabase.class,
                            databaseName
                    )
                            .addMigrations()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return kuKeDatabase;
    }

    public abstract LoginModeDao loginModeDao();
}
