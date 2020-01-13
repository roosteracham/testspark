package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseUtil {

    private static Admin hBaseAdmin;

    private static Connection connection;
    private static Configuration configuration;
    private static HTablePool pool;
    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "had1");
        try {
            connection = ConnectionFactory.createConnection(configuration);
            hBaseAdmin = connection.getAdmin();
            pool = new HTablePool(configuration, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean tableExists(String name) throws IOException {
        Configuration config = new Configuration();
        config.set("hbase.zookeeper.quorum", "had1");
        hBaseAdmin = new HBaseAdmin(config);
        return hBaseAdmin.tableExists(TableName.valueOf(name));
    }

    public static final void close(Connection conn) throws IOException {
        if (conn != null)
            conn.close();
        if (hBaseAdmin!=null)
            hBaseAdmin.close();
    }

    private static boolean tableExists(String np, String name) throws IOException {

        TableName table = TableName.valueOf(np + ":" + name);
        return hBaseAdmin.tableExists(table);

    }
    public static void createTable(String np, String name, String[] families) throws IOException {
        HTableDescriptor desc = new HTableDescriptor(TableName.META_TABLE_NAME.valueOf(np+ ":"+name));
        for (String f : families) {
            HColumnDescriptor family = new HColumnDescriptor(f);
            desc.addFamily(family);
        }
        hBaseAdmin.createTable(desc);
    }

    public static void putData(String tableName, String row, String family, String col, String val) throws IOException {
        //hBaseAdmin.deleteColumn();
        HTable hTable = new HTable(TableName.valueOf(tableName), connection);
        Put put = new Put(row.getBytes());
        put.addColumn(family.getBytes(), col.getBytes(), val.getBytes());
        hTable.put(put);
        hTable.close();
    }

    public static void dropTable(String name) throws IOException {

        if (tableExists(name)) {
            TableName tableName = TableName.valueOf(name);
            hBaseAdmin.disableTable(tableName);
            hBaseAdmin.deleteTable(tableName);
        }
    }

    public static final void getData(String tableName, String row,String family, String col, int version) throws IOException {
        HTable table = new HTable(TableName.valueOf(tableName), connection);
        Get get = new Get(row.getBytes());
        get.addFamily(family.getBytes());
        get.addColumn(family.getBytes(), col.getBytes());
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println("row:" + Bytes.toString(cell.getRow())
                    + "family: " + Bytes.toString(cell.getFamily())
                    + "cell: " + Bytes.toString(cell.getQualifier())
                    + "value: " + Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }


    public static final void scanTable(String tb) throws IOException {
        HTable hTable = new HTable(configuration, TableName.valueOf(tb));
        Scan scan = new Scan();
        ResultScanner results = hTable.getScanner(scan);
        for (Result result : results) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println("row:" + Bytes.toString(cell.getRow())
                + "family: " + Bytes.toString(cell.getFamily())
                + "cell: " + Bytes.toString(cell.getQualifier())
                + "value: " + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
    }

    // 放入数据
    public static void putData() throws IOException {
        HTableInterface table = pool.getTable("t1");
        Put put = new Put("TheReslMT".getBytes());
        put.addColumn("c1".getBytes(), "name".getBytes(), "Mark Twain".getBytes());
        put.addColumn("c1".getBytes(), "email".getBytes(), "samuel@clemens.org".getBytes());
        put.addColumn("c1".getBytes(), "pwd".getBytes(), "LangHorne".getBytes());
        table.put(put);
        table.close();
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(tableExists("bd","t2"));
//        createTable("bd","t2",new String[]{"c1"});
//        System.out.println(tableExists("bd","t2"));
//
//        dropTable("t2");
//        putData("bd:t2", "r0002", "c1", "sex", "male");
//        getData("bd:t2", "r0002", "c1", "name", 0);
//        scanTable("t1");
//        close(connection);
        putData();
    }
}
