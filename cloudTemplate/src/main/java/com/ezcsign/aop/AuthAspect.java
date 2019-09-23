package com.ezcsign.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AuthAspect {

    /**
     * Spring中使用@Pointcut注解来定义方法切入点
     * @Pointcut 用来定义切点，针对方法  @Aspect 用来定义切面，针对类
     * 后面的增强均是围绕此切入点来完成的
     * 此处仅配置被我们刚才定义的注解：AuthToken修饰的方法即可
     *
     */

    public AuthAspect(){
        System.out.println();
        System.out.println();
        System.out.println("--------------注解");
    }

    //估计 authToken 为 @Pointcut 中的最后一个 authToken
    @Pointcut("@annotation(authToken) || @within(authToken) ")
    public void doAuthToken(Required authToken) {
    }
    /**
     *
     * @param pjp
     * @param authToken 类注解
     * @return
     * @throws Throwable
     */
    @Around("doAuthToken(authToken)")
    public Object deBefore(ProceedingJoinPoint pjp, Required authToken) throws Throwable {
        System.out.println("Around----------------");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //从切面中获取当前方法注解
        Required method = signature.getMethod().getAnnotation(Required.class);
        System.out.println("执行方法名:" + signature.getName());


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取 request response session
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession session = request.getSession();

        //方法上的参数 依次
        Object[] args = pjp.getArgs();
        //get 参数
        String params = request.getQueryString();

        try {
            //类注解
            System.out.println(authToken.value()[0]);

        } catch (Exception e) {
        }
        try {
            //方法注解
            System.out.println(method.value()[0]);

        } catch (Exception e) {
        }


        System.out.println("getSession: " + request.getSession().getId());
        System.out.println("---------原方法执行之前-------------");
        // 执行原方法，并记录返回值。
        Object proceed = pjp.proceed();

        System.out.println("---------原方法执行之后-------------");
        return proceed;
    }

//    public static Map<String, Object> getKeyAndValue(Object obj) {
//        Map<String, Object> map = new HashMap<>();
//        // 得到类对象
//        Class userCla = (Class) obj.getClass();
//        /* 得到类中的所有属性集合 */
//        Field[] fs = userCla.getDeclaredFields();
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            f.setAccessible(true); // 设置些属性是可以访问的
//            Object val = new Object();
//            try {
//                val = f.get(obj);
//                // 得到此属性的值
//                map.put(f.getName(), val);// 设置键值
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return map;
//    }

}
