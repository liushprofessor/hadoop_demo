import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;

/**
 * @author Liush
 * @description
 * @date 2019/3/26 0026 15:26
 **/
public class Test {

    private HBaseAdmin admin;
    private Configuration configuration;

    @org.junit.Test
    @Before
    public void init()throws Exception{

        configuration=HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","192.168.1.10");
        admin=new HBaseAdmin(configuration);
        System.out.println(admin);

    }

    @org.junit.Test
    public void  createTable()throws Exception{
        TableName tableName=TableName.valueOf("user2");
        HTableDescriptor descriptor=new HTableDescriptor(tableName);
        HColumnDescriptor colum=new HColumnDescriptor("data");
        descriptor.addFamily(colum);
        admin.createTable(descriptor);
        HTableDescriptor[] descriptors=admin.listTables();
        for (HTableDescriptor descriptor1:descriptors) {
            System.out.println(descriptor1.getTableName());
        }

    }

    @org.junit.Test
    public void put()throws Exception{
        HTable hTable=new HTable(configuration,"test2");
        byte[] row=Bytes.toBytes("row22");
        byte[] columnFamily=Bytes.toBytes("name");
        byte[] qualifier=Bytes.toBytes("2");
        byte[] value=Bytes.toBytes("值");
        Put put=new Put(row);
        put.addColumn(columnFamily,qualifier,value);
        hTable.put(put);

    }

    @org.junit.Test
    public void get()throws Exception{
        HTable table=new HTable(configuration,"test2");
        Get get=new Get(Bytes.toBytes("row22"));
        Result result=table.get(get);
        System.out.println(result.getValue(Bytes.toBytes("name"),Bytes.toBytes("2")));
        System.out.println(result);
    }


}
