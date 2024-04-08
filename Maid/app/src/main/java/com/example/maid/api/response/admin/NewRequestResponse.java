package com.example.maid.api.response.admin;

import java.util.ArrayList;

public class NewRequestResponse {
    private int status;
    private String message;

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

    public ArrayList<NewRequestResponseInner> getData() {
        return data;
    }

    public void setData(ArrayList<NewRequestResponseInner> data) {
        this.data = data;
    }

    private ArrayList<NewRequestResponseInner> data;

    public class NewRequestResponseInner{
        private String booking_id;
        private String Name;
        private String Email;
        private String Contact_Number;
        private String booking_date;
        private String assignTo;
        private String status;
        private String CategoryName;

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String categoryName) {
            CategoryName = categoryName;
        }

        public String getBooking_id() {
            return booking_id;
        }

        public void setBooking_id(String booking_id) {
            this.booking_id = booking_id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getContact_Number() {
            return Contact_Number;
        }

        public void setContact_Number(String contact_Number) {
            Contact_Number = contact_Number;
        }

        public String getBooking_date() {
            return booking_date;
        }

        public void setBooking_date(String booking_date) {
            this.booking_date = booking_date;
        }

        public String getAssignTo() {
            return assignTo;
        }

        public void setAssignTo(String assignTo) {
            this.assignTo = assignTo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
