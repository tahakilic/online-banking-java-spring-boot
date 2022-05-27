package com.patikadev.onlinebanking.model.request;

import java.math.BigDecimal;


public record TransferRequest(BigDecimal amount,
                              String description) {
}
