package com.example.maid.api.response.admin;

import java.util.ArrayList;

public class AssignedRequestResponse {
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

    public ArrayList<AssignedRequestInner> getData() {
        return data;
    }

    public void setData(ArrayList<AssignedRequestInner> data) {
        this.data = data;
    }

    private ArrayList<AssignedRequestInner> data;
    public class AssignedRequestInner{
        private String booking_id;
        private String name;
        private String email;
        private String contact_Number;
        private String booking_date;
        private String assignTo;
        private String status;

        public String getBooking_id() {
            return booking_id;
        }

        public void setBooking_id(String booking_id) {
            this.booking_id = booking_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContact_Number() {
            return contact_Number;
        }

        public void setContact_Number(String contact_Number) {
            this.contact_Number = contact_Number;
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
