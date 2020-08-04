package spring.ioc.custom.container;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class XMLConfigReader {

    BeanFactoryImpl beanFactory;

    public XMLConfigReader(BeanFactoryImpl beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void readConfig(String configPath) throws DocumentException {
        Document document = reaDocument(configPath);
        Element rootElt = document.getRootElement();
        //获取do节点
        List<Element> doElement = rootElt.selectNodes("//bean");
        for (Element element : doElement) {
//            Element ele = (Element) element;
            BeanDefinition beanDefinition = parseBeanDefinition(element);
            beanFactory.registryBeanDefinition(beanDefinition);
        }
    }

    private BeanDefinition parseBeanDefinition(Element element) {
        BeanDefinition beanDefinition = new BeanDefinition();
        String id = element.attributeValue("id");
        String aClass = element.attributeValue("class");
        String scope = element.attributeValue("scope");
        beanDefinition.setName(id);
//        beanDefinition.setScope(scope);
        beanDefinition.setTclass(aClass);
        List<Element> properties = element.elements("property");
        if (CollectionUtils.isNotEmpty(properties)) {
            List<Property> propertyList = Lists.newArrayListWithCapacity(properties.size());
            for (Element element1 : properties) {
                String name = element1.attributeValue("name");
                String ref = element1.attributeValue("ref");
                String value = element1.attributeValue("value");
                Property property = new Property(name, value, ref);
                propertyList.add(property);
            }
            beanDefinition.setProperties(propertyList);
        }
        return beanDefinition;
    }

    private Document reaDocument(String configPath) throws DocumentException {
        // 创建一个SAXReader对象
        SAXReader sax = new SAXReader();
        // 根据指定的路径创建file对象
        File xmlFile = new File(configPath);
        // 获取document对象,如果文档无节点，则会抛出Exception提前结束
        return sax.read(xmlFile);
    }
}
