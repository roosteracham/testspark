package spring.自定义属性编辑器;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class UserManager {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath" +
                ":spring.xml");
        UserManager userManager = context.getBean(UserManager.class);

    }
}
