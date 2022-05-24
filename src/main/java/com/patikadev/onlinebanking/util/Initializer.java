package com.patikadev.onlinebanking.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class Initializer {

    @PostConstruct
    public void onInit(){
    }
}
