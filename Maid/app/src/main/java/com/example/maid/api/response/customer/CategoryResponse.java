package com.example.maid.api.response.customer;

import java.util.ArrayList;

public class CategoryResponse {
    public int status;
    public String message;
    public ArrayList<CategoryInnerResponse> data;

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

    public ArrayList<CategoryInnerResponse> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryInnerResponse> data) {
        this.data = data;
    }

    public class CategoryInnerResponse{
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getPer_hour_amount() {
            return per_hour_amount;
        }

        public void setPer_hour_amount(String per_hour_amount) {
            this.per_hour_amount = per_hour_amount;
        }

        public String getMonthly_amount() {
            return monthly_amount;
        }

        public void setMonthly_amount(String monthly_amount) {
            this.monthly_amount = monthly_amount;
        }

        private String category_name;
        private String per_hour_amount;
        private String monthly_amount;
    }
}
