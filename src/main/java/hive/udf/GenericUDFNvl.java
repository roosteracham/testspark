package hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUtils;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

@Description(
        name = "nvl",
        value = "return first not null value",
        extended = "extended msg nvl"
)
public class GenericUDFNvl extends GenericUDF {

    private GenericUDFUtils.ReturnObjectInspectorResolver returnObjectInspectorResolver;

    private ObjectInspector[] argumentIOS;

    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        if (arguments.length != 2) {
            throw new UDFArgumentLengthException("args length is not 2");
        }

        argumentIOS = arguments;
        returnObjectInspectorResolver = new GenericUDFUtils.ReturnObjectInspectorResolver(true);

        if (!(returnObjectInspectorResolver.update(argumentIOS[0]) && returnObjectInspectorResolver.update(argumentIOS[1]))) {
            throw new UDFArgumentTypeException(2, "args should have same type..");
        }
        return returnObjectInspectorResolver.get();

    }

    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        Object retVal = returnObjectInspectorResolver.convertIfNecessary(arguments[0].get(), argumentIOS[0]);
        if (retVal == null) {
            retVal = returnObjectInspectorResolver.convertIfNecessary(arguments[1].get(), argumentIOS[1]);
        }
        return retVal;
    }

    @Override
    public String getDisplayString(String[] children) {
        StringBuilder str = new StringBuilder();
        str.append("if ")
                .append(children[0])
                .append(" is null, ")
                .append("returns ")
                .append(children[1]);
        return str.toString();
    }
}
