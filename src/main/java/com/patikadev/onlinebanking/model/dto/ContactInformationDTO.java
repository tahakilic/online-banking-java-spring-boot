package com.patikadev.onlinebanking.model.dto;

public record ContactInformationDTO(String primaryEmail,
                                    String secondaryEmail,
                                    String primaryPhoneNumber,
                                    String secondaryPhoneNumber) {
}
