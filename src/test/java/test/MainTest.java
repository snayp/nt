package test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MainTest {

    CurrenciesPage cp = new CurrenciesPage();

    @BeforeEach
    void setUp() {
        cp.setUp();
        open("https://www.sberbank.ru/ru/quotes/currencies");
    }

    @AfterEach
    void teardown() {
        Selenide.close();
    }

    @Test
    void checkFirstViewTest() {
        cp.checkTitle();
        cp.checkCurrForCompare();
        cp.checkCurrDrop();
        cp.checkPeriod();
        cp.ratesRightCheck();
        cp.detailsRatesUSDCheck();
    }

    @Test
    void checkCurrTest() {
        cp.currCheck();
    }
}
