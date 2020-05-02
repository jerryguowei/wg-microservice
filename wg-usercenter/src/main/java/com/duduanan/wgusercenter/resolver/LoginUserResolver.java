package com.duduanan.wgusercenter.resolver;

import com.duduanan.commons.entity.SysUser;
import com.duduanan.wgusercenter.annotation.LoginUser;
import com.duduanan.wgusercenter.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;

@Component
public class LoginUserResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return findMethodAnnotation(LoginUser.class, parameter) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        Object principal = authentication.getPrincipal();
        if(principal instanceof Jwt){
            String username = ((Jwt) principal).getClaim("user_name");
            SysUser sysUser = sysUserRepository.findByUsername(username);
            return sysUser;

        }
        return null;
    }

    private <T extends Annotation> T findMethodAnnotation(Class<T> annotationClass, MethodParameter parameter) {
        T annotation = parameter.getParameterAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        Annotation[] annotationsToSearch = parameter.getParameterAnnotations();
        for (Annotation toSearch : annotationsToSearch) {
            annotation = AnnotationUtils.findAnnotation(toSearch.annotationType(), annotationClass);
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }
}
