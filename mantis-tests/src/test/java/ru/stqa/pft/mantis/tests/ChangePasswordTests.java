package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase {

   @BeforeMethod

   public void startMailServer()  {

       app.mail().start();
}

@Test

public void testChangePassword() throws IOException, MessagingException {
   Users allUsers = app.db().allUsers();
   User admin =  app.db().users().get(app.changePassword().adminIndex());
   Users withoutAdmin = allUsers.withOut(admin);
   User user = withoutAdmin.iterator().next();
   String userName =  user.getName();
   String email = user.getEmail();
   app.changePassword().login();
   app.changePassword().resetPassword(userName);
   List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
   String confirmationLink =findConfirmationLink(mailMessages, email);
   app.changePassword().newPassword(confirmationLink, userName);
}

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}