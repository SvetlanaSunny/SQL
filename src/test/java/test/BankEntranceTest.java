package test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class BankEntranceTest {

    @BeforeEach
    void login() {
        open("http://localhost:9999");
    }
    @AfterAll
    static void tearDown(){
        cleanDatabase();
    }

    @Test
    void shouldSuccessfullLogin() {
        var loginPage = new LoginPage();
        var autoInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(autoInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldErrorUnSuccessfullVerificationCode() {
        var loginPage = new LoginPage();
        var autoInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(autoInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.verifyCorrect(verificationCode.getCode());
        verificationPage.verifyErrorNotificationVisibility();
    }

    @Test
    void shouldShowErrorWhileUser(){
        var login = new LoginPage();
        var autoInfo = DataHelper.generateRandomUser();
        login.validLogin(autoInfo);
        login.verifyErrorNotificationVisibility();
    }






}
