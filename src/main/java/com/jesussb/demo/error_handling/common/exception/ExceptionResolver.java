package com.jesussb.demo.error_handling.common.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Class that resolves internationalized messages in the resource files (e.g., messages.properties) depending on the locale.
 *
 * @author jesussb
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
public class ExceptionResolver {

    private final MessageSource messageSource;

    /**
     * Method that resolves an internationalized message by the key.
     * @param key The key of the message in the resource folder.
     * @param locale The specific geographical region for the language identification.
     * @param args Arguments used in case of text interpolation.
     * @return The resolved message.
     */
    public String resolveMessage(String key, Locale locale, Object... args){
        return messageSource.getMessage(key, args, locale);
    }

}
