package com.practice.achilles.config;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Aleksey Konkin
 * @since 26.12.2022
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface LogWriteRequestCount {
}
