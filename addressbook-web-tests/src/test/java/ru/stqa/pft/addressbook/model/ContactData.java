package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table (name = "addressbook")
@XStreamAlias("contact")

public class ContactData {

    @Id
    @Column (name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column (name = "firstname")
    private String name;
    @Expose
    @Column (name = "lastname")
    private String surname;
    @Transient
    private String jobtitle;
    @Transient
    private String companyname;
    @Column (name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column (name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column (name = "work")
    @Type(type = "text")
    private String workPhone;
    @Column (name = "email")
    @Type(type = "text")
    private String email1;
    @Column (name = "email2")
    @Type(type = "text")
    private String email2;
    @Column (name = "email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String day;
    @Transient
    private String month;
    @Transient
    private String year;
    @Transient
    private String group;
    @Transient
    private String allPhones;
    @Column (name = "address")
    @Type(type = "text")
    private String address;
    @Transient
    private String allEmails;
    @Column (name = "photo")
    @Type(type = "text")
    private String photo;



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

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
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

    public String getAllPhones() {
            return allPhones;
    }

    public String getAddress() {
        return address;
    }

    public String getAllEmails() {
            return allEmails;
    }

    public File getPhoto() {
        if (photo != null) {
            return new File (photo);
        } else {
            return null; }
    }


    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ContactData withJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
        return this;
    }

    public ContactData withCompanyname(String companyname) {
        this.companyname = companyname;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.homePhone = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withDay(String day) {
        this.day = day;
        return this;
    }

    public ContactData withMonth(String month) {
        this.month = month;
        return this;
    }

    public ContactData withYear(String year) {
        this.year = year;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
