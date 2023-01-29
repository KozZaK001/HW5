package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LessonTest extends AbstractTest {

    @Test
    void myActiontest() throws InterruptedException {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("https://www.vstu.ru"));
        Assertions.assertTrue(getDriver().getTitle().contains("университет"), "страница входа недоступна");

        // Создадим экземпляр класса Actions
        Actions search = new Actions(getDriver());

        search.click(getDriver().findElement(By.cssSelector(".sb-icon-search")))
                .pause(1000l)
                .sendKeys(getDriver().findElement(By.cssSelector(".sb-search-input")), "кравец")
                .pause(1000l)
                .click(getDriver().findElement(By.cssSelector(".sb-search-submit")))
                .build()
                .perform();

        Thread.sleep(1000);
    }

    @Test
    void jsTest() throws InterruptedException {
        // Приводим Driver к интерфейсу Javascript
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        long windowWidth = (long) jsExecutor.executeScript("return window.innerWidth");
        System.out.println("Размер окна " + windowWidth);
        jsExecutor.executeScript("window.scrollBy(0,500)");

        Thread.sleep(1000);
        getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);

    }

    @Test
    void cookieTest() {
        getDriver().manage().addCookie(new Cookie("promo_code", "november2"));
        for (Cookie cookie : getDriver().manage().getCookies()) {
            System.out.println(cookie.getName() + " " + cookie.getExpiry());
        }

        getDriver().manage().deleteCookie(new Cookie("promo_code", "november2"));

    }
}