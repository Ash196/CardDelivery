package ru.netology;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDelivery {


    @Test
    void shouldTestAllFields() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String dayVisit = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Александр Кузнецов");
        $("[data-test-id=phone] input").setValue("+79944411122");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dayVisit));
    }

    @Test
    void shouldValidateNameIsEng() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String dayVisit = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Alexsandr Kuznetsov");
        $("[data-test-id=phone] input").setValue("+79944411122");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Ошибка!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldValidateNoName() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String dayVisit = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79944411122");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Ошибка!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Поле обязательно для заполнения."));

    }
    @Test
    void shouldValidateNoPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String dayVisit = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Александр Кузнецов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Ошибка!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Поле обязательно для заполнения."));

    }
    @Test
    void shouldValidateNameIsNumber() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String dayVisit = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("123123123");
        $("[data-test-id=phone] input").setValue("+79944411122");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Ошибка!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
    @Test
    void shouldValidateNoCheckBox() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String dayVisit = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Александр Кузнецов");
        $("[data-test-id=phone] input").setValue("+79944411122");
        //$("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Ошибка!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных."));

    }


}
