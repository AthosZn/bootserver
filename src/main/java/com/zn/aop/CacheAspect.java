package com.zn.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @author :zhounan
 */
@Configuration
//开启AspectJ 自动代理模式,如果不填proxyTargetClass=true，默认为false，
@EnableAspectJAutoProxy(proxyTargetClass=true)
//扫描注入类
@ComponentScan(basePackages = "com.zn.*")
@Component
@Aspect
public class CacheAspect {

    @Around("@annotation(cacheableMem)")
    public Object catCacheTransactionProcess(ProceedingJoinPoint pjp, CacheableMem cacheableMem) throws Throwable {
        String transName = pjp.getSignature().getName();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getMethod().getName();
        String className = pjp.getTarget().getClass().getName();
        StringBuilder attribute = new StringBuilder();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = pjp.getArgs();
        try {
            doSth(cacheableMem.key());
            ExpressionParser parser = new SpelExpressionParser();
            StandardEvaluationContext context = new StandardEvaluationContext();
            int i=0;
            for(String parameterName:parameterNames){
                context.setVariable(parameterName,args[i]);i++;
            }
            //SpEL 表达式
            Expression exp =parser.parseExpression(cacheableMem.key());
            String phraseStr = (String)exp.getValue(context);
            Object result = pjp.proceed();
            doSth(phraseStr);
            return result;
        } catch (Throwable e) {
            throw e;
        }finally{
        }
    }

    private void doSth(Object arg1){
        System.out.println(arg1);
    }

}
