

package com.kuke.common.database.login;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * @date: 2020-04-03
 * @version: 1.0
 * @author: wangchenxing
 */
@Dao
public interface LoginModeDao {
    @Insert
    void insert(LoginTable loginTable);

    @Query("SELECT * FROM login_table")
    LoginTable getLoginTable();

    @Update
    void upDate(LoginTable loginTable);
}
