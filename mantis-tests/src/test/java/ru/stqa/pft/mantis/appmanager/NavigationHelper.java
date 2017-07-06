package ru.stqa.pft.mantis.appmanager;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager wd) {
        super((ApplicationManager) wd);
    }
    public void goToLoginPage() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }
    public void goToManageUserPage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }
}
