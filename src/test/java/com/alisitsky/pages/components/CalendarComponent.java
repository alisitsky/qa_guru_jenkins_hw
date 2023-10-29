package com.alisitsky.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOptionContainingText(month);
        $$("div .react-datepicker__day").findBy(text(day)).click();
    }
}
