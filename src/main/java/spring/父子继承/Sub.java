package spring.父子继承;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Sub extends Base {

    private String b;

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Sub{" +
                "b='" + b + '\'' +
                ", a='" + a + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Sub sub = new XmlBeanFactory(new ClassPathResource("spring2.xml")).getBean(Sub.class);
        System.out.println(sub);
    }
}
