package com.liu;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author Liush
 * @description 复杂的类对象的装配客用FactoryBean去构建，如Mybatis的SqlSessionFactoryBean，如果只是简单的类可以只用xml的形式去配置，实现完FactoryBean后
 * 需要加注解在Spring容器中注册加@Component，或者用xml的形式注册
 * @date 2019/5/27 11:44
 **/
@Component
public class MyFactoryBean implements FactoryBean<MyBean> {


    @Override
    public MyBean getObject() throws Exception {
        return new MyBean("这个是我的bean");
    }

    @Override
    public Class<?> getObjectType() {

        return MyBean.class;
    }
}
