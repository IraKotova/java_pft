package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.User;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordHelper extends HelperBase {

    long now = System.currentTimeMillis();
    String password = app.getProperty("web.adminPassword")  + now;
    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }
    public void login() {
        wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys(app.getProperty("web.adminLogin"));
        wd.findElement(By.xpath("//input[@value='Login']")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(app.getProperty("web.adminPassword"));
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void resetPassword(String user) {

        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Summary'])[1]/following::i[1]")).click();
        wd.findElement(By.linkText("Manage Users")).click();
        wd.findElement(By.linkText(user)).click();
        wd.findElement(By.xpath("//input[@value='Reset Password']")).click();
    }

    public void newPassword(String confirmationLink, String user) throws IOException {
        wd.get(confirmationLink);
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(password);
        wd.findElement(By.id("password-confirm")).click();
        wd.findElement(By.id("password-confirm")).clear();
        wd.findElement(By.id("password-confirm")).sendKeys(password);
        wd.findElement(By.xpath("//button[@type='submit']")).click();
        HttpSession session = app.newSession();
        assertTrue(session.login(user, password));


    }
    public int adminIndex() {
        List<User> users = app.db().users();
        for (int i = 0; i < users.size(); i++) {
            String adminName = users.get(i).getName();
            if (adminName.equals("administrator")) {
                return i;
            }
        }
        return Integer.parseInt(null);
    }

}




