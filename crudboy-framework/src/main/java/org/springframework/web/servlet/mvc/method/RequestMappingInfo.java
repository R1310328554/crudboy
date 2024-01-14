//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.web.servlet.mvc.method;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.AbstractRequestCondition;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.pattern.PathPatternParser;

public final class RequestMappingInfo implements RequestCondition<RequestMappingInfo> {
    private static final PathPatternsRequestCondition EMPTY_PATH_PATTERNS = new PathPatternsRequestCondition();
    private static final PatternsRequestCondition EMPTY_PATTERNS = new PatternsRequestCondition(new String[0]);
    private static final RequestMethodsRequestCondition EMPTY_REQUEST_METHODS = new RequestMethodsRequestCondition(new RequestMethod[0]);
    private static final ParamsRequestCondition EMPTY_PARAMS = new ParamsRequestCondition(new String[0]);
    private static final HeadersRequestCondition EMPTY_HEADERS = new HeadersRequestCondition(new String[0]);
    private static final ConsumesRequestCondition EMPTY_CONSUMES = new ConsumesRequestCondition(new String[0]);
    private static final ProducesRequestCondition EMPTY_PRODUCES = new ProducesRequestCondition(new String[0]);
    private static final RequestConditionHolder EMPTY_CUSTOM = new RequestConditionHolder((RequestCondition)null);
    @Nullable
    private final String name;
    @Nullable
    private final PathPatternsRequestCondition pathPatternsCondition;
    @Nullable
    private final PatternsRequestCondition patternsCondition;
    private final RequestMethodsRequestCondition methodsCondition;
    private final ParamsRequestCondition paramsCondition;
    private final HeadersRequestCondition headersCondition;
    private final ConsumesRequestCondition consumesCondition;
    private final ProducesRequestCondition producesCondition;
    private final RequestConditionHolder customConditionHolder;
    private final int hashCode;
    private final BuilderConfiguration options;

    @Value("${yudao.web.methodNameAsPaths:true}")
    public static boolean methodNameAsPathsaa = true; // 没有用.. 因为是静态类。

    /** @deprecated */
    @Deprecated
    public RequestMappingInfo(@Nullable String name, @Nullable PatternsRequestCondition patterns, @Nullable RequestMethodsRequestCondition methods, @Nullable ParamsRequestCondition params, @Nullable HeadersRequestCondition headers, @Nullable ConsumesRequestCondition consumes, @Nullable ProducesRequestCondition produces, @Nullable RequestCondition<?> custom) {
        this(name, (PathPatternsRequestCondition)null, patterns != null ? patterns : EMPTY_PATTERNS, methods != null ? methods : EMPTY_REQUEST_METHODS, params != null ? params : EMPTY_PARAMS, headers != null ? headers : EMPTY_HEADERS, consumes != null ? consumes : EMPTY_CONSUMES, produces != null ? produces : EMPTY_PRODUCES, custom != null ? new RequestConditionHolder(custom) : EMPTY_CUSTOM, new BuilderConfiguration());
    }

    /** @deprecated */
    @Deprecated
    public RequestMappingInfo(@Nullable PatternsRequestCondition patterns, @Nullable RequestMethodsRequestCondition methods, @Nullable ParamsRequestCondition params, @Nullable HeadersRequestCondition headers, @Nullable ConsumesRequestCondition consumes, @Nullable ProducesRequestCondition produces, @Nullable RequestCondition<?> custom) {
        this((String)null, patterns, methods, params, headers, consumes, produces, custom);
    }

    /** @deprecated */
    @Deprecated
    public RequestMappingInfo(RequestMappingInfo info, @Nullable RequestCondition<?> customRequestCondition) {
        this(info.name, info.patternsCondition, info.methodsCondition, info.paramsCondition, info.headersCondition, info.consumesCondition, info.producesCondition, customRequestCondition);
    }

    private RequestMappingInfo(@Nullable String name, @Nullable PathPatternsRequestCondition pathPatternsCondition, @Nullable PatternsRequestCondition patternsCondition, RequestMethodsRequestCondition methodsCondition, ParamsRequestCondition paramsCondition, HeadersRequestCondition headersCondition, ConsumesRequestCondition consumesCondition, ProducesRequestCondition producesCondition, RequestConditionHolder customCondition, BuilderConfiguration options) {
        Assert.isTrue(pathPatternsCondition != null || patternsCondition != null, "Neither PathPatterns nor String patterns condition");
        this.name = StringUtils.hasText(name) ? name : null;
        this.pathPatternsCondition = pathPatternsCondition;
        this.patternsCondition = patternsCondition;
        this.methodsCondition = methodsCondition;
        this.paramsCondition = paramsCondition;
        this.headersCondition = headersCondition;
        this.consumesCondition = consumesCondition;
        this.producesCondition = producesCondition;
        this.customConditionHolder = customCondition;
        this.options = options;
        this.hashCode = calculateHashCode(this.pathPatternsCondition, this.patternsCondition, this.methodsCondition, this.paramsCondition, this.headersCondition, this.consumesCondition, this.producesCondition, this.customConditionHolder);
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    @Nullable
    public PathPatternsRequestCondition getPathPatternsCondition() {
        return this.pathPatternsCondition;
    }

    @Nullable
    public PatternsRequestCondition getPatternsCondition() {
        return this.patternsCondition;
    }

    public <T> RequestCondition<T> getActivePatternsCondition() {
        if (this.pathPatternsCondition != null) {
            return (RequestCondition<T>) this.pathPatternsCondition;
        } else if (this.patternsCondition != null) {
            return (RequestCondition<T>) this.patternsCondition;
        } else {
            throw new IllegalStateException();
        }
    }

    public Set<String> getDirectPaths() {
        RequestCondition<?> condition = this.getActivePatternsCondition();
        return condition instanceof PathPatternsRequestCondition ? ((PathPatternsRequestCondition)condition).getDirectPaths() : ((PatternsRequestCondition)condition).getDirectPaths();
    }

    public Set<String> getPatternValues() {
        RequestCondition<?> condition = this.getActivePatternsCondition();
        return condition instanceof PathPatternsRequestCondition ? ((PathPatternsRequestCondition)condition).getPatternValues() : ((PatternsRequestCondition)condition).getPatterns();
    }

    public RequestMethodsRequestCondition getMethodsCondition() {
        return this.methodsCondition;
    }

    public ParamsRequestCondition getParamsCondition() {
        return this.paramsCondition;
    }

    public HeadersRequestCondition getHeadersCondition() {
        return this.headersCondition;
    }

    public ConsumesRequestCondition getConsumesCondition() {
        return this.consumesCondition;
    }

    public ProducesRequestCondition getProducesCondition() {
        return this.producesCondition;
    }

    @Nullable
    public RequestCondition<?> getCustomCondition() {
        return this.customConditionHolder.getCondition();
    }

    public RequestMappingInfo addCustomCondition(RequestCondition<?> customCondition) {
        return new RequestMappingInfo(this.name, this.pathPatternsCondition, this.patternsCondition, this.methodsCondition, this.paramsCondition, this.headersCondition, this.consumesCondition, this.producesCondition, new RequestConditionHolder(customCondition), this.options);
    }

    public RequestMappingInfo combine(RequestMappingInfo other) {
        String name = this.combineNames(other);
        PathPatternsRequestCondition pathPatterns = this.pathPatternsCondition != null && other.pathPatternsCondition != null ? this.pathPatternsCondition.combine(other.pathPatternsCondition) : null;
        PatternsRequestCondition patterns = this.patternsCondition != null && other.patternsCondition != null ? this.patternsCondition.combine(other.patternsCondition) : null;
        RequestMethodsRequestCondition methods = this.methodsCondition.combine(other.methodsCondition);
        ParamsRequestCondition params = this.paramsCondition.combine(other.paramsCondition);
        HeadersRequestCondition headers = this.headersCondition.combine(other.headersCondition);
        ConsumesRequestCondition consumes = this.consumesCondition.combine(other.consumesCondition);
        ProducesRequestCondition produces = this.producesCondition.combine(other.producesCondition);
        RequestConditionHolder custom = this.customConditionHolder.combine(other.customConditionHolder);
        return new RequestMappingInfo(name, pathPatterns, patterns, methods, params, headers, consumes, produces, custom, this.options);
    }

    @Nullable
    private String combineNames(RequestMappingInfo other) {
        if (this.name != null && other.name != null) {
            String separator = "#";
            return this.name + separator + other.name;
        } else {
            return this.name != null ? this.name : other.name;
        }
    }

    @Nullable
    public RequestMappingInfo getMatchingCondition(HttpServletRequest request) {
        RequestMethodsRequestCondition methods = this.methodsCondition.getMatchingCondition(request);
        if (methods == null) {
            return null;
        } else {
            ParamsRequestCondition params = this.paramsCondition.getMatchingCondition(request);
            if (params == null) {
                return null;
            } else {
                HeadersRequestCondition headers = this.headersCondition.getMatchingCondition(request);
                if (headers == null) {
                    return null;
                } else {
                    ConsumesRequestCondition consumes = this.consumesCondition.getMatchingCondition(request);
                    if (consumes == null) {
                        return null;
                    } else {
                        ProducesRequestCondition produces = this.producesCondition.getMatchingCondition(request);
                        if (produces == null) {
                            return null;
                        } else {
                            PathPatternsRequestCondition pathPatterns = null;
                            if (this.pathPatternsCondition != null) {
                                pathPatterns = this.pathPatternsCondition.getMatchingCondition(request);
                                if (pathPatterns == null) {
                                    return null;
                                }
                            }

                            PatternsRequestCondition patterns = null;
                            if (this.patternsCondition != null) {
                                patterns = this.patternsCondition.getMatchingCondition(request);
                                if (patterns == null) {
                                    return null;
                                }
                            }

                            RequestConditionHolder custom = this.customConditionHolder.getMatchingCondition(request);
                            return custom == null ? null : new RequestMappingInfo(this.name, pathPatterns, patterns, methods, params, headers, consumes, produces, custom, this.options);
                        }
                    }
                }
            }
        }
    }

    public int compareTo(RequestMappingInfo other, HttpServletRequest request) {
        int result;
        if (HttpMethod.HEAD.matches(request.getMethod())) {
            result = this.methodsCondition.compareTo(other.getMethodsCondition(), request);
            if (result != 0) {
                return result;
            }
        }

        result = this.getActivePatternsCondition().compareTo(other.getActivePatternsCondition(), request);
        if (result != 0) {
            return result;
        } else {
            result = this.paramsCondition.compareTo(other.getParamsCondition(), request);
            if (result != 0) {
                return result;
            } else {
                result = this.headersCondition.compareTo(other.getHeadersCondition(), request);
                if (result != 0) {
                    return result;
                } else {
                    result = this.consumesCondition.compareTo(other.getConsumesCondition(), request);
                    if (result != 0) {
                        return result;
                    } else {
                        result = this.producesCondition.compareTo(other.getProducesCondition(), request);
                        if (result != 0) {
                            return result;
                        } else {
                            result = this.methodsCondition.compareTo(other.getMethodsCondition(), request);
                            if (result != 0) {
                                return result;
                            } else {
                                result = this.customConditionHolder.compareTo(other.customConditionHolder, request);
                                return result != 0 ? result : 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof RequestMappingInfo)) {
            return false;
        } else {
            RequestMappingInfo otherInfo = (RequestMappingInfo)other;
            return this.getActivePatternsCondition().equals(otherInfo.getActivePatternsCondition()) && this.methodsCondition.equals(otherInfo.methodsCondition) && this.paramsCondition.equals(otherInfo.paramsCondition) && this.headersCondition.equals(otherInfo.headersCondition) && this.consumesCondition.equals(otherInfo.consumesCondition) && this.producesCondition.equals(otherInfo.producesCondition) && this.customConditionHolder.equals(otherInfo.customConditionHolder);
        }
    }

    public int hashCode() {
        return this.hashCode;
    }

    private static int calculateHashCode(@Nullable PathPatternsRequestCondition pathPatterns, @Nullable PatternsRequestCondition patterns, RequestMethodsRequestCondition methods, ParamsRequestCondition params, HeadersRequestCondition headers, ConsumesRequestCondition consumes, ProducesRequestCondition produces, RequestConditionHolder custom) {
        return ((AbstractRequestCondition)(pathPatterns != null ? pathPatterns : patterns)).hashCode() * 31 + methods.hashCode() + params.hashCode() + headers.hashCode() + consumes.hashCode() + produces.hashCode() + custom.hashCode();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (!this.methodsCondition.isEmpty()) {
            Set<RequestMethod> httpMethods = this.methodsCondition.getMethods();
            builder.append(httpMethods.size() == 1 ? httpMethods.iterator().next() : httpMethods);
        }

        builder.append(' ').append(this.getActivePatternsCondition());
        if (!this.paramsCondition.isEmpty()) {
            builder.append(", params ").append(this.paramsCondition);
        }

        if (!this.headersCondition.isEmpty()) {
            builder.append(", headers ").append(this.headersCondition);
        }

        if (!this.consumesCondition.isEmpty()) {
            builder.append(", consumes ").append(this.consumesCondition);
        }

        if (!this.producesCondition.isEmpty()) {
            builder.append(", produces ").append(this.producesCondition);
        }

        if (!this.customConditionHolder.isEmpty()) {
            builder.append(", and ").append(this.customConditionHolder);
        }

        builder.append('}');
        return builder.toString();
    }

    public Builder mutate() {
        return new MutateBuilder(this);
    }

    public static Builder paths(String... paths) {
        return new DefaultBuilder(paths);
        // return new DefaultBuilder(paths, methodNameAsPaths); 行不通
    }

    public static class BuilderConfiguration {
        @Nullable
        private PathPatternParser patternParser;
        @Nullable
        private PathMatcher pathMatcher;
        private boolean trailingSlashMatch = true;
        private boolean suffixPatternMatch = false;
        private boolean registeredSuffixPatternMatch = false;
        @Nullable
        private ContentNegotiationManager contentNegotiationManager;

        public BuilderConfiguration() {
        }

        public void setPatternParser(@Nullable PathPatternParser patternParser) {
            this.patternParser = patternParser;
        }

        @Nullable
        public PathPatternParser getPatternParser() {
            return this.patternParser;
        }

        /** @deprecated */
        @Deprecated
        public void setUrlPathHelper(@Nullable UrlPathHelper urlPathHelper) {
        }

        /** @deprecated */
        @Nullable
        @Deprecated
        public UrlPathHelper getUrlPathHelper() {
            return UrlPathHelper.defaultInstance;
        }

        public void setPathMatcher(@Nullable PathMatcher pathMatcher) {
            this.pathMatcher = pathMatcher;
        }

        @Nullable
        public PathMatcher getPathMatcher() {
            return this.pathMatcher;
        }

        public void setTrailingSlashMatch(boolean trailingSlashMatch) {
            this.trailingSlashMatch = trailingSlashMatch;
        }

        public boolean useTrailingSlashMatch() {
            return this.trailingSlashMatch;
        }

        /** @deprecated */
        @Deprecated
        public void setSuffixPatternMatch(boolean suffixPatternMatch) {
            this.suffixPatternMatch = suffixPatternMatch;
        }

        /** @deprecated */
        @Deprecated
        public boolean useSuffixPatternMatch() {
            return this.suffixPatternMatch;
        }

        /** @deprecated */
        @Deprecated
        public void setRegisteredSuffixPatternMatch(boolean registeredSuffixPatternMatch) {
            this.registeredSuffixPatternMatch = registeredSuffixPatternMatch;
            this.suffixPatternMatch = registeredSuffixPatternMatch || this.suffixPatternMatch;
        }

        /** @deprecated */
        @Deprecated
        public boolean useRegisteredSuffixPatternMatch() {
            return this.registeredSuffixPatternMatch;
        }

        /** @deprecated */
        @Nullable
        @Deprecated
        public List<String> getFileExtensions() {
            return this.useRegisteredSuffixPatternMatch() && this.contentNegotiationManager != null ? this.contentNegotiationManager.getAllFileExtensions() : null;
        }

        public void setContentNegotiationManager(ContentNegotiationManager contentNegotiationManager) {
            this.contentNegotiationManager = contentNegotiationManager;
        }

        @Nullable
        public ContentNegotiationManager getContentNegotiationManager() {
            return this.contentNegotiationManager;
        }
    }

    private static class MutateBuilder implements Builder {
        @Nullable
        private String name;
        @Nullable
        private PathPatternsRequestCondition pathPatternsCondition;
        @Nullable
        private PatternsRequestCondition patternsCondition;
        private RequestMethodsRequestCondition methodsCondition;
        private ParamsRequestCondition paramsCondition;
        private HeadersRequestCondition headersCondition;
        private ConsumesRequestCondition consumesCondition;
        private ProducesRequestCondition producesCondition;
        private RequestConditionHolder customConditionHolder;
        private BuilderConfiguration options;

        public MutateBuilder(RequestMappingInfo other) {
            this.name = other.name;
            this.pathPatternsCondition = other.pathPatternsCondition;
            this.patternsCondition = other.patternsCondition;
            this.methodsCondition = other.methodsCondition;
            this.paramsCondition = other.paramsCondition;
            this.headersCondition = other.headersCondition;
            this.consumesCondition = other.consumesCondition;
            this.producesCondition = other.producesCondition;
            this.customConditionHolder = other.customConditionHolder;
            this.options = other.options;
        }

        public Builder paths(String... paths) {
            if (this.options.patternParser != null) {
                // todo
                this.pathPatternsCondition = ObjectUtils.isEmpty(paths) ? RequestMappingInfo.EMPTY_PATH_PATTERNS : new PathPatternsRequestCondition(this.options.patternParser, paths);
            } else {
                // todo 做成跟  org.springframework.web.servlet.mvc.method.RequestMappingInfo.DefaultBuilder.build 一样的
                this.patternsCondition = ObjectUtils.isEmpty(paths) ? RequestMappingInfo.EMPTY_PATTERNS : new PatternsRequestCondition(paths, (UrlPathHelper)null, this.options.getPathMatcher(), this.options.useSuffixPatternMatch(), this.options.useTrailingSlashMatch(), this.options.getFileExtensions());
            }

            return this;
        }

        public Builder methods(RequestMethod... methods) {
            this.methodsCondition = ObjectUtils.isEmpty(methods) ? RequestMappingInfo.EMPTY_REQUEST_METHODS : new RequestMethodsRequestCondition(methods);
            return this;
        }

        public Builder params(String... params) {
            this.paramsCondition = ObjectUtils.isEmpty(params) ? RequestMappingInfo.EMPTY_PARAMS : new ParamsRequestCondition(params);
            return this;
        }

        public Builder headers(String... headers) {
            this.headersCondition = ObjectUtils.isEmpty(headers) ? RequestMappingInfo.EMPTY_HEADERS : new HeadersRequestCondition(headers);
            return this;
        }

        public Builder consumes(String... consumes) {
            this.consumesCondition = ObjectUtils.isEmpty(consumes) ? RequestMappingInfo.EMPTY_CONSUMES : new ConsumesRequestCondition(consumes);
            return this;
        }

        public Builder produces(String... produces) {
            this.producesCondition = ObjectUtils.isEmpty(produces) ? RequestMappingInfo.EMPTY_PRODUCES : new ProducesRequestCondition(produces, (String[])null, this.options.getContentNegotiationManager());
            return this;
        }

        public Builder mappingName(String name) {
            this.name = name;
            return this;
        }

        public Builder customCondition(RequestCondition<?> condition) {
            this.customConditionHolder = new RequestConditionHolder(condition);
            return this;
        }

        public Builder options(BuilderConfiguration options) {
            this.options = options;
            return this;
        }

        public RequestMappingInfo build() {
            return new RequestMappingInfo(this.name, this.pathPatternsCondition, this.patternsCondition, this.methodsCondition, this.paramsCondition, this.headersCondition, this.consumesCondition, this.producesCondition, this.customConditionHolder, this.options);
        }
    }

    private static class DefaultBuilder implements Builder {
        private String[] paths;
        private RequestMethod[] methods = new RequestMethod[0];
        private String[] params = new String[0];
        private String[] headers = new String[0];
        private String[] consumes = new String[0];
        private String[] produces = new String[0];
        private boolean hasContentType;
        private boolean hasAccept;
        @Nullable
        private String mappingName;
        @Nullable
        private RequestCondition<?> customCondition;
        private BuilderConfiguration options = new BuilderConfiguration();

        @Value("${yudao.web.methodNameAsPaths:true}")
        private boolean methodNameAsPaths; // 没有用.. 因为是静态类。

        public DefaultBuilder(String... paths) {
            this.paths = paths;
        }

        public Builder paths(String... paths) {
            this.paths = paths;
            return this;
        }

        public DefaultBuilder methods(RequestMethod... methods) {
            this.methods = methods;
            return this;
        }

        public DefaultBuilder params(String... params) {
            this.params = params;
            return this;
        }

        public DefaultBuilder headers(String... headers) {
            String[] var2 = headers;
            int var3 = headers.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String header = var2[var4];
                this.hasContentType = this.hasContentType || header.contains("Content-Type") || header.contains("content-type");
                this.hasAccept = this.hasAccept || header.contains("Accept") || header.contains("accept");
            }

            this.headers = headers;
            return this;
        }

        public DefaultBuilder consumes(String... consumes) {
            this.consumes = consumes;
            return this;
        }

        public DefaultBuilder produces(String... produces) {
            this.produces = produces;
            return this;
        }

        public DefaultBuilder mappingName(String name) {
            this.mappingName = name;
            return this;
        }

        public DefaultBuilder customCondition(RequestCondition<?> condition) {
            this.customCondition = condition;
            return this;
        }

        public Builder options(BuilderConfiguration options) {
            this.options = options;
            return this;
        }

        public RequestMappingInfo build() {
            PathPatternsRequestCondition pathPatterns = null;
            PatternsRequestCondition patterns = null;
            if (this.options.patternParser != null) {
                if (ObjectUtils.isEmpty(this.paths)) {
                    if ((methodNameAsPathsaa)) {
                        pathPatterns = new PathPatternsRequestCondition();
                        // pathPatterns.addPatternValues(this.methods[0].name()) todo 没有这样的方法 。。 头大
                    } else {
                        pathPatterns = RequestMappingInfo.EMPTY_PATH_PATTERNS;
                    }
                } else {
                    pathPatterns = new PathPatternsRequestCondition(this.options.patternParser, this.paths);
                }
            } else {
                if (ObjectUtils.isEmpty(this.paths)) {
                    if ((methodNameAsPathsaa)) {
                        // 根本行不通， 因为这里的 methods 不是原方法， 而是 RequestMethod[]
                        patterns = new PatternsRequestCondition(this.methods[0].name());
                    } else {
                        patterns = RequestMappingInfo.EMPTY_PATTERNS;
                    }
                } else {
                    patterns = new PatternsRequestCondition(this.paths, (UrlPathHelper)null, this.options.getPathMatcher(), this.options.useSuffixPatternMatch(), this.options.useTrailingSlashMatch(), this.options.getFileExtensions());
                }
            }

            ContentNegotiationManager manager = this.options.getContentNegotiationManager();
            return new RequestMappingInfo(this.mappingName, pathPatterns, patterns, ObjectUtils.isEmpty(this.methods) ? RequestMappingInfo.EMPTY_REQUEST_METHODS : new RequestMethodsRequestCondition(this.methods), ObjectUtils.isEmpty(this.params) ? RequestMappingInfo.EMPTY_PARAMS : new ParamsRequestCondition(this.params), ObjectUtils.isEmpty(this.headers) ? RequestMappingInfo.EMPTY_HEADERS : new HeadersRequestCondition(this.headers), ObjectUtils.isEmpty(this.consumes) && !this.hasContentType ? RequestMappingInfo.EMPTY_CONSUMES : new ConsumesRequestCondition(this.consumes, this.headers), ObjectUtils.isEmpty(this.produces) && !this.hasAccept ? RequestMappingInfo.EMPTY_PRODUCES : new ProducesRequestCondition(this.produces, this.headers, manager), this.customCondition != null ? new RequestConditionHolder(this.customCondition) : RequestMappingInfo.EMPTY_CUSTOM, this.options);
        }
    }

    public interface Builder {
        Builder paths(String... paths);

        Builder methods(RequestMethod... methods);

        Builder params(String... params);

        Builder headers(String... headers);

        Builder consumes(String... consumes);

        Builder produces(String... produces);

        Builder mappingName(String name);

        Builder customCondition(RequestCondition<?> condition);

        Builder options(BuilderConfiguration options);

        RequestMappingInfo build();
    }
}
