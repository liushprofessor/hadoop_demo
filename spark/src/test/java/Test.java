import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.junit.Before;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * @author Liush
 * @description
 * @date 2019/3/29 0029 16:07
 **/
public class Test implements Serializable {

    private Configuration configuration;

    @org.junit.Test
    @Before
    public void init()throws Exception{

        configuration= HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","192.168.1.10");
    }

    //将数据写入到Hbase
    @org.junit.Test
    public void writeToHbase(){
        SparkConf sparkConf=new SparkConf();
        sparkConf.setAppName("Liush的测试程序");
        sparkConf.setMaster("spark://192.168.1.10:7077");
        JavaSparkContext sc=new JavaSparkContext("local","Test",sparkConf);
        JavaRDD<String> lines=sc.textFile("hdfs://192.168.1.10:9000/user/root/sparktest.txt");
        JavaRDD<String[]> records=lines.map(s->{
            String[] recordsArray=null;
            if(s==null || s.trim().isEmpty()){
                return recordsArray;
            }
            return s.split("\t");
        });
        JavaPairRDD<String ,String> pairRDD=records.mapToPair(strings ->{
            //根据事件和随机数生成key，后续用作hbase的rowkey
            Random random=new Random();
            int ranNum=random.nextInt(1000);
            Date date=new Date();
            String rowKey=ranNum+"_"+date.getTime();
            String value=strings[1]+","+strings[2];
            //将数据写入到Hbase
            HbaseTest test=new HbaseTest();
            test.put("sparktest",rowKey,"info","age",strings[1]);
            test.put("sparktest",rowKey,"info","addr",strings[2]);
            return new Tuple2<>(rowKey,value);
        });
        //Spark API采用懒加载，直到执行时才正宗开启任务
        pairRDD.saveAsTextFile("C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)/liu/spark5");



    }


    //将Hbase中的数据读入到Spark中
    @org.junit.Test
    public void readToSpark()throws Exception{
        SparkConf sparkConf=new SparkConf();
        sparkConf.setAppName("Liush的测试程序");
        JavaSparkContext sc=new JavaSparkContext("local","Test",sparkConf);
        sparkConf.setMaster("spark://192.168.1.10:7077");
        Configuration hbaseConf=HBaseConfiguration.create();
        hbaseConf.set("hbase.zookeeper.quorum","192.168.1.10");
        hbaseConf.set(TableInputFormat.INPUT_TABLE, "sparktest");
        JavaPairRDD<ImmutableBytesWritable, Result> javaPairRdd=sc.newAPIHadoopRDD(hbaseConf, TableInputFormat.class,ImmutableBytesWritable.class, Result.class);
        /*JavaRDD<Row> javaRDD=javaPairRdd.map(new Function<Tuple2<ImmutableBytesWritable,Result>, Row>() {
            @Override
            public Row call(Tuple2<ImmutableBytesWritable, Result> immutableBytesWritableResultTuple2) throws Exception {
                Result result=immutableBytesWritableResultTuple2._2();
                byte[] valueByte=result.getValue(Bytes.toBytes("info"),Bytes.toBytes("age"));
                System.out.println(Bytes.toString(valueByte));
                valueByte=result.getValue(Bytes.toBytes("info"),Bytes.toBytes("age"));
                System.out.println(valueByte);
                return null;
            }
        });*/
        javaPairRdd.foreach(new VoidImple());
        javaPairRdd.saveAsTextFile("C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\liu\\spark9");
    }


    public class VoidImple implements VoidFunction<Tuple2<ImmutableBytesWritable, Result>>,Serializable{
        @Override
        public void call(Tuple2<ImmutableBytesWritable, Result> immutableBytesWritableResultTuple2) throws Exception {
            //Result result=immutableBytesWritableResultTuple2._2();
            /*byte[] valueByte=result.getValue(Bytes.toBytes("info"),Bytes.toBytes("age"));
            System.out.println(Bytes.toString(valueByte));
            valueByte=result.getValue(Bytes.toBytes("info"),Bytes.toBytes("age"));
            System.out.println(Bytes.toString(valueByte));*/
        }
    }

}
