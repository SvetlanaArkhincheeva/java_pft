package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangeUserPasswordByAdmin() throws IOException, MessagingException {
        app.navigation().goToLoginPage();
        app.user().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"));
        app.navigation().goToManageUserPage();
        String newPass = app.getProperty("web.userNewPass");
        String user="user1499160159474";
        String email = "user1499160159474@localhost.localdomain";
        app.helper().select(user);
        app.user().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, newPass);
        HttpSession sessionUser = app.newSession();
        assertTrue(sessionUser.login(user, newPass));
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
