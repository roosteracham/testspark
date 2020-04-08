package jvmstuff.reflect;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import domain.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMethod {

    static MethodAccess access = MethodAccess.get(User.class);

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        User user = new User();
        Class<? extends User> userClass = user.getClass();
        Map<Integer, Integer> hashMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        setValue(user, userClass, hashMap);
//        Method method;
//        method = userClass.getMethod("setState", list.getClass().getInterfaces()[0]);
//        method.invoke(user, list);
        setValueASM(user, "setState", list);
        System.out.println(user);

    }

    private static <T> void setValue(User user, Class<? extends User> userClass, T hashMap) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = userClass.getMethod("setState", hashMap.getClass().getInterfaces()[0]);
        method.invoke(user, hashMap);
    }

    private static <T> void setValueASM(User user, String method, T hashMap) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        access.invoke(user, method, hashMap);
    }
}
