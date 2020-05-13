package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.UpdateContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitNewContact() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
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

    if (creation){
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    }

    private void clickcontact(By locator, String param) {
        if (param != null) {
            new Select(wd.findElement(locator)).selectByVisibleText(param);
            wd.findElement(locator).click();
        }
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    private void typecontact(By locator, String text) {
        wd.findElement(locator).click();
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public void createContact(ContactData contactData, boolean b) {
        initContactCreation();
        fillContactForm(new ContactData("Ivan", "Ivanov", "tester", "Testcom", "8123456789", "test@test.com", "1", "February", "1990", "test1"),true);
        submitNewContact();
        returnToHomePage();
    }

    public void modifyContact(int index, ContactData contact) {
        selectContact(index);
        fillContactForm(contact,false);
        submitContactModification();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("entry"));
        for (WebElement cell : elements) {
            List<WebElement> cells = cell.findElements(By.cssSelector("td"));
                String name = cells.get(1).getText();
                String surname = cells.get(2).getText();
                int id1 = Integer.parseInt(cell.findElements(By.tagName("input")).get(1).getAttribute("value"));
                int id2 = Integer.parseInt(cell.findElements(By.tagName("input")).get(2).getAttribute("value"));
                ContactData contact = new ContactData(name, surname, null, null, null, null, null, null, null, null);
                contacts.add(contact);
        }
        return contacts;
    }
}
