package hbase.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FilterTest {

    {}

    public static void main(String[] args) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "had1");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table table = connection.getTable(TableName.valueOf("t1"));
        Scan scan = new Scan();
        PrefixFilter prefixFilter = new PrefixFilter(Bytes.toBytes("r"));
        QualifierFilter qualifierFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator("name".getBytes()));
        scan.setFilter(qualifierFilter);
        scan.setBatch(1);
        // 將設置起始行和結束行， 不完整相當於前綴過濾器
        scan.setRowPrefixFilter("r".getBytes());

        ResultScanner scanner = table.getScanner(scan);

        for (Result result : scanner) {
            System.out.println(("rowKey: " + Bytes.toString(result.getRow())));
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println("rowKey: " + Bytes.toString(CellUtil.cloneRow(cell)) +
                        ", family: " + Bytes.toString(CellUtil.cloneFamily(cell)) +
                        ", qualifier: " + Bytes.toString(CellUtil.cloneQualifier(cell)) +
                        ", qualifier: " + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
    }
}
