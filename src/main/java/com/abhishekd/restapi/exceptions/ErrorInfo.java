package com.abhishekd.restapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

    private Integer errorCode;
    private HttpStatus errorStatus;
    private String errorMessage;
    private String path;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorInfo(Integer errorCode, HttpStatus errorStatus, String errorMessage, String path) {
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
        this.path = path;
    }
}