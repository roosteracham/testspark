package spring.源码解读;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import spring.ioc.custom.domain.Student;
import spring.ioc.custom.domain.Teacher;

public class XMLBeanFactoryTest {
    public static void main(String[] args) {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("ioc.xml"));
        Teacher teacher = beanFactory.getBean("teacher", Teacher.class);
        System.out.println(JSON.toJSONString(teacher));
    }
}
