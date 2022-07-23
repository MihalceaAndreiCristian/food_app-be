package ro.andreimihalcea.food_app.enums;

public enum AppRole {

    USER,
    ADMIN;

    /**
     * Returns the enum from a string.
     *
     * @param role the input {@link String}
     * @return {@link AppRole}
     */
    public static AppRole getAppRole(String role) {
        return switch (role) {
            case "ADMIN" -> AppRole.ADMIN;
            default -> AppRole.USER;
        };
    }
}
