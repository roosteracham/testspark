package dubbo.impl;

import dubbo.EchoService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String msg) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
        msg = Thread.currentThread().getName() + " at " + format.format(new Date()) + " received " + msg;
        System.out.println(msg);
        return msg;
    }
}
