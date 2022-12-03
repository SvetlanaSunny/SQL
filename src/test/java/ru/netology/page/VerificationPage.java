package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement buttonVerify = $("[data-test-id=action-verify]");
    private SelenideElement unverifyCode = $ ("[data-test-id=error-notification]");

    public void verifyVerificationPageVisibility() {
        codeField.shouldBe(visible);
    }

    public void verifyErrorNotificationVisibility(){
        unverifyCode.shouldBe(visible);
    }

    public void verifyCorrect(String verificationCode){
        codeField.setValue(verificationCode);
        buttonVerify.click();
    }

    public DashboardPage validVerify(String verificationCode) {
        verifyCorrect(verificationCode);
        return new DashboardPage();
    }


}
