package hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Description(name = "zodiac", value = "a simple udf",
extended = "extended msg")
public class UDFZodiacSign extends UDF {

    private ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        public DateFormat get() {
            return new SimpleDateFormat("MM-dd-yyyy");
        }
    };

    public String evaluate(Integer month, Integer day) {
        if (month == 1) {
            if (day < 20) {
                return "Capricorn";
            } else {
                return "Aquarious";
            }
        }

        if (2 == month) {
            if (day < 19) {
                return "Aquarious";
            } else {
                return "Pisces0";
            }
        }
        return "zsf";
    }

    public String evaluate(Date date) {
        return  this.evaluate(date.getMonth() + 1, date.getDate());
    }

    public String evaluate(String body) {
        Date date = null;
        try {
            date = dateFormat.get().parse(body);
        } catch (Exception e) {
            return  null;
        }
        return  this.evaluate(date.getMonth() + 1, date.getDate());
    }
}
