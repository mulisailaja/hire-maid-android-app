package com.example.maid.api.response.customer;

import java.util.ArrayList;

public class MaidStatusResponse {
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


    private ArrayList<MaidStatusInnerResponse> data;

    public ArrayList<MaidStatusInnerResponse> getData() {
        return data;
    }

    public void setData(ArrayList<MaidStatusInnerResponse> data) {
        this.data = data;
    }

    public class MaidStatusInnerResponse {

        private String book_id;
        private String name;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAssign_to() {
            return assign_to;
        }

        public void setAssign_to(String assign_to) {
            this.assign_to = assign_to;
        }

        public String getBooking_date() {
            return booking_date;
        }

        public void setBooking_date(String booking_date) {
            this.booking_date = booking_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private String amount;
        private String assign_to;
        private String booking_date;
        private String status;
        private String service;

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }
    }
}
