package dubbo;


import org.apache.dubbo.common.extension.SPI;

@SPI("wrapper")
public interface IPrintService {
    void printInfo();
}
