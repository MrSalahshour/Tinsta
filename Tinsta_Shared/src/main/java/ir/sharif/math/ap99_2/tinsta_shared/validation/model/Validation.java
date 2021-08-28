package ir.sharif.math.ap99_2.tinsta_shared.validation.model;


import java.time.LocalDate;

public class Validation {

    private final static String nameRegex = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
    private final static String userNameRegex = "^[A-Za-z]\\w{5,29}$";
    private final static String passwordRegex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
    private final static String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
    private final static String phoneNumberRegex = "^[0][1-9]\\d{9}$|^[1-9]\\d{9}$";

    public Validation() {
    }


    public static boolean isValidName(String name) {
        return name != null && name.matches(nameRegex);
    }



    public static String getNameValidationHelp(){
        return """
                * valid name or last name
                     * Names are between 1 and 25 characters.
                     * Names can only start with an a-z (ignore case) character.
                     * After that the first name can contain a-z (ignore case) and ['-,.].
                     * Names can only end with an a-z (ignore case) character.""";
    }

    public static boolean isValidLastName(String name) {

        return name != null && name.matches(nameRegex);
    }

    public static boolean isValidAge(String year, String month, String day) {
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
                .isBefore(LocalDate.now()) || year.equals("1");
    }

    public static boolean isValidUsername(String username) {
        return username != null && username.matches(userNameRegex);
    }



    public static boolean isValidPassword(String password) {
        return password.matches(passwordRegex);
    }



    public static boolean isValidEmail(String email) {

        return email != null && email.matches(emailRegex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {

        return phoneNumber != null && (phoneNumber.matches(phoneNumberRegex) ||
                phoneNumber.equals("NoPhoneNumber"));
    }


    public static String getUsernameValidationHelp(){
        return """
                * valid username
                     * The username consists of 6 to 30 characters inclusive. If the username
                     * consists of less than 6 or greater than 30 characters, then it is an invalid username.
                     * The username can only contain alphanumeric characters and underscores (_).
                     * Alphanumeric characters describe the character set consisting of lowercase characters [a-z],
                     * uppercase characters [A-Z], and digits [0-9].
                     * The first character of the username must be an alphabetic character, i.e., either lowercase character
                     * [a-z] or uppercase character [A-Z].""";
    }

    public static String getPasswordValidationHelp(){
        return """
                * valid password
                     * It contains at least 8 characters and at most 20 characters.
                     * It contains at least one digit.
                     * It contains at least one upper case alphabet.
                     * It contains at least one lower case alphabet.
                     * It contains at least one special character which includes !@#$%&*()-+=^.
                     * It doesnt contain any white space.""";
    }

}
