package spring.ioc.custom.container;

import com.alibaba.fastjson.JSON;
import org.dom4j.DocumentException;

public class XMLBeanFactory extends BeanFactoryImpl {
    XMLConfigReader reader = new XMLConfigReader(this);

    public XMLBeanFactory() {
    }

    public XMLBeanFactory(String configPath) throws DocumentException {
        reader.readConfig(configPath);
    }

    public static void main(String[] args) throws DocumentException {
        XMLBeanFactory factory = new XMLBeanFactory("ioc.xml");
        Object bean = factory.getBean("cls");
        System.out.println(JSON.toJSONString(bean));
    }
}
