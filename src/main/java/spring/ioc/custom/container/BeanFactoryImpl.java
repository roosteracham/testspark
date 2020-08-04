package spring.ioc.custom.container;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactoryImpl implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private Map<String, Object> singletonMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) {
        Object bean = singletonMap.get(name);
        if (Objects.nonNull(bean)) {
            return bean;
        }

        bean = beanDefinitionMap.get(name);
        if (Objects.isNull(bean)) {
            throw new RuntimeException("has no proper bean, name: " + name);
        }

        BeanDefinition beanDefinition = (BeanDefinition) bean;
        Object realBean = createBean(beanDefinition);
        if ("singleton".equals(beanDefinition.getScope())) {
            singletonMap.put(name, realBean);
        }
        return realBean;
    }

    private Object createBean(BeanDefinition beanDefinition) {

        String tclass = beanDefinition.getTclass();
        if (StringUtils.isBlank(tclass)) {
            throw new RuntimeException("bean class is blank");
        }
        Class<?> clazz;
        try {
            clazz = Class.forName(tclass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("ClassNotFoundException");
        }
        Constructor<?> constructor;
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("NoSuchMethodException");
        }
        Object bean;
        try {
            bean = constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("InstantiationException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("IllegalAccessException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("InvocationTargetException");
        }

        List<Property> properties = beanDefinition.getProperties();
        for (Property property : properties) {
            String name = property.getName();
            String value = property.getValue();
            String ref = property.getRef();
            if (StringUtils.isNotBlank(value)) {
                Map<String, String> params = new HashMap<>();
                params.put(name, value);
                try {
                    BeanUtils.populate(bean, params);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException("IllegalAccessException");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException("InvocationTargetException");
                }
            } else
                if (StringUtils.isNotBlank(ref)) {
                    try {
                        BeanUtils.setProperty(bean, name, getBean(ref));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException("IllegalAccessException");
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        throw new RuntimeException("InvocationTargetException");
                    }
                }
        }
        return bean;
    }

    public void registryBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinition.getName(), beanDefinition);
    }

}
