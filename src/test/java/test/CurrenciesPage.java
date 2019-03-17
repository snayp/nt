package test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CurrenciesPage {
    SelenideElement title = $("h1.header_widget");
    ElementsCollection filterTitlesText = $$("h6.rates-aside__filter-title-text");
    ElementsCollection allCurr = $$("div.select div span");
    SelenideElement header = $("div.select header em");
    SelenideElement curensies = $(byAttribute("role", "group"));
    ElementsCollection currCheckBox = $$(byAttribute("role", "checkbox"));
    SelenideElement radioGroup = $(".kit-radio-group");
    ElementsCollection ratesDetails = $$(".rates-details");
    SelenideElement ratesRightHeader = $(".rates-current__table tr", 0);
    ElementsCollection ratesRightRowsUSD = $$(".rates-current__table-row").excludeWith(cssClass("rates-current__table-row_header"));
    ElementsCollection ratesRightRowsAll = $$(".rates-current__table-row.rates-current__table-row_odd").excludeWith(cssClass("rates-current__table-row_header"));
    ElementsCollection ratesRightCell = $$("td.rates-current__table-cell rates-current__table-cell_column_name").excludeWith(cssClass("rates-current__table-cell_column_name"));


    public void setUp() {
        Configuration.timeout = 10000;
    }

    void checkTitle() {
        title.shouldHave(text("Курсы иностранных валют в отделениях для наличных и безналичных (некарточных) конверсий"));
    }

    void checkCurrForCompare() {
        filterTitlesText.shouldHave(CollectionCondition.size(3));
        filterTitlesText.shouldHave(texts("Валюты для сравнения", "Выбрать валюту", "Период"));
        curensies.shouldHave(text("Евро Доллар США"));
        currCheckBox.shouldHave(CollectionCondition.size(2));
        currCheckBox.findBy(text("Доллар США")).shouldHave(cssClass("kit-checkbox_checked"));
        currCheckBox.findBy(text("Евро")).shouldNotHave(cssClass("kit-checkbox_checked"));

    }

    void checkCurrDrop() {
        header.click();
        allCurr.shouldHave(texts("Китайский юань Жэньминьби", "Польский злотый", "Датская крона", "Гонконгский доллар", "Белорусский рубль", "Канадский доллар", "Швейцарский франк", "Чешская крона", "Фунт стерлингов Соединенного Королевства", "Японская иена", "Казахский тенге", "Норвежская крона", "Шведская крона", "Сингапурский доллар"));

    }

    void checkPeriod() {
        header.click();
        radioGroup.shouldHave(text("Месяц Полгода Квартал Год Произвольный"));
    }

    void ratesRightCheck() {
        ratesRightHeader.shouldHave(text("Валюта Покупка Продажа"));
        ratesRightRowsUSD.shouldHave(texts("USD / RUB"));
    }

    void detailsRatesUSDCheck() {
        ratesDetails.shouldHave(texts("Доллар США"));
    }

    //TODO доделать тест на выбор разных валют.
    void euroCheck() {
        currCheckBox.findBy(text("Евро")).click();
        ratesRightRowsAll.shouldHave(texts("EUR / RUB"));
        ratesDetails.shouldHave(texts("Евро"));
    }

}