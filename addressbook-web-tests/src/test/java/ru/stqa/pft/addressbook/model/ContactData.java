package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private final String name;
    private final String surname;
    private final String jobtitle;
    private final String companyname;
    private final String phone;
    private final String email;
    private final String day;
    private final String month;
    private final String year;
    private final String group;

    public ContactData(String name, String surname, String jobtitle, String companyname, String phone, String email, String day, String month, String year, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.surname = surname;
        this.jobtitle = jobtitle;
        this.companyname = companyname;
        this.phone = phone;
        this.email = email;
        this.day = day;
        this.month = month;
        this.year = year;
        this.group = group;
    }
    public ContactData(int id, String name, String surname, String jobtitle, String companyname, String phone, String email, String day, String month, String year, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jobtitle = jobtitle;
        this.companyname = companyname;
        this.phone = phone;
        this.email = email;
        this.day = day;
        this.month = month;
        this.year = year;
        this.group = group;
    }

    public int getId() {
        return id;
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

    public String getGroup() {
        return group;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }
}
