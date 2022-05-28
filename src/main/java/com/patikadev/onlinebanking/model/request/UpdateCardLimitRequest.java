package com.patikadev.onlinebanking.model.request;

import java.math.BigDecimal;

public record UpdateCardLimitRequest(BigDecimal cardLimit,
                                     BigDecimal currentLimit
) {
}
