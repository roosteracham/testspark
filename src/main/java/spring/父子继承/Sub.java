package spring.父子继承;

import domain.User;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Sub extends Base {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String b;

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String abc(int v) {
        return user.getName() + "-" + v;
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
