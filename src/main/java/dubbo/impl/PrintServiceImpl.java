package dubbo.impl;

import dubbo.IPrintService;

public class PrintServiceImpl implements IPrintService {
    @Override
    public void printInfo() {
        System.out.println("hello world!");
    }
}
