package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CalculatorPage {
    private WebDriver driver;

    private By mortgageAmountField = By.xpath("//input[@id='KJE-LOAN_AMOUNT']");
    private By getStartedButton = By.xpath("//a[@class='btn btn-primary btn-wide mt-5 transition-3d-hover']");
    private By closeMark = By.xpath("//span[@aria-hidden='true']");
    private By mortgagePaymentCalculatorTitle = By.xpath("//h1[@class='font-weight-medium h2']");
    private By calculateButton = By.id("KJECalculate");
    private By termInYearsDropDown = By.xpath("//select[@id='KJE-TERM']");
    private By interestRateField = By.id("KJE-INTEREST_RATE");
    private By annuallyReportAmortizationRadioButton = By.xpath("//input[@id='KJE-BY_YEAR1']");
    private By monthlyPayment = By.id("KJE-MONTHLY_PAYMENT");
    private By totalPayments = By.xpath("//h2[contains(text(),'Total Payments')]");
    private By totalInterest = By.xpath("//div[contains(text(),'Total Interest')]");

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeGetStartedDialogWindow() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(
                    driver.findElement(getStartedButton)));
            driver.findElement(closeMark).click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Close mark in Get Started dialog window" + e.getMessage());
        }
    }

    public void enterMortgageAmount(String mortgageAmount) {
        scrollToWebElementJS(driver.findElement(mortgagePaymentCalculatorTitle));
        try {
            if (driver.findElement(mortgageAmountField).isDisplayed()) {
                driver.findElement(mortgageAmountField).clear();
                driver.findElement(mortgageAmountField).sendKeys(mortgageAmount);
                driver.findElement(mortgageAmountField).sendKeys(Keys.ENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Mortgage Amount Field" + e.getMessage());
        }
    }

    public void selectFromDropDown(String option) {
        findDropDownElement().selectByVisibleText(option);
    }

    public void enterInterestRate(String rate) {
        try {
            if (driver.findElement(interestRateField).isDisplayed()) {
                driver.findElement(interestRateField).clear();
                driver.findElement(interestRateField).sendKeys(rate);
                driver.findElement(interestRateField).sendKeys(Keys.ENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Interest Rate Field" + e.getMessage());
        }
    }

    public void selectAnnuallyReportAmortizationRadioButton() {
        if (!driver.findElement(annuallyReportAmortizationRadioButton).isSelected())
            driver.findElement(annuallyReportAmortizationRadioButton).click();
    }

    public void clickCalculateButton() {
        try {
            if (driver.findElement(calculateButton).isEnabled())
                driver.findElement(calculateButton).click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on [Calculate] button" + e.getMessage());
        }
    }

    public String getMonthlyPayment() {
        return driver.findElement(monthlyPayment).getText();
    }

    public String getTotalPayments() {
        return driver.findElement(totalPayments).getText();
    }

    public String getTotalInterest() {
        return driver.findElement(totalInterest).getText();
    }

    public WebElement scrollToWebElementJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    private Select findDropDownElement() {
        return new Select(driver.findElement(termInYearsDropDown));
    }
}
