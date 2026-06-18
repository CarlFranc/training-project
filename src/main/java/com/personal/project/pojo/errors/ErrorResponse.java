package com.personal.project.pojo.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal.project.constants.Constants;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Builder
@Getter
public class ErrorResponse {

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT, timezone = Constants.TIMEZONE)
    private Instant timestamp;

    private HttpStatus status;

}
