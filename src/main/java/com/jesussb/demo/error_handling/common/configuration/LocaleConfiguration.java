package com.jesussb.demo.error_handling.common.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * Class that configures a default locale language.
 *
 * @author jesussb
 * @version 1.0.0
 */
@Configuration
public class LocaleConfiguration {

    /**
     * Configures locale resolution based on HTTP Accept-Language header.
     *
     * @return LocaleResolver that extracts locale from request headers,
     *         defaults to system locale if no header is present
     */
    @Bean
    public LocaleResolver localeResolver(){
        var resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.getDefault());
        return resolver;
    }

    /**
     * Creates a validator factory with internationalized validation messages.
     *
     * @param messageSource Source for localized validation error messages
     * @return LocalValidatorFactoryBean configured with custom message source
     */
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource){
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }

    /**
     * Provides a standard Validator instance for programmatic validation.
     *
     * @param messageSource Source for localized validation error messages
     * @return Validator instance with internationalization support
     */
    @Bean
    public Validator getValidator(MessageSource messageSource) {
        return validatorFactoryBean(messageSource);
    }

}
