package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;

public interface Validator<T> {
    void validate(T input) throws BaseException;
}
