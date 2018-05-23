package com.yuehai.android.common.bean;

import java.util.List;

public class UsersBean extends BaseBean2 {

    //    {
//        "code": 0,
//            "list": [
//        {
//            "id": 1,
//                "mobile": "18511073583",
//                "password": "123456",
//                "userName": "月海"
//        }
//  ],
//        "message": "success"
//    }
    private List<User> list;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public static class User {
        private int id;
        private String mobile;
        private String password;
        private String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "id:" + id + "userName:" + userName + "\npassword:" + password + "\nmobile:" + mobile;
        }
    }
}
