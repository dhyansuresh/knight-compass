package edu.ucf.knightcompass;

final class User {
    final String email;
    final String firstName;
    final String lastName;

    final boolean isNew;

    User(String email, String firstName, String lastName, boolean isNew) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isNew = isNew;
    }
}
