package com.alisitsky.utils;

import com.alisitsky.testData.RegistrationPageData;
import com.github.javafaker.Faker;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RandomUtils {

    static Faker faker = new Faker();
    static RegistrationPageData registrationPageData = new RegistrationPageData();

    public static String getRandomGender() {
        return faker.options().option(RegistrationPageData.genders);
    }

    public static String getRandomSubject() {
        return faker.options().option(RegistrationPageData.subjects);
    }

    public static String getRandomState() {
        return faker.options().option(RegistrationPageData.states);
    }

    public static String getRandomHobbie() {
        return faker.options().option(RegistrationPageData.hobbies);
    }

    public static String getRandomCity(String[] cities) {
        return faker.options().option(cities);
    }

    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().cellPhone().replaceAll("[.\\s()-]", "").substring(0, 10);
    }

    public static String getYearFromBirthDate(String dateString) {
        return dateString.substring(dateString.length() - 4);
    }

    public static String getMonthFromBirthDate(String dateString) {
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            SimpleDateFormat monthFullNameFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);

            Date inputDate = inputDateFormat.parse(dateString);
            String monthName = monthFullNameFormat.format(inputDate);

            return monthName;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDayFromBirthDate(String dateString) {
        String day = dateString.substring(8, 10);
        return (day.charAt(0) == '0') ? day.charAt(1) + "" : day;
    }

    public static Map<String, String[]> createStateAndCityMap(String[] states) throws NoSuchFieldException, IllegalAccessException {

        Map<String, String[]> stateAndCityMap = new HashMap<>();

        int numberOfStates = states.length;

        for (int i = 1; i <= numberOfStates; i++) {
            String citiesArrayFieldName = "citiesForState" + i;
            Field field = registrationPageData.getClass().getDeclaredField(citiesArrayFieldName);
            field.setAccessible(true);
            String[] citiesArray = (String[]) field.get(registrationPageData);
            stateAndCityMap.put(states[i - 1], citiesArray);
        }
        return stateAndCityMap;
    }

    public static String getRandomCityForChosenState(String state) throws NoSuchFieldException, IllegalAccessException {
        String[] cities = createStateAndCityMap(registrationPageData.states).get(state);
        return getRandomCity(cities);
    }
}
