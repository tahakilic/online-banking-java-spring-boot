package com.patikadev.onlinebanking.repository.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

@Component
public class IDValidator implements Validator<Long> {
    @Override
    public void validate(Long id) throws BaseException {
        if (id <= 0) {
            throw new ValidationOperationException.IDNotValidException("ID cannot be less than or equal to zero!");
        }
    }
}
