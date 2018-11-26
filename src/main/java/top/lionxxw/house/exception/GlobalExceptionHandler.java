package top.lionxxw.house.exception;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.lionxxw.house.web.base.ServerResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 全局捕获页面异常
 * created on 2018/4/24 13:30
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@Aspect
@Component
public class GlobalExceptionHandler {

    private final static String ERROR = "/500.html";

    /**
     * 定义AOP扫描路径
     */
    @Pointcut("execution(public * top.lionxxw.house.web.*.*Controller.*(..))")
    public void log() {
    }

    @AfterThrowing(value = "log()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            log.error("attributes 未获取到");
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        if (null == response) {
            log.error("response 未获取到");
            return;
        }
        String url = request.getRequestURI();
        log.error("url:{} GlobalExceptionHandler afterThrowing:{}", url, ex);
        RestController annotation = null;
        try {
            Class<?> aClass = Class.forName(joinPoint.getSignature().getDeclaringTypeName());
            annotation = AnnotationUtils.findAnnotation(aClass, RestController.class);
        } catch (ClassNotFoundException e) {
            log.error("RestController e:{}", e);
        }
        if (annotation == null) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if (null == responseBody) {
                try {
                    response.sendRedirect(ERROR);
                } catch (IOException e) {
                    log.error("response.sendRedirect e:{}", e);
                }
            }else{
                backErrorInfo(ex, response);
            }
        }else{
            backErrorInfo(ex, response);
        }
    }

    private void backErrorInfo(Exception ex, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            ServerResult result;
            if (ex instanceof ServerException) {
                ServerException serverException = (ServerException) ex;
                result = ServerResult.error(serverException.getCode(), serverException.getMessage());
            } else {
                result = ServerResult.error(ErrorCode.ERROR);
            }

            out.append(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}