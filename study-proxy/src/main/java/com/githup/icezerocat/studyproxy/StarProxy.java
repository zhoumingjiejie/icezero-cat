package com.githup.icezerocat.studyproxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: 调用明星代理
 * CreateDate:  2020/9/14 9:03
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
public class StarProxy implements InvocationHandler {

    /**
     * 目标类，也就是被代理对象
     */
    private Object target;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.debug("我是经纪人，负责收钱，演出准备：{}", args);
        return method.invoke(this.target, args);
    }

    /**
     * 设置被代理对象
     *
     * @param target 被代理对象
     */
    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * 创建代理类实例（虚拟实现类）
     *
     * @return 代理类
     */
    public Object creatProxyObj() {
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }
}
