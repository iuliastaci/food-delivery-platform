package main.validator;

import main.exceptions.ValidationException;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}
