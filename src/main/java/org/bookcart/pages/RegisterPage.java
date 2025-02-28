package org.bookcart.pages;

import io.qameta.allure.Step;
import org.bookcart.model.User;
import org.bookcart.pages.base.BasePage;
import org.bookcart.util.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    private final String passwordErrorMessage = "Password should have minimum 8 characters, at least 1 uppercase letter, 1 lowercase letter and 1 number";
    private final By firstNameTextField = By.xpath("//input[@placeholder='First name']");
    private final By lastNameTextField = By.xpath("//input[@placeholder='Last Name']");
    private final By userNameTextField = By.xpath("//input[@placeholder='User name']");
    private final By passwordTextField = By.xpath("//input[@placeholder='Password']");
    private final By confirmPasswordTextField = By.xpath("//input[@placeholder='Confirm Password']");
    private final By maleGenderRadioButton = By.id("mat-radio-0");
    private final By femaleGenderRadioButton = By.id("mat-radio-3-input");
    private final By registerButton = By.xpath("//span[text()='Register']");
    private final By invalidUsernameErrorMessage = By.xpath("//mat-error[text()='User Name is not available']");
    private final By invalidPasswordErrorMessage = By.xpath("//mat-error[contains(text(), '"+passwordErrorMessage+"')]");
    private final By passwordNotMatchingError = By.xpath("//mat-error[text()= ' Password do not match ']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Entering new user details")
    public void enterNewUserDetails(User user) {
        sendKeys(user.getFirstName(), firstNameTextField);
        sendKeys(user.getLastName(), lastNameTextField);
        sendKeys(user.getUserName(), userNameTextField);
        sendKeys(user.getPassword(), passwordTextField);
        sendKeys(user.getConfirmPassword(), confirmPasswordTextField);

        if (user.getGender().equalsIgnoreCase("Female")) {
            click(femaleGenderRadioButton);
        } else {
            click(maleGenderRadioButton);
        }
    }

    public void clickOnRegisterButton() {
        // TODO The element is getting clicked but nothing happens in website,
        //  tried with WebDriverWait but not working.
        WaitUtils.forcedDelay(1);
        click(registerButton);
    }

    public boolean isUsernameErrorMessageDisplayed(){
        return isDisplayed(invalidUsernameErrorMessage);
    }

    public boolean isInvalidPasswordErrorMessageDisplayed(){
        return isDisplayed(invalidPasswordErrorMessage);
    }

    public boolean isPasswordNotMatchingErrorMessageDisplayed(){
        return isDisplayed(passwordNotMatchingError);
    }
}
