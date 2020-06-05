package basic.stream;

import com.google.common.collect.Lists;
import domain.User;
import org.apache.spark.sql.sources.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        User user = new User(Lists.newArrayList(1,2,43), 12);
        ArrayList<User> users = Lists.newArrayList(user, new User(Lists.newArrayList(23), 12), new User(Lists.newArrayList(4,5), 13));

        BiConsumer<List<Integer>, List<Integer>> accumulator = List::addAll;

        BinaryOperator<List<Integer>> combiner = (l, r) -> {
            l.addAll(r);
            return l;
        };

        Map<Integer, List<Integer>> collect = users.stream().collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getStateList, Collector.of(
                Lists::newArrayList,
                accumulator,
                combiner,
                Collector.Characteristics.UNORDERED
        ))));
        System.out.println(collect);
    }
}
