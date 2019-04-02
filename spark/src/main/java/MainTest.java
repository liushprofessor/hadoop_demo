import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.io.Serializable;

/**
 * @author Liush
 * @description
 * @date 2019/3/31 0031 13:09
 **/
public class MainTest implements Serializable {


    public static void main(String[] args)throws Exception {
        Configuration configuration= HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","192.168.1.10");
        MainTest mainTest=new MainTest();
        mainTest.readToSpark();
    	}



    public void readToSpark()throws Exception{
        SparkConf sparkConf=new SparkConf();
        sparkConf.setAppName("Liush的测试程序");
        JavaSparkContext sc=new JavaSparkContext("local","Test",sparkConf);
        sparkConf.setMaster("spark://192.168.1.10:7077");
        Configuration hbaseConf=HBaseConfiguration.create();
        hbaseConf.set("hbase.zookeeper.quorum","192.168.1.10");
        hbaseConf.set(TableInputFormat.INPUT_TABLE, "sparktest");
        JavaPairRDD<ImmutableBytesWritable, Result> javaPairRdd=sc.newAPIHadoopRDD(hbaseConf, TableInputFormat.class,ImmutableBytesWritable.class, Result.class);
        javaPairRdd.foreach(new VoidImple());
        javaPairRdd.saveAsTextFile("C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\liu\\spark18");
    }


    public class VoidImple implements VoidFunction<Tuple2<ImmutableBytesWritable, Result>>,Serializable{
        @Override
        public void call(Tuple2<ImmutableBytesWritable, Result> immutableBytesWritableResultTuple2) throws Exception {
            Result result=immutableBytesWritableResultTuple2._2();
            byte[] valueByte=result.getValue(Bytes.toBytes("info"),Bytes.toBytes("age"));
            System.out.println(Bytes.toString(valueByte));
            valueByte=result.getValue(Bytes.toBytes("info"),Bytes.toBytes("addr"));
            System.out.println(Bytes.toString(valueByte));
        }
    }
}
