package com.tiago.movies_api.errors;

public class InvalidCategoryException extends Exception{
    public InvalidCategoryException() {
        super("A categoria informada não existe.");
    }
}
