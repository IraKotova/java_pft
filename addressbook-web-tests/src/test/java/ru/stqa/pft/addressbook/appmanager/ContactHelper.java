package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

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
        typecontact(By.name("home"), contactData.getHomePhone());
        typecontact(By.name("mobile"), contactData.getMobilePhone());
        typecontact(By.name("work"), contactData.getWorkPhone());
        typecontact(By.name("email"), contactData.getEmail1());
        typecontact(By.name("email2"), contactData.getEmail2());
        typecontact(By.name("email3"), contactData.getEmail3());
        clickcontact(By.name("bday"), contactData.getDay());
        clickcontact(By.name("bmonth"), contactData.getMonth());
        typecontact(By.name("byear"), contactData.getYear());
        clickcontact(By.name("new_group"), contactData.getGroup());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
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
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    private void typecontact(By locator, String text) {
        wd.findElement(locator).click();
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void initContactModification(int id) {
       // wd.findElements(By.name("edit")).get(id).click();
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
        WebElement row =checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void submitContactModification() {
        wd.findElement(By.xpath("(//input[@name='update'])[2]")).click();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void create(ContactData contactData, boolean b) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitNewContact();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
        confirmDeletionContact();
        closeConfirmContact();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        confirmDeletionContact();
        closeConfirmContact();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

/*    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        WebElement table = wd.findElement(By.id("maintable"));
        List<WebElement> elements = table.findElements(By.cssSelector("entry"));
        for (WebElement cell : elements) {
            List<WebElement> cells = cell.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(cell.findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(1).getText();
            String surname = cells.get(2).getText();
            ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname);
            contacts.add(contact);
        }
        return contacts;
    }
*/
/*    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = table.findElements(By.name("entry"));
        for (WebElement cell : elements) {
            List<WebElement> cells = cell.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(1).getText();
            String surname = cells.get(2).getText();
            ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname);
            contacts.add(contact);
        }
        return contacts;
    }
*/
    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement cell : elements) {
            List<WebElement> cells = cell.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String surname = cells.get(1).getText();
//            String[] phones = cells.get(5).getText().split("\n");
            String allPhones = cells.get(5).getText();
            String address = cells.get(3).getText();
            String[] emails = cells.get(4).getText().split("\n");
//            String allEmails = cells.get(4).getText();
            contactCache.add(new ContactData().withId(id).withName(name).withSurname(surname)
//                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
                    .withAllPhones(allPhones).withAddress(address)
                    .withEmail1(emails[0]).withEmail2(emails[1]).withEmail3(emails[2]));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String surname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name).withSurname(surname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail1(email1).withEmail2(email2).withEmail3(email3);
    }
}

