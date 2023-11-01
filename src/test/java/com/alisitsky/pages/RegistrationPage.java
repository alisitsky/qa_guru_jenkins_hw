package com.alisitsky.pages;

import com.codeborne.selenide.SelenideElement;
import com.alisitsky.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    CalendarComponent calendar = new CalendarComponent();
    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            modalContent = $(".modal-content"),
            modalHeader = $(".modal-content .modal-header"),
            birthDateInput = $(".react-datepicker-wrapper"),
            genderRadio = $("#genterWrapper"),
            phoneNumberInput = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            hobbiesCheckBoxes = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            stateCityDropDown = $("#stateCity-wrapper"),
            submitButton = $("button#submit"),
            studentNameLine = $$("div.table-responsive table tbody tr").findBy(text("Student Name")),
            studentEmailLine = $$("div.table-responsive table tbody tr").findBy(text("Student Email")),
            genderLine = $$("div.table-responsive table tbody tr").findBy(text("Gender")),
            mobileLine = $$("div.table-responsive table tbody tr").findBy(text("Mobile")),
            dateOfBirthLine = $$("div.table-responsive table tbody tr").findBy(text("Date of Birth")),
            subjectsLine = $$("div.table-responsive table tbody tr").findBy(text("Subjects")),
            hobbiesLine = $$("div.table-responsive table tbody tr").findBy(text("Hobbies")),
            pictureLine = $$("div.table-responsive table tbody tr").findBy(text("Picture")),
            addressLine = $$("div.table-responsive table tbody tr").findBy(text("Address")),
            stateAndCityLine = $$("div.table-responsive table tbody tr").findBy(text("State and City"));

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        birthDateInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderRadio.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setPhoneNumber(String value) {
        phoneNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbie(String value) {
        hobbiesCheckBoxes.$(byText(value)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        uploadPictureInput.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        stateInput.click();
        stateCityDropDown.$(byText(state)).click();
        cityInput.click();
        stateCityDropDown.$(byText(city)).click();
        return this;
    }

    public RegistrationPage submitButtonClick() {
        submitButton.scrollTo().click();
        return this;
    }

    public RegistrationPage checkModalContentVisible() {
        modalContent.shouldBe(visible);
        return this;
    }

    public RegistrationPage checkModalContentHasHeader(String value) {
        modalHeader.shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkStudentNameValueVisible(String firstName, String lastName) {
        studentNameLine.shouldHave(text(firstName + " " + lastName));
        return this;
    }

    public RegistrationPage checkStudentEmailValueVisible(String value) {
        studentEmailLine.shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkGenderValueVisible(String value) {
        genderLine.shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkMobileValueVisible(String value) {
        mobileLine.shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkBirthDateValueVisible(String day, String month, String year) {
        dateOfBirthLine.shouldHave(text(day + " " + month + "," + year));
        return this;
    }

    public RegistrationPage checkSubjectsValueVisible(String value) {
        subjectsLine.shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkHobbiesValueVisible(String value) {
        hobbiesLine.shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkPictureValueVisible(String value) {
        pictureLine.shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkAddressValueVisible(String value) {
        addressLine.shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkStateAndCityValueVisible(String state, String city) {
        stateAndCityLine.shouldHave(text(state + " " + city));
        return this;
    }
}