package com.githup.icezerocat.studyproxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description: cglib代理
 * CreateDate:  2020/9/14 9:49
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
public class CglibProxy implements MethodInterceptor {

    /**
     * 根据一个类型产生代理类，此方法不要求一定放在MethodInterceptor中
     *
     * @param clazz 类字节码
     * @return 创建代理类
     */
    public Object CreatProxyObj(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }


    /**
     * 截听
     *
     * @param o           被的代理对象
     * @param method      拦截的方法
     * @param objects     参数
     * @param methodProxy cglib代理方法
     * @return 结果
     * @throws Throwable 抛出
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.debug("拿人钱财，替人消灾！：{}", objects);
        return methodProxy.invokeSuper(o, objects);
    }
}
