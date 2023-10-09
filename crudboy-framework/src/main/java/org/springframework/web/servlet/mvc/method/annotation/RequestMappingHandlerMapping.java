//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.web.servlet.mvc.method.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringValueResolver;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.MatchableHandlerMapping;
import org.springframework.web.servlet.handler.RequestMatchResult;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

public class RequestMappingHandlerMapping extends RequestMappingInfoHandlerMapping implements MatchableHandlerMapping, EmbeddedValueResolverAware {
    private boolean useSuffixPatternMatch = false;
    private boolean useRegisteredSuffixPatternMatch = false;
    private boolean useTrailingSlashMatch = true;
    private Map<String, Predicate<Class<?>>> pathPrefixes = Collections.emptyMap();
    private ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager();
    @Nullable
    private StringValueResolver embeddedValueResolver;
    private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();

    public RequestMappingHandlerMapping() {
    }

    /** @deprecated */
    @Deprecated
    public void setUseSuffixPatternMatch(boolean useSuffixPatternMatch) {
        this.useSuffixPatternMatch = useSuffixPatternMatch;
    }

    /** @deprecated */
    @Deprecated
    public void setUseRegisteredSuffixPatternMatch(boolean useRegisteredSuffixPatternMatch) {
        this.useRegisteredSuffixPatternMatch = useRegisteredSuffixPatternMatch;
        this.useSuffixPatternMatch = useRegisteredSuffixPatternMatch || this.useSuffixPatternMatch;
    }

    public void setUseTrailingSlashMatch(boolean useTrailingSlashMatch) {
        this.useTrailingSlashMatch = useTrailingSlashMatch;
        if (this.getPatternParser() != null) {
            this.getPatternParser().setMatchOptionalTrailingSeparator(useTrailingSlashMatch);
        }

    }

    public void setPathPrefixes(Map<String, Predicate<Class<?>>> prefixes) {
        this.pathPrefixes = !prefixes.isEmpty() ? Collections.unmodifiableMap(new LinkedHashMap(prefixes)) : Collections.emptyMap();
    }

    public Map<String, Predicate<Class<?>>> getPathPrefixes() {
        return this.pathPrefixes;
    }

    public void setContentNegotiationManager(ContentNegotiationManager contentNegotiationManager) {
        Assert.notNull(contentNegotiationManager, "ContentNegotiationManager must not be null");
        this.contentNegotiationManager = contentNegotiationManager;
    }

    public ContentNegotiationManager getContentNegotiationManager() {
        return this.contentNegotiationManager;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.embeddedValueResolver = resolver;
    }

    public void afterPropertiesSet() {
        this.config = new RequestMappingInfo.BuilderConfiguration();
        this.config.setTrailingSlashMatch(this.useTrailingSlashMatch());
        this.config.setContentNegotiationManager(this.getContentNegotiationManager());
        if (this.getPatternParser() != null) {
            this.config.setPatternParser(this.getPatternParser());
            Assert.isTrue(!this.useSuffixPatternMatch && !this.useRegisteredSuffixPatternMatch, "Suffix pattern matching not supported with PathPatternParser.");
        } else {
            this.config.setSuffixPatternMatch(this.useSuffixPatternMatch());
            this.config.setRegisteredSuffixPatternMatch(this.useRegisteredSuffixPatternMatch());
            this.config.setPathMatcher(this.getPathMatcher());
        }

        super.afterPropertiesSet();
    }

    /** @deprecated */
    @Deprecated
    public boolean useSuffixPatternMatch() {
        return this.useSuffixPatternMatch;
    }

    /** @deprecated */
    @Deprecated
    public boolean useRegisteredSuffixPatternMatch() {
        return this.useRegisteredSuffixPatternMatch;
    }

    public boolean useTrailingSlashMatch() {
        return this.useTrailingSlashMatch;
    }

    /** @deprecated */
    @Nullable
    @Deprecated
    public List<String> getFileExtensions() {
        return this.config.getFileExtensions();
    }

    public RequestMappingInfo.BuilderConfiguration getBuilderConfiguration() {
        return this.config;
    }

    protected boolean isHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) || AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class);
    }

    @Nullable
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = this.createRequestMappingInfo(method);
        if (info != null) {
            RequestMappingInfo typeInfo = this.createRequestMappingInfo(handlerType);
            if (typeInfo != null) {
                info = typeInfo.combine(info);
            }

            String prefix = this.getPathPrefix(handlerType);
            if (prefix != null) {
                info = RequestMappingInfo.paths(new String[]{prefix}).options(this.config).build().combine(info);
            }
        }

        return info;
    }

    @Nullable
    String getPathPrefix(Class<?> handlerType) {
        Iterator var2 = this.pathPrefixes.entrySet().iterator();

        Map.Entry entry;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            entry = (Map.Entry)var2.next();
        } while(!((Predicate)entry.getValue()).test(handlerType));

        // info.getPatternsCondition() == null || info.getPatternsCondition().getPatterns().size() == 0
        String prefix = (String)entry.getKey();
        if (this.embeddedValueResolver != null) {
            prefix = this.embeddedValueResolver.resolveStringValue(prefix);
        }

        return prefix;
    }

    @Nullable
    private RequestMappingInfo createRequestMappingInfo(AnnotatedElement element) {
        RequestMapping requestMapping = (RequestMapping)AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
        if (requestMapping == null) {
            return null;
        }
        String[] path = requestMapping.path();
        if (ArrayUtils.isEmpty(path)) {
//            requestMapping.path() = "ad";
            if (element instanceof Method) {
                Method method = (Method) element;
                RequestMapping finalRequestMapping = requestMapping;
                requestMapping = new RequestMapping() {
                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return finalRequestMapping.annotationType();
                    }

                    @Override
                    public String name() {
                        return finalRequestMapping.name();
                    }

                    @Override
                    public String[] value() {
                        return new String[]{method.getName()};
                    }

                    @Override
                    public String[] path() {
                        return new String[]{method.getName()};
                    }

                    @Override
                    public RequestMethod[] method() {
                        return finalRequestMapping.method();
                    }

                    @Override
                    public String[] params() {
                        return finalRequestMapping.path();
                    }

                    @Override
                    public String[] headers() {
                        return finalRequestMapping.headers();
                    }

                    @Override
                    public String[] consumes() {
                        return finalRequestMapping.consumes();
                    }

                    @Override
                    public String[] produces() {
                        return finalRequestMapping.produces();
                    }
                };
            }
        }

        RequestCondition<?> condition = element instanceof Class ? this.getCustomTypeCondition((Class)element) : this.getCustomMethodCondition((Method)element);
        return this.createRequestMappingInfo(requestMapping, condition);
    }

    @Nullable
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return null;
    }

    @Nullable
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return null;
    }

    protected RequestMappingInfo createRequestMappingInfo(RequestMapping requestMapping, @Nullable RequestCondition<?> customCondition) {
        RequestMappingInfo.Builder builder = RequestMappingInfo.paths(this.resolveEmbeddedValuesInPatterns(requestMapping.path())).methods(requestMapping.method()).params(requestMapping.params()).headers(requestMapping.headers()).consumes(requestMapping.consumes()).produces(requestMapping.produces()).mappingName(requestMapping.name());
        if (customCondition != null) {
            builder.customCondition(customCondition);
        }

        return builder.options(this.config).build();
    }

    protected String[] resolveEmbeddedValuesInPatterns(String[] patterns) {
        if (this.embeddedValueResolver == null) {
            return patterns;
        } else {
            String[] resolvedPatterns = new String[patterns.length];

            for(int i = 0; i < patterns.length; ++i) {
                resolvedPatterns[i] = this.embeddedValueResolver.resolveStringValue(patterns[i]);
            }

            return resolvedPatterns;
        }
    }

    public void registerMapping(RequestMappingInfo mapping, Object handler, Method method) {
        super.registerMapping(mapping, handler, method);
        this.updateConsumesCondition(mapping, method);
    }

    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        super.registerHandlerMethod(handler, method, mapping);
        this.updateConsumesCondition(mapping, method);
    }

    private void updateConsumesCondition(RequestMappingInfo info, Method method) {
        ConsumesRequestCondition condition = info.getConsumesCondition();
        if (!condition.isEmpty()) {
            Parameter[] var4 = method.getParameters();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Parameter parameter = var4[var6];
                MergedAnnotation<RequestBody> annot = MergedAnnotations.from(parameter).get(RequestBody.class);
                if (annot.isPresent()) {
                    condition.setBodyRequired(annot.getBoolean("required"));
                    break;
                }
            }
        }

    }

    public RequestMatchResult match(HttpServletRequest request, String pattern) {
        Assert.isNull(this.getPatternParser(), "This HandlerMapping requires a PathPattern");
        RequestMappingInfo info = RequestMappingInfo.paths(new String[]{pattern}).options(this.config).build();
        RequestMappingInfo match = info.getMatchingCondition(request);
        return match != null && match.getPatternsCondition() != null ? new RequestMatchResult((String)match.getPatternsCondition().getPatterns().iterator().next(), UrlPathHelper.getResolvedLookupPath(request), this.getPathMatcher()) : null;
    }

    protected CorsConfiguration initCorsConfiguration(Object handler, Method method, RequestMappingInfo mappingInfo) {
        HandlerMethod handlerMethod = this.createHandlerMethod(handler, method);
        Class<?> beanType = handlerMethod.getBeanType();
        CrossOrigin typeAnnotation = (CrossOrigin)AnnotatedElementUtils.findMergedAnnotation(beanType, CrossOrigin.class);
        CrossOrigin methodAnnotation = (CrossOrigin)AnnotatedElementUtils.findMergedAnnotation(method, CrossOrigin.class);
        if (typeAnnotation == null && methodAnnotation == null) {
            return null;
        } else {
            CorsConfiguration config = new CorsConfiguration();
            this.updateCorsConfig(config, typeAnnotation);
            this.updateCorsConfig(config, methodAnnotation);
            if (CollectionUtils.isEmpty(config.getAllowedMethods())) {
                Iterator var9 = mappingInfo.getMethodsCondition().getMethods().iterator();

                while(var9.hasNext()) {
                    RequestMethod allowedMethod = (RequestMethod)var9.next();
                    config.addAllowedMethod(allowedMethod.name());
                }
            }

            return config.applyPermitDefaultValues();
        }
    }

    private void updateCorsConfig(CorsConfiguration config, @Nullable CrossOrigin annotation) {
        if (annotation != null) {
            String[] var3 = annotation.origins();
            int var4 = var3.length;

            int var5;
            String header;
            for(var5 = 0; var5 < var4; ++var5) {
                header = var3[var5];
                config.addAllowedOrigin(this.resolveCorsAnnotationValue(header));
            }

            var3 = annotation.originPatterns();
            var4 = var3.length;

            for(var5 = 0; var5 < var4; ++var5) {
                header = var3[var5];
                config.addAllowedOriginPattern(this.resolveCorsAnnotationValue(header));
            }

            RequestMethod[] var7 = annotation.methods();
            var4 = var7.length;

            for(var5 = 0; var5 < var4; ++var5) {
                RequestMethod method = var7[var5];
                config.addAllowedMethod(method.name());
            }

            var3 = annotation.allowedHeaders();
            var4 = var3.length;

            for(var5 = 0; var5 < var4; ++var5) {
                header = var3[var5];
                config.addAllowedHeader(this.resolveCorsAnnotationValue(header));
            }

            var3 = annotation.exposedHeaders();
            var4 = var3.length;

            for(var5 = 0; var5 < var4; ++var5) {
                header = var3[var5];
                config.addExposedHeader(this.resolveCorsAnnotationValue(header));
            }

            String allowCredentials = this.resolveCorsAnnotationValue(annotation.allowCredentials());
            if ("true".equalsIgnoreCase(allowCredentials)) {
                config.setAllowCredentials(true);
            } else if ("false".equalsIgnoreCase(allowCredentials)) {
                config.setAllowCredentials(false);
            } else if (!allowCredentials.isEmpty()) {
                throw new IllegalStateException("@CrossOrigin's allowCredentials value must be \"true\", \"false\", or an empty string (\"\"): current value is [" + allowCredentials + "]");
            }

            if (annotation.maxAge() >= 0L) {
                config.setMaxAge(annotation.maxAge());
            }

        }
    }

    private String resolveCorsAnnotationValue(String value) {
        if (this.embeddedValueResolver != null) {
            String resolved = this.embeddedValueResolver.resolveStringValue(value);
            return resolved != null ? resolved : "";
        } else {
            return value;
        }
    }
}
