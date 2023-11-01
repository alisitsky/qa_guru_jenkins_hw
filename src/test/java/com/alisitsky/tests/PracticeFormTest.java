package com.alisitsky.tests;

import com.alisitsky.pages.RegistrationPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static com.alisitsky.utils.RandomUtils.*;
import static io.qameta.allure.Allure.step;

public class PracticeFormTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    @Test
    public void fillFormsAndSubmitWithPageObjectsTest() throws NoSuchFieldException, IllegalAccessException {
        Attachments attachments = new Attachments();

        String userFirstName = faker.name().firstName(),
                userLastName = faker.name().lastName(),
                userEmail = faker.internet().emailAddress(),
                userGender = getRandomGender(),
                userPhoneNumber = getRandomPhoneNumber(),
                userBirthDate = faker.date().birthday().toString(),
                userSubject = getRandomSubject(),
                userHobby = getRandomHobbie(),
                pathToPicture = "com/alisitsky/FillFormsAndSubmitPicture.jpg",
                pictureFileName = pathToPicture.substring(pathToPicture.lastIndexOf("/") + 1),
                userAddress = faker.address().fullAddress(),
                userState = getRandomState(),
                userCity = getRandomCityForChosenState(userState);

        step("Открыть форму регистрации", () -> {
            registrationPage
                    .openPage()
                    .removeBanners();
            attachments.takeScreenshot();
        });

        step("Заполнить все поля", () -> {
            registrationPage.setFirstName(userFirstName)
                    .setLastName(userLastName)
                    .setUserEmail(userEmail)
                    .setGender(userGender)
                    .setPhoneNumber(userPhoneNumber)
                    .setBirthDate(getDayFromBirthDate(userBirthDate),
                            getMonthFromBirthDate(userBirthDate),
                            getYearFromBirthDate(userBirthDate))
                    .setSubject(userSubject)
                    .setHobbie(userHobby)
                    .uploadPicture(pathToPicture)
                    .setCurrentAddress(userAddress)
                    .setStateAndCity(userState, userCity);
            attachments.takeScreenshot();
        });

        step("Нажать кнопку Submit", () -> {
            registrationPage.submitButtonClick();

        });

        step("Проверить контент в модальном окне", () -> {
            registrationPage.checkModalContentVisible()
                    .checkModalContentHasHeader("Thanks for submitting the form")
                    .checkStudentNameValueVisible(userFirstName, userLastName)
                    .checkStudentEmailValueVisible(userEmail)
                    .checkGenderValueVisible(userGender)
                    .checkMobileValueVisible(userPhoneNumber)
                    .checkBirthDateValueVisible(getDayFromBirthDate(userBirthDate),
                            getMonthFromBirthDate(userBirthDate),
                            getYearFromBirthDate(userBirthDate))
                    .checkSubjectsValueVisible(userSubject)
                    .checkHobbiesValueVisible(userHobby)
                    .checkPictureValueVisible(pictureFileName)
                    .checkAddressValueVisible(userAddress)
                    .checkStateAndCityValueVisible(userState, userCity);
        });
    }
}