package com.example.bookings.exceptions;

import com.example.bookings.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class ApplicationException {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public ApplicationException(PropertiesConfig propertiesConfig) {
        ApplicationException.propertiesConfig = propertiesConfig;
    }

    /**
     * Returns new RuntimeException based on EntityType, ExceptionType and args
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    public static RuntimeException buildException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        return buildException(exceptionType, messageTemplate, args);
    }

    /**
     * Returns new RuntimeException based on template and args
     *
     * @param messageTemplate
     * @param args
     * @return
     */
    private static RuntimeException buildException(ExceptionType exceptionType, String messageTemplate, String... args) {
        switch (exceptionType) {
            case ENTITY_NOT_FOUND:
                return new EntityNotFoundException(format(messageTemplate, args));
            case DUPLICATE_ENTITY:
                return new DuplicateEntityException(format(messageTemplate, args));
            case NOT_AVAILABLE:
                return new NotAvailableException(format(messageTemplate, args));
            case FORBIDDEN:
                return new ForbiddenException(format(messageTemplate, args));
            default:
                return new RuntimeException(format(messageTemplate, args));
        }
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        if (templateContent.isPresent()) {
            return MessageFormat.format(templateContent.get(), (Object[]) args);
        }
        return String.format(template, (Object[]) args);
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }

    public static class NotAvailableException extends RuntimeException {
        public NotAvailableException(String message) {
            super(message);
        }
    }

    public static class InternalServerException extends RuntimeException {
        public InternalServerException(String message) {
            super(message);
        }
    }

    public static class ForbiddenException extends RuntimeException {
        public ForbiddenException(String message) {
            super(message);
        }
    }
}