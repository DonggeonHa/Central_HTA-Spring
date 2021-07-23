package com.sample.web.interceptor;

import com.sample.web.annotation.LoginUser;
import com.sample.web.utils.SessionUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    LoginCheckInterceptor
        - 클라이언트의 요청이 로그인이 필요한 요청인 경우 세션에 로그인된 사용자 정보가 존재하는지 확인한다.
        - 세션에 로그인된 사용자 정보가 존재하면 클라이언트의 요청을 처리할 요청핸들러 메소드를 실행시킨다.
            * preHandle(req, res, handler)가 true 값을 반환함.
        - 세션에 로그인된 사용자 정보가 존재하지 않으면 요청핸들러 메소드를 실행시키지 않는다.
            * preHandle(req, res, handler)가 false 값을 반환함. + 로그인 폼을 재요청하는 응답을 보낸다.
            * preHandle(req, res, handler)가 예외를 발생시킨다.
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 요청핸들러 메소드 정보를 포함하고 있는 객체를 조회한다.
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 요청핸들러 메소드의 모든 매개변수정보를 조회한다.
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();

        // @LoginUser 어노테이션을 포함하고 있는지 여부를 저장하는 변수 선언
        boolean hasLoginUserAnnotation = false;
        for (MethodParameter parameter : methodParameters) {
            // 매개변수에 @LoginUser 어노테이션이 저장되어 있는지 확인한다.
            if (parameter.getParameterAnnotation(LoginUser.class) != null) {
                hasLoginUserAnnotation =true;
                break;
            }
        }

        // hasLoginUserAnnotation이 true면 요청핸들러의 매개변수중에서 하나가 @LoginUser 어노테이션을 포함하고 있는 경우
        // 요청핸들러 메소드의 매개변수에 @LoginUser가 있고, 로그인된 사용자 정보가 없는 경우
        if (hasLoginUserAnnotation && SessionUtils.getAttribute("LOGINED_USER") == null) {

            // 로그인 후 되돌아가 경로를 생성해서 세션에 저장한다.
            String requestURI = request.getRequestURI().replace(request.getContextPath(), "");
            String queryString = request.getQueryString();

            String returnPath = requestURI + (queryString != null ? "?" + queryString : "");
            request.getSession().setAttribute("returnPath", returnPath);

            // 로그인화면을 재요청하는 URL을 응답으로 보낸다.
            response.sendRedirect("/spring-mvc/login?error=deny");
            // 요청핸들러 메소드가 실행되지 않도록 false값을 반환한다.
            return false;
        }

        // 요청핸들러 메소드가 실행되도록 true값을 반환한다.
        return true;
    }
}
