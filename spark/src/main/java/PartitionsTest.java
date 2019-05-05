import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.io.Serializable;
import java.util.*;

/**
 * @author Liush
 * @description
 * @date 2019/5/4 0004 10:08
 **/
public class PartitionsTest implements Serializable {


    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("Liush的测试程序");
        sparkConf.setMaster("spark://192.168.1.10:7077");
        JavaSparkContext sc = new JavaSparkContext("local", "Test", sparkConf);
        JavaRDD<String> lines = sc.textFile("hdfs://192.168.1.10:9000/cat", 1);
        //JavaRDD<String> javaRDD = lines.coalesce(1); 设置运行分区，reduceByKey（聚合）后执行的任务的分区
        JavaPairRDD<String, Integer> pairRDD = lines.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                String[] array = s.split(",");
                return new Tuple2<>(array[0], Integer.valueOf(array[1]));
            }
        });
        //将多个文件聚合
        JavaPairRDD<String, Integer> pairRDD2=pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        pairRDD2.saveAsTextFile("C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\liu\\spark25");






    }
}
