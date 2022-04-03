package entities;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender parseValue(String string) {
        return Gender.valueOf(string.toUpperCase());
    }

    public static String getSimpleValue(Gender gender) {
        return gender.toString().toLowerCase();
    }

    public static String getComplexValue(Gender gender) {
        return gender.toString().charAt(0) + gender.toString().toLowerCase().substring(1);
    }
}
