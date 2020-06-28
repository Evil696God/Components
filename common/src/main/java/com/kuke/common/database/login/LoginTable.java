
package com.kuke.common.database.login;

import com.kuke.common.base.basebean.BaseTable;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * @date: 2020-04-03
 * @version: 1.0
 * @author: wangchenxing
 */
@Entity(tableName = "login_table")
public class LoginTable extends BaseTable {

    /**
     * employeeId : 2
     * employeeZhName : 张三@
     * mobilePhone : 1548787819
     * dutyDate : 2018-12-05 00:00:00
     * organization : 1001
     * department : 100101
     * officeLocation : 1001
     * post : 1001
     * appPassword :
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "_id")
    public int id = 1;
    @NonNull
    @ColumnInfo(name = "token")
    private String token;
    @NonNull
    @ColumnInfo(name = "data")
    private UserBean data;

    public LoginTable() {
        token = "";
        data = null;
    }

    public LoginTable(LoginSuccessBean.DataBean dataBean) {
        token = dataBean.getToken();
        LoginSuccessBean.DataBean.UserBean user = dataBean.getUser();
        UserBean userBean = new UserBean();
        userBean.setUsername(user.getUsername());
        userBean.setDetails(user.getDetails());
        userBean.setRoles(user.getRoles());
        setData(userBean);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
    }

    @NonNull
    public UserBean getData() {
        return data;
    }

    public void setData(LoginSuccessBean.DataBean dataBean) {
        token = dataBean.getToken();
        LoginSuccessBean.DataBean.UserBean user = dataBean.getUser();
        UserBean userBean = new UserBean();
        userBean.setUsername(user.getUsername());
        userBean.setDetails(user.getDetails());
        userBean.setRoles(user.getRoles());
        setData(userBean);
    }

    public void setData(@NonNull UserBean data) {
        this.data = data;
    }

    public static class UserBean {
        /**
         * details : Tom
         * roles : []
         * username : Tom
         */
        private int details;
        private String username;
        private List<?> roles;

        public int getDetails() {
            return details;
        }

        public void setDetails(int details) {
            this.details = details;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }
    }
}
