package dubbo;

import dubbo.impl.EchoServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class EchoServiceTest {
    public static void main(String[] args) throws IOException {
        startService();
//        consumeService();
    }

    private static void consumeService() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer" +
                ".xml");
        context.start();

        EchoService echoService = context.getBean(EchoService.class);
        System.out.println(echoService.echo("abc"));
    }

    private static void startService() throws IOException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider" +
//                ".xml");
//        context.start();

        ServiceConfig<EchoService> config = new ServiceConfig<>();
        config.setApplication(new ApplicationConfig("echo-service"));
        config.setRegistry(new RegistryConfig("zookeeper://8.131.65.50:2181"));
        config.setInterface(EchoService.class);
        config.setRef(new EchoServiceImpl());
//        config.setProtocol(new Do);
        config.export();
        System.in.read();
    }
}
