package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.dto.AccountDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateAccountDTOValidator implements Validator<AccountDTO>{
    @Override
    public void validate(AccountDTO accountDTO) throws BaseException {
        if (Objects.isNull(accountDTO)) {
            throw new ValidationOperationException.AccountNotValidException("Account can not be null or empty!");
        }
        if (!(StringUtils.hasLength(accountDTO.iban()))) {
            throw new ValidationOperationException.AccountNotValidException("Account 'iban' can not be null or empty!");
        }
        if (!(StringUtils.hasLength(accountDTO.bankCode()))) {
            throw new ValidationOperationException.AccountNotValidException("Account 'bankCode' country can not be null or empty!");
        }
        if (!(StringUtils.hasLength(accountDTO.branchCode()))) {
            throw new ValidationOperationException.AccountNotValidException("Account 'branchCode' can not be null or empty!");
        }
        if (Objects.isNull(accountDTO.currencyCode())) {
            throw new ValidationOperationException.AccountNotValidException("Account 'currencyCode' can not be null or empty!");
        }
        if (accountDTO.accountNumber()<1) {
            throw new ValidationOperationException.AccountNotValidException("Account 'accountNumber' can not be zero or negative!");
        }
        if (Objects.isNull(accountDTO.accountType())){
            throw new ValidationOperationException.AccountNotValidException("Account 'accountType' can not be null or empty!");
        }

    }
}
