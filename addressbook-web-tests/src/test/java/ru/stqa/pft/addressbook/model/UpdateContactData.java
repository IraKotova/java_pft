package ru.stqa.pft.addressbook.model;

public class UpdateContactData {
        private final String name;
        private final String surname;
        private final String jobtitle;
        private final String companyname;
        private final String phone;
        private final String email;
        private final String day;
        private final String month;
        private final String year;


        public UpdateContactData(String name, String surname, String jobtitle, String companyname, String phone, String email, String day, String month, String year) {
            this.name = name;
            this.surname = surname;
            this.jobtitle = jobtitle;
            this.companyname = companyname;
            this.phone = phone;
            this.email = email;
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getJobtitle() {
            return jobtitle;
        }

        public String getCompanyname() {
            return companyname;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getDay() {
            return day;
        }

        public String getMonth() {
            return month;
        }

        public String getYear() {
            return year;
        }
    }

