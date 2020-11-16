package dubbo;


import org.apache.dubbo.common.extension.SPI;

@SPI("impl")
public interface IPrintService {
    void printInfo();
}
