package dubbo.impl;

import dubbo.IPrintService;
import org.apache.dubbo.common.extension.Adaptive;

@Adaptive({"impl1","impl"})
public class PrintServiceWrapper implements IPrintService {

    private IPrintService printService;

    public PrintServiceWrapper(IPrintService printService) {
        this.printService = printService;
    }

    @Override
    public void printInfo() {
        printService.printInfo();
    }
}
