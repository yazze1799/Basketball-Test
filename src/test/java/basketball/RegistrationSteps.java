package basketball;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationSteps {
    WebDriver driver;

    @Given("^I am on the registration page$")
    public void iAmOnTheRegistrationPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("^I fill in all required fields correctly$")
    public void iFillInAllRequiredFieldsCorrectly() {
        double randomNumber = Math.random() * 1000;
        WebElement dobField = driver.findElement(By.id("dp"));
        dobField.clear();
        dobField.sendKeys("03/02/1999");

        WebElement firstNameField = driver.findElement(By.id("member_firstname"));
        firstNameField.sendKeys("John");

        WebElement lastNameField = driver.findElement(By.id("member_lastname"));
        lastNameField.sendKeys("Doe");

        WebElement emailAddress = driver.findElement(By.id("member_emailaddress"));
        emailAddress.sendKeys("testing" + randomNumber + "@gmail.com");

        WebElement emailAddressConfirm = driver.findElement(By.id("member_confirmemailaddress"));
        emailAddressConfirm.sendKeys("testing" + randomNumber + "@gmail.com");

        WebElement password = driver.findElement(By.id("signupunlicenced_password"));
        password.sendKeys("pass123");

        WebElement passwordConfirm = driver.findElement(By.id("signupunlicenced_confirmpassword"));
        passwordConfirm.sendKeys("pass123");

        WebElement termsCheckbox = driver.findElement(By.xpath("//form[@id='signup_form']/div[11]/div/div[2]/div/label/span[3]"));
        termsCheckbox.click();

        WebElement ageCheckbox = driver.findElement(By.xpath("//form[@id='signup_form']/div[11]/div/div[2]/div[2]/label/span[3]"));
        ageCheckbox.click();

        WebElement codeOfConduct = driver.findElement(By.xpath("//form[@id='signup_form']/div[11]/div/div[7]/label/span[3]"));
        codeOfConduct.click();

    }

    @When("^I fill in all required fields except the last name$")
    public void iFillInAllRequiredFieldsExceptTheLastName() {
        iFillInAllRequiredFieldsCorrectly();

        WebElement lastNameField = driver.findElement(By.id("member_lastname"));
        lastNameField.clear();
    }

    @When("^I fill in all required fields with mismatched passwords$")
    public void iFillInAllRequiredFieldsWithMismatchedPasswords() {
        iFillInAllRequiredFieldsCorrectly();

        WebElement passwordConfirm = driver.findElement(By.id("signupunlicenced_confirmpassword"));
        passwordConfirm.clear();
        passwordConfirm.sendKeys("pass321");

    }

    @When("^I fill in all required fields without accepting the terms and conditions$")
    public void iFillInAllRequiredFieldsExceptTerms() {
        iFillInAllRequiredFieldsCorrectly();
        WebElement termsCheckbox = driver.findElement(By.xpath("//form[@id='signup_form']/div[11]/div/div[2]/div/label/span[3]"));
        termsCheckbox.click();
    }

    @When("^I submit the registration form$")
    public void iSubmitTheRegistrationForm() {
        WebElement submitButton = driver.findElement(By.xpath("//input[@name='join']"));
        submitButton.click();
    }

    @Then("^I should be redirected to a confirmation page$")
    public void iShouldSeeAConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/Account/SignUpConfirmation"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/Account/SignUpConfirmation"));
        driver.quit();
    }

    @Then("^I should see an error message indicating missing last name$")
    public void iShouldSeeAnErrorMessageIndicatingMissingLastName() {
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),'Last Name is required')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
        driver.quit();
    }

    @Then("^I should see an error message indicating password mismatch$")
    public void iShouldSeeAnErrorMessageIndicatingPasswordMismatch() {
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),'Password did not match')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
        driver.quit();
    }

    @Then("^I should see an error message indicating terms and conditions not accepted$")
    public void iShouldSeeAnErrorMessageIndicatingTermsAndConditionsNotAccepted() {
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),'You must confirm that you have read and accepted our Terms and Conditions')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
        driver.quit();
    }
}
