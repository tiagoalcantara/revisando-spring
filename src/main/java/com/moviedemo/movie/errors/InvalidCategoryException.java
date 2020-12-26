package com.moviedemo.movie.errors;

public class InvalidCategoryException extends Exception {
    public InvalidCategoryException(){
        super("Categoria inválida");
    }
}
