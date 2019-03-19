package com.fintechsn.huahuadai.common.resolver;

import com.fintechsn.huahuadai.common.annotation.JwtClaim;
import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.exception.RequestException;
import com.fintechsn.huahuadai.common.util.JwtUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * @author licoy.cn
 * @version 2018/10/16
 */
public class JwtTokenArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JwtClaim.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String authorization = request.getHeader("Authorization");
        String result = null;
        JwtClaim token = null;
        if (authorization != null) {
            Annotation[] methodAnnotations = parameter.getParameterAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {
                if (methodAnnotation instanceof JwtClaim) {
                    token = (JwtClaim) methodAnnotation;
                    break;
                }
            }
            if (token != null) {
                result = JwtUtil.get(authorization, token.value());
            }
        }
        if (result != null) {
            return result;
        }
        if (token == null || token.exception()) {
            throw new RequestException(ResponseCode.NOT_SING_IN);
        } else {
            return null;
        }
    }
}
