package spring.自定义属性编辑器;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatePropertyEditor extends PropertyEditorSupport {
    private String format;

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            this.setValue(new SimpleDateFormat(format).parse(text));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
