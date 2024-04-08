package com.example.maid.api.response;

public class LoginResponse {
    private int status;
    private String message;
    private Data data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private boolean logged_in;
        private String uid;
        private String username;
        private String useremail;
        private String user_mobile;

        public boolean isLogged_in() {
            return logged_in;
        }

        public void setLogged_in(boolean logged_in) {
            this.logged_in = logged_in;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUseremail() {
            return useremail;
        }

        public void setUseremail(String useremail) {
            this.useremail = useremail;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }
    }

}
