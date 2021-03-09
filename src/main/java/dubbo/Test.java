package dubbo;


import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {

        // java spi
//        ServiceLoader<IPrintService> serviceLoader = ServiceLoader.load(IPrintService.class);
//        serviceLoader.forEach(IPrintService::printInfo);

        // dubbo spi
        IPrintService service = ExtensionLoader.getExtensionLoader(IPrintService.class).getDefaultExtension();
        service.printInfo();
    }
}
