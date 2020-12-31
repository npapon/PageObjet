package com.openclassrooms.testing.calcul.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.openclassrooms.testing.calcul.e2e.page.CalculatorPage;

import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith( SpringExtension.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class StudentMultiplicationJourneyE2E {

    @LocalServerPort
    private Integer   port;
    private WebDriver webDriver = null;
    private String    baseUrl;

    @BeforeAll
    public static void setUpFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();

    }

    @BeforeEach
    public void setUpWebDriver() {
        webDriver = new FirefoxDriver();
        baseUrl = "http://localhost:" + port + "/calculator";
    }

    @AfterEach
    public void quitWebDriver() {
        if ( webDriver != null ) {
            webDriver.quit();
        }
    }

    @Test
    public void aStudentUsesTheCalculatorToMultiplyTwoBySixteen() {

        // GIVEN
        webDriver.get( baseUrl );
        /*
         * final WebElement leftField = webDriver.findElement( By.id( "left" )
         * ); final WebElement typeDropdown = webDriver.findElement( By.id(
         * "type" ) ); final WebElement rightField = webDriver.findElement(
         * By.id( "right" ) ); final WebElement submitButton =
         * webDriver.findElement( By.id( "submit" ) );
         */
        CalculatorPage calculatorPage = new CalculatorPage( webDriver );

        // WHEN

        /*
         * leftField.sendKeys( "2" ); typeDropdown.sendKeys( "x" );
         * rightField.sendKeys( "16" ); submitButton.click();
         */

        final String solution = calculatorPage.multiply( "2", "16" );

        // THEN
        /*
         * final WebDriverWait waiter = new WebDriverWait( webDriver, 5 ); final
         * WebElement solutionElement = waiter.until(
         * ExpectedConditions.presenceOfElementLocated( By.id( "solution" ) ) );
         * final String solution = solutionElement.getText();
         */
        assertThat( solution ).isEqualTo( "32" ); // 2 x 16
    }
}
