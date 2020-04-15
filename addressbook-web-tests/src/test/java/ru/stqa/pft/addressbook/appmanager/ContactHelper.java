package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.UpdateContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitNewContact() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        typecontact(By.name("firstname"), contactData.getName());
        typecontact(By.name("lastname"), contactData.getSurname());
        typecontact(By.name("title"), contactData.getJobtitle());
        typecontact(By.name("company"), contactData.getCompanyname());
        typecontact(By.name("home"), contactData.getPhone());
        typecontact(By.name("email"), contactData.getEmail());
        clickcontact(By.name("bday"), contactData.getDay());
        clickcontact(By.name("bmonth"), contactData.getMonth());
        typecontact(By.name("byear"), contactData.getYear());
        clickcontact(By.name("new_group"), contactData.getGroup());
    }

    private void clickcontact(By locator, String param) {
        wd.findElement(locator).click();
        new Select(wd.findElement(locator)).selectByVisibleText(param);
        wd.findElement(locator).click();
    }

    private void typecontact(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void initContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void initContactModification() {
        click(By.cssSelector("img[alt=\"Edit\"]"));
    }

    public void submitContactModification() {
        wd.findElement(By.xpath("(//input[@name='update'])[2]")).click();
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void confirmDeletionContact() {
        wd.switchTo().alert().accept();

    }

    public void closeConfirmContact() {
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void updateContactForm(UpdateContactData updateContactData) {
        typecontact(By.name("firstname"), updateContactData.getName());
        typecontact(By.name("lastname"), updateContactData.getSurname());
        typecontact(By.name("title"), updateContactData.getJobtitle());
        typecontact(By.name("company"), updateContactData.getCompanyname());
        typecontact(By.name("home"), updateContactData.getPhone());
        typecontact(By.name("email"), updateContactData.getEmail());
        clickcontact(By.name("bday"), updateContactData.getDay());
        clickcontact(By.name("bmonth"), updateContactData.getMonth());
        typecontact(By.name("byear"), updateContactData.getYear());
    }
}
