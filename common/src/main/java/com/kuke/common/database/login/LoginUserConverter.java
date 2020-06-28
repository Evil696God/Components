
package com.kuke.common.database.login;

import com.kuke.common.utils.GsonUtils;

import androidx.room.TypeConverter;

/**
 * @date: 2020-04-03
 * @version: 1.0
 * @author: wangchenxing
 */
public class LoginUserConverter {
    @TypeConverter
    public static LoginTable.UserBean revertUserBean(String organizations) {
        // 使用Gson方法把json格式的string转成List
        return GsonUtils.jsonToBean(organizations, LoginTable.UserBean.class);
    }

    @TypeConverter
    public static String converterOrganizationsBean(LoginTable.UserBean userBean) {
        // 使用Gson方法把List转成json格式的string，便于我们用的解析
        return GsonUtils.toJsons(userBean);
    }
}
