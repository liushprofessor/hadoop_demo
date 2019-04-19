/**
 * @author Liush
 * @description
 * @date 2019/4/11 0011 16:49
 **/
public class MainTest {

    public static void main(String[] args) {

        Consumer consumer=new Consumer();
        consumer.init("node1:9092","liu");
        consumer.receive2("userViewOut");

    	}
}
