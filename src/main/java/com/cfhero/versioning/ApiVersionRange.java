package com.cfhero.versioning;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the API version range for a method of any {@link RestController} or the class itself.
 * When the parameter "to" is not specified the default value is -1, which means that the method
 * supports all version from "from" parameter to the newest version.
 * The annotation closely linked to {@link ApiVersionRequestMappingHandlerMapping}, where is used
 * for mapping the requests.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersionRange {

    public int from() default 0;

    public int to() default -1;

}