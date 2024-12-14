package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest extends TestBase {

    private static  final String REPOSITORY = "eroshenkoam/allure-example";
    private static  final int ISSUES = 80;

    @Test
   public void testIssueSearch () {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();
                });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step ("Открываем таб Issues", () -> {
                    $("#issues-tab").click();
                });
        step ("Проверяем наличие issues с номером" + ISSUES, () -> {
            $(withText("#" + ISSUES)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        WebStepsTest steps = new WebStepsTest();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssuesWithNumber(ISSUES);
    }
}
