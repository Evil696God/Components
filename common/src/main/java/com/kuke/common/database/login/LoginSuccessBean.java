

package com.kuke.common.database.login;


import com.kuke.common.base.basebean.BaseBean;

import java.util.List;

/**
 *
 * @date: 2020-04-03
 * @version: 1.0
 * @author: wangchenxing
 */
public class LoginSuccessBean extends BaseBean {

    /**
     * code : 200
     * data : {"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub25lIiwibmJmIjoxNTU1MTM3MDYwLCJpc3MiOiJub25lIiwib3duX3BhcmFtcyI6eyJ1c2VybmFtZSI6IlRvbSIsInJvbGVzIjpbIjEiXSwiZGV0YWlscyI6MSwiand0IjpudWxsLCJwYXR0ZXJuIjoiMiJ9LCJleHAiOjE1NTU1OTc4NjAsImlhdCI6MTU1NTEzNzA2MH0.SKHgoeAP9G4ju--oyevt8sukKLxYYUmx2iDty9im3u4","user":{"details":1,"pattern":"2","roles":["1"],"username":"Tom"},"ws_app":"/ws/web","ws_web":"/ws/app"}
     * errorInfo :
     * msg : key_request_success
     */

    private int code;
    private DataBean data;
    private String errorInfo;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub25lIiwibmJmIjoxNTU1MTM3MDYwLCJpc3MiOiJub25lIiwib3duX3BhcmFtcyI6eyJ1c2VybmFtZSI6IlRvbSIsInJvbGVzIjpbIjEiXSwiZGV0YWlscyI6MSwiand0IjpudWxsLCJwYXR0ZXJuIjoiMiJ9LCJleHAiOjE1NTU1OTc4NjAsImlhdCI6MTU1NTEzNzA2MH0.SKHgoeAP9G4ju--oyevt8sukKLxYYUmx2iDty9im3u4
         * user : {"details":1,"pattern":"2","roles":["1"],"username":"Tom"}
         * ws_app : /ws/web
         * ws_web : /ws/app
         */

        private String token;
        private UserBean user;
        private String ws_app;
        private String ws_web;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getWs_app() {
            return ws_app;
        }

        public void setWs_app(String ws_app) {
            this.ws_app = ws_app;
        }

        public String getWs_web() {
            return ws_web;
        }

        public void setWs_web(String ws_web) {
            this.ws_web = ws_web;
        }

        public static class UserBean {
            /**
             * details : 1
             * pattern : 2
             * roles : ["1"]
             * username : Tom
             */

            private int details;
            private String pattern;
            private String username;
            private List<String> roles;

            public int getDetails() {
                return details;
            }

            public void setDetails(int details) {
                this.details = details;
            }

            public String getPattern() {
                return pattern;
            }

            public void setPattern(String pattern) {
                this.pattern = pattern;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public List<String> getRoles() {
                return roles;
            }

            public void setRoles(List<String> roles) {
                this.roles = roles;
            }
        }
    }
}
