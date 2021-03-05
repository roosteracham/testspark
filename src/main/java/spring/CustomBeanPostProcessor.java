package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import spring.父子继承.Sub;

@Component
public class CustomBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 只修改sub 类型的bean
        if (bean instanceof Sub) {
            ((Sub)bean).setB("post b");
        }
        return bean;
    }
}
