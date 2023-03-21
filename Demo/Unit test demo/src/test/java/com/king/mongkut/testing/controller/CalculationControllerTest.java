package com.king.mongkut.testing.controller;

import com.king.mongkut.testing.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

    @Mock
    private CalculationService calculationService;

    @InjectMocks
    private CalculationController calculationController;

    @Test
    void should_return_status_ok_when_call_plus_with_number1_and_number2(){
        when(calculationService.plus("1","2")).thenReturn(3);

        ResponseEntity<?> response = calculationController.plus("1", "2");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody());

    }
    @Test
    void should_return_status_bad_request_when_call_plus_with_number1_and_number2() {
        when(calculationService.plus("1","2")).thenThrow(new NumberFormatException("number wrong format"));

        ResponseEntity<?> response = calculationController.plus("1", "2");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("number wrong format", response.getBody());
    }
    @Test
    void should_return_status_ok_when_call_minus_give_number1_and_number2() {
        // Given
        when(calculationService.minus("10", "5")).thenReturn(5);

        // When
        ResponseEntity<?> actual = calculationController.minus("10", "5");

        // Then
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertAll("should return success status",
                () -> assertEquals(5, actual.getBody())
        );
    }

    @Test
    void should_return_status_bad_request_when_call_minus_give_number1_and_number2_and_get_minus_one_from_service() {
        // Given
        when(calculationService.minus("X", "5")).thenReturn(-1);

        // When
        ResponseEntity<?> actual = calculationController.minus("X", "5");

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertAll("should return bad request status",
                () -> assertEquals("something went wrong", actual.getBody())
        );
    }

    @Test
    void should_return_status_ok_when_call_multiply_with_number1_and_number2(){
        when(calculationService.multiply("1","2")).thenReturn(2);

        ResponseEntity<?> response = calculationController.multiply("1", "2");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody());

    }
    @Test
    void should_return_status_bad_request_when_call_multiply_with_number1_and_number2() {
        when(calculationService.multiply("1","2")).thenThrow(new NumberFormatException("number wrong format"));

        ResponseEntity<?> response = calculationController.multiply("1", "2");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("number wrong format", response.getBody());
    }

    @Test
    void should_return_status_ok_when_call_divide_give_number1_and_number2() {
        // Given
        when(calculationService.divide("10", "5")).thenReturn(2);

        // When
        ResponseEntity<?> actual = calculationController.divide("10", "5");

        // Then
        assertEquals(HttpStatus.OK, actual.getStatusCode());
//        assertEquals(HttpStatus.OK, actual.getStatusCode());
//        assertEquals(HttpStatus.OK, actual.getStatusCode());
//        assertEquals(HttpStatus.OK, actual.getStatusCode());

        assertAll("should return success status",
//                () -> assertEquals(2, actual.getBody()),
//                () -> assertEquals(2, actual.getBody()),
//                () -> assertEquals(2, actual.getBody()),
                () -> assertEquals(2, actual.getBody())
        );
    }

    @Test
    void should_return_status_bad_request_when_call_divide_give_number1_and_number2_and_get_minus_one_from_service() {
        // Given
        when(calculationService.divide("10", "X")).thenReturn(-1);

        // When
        ResponseEntity<?> actual = calculationController.divide("10", "X");

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertAll("should return bad request status",
                () -> assertEquals("something went wrong", actual.getBody())
        );
    }
}