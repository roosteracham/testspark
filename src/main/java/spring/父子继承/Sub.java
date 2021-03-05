package spring.父子继承;

import domain.User;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import spring.util.SpringUtil;

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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(("spring2" +
                ".xml"));
//        Sub sub = context.getBean(Sub.class);
//        System.out.println(sub);
        System.out.println(SpringUtil.getBean(Sub.class));
    }
}
