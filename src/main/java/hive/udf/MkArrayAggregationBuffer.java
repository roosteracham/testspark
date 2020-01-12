package hive.udf;

import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;

import java.util.List;

public class MkArrayAggregationBuffer implements GenericUDAFEvaluator.AggregationBuffer {
    List<Object> container;
}
