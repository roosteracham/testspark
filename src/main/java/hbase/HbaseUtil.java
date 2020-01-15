package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Arrays;

public class HbaseUtil {

    // 执行表的操作
    private static Admin hBaseAdmin;

    // 获得与hbase的连接
    private static Connection connection;

    // 配置
    private static Configuration configuration;
    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "had1");
        try {
            connection = ConnectionFactory.createConnection(configuration);
            hBaseAdmin = connection.getAdmin();
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
    public static void putData(boolean writeToWAL) throws IOException {
        Table table = connection.getTable(TableName.valueOf("t1"));
        Put put = new Put("TheReslMT".getBytes());
        put.addColumn("c1".getBytes(), "name".getBytes(), "Mark Twain".getBytes());
        put.addColumn("c1".getBytes(), "email".getBytes(), "samuel@clemens.org".getBytes());
        put.addColumn("c1".getBytes(), "pwd".getBytes(), "LangHorne".getBytes());
        put.setWriteToWAL(writeToWAL);
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
//        putData(true);
//        getDataByRowKey("TheReslMT");

        scanAllTable("t1");
    }

    static void getDataByRowKey(String rowkey) throws IOException {
        Table table = connection.getTable(TableName.valueOf("t1"));
        Get get = new Get(rowkey.getBytes());
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        Arrays.stream(cells).forEach(cell -> System.out.println("family: " + Bytes.toString(CellUtil.cloneFamily(cell)) +
                            "qualifier: " + Bytes.toString(CellUtil.cloneQualifier(cell)) +
                            "value: " + Bytes.toString(CellUtil.cloneValue(cell))));
    }

    static void getDataByQualifier(String rowkey, String family, String qualifier) throws IOException {
        Table table = connection.getTable(TableName.valueOf("t1"));
        Get get = new Get(rowkey.getBytes());
        get.addColumn(Bytes.toBytes(family), qualifier.getBytes());
        Result result = table.get(get);
        byte[] value = result.getValue(family.getBytes(), qualifier.getBytes());
        System.out.println(Bytes.toString(value));
    }

    static void scanAllTable(String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        // 每次扫描表返回的行数， 默认是1
        scan.addColumn("c1".getBytes(), "email".getBytes());
        scan.setCaching(50);
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
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

    static void creaetTable() throws IOException {
        HTableDescriptor twits = new HTableDescriptor("twits");
        HColumnDescriptor columnDescriptor = new HColumnDescriptor("twits");
        columnDescriptor.setMaxVersions(1);
        twits.addFamily(columnDescriptor);
        hBaseAdmin.createTable(twits);
    }
}
