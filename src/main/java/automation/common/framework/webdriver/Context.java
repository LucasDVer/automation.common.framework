package automation.common.framework.webdriver;

import automation.common.framework.datamanagement.EnvironmentProvider;
import automation.common.framework.datamanagement.ExecutionPropertiesProvider;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Context {

    private static Context instance;
    private String ENVIRONMENT = ExecutionPropertiesProvider.getString("environment").replaceAll(" ", "");

    private Context() {
    }

    public static synchronized Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public static String getEnvironment() {
        return getInstance().ENVIRONMENT;
    }

    public static void initializePageInstance(WebDriver driver, String app)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        driver.get(EnvironmentProvider.getUrl(app + "." + getEnvironment()));
        System.out.println("---");
        System.out.println("ENVIRONMENT: " + EnvironmentProvider.getUrl(getEnvironment()));
    }
}
