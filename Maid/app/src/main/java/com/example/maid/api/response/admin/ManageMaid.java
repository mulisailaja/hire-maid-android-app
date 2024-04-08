package com.example.maid.api.response.admin;

import java.util.ArrayList;

public class ManageMaid {
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

    public ArrayList<ManageMaidInnerClass> getData() {
        return data;
    }

    public void setData(ArrayList<ManageMaidInnerClass> data) {
        this.data = data;
    }

    private ArrayList<ManageMaidInnerClass> data;
    public class ManageMaidInnerClass {
        private String Proficient;
        private String Name;
        private String Email;
        private String ContactNumber;
        private String Experience;
        private String Location;

        private String Willing_to_Work;

        public String getProficient() {
            return Proficient;
        }

        public void setProficient(String proficient) {
            Proficient = proficient;
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

        public String getContactNumber() {
            return ContactNumber;
        }

        public void setContactNumber(String contactNumber) {
            ContactNumber = contactNumber;
        }

        public String getExperience() {
            return Experience;
        }

        public void setExperience(String experience) {
            Experience = experience;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        public String getWilling_to_Work() {
            return Willing_to_Work;
        }

        public void setWilling_to_Work(String willing_to_Work) {
            Willing_to_Work = willing_to_Work;
        }

        public String getPreferredLocations() {
            return preferredLocations;
        }

        public void setPreferredLocations(String preferredLocations) {
            this.preferredLocations = preferredLocations;
        }

        public String getDate_of_Registration() {
            return Date_of_Registration;
        }

        public void setDate_of_Registration(String date_of_Registration) {
            Date_of_Registration = date_of_Registration;
        }

        private String preferredLocations;
        private String Date_of_Registration;
    }
}
