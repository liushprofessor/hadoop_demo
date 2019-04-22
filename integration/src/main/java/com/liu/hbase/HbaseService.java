package com.liu.hbase;

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
 * @date 2019/4/22 0022 18:00
 **/
public class HbaseService {


    private HBaseAdmin admin;
    private Configuration configuration;


    public void HbaseService()throws Exception{

        configuration= HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","192.168.1.10");
        admin=new HBaseAdmin(configuration);
        System.out.println(admin);

    }


    public void  createTable(String name,String familyName)throws Exception{
        TableName tableName=TableName.valueOf(name);
        HTableDescriptor descriptor=new HTableDescriptor(tableName);
        HColumnDescriptor column=new HColumnDescriptor(familyName);
        descriptor.addFamily(column);
        admin.createTable(descriptor);
        /*HTableDescriptor[] descriptors=admin.listTables();
        for (HTableDescriptor descriptor1:descriptors) {
            System.out.println(descriptor1.getTableName());
        }*/

    }


    public void put(String row,String columnFamily,String qualifier,String value)throws Exception{
        HTable hTable=new HTable(configuration,"test2");
        byte[] rowByte= Bytes.toBytes(row);
        byte[] columnFamilyByte=Bytes.toBytes(columnFamily);
        byte[] qualifierByte=Bytes.toBytes(qualifier);
        byte[] valueByte=Bytes.toBytes(value);
        Put put=new Put(rowByte);
        put.addColumn(columnFamilyByte,qualifierByte,valueByte);
        hTable.put(put);

    }


    public void get()throws Exception{
        HTable table=new HTable(configuration,"test2");
        Get get=new Get(Bytes.toBytes("row22"));
        Result result=table.get(get);
        System.out.println(result.getValue(Bytes.toBytes("name"),Bytes.toBytes("2")));
        System.out.println(result);
    }
}
