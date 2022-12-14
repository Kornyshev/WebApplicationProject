package me.kornyshev.WebCalculator.utils;

public class DatabaseQueries {

    private static final String DELETE_DATA_FROM_CALCULATOR = "delete from calculations";

    public static void deleteDataFromCalculator() {
        DbUtils.getInstance().executeUpdate(DELETE_DATA_FROM_CALCULATOR);
    }

}
