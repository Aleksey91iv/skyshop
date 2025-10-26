package org.skypro.skyshop.exception.handler;

import org.skypro.skyshop.exception.service.NoSuchProductException;
import org.springframework.boot.autoconfigure.reactor.ReactorProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<String> handleNoSuchProductException(NoSuchProductException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Не корректный формат ожидаемых данных");
    }
}
