package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {

        MantisConnectPortType mc =  new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("web.baseUrl")+ "/api/soap/mantisconnect.php"));

        IssueData checkedIssue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));

        ObjectRef status = checkedIssue.getResolution();
        String issueStatus =   status.getName();
        if (issueStatus.equals("fixed")) {
            System.out.println("fixed");
            return true;

        } else
            System.out.println("notFixed");
        return false;
    }

    public void skipIfNotFixed(int issueID) throws RemoteException, ServiceException, MalformedURLException {

        if (isIssueOpen(issueID) == false) {
            throw new SkipException("Ignored because of Issue" + issueID);
        }

    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php", "config_inc.php.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.back", "config_inc.php");
        app.stop();
    }
}
