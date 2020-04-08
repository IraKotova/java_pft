package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
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
}
