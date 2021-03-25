package domain;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class User implements Externalizable {
    public static final long serializationVersionID = 1L;
    private String name;
    private double salary;
    private Integer age;
    private String country = "ZG";

    public List<Integer> getStateList() {
        return stateList;
    }

    public void setStateList(List<Integer> stateList) {
        this.stateList = stateList;
    }

    private String state = "HN";
    private List<Integer> stateList;

    public User(List<Integer> stateList, Integer age) {
        this.stateList = stateList;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public void setState(Map<Integer, Integer> state) {
        this.state = "state";
    }

    public void setState(List<Integer> state) {
        this.name = "stateList";
    }

    public User(String name, Double salary, Integer age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.name.getBytes());
        out.writeDouble(this.salary);
        out.writeInt(this.age);
        out.writeObject(this.country.getBytes());
        out.writeObject(this.state.getBytes());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = Bytes.toString((byte[]) in.readObject());
        this.salary = in.readDouble();
        this.age = in.readInt();
        this.country = Bytes.toString((byte[]) in.readObject());
        this.state = Bytes.toString((byte[]) in.readObject());
    }

    public static void main(String[] args) {
        try {
            String fileName = "user.txt";
//            OutputStream outputStream = new FileOutputStream(fileName);
//            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
//            User ab = new User("ab", 12D, 2);
//            stream.writeObject(ab);
//            stream.flush();;
//            stream.close();

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            User user = (User) inputStream.readObject();
            System.out.println(JSON.toJSON(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
