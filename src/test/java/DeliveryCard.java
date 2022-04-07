import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;


public class DeliveryCard {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;

    }

    @Test
    void shouldRegisterCard() {
        browserSize = "1900x900";
        String currentDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@type='text']").val("Ижевск");
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[name='name']").setValue("Акакий Акакиев");
        $("[name='phone']").setValue("+88005553535");
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + currentDate), Duration.ofSeconds(15));

    }


    @Test
    void noCity() {

        browserSize = "1900x900";
        String currentDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[name='name']").setValue("Акакий Акакиев");
        $("[name='phone']").setValue("+88005553535");
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='city'] .input__sub").shouldHave(text("Поле обязательно для заполнения"), Duration.ofSeconds(10));

    }


    @Test
    void noName() {
        browserSize = "1900x900";
        String currentDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@type='text']").val("Ижевск");
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[name='phone']").setValue("+88005553535");
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='name'] .input__sub").shouldHave(text("Поле обязательно для заполнения"), Duration.ofSeconds(10));

    }


    @Test
    void noPhone() {
        browserSize = "1900x900";
        String currentDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@type='text']").val("Ижевск");
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[name='name']").setValue("Акакий Акакиев");
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Поле обязательно для заполнения"), Duration.ofSeconds(10));

    }

    @Test
    void noCheck() {
        browserSize = "1900x900";
        String currentDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@type='text']").val("Ижевск");
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[name='name']").setValue("Акакий Акакиев");
        $("[name='phone']").setValue("+88005553535");
        $(".button__text").click();
        $("[data-test-id='agreement']").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"), Duration.ofSeconds(10));

    }

    @Test
    void wrongCity() {
        browserSize = "1900x900";
        String currentDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@type='text']").val("Ижевску");
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[name='name']").setValue("Акакий Акакиев");
        $("[name='phone']").setValue("+88005553535");
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='city'] .input__sub").shouldHave(text("Доставка в выбранный город недоступна"), Duration.ofSeconds(10));
    }

    @Test
    void psykeThatsAWrongNumber() {
        browserSize = "1900x900";
        String currentDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@type='text']").val("Ижевск");
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[name='name']").setValue("Акакий Акакиев");
        $("[name='phone']").setValue("+880055535");
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), Duration.ofSeconds(10));
    }
}