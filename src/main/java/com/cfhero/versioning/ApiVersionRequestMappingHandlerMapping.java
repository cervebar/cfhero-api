package com.cfhero.versioning;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom MappingHandler that maps all request to the versions specified by the annotation {@link ApiVersionRange}.
 * Versioning is supported in range from firstSupportedVersion to lastestVersion.
 *
 * @see ApiVersionRange
 */
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private final String prefix;

    int latestVersion = 1;
    int firstSuportedVersion = 1;

    public ApiVersionRequestMappingHandlerMapping(String prefix, int latestVersion, int firstSuportedVersion) {
        this.prefix = prefix;
        this.latestVersion = latestVersion;
        this.firstSuportedVersion = firstSuportedVersion;
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);

        ApiVersionRange methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersionRange.class);
        if (methodAnnotation != null) {
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            info = createApiVersionInfo(methodAnnotation, methodCondition).combine(info);
        } else {
            ApiVersionRange typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersionRange.class);
            if (typeAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                info = createApiVersionInfo(typeAnnotation, typeCondition).combine(info);
            }
        }
        return info;
    }

    private RequestMappingInfo createApiVersionInfo(ApiVersionRange annotation, RequestCondition<?> customCondition) {
        int from = annotation.from();
        int to = annotation.to();

        // -1 means is undefined
        if (to == -1 || to > latestVersion) {
            to = latestVersion;
        }

        List<String> patterns = new ArrayList<String>();

        for (int versionNumber = from; versionNumber <= to; versionNumber++) {
            if (versionNumber < firstSuportedVersion) {
                continue;
            }
            patterns.add(prefix + versionNumber);
            if (versionNumber == latestVersion) {
                patterns.add("");// default mapping (api/info => api/v1/info) is to latest version
            }
        }
        return new RequestMappingInfo(
                new PatternsRequestCondition(patterns.toArray(new String[patterns.size()]), getUrlPathHelper(), getPathMatcher(), useSuffixPatternMatch(),
                        useTrailingSlashMatch(), getFileExtensions()),
                new RequestMethodsRequestCondition(),
                new ParamsRequestCondition(),
                new HeadersRequestCondition(),
                new ConsumesRequestCondition(),
                new ProducesRequestCondition(),
                customCondition);
    }

}