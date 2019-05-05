import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.CoprocessorEnvironment;

import java.io.IOException;

/**
 * @author Liush
 * @description
 * @date 2019/4/30 0030 13:54
 **/
public class MyCoprocessor implements Coprocessor {
    @Override
    public void start(CoprocessorEnvironment coprocessorEnvironment) throws IOException {
                System.out.println("成功。。。。。。。。。。。。。。。。。。。。");
    }

    @Override
    public void stop(CoprocessorEnvironment coprocessorEnvironment) throws IOException {

    }
}
