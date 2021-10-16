package com.example.demo.lambda.util;

@FunctionalInterface
public interface Consumer<T> {

    void accept(T t);

    default Consumer<T> andThen(Consumer<T> other){
        return (T t) -> {this.accept(t); other.accept(t);};
    }
}
