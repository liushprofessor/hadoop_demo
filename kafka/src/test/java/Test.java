/**
 * @author Liush
 * @description
 * @date 2019/4/11 0011 13:58
 **/
public class Test {

    @org.junit.Test
    public void producer()throws Exception{
        Producer producer=new Producer();
        producer.init("node1:9092,node1:9093,node1:9094");
        producer.send("my-replicated-topic3","XXXXXX");
    }


    @org.junit.Test
    public void consumer(){
        Consumer consumer=new Consumer();
        consumer.init("node1:9092,node1:9093,node1:9094","liu");
        consumer.receive("my-replicated-topic3");
    }

}
