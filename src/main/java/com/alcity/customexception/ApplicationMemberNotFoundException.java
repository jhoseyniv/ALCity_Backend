package com.alcity.customexception;

public class ApplicationMemberNotFoundException extends  RuntimeException {

    private String animal;
    private String  message;

    public String getAnimal() {
        return animal;
    }

    public void setCartTitle(String animal) {
        this.animal = animal;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApplicationMemberNotFoundException(String animal, String message) {
        this.animal = animal;
        this.message =  message;
    }
}
