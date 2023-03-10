package com.king.mongkut.testing.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @InjectMocks
    private CalculationService calculationService;

    @Test

    void should_return_success_when_call_plus_with_number1_and_number2(){
        int result = calculationService.plus("2","3");
        assertEquals(5,result);
    }

    @Test
    void should_return_error_when_call_plus_with_number1_and_number2() {
        assertThrows(NumberFormatException.class, () -> {
            calculationService.plus("b", "3");
        });
    }

    @Test
    void should_return_number_when_call_minus_given_number1_and_number2() {
        // Given
        // When
        int actual = calculationService.minus("10", "5");

        // Then
        assertEquals(5, actual);
    }

    @Test
    void should_return_minus_one_when_call_minus_given_number1_is_X() {
        // Given
        // When
        int actual = calculationService.minus("X", "5");

        // Then
        assertEquals(-1, actual);
    }

    @Test
    void should_return_success_when_call_multiply_with_number1_and_number2(){
        int result = calculationService.multiply("1","2");
        assertEquals(2,result);
    }

    @Test
    void should_return_error_when_call_multiply_with_number1_and_number2() {
        assertThrows(NumberFormatException.class, () -> {
            calculationService.multiply("X", "2");
        });
    }

    @Test
    void should_return_number_when_call_divide_given_number1_and_number2() {
        // Given
        // When
        int actual = calculationService.divide("10", "5");

        // Then
        assertEquals(2, actual);
    }

    @Test
    void should_return_minus_one_when_call_divide_given_number2_is_X() {
        // Given
        // When
        int actual = calculationService.divide("10", "X");

        // Then
        assertEquals(-1, actual);
    }
}