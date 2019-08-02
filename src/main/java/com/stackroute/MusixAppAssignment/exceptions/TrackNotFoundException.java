package com.stackroute.MusixAppAssignment.exceptions;

public class TrackNotFoundException extends Exception {

    private String message;

    public TrackNotFoundException(String message) {

        super(message);

        this.message=message;

    }
}
