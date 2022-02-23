package com.mehdi.rentcar.exception.utils;

import com.mehdi.rentcar.exception.dto.ApiError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

public final class ExceptionUtils {

    public static final String ACCESS_DENIED = "Access denied!";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String LIST_JOIN_DELIMITER = ",";


    public static String getMessageForStatus(final HttpStatus status) {

        var result = status.getReasonPhrase();
        switch (status) {
            case UNAUTHORIZED:
                result = ACCESS_DENIED;
                break;
            case BAD_REQUEST:
                result = INVALID_REQUEST;
                break;
        }
        return result;
    }

    public static String getErrorMessageForLogger(final List<String> errors, final HttpStatus status) {
        return !errors.isEmpty() ?
                errors.stream().filter(StringUtils::isNotEmpty).collect(Collectors.joining(LIST_JOIN_DELIMITER))
                : status.getReasonPhrase();
    }


    public static ApiError getApiError(final Exception exception,
                                       final HttpStatus status,
                                       final String path,
                                       final List<String> errors) {
        ApiError apiError = new ApiError(status);
        apiError.setType(exception.getClass().getSimpleName());
        apiError.setPath(path);
        apiError.setMessage(getMessageForStatus(status));
        if (errors.isEmpty() && exception.getMessage() != null) {
            errors.add(exception.getMessage());
        }

        apiError.setErrors(errors);
        return apiError;
    }
}
