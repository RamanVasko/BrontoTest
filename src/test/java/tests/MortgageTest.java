package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class MortgageTest extends BaseTest {
    @Test
    public void mortgagePaymentCalculator() {
        calculatorPage.closeGetStartedDialogWindow();
        calculatorPage.enterMortgageAmount("200000");
        calculatorPage.enterInterestRate("5");
        calculatorPage.selectFromDropDown("30 years");
        calculatorPage.selectAnnuallyReportAmortizationRadioButton();
        calculatorPage.clickCalculateButton();
        assertTrue(calculatorPage.getMonthlyPayment().contains("$1,073.64"), "Monthly Payment is incorrect");
        assertTrue(calculatorPage.getTotalPayments().contains("$386,513"), "Total Payments is incorrect");
        assertTrue(calculatorPage.getTotalInterest().contains("$186,513"), "Total Interest is incorrect");
    }
}