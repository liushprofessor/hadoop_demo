import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;

import java.io.Serializable;

/**
 * @author Liush
 * @description 如果将这段代码直接放在spark rdd的map的匿名内部类中会org.apache.spark.SparkException: Task not serializable
 * 所有将其单独抽出来到一个类中并实现java序列化接口
 * @date 2019/3/30 0030 16:49
 **/
public class HbaseTest implements Serializable {

    private HBaseAdmin admin;
    private Configuration configuration;

    public HbaseTest()throws Exception{

        configuration= HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","192.168.1.10");
        admin=new HBaseAdmin(configuration);


    }

    //将数据存入hbase
    public void put(String tableName,String rowKey,String columnFamily,String qualifier,String value)throws Exception{
        HTable hTable=new HTable(configuration,tableName);
        byte[] rowByte= Bytes.toBytes(rowKey);
        byte[] columnFamilyByte=Bytes.toBytes(columnFamily);
        byte[] qualifierByte=Bytes.toBytes(qualifier);
        byte[] valueByte=Bytes.toBytes(value);
        Put put=new Put(rowByte);
        put.addColumn(columnFamilyByte,qualifierByte,valueByte);
        hTable.put(put);

    }
}
