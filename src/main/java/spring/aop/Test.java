package spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import spring.util.SpringUtil;

@Component
public class Test {
    public void test() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring2" +
                ".xml");

        Test test = SpringUtil.getBean(Test.class);
        test.test();
    }
}
