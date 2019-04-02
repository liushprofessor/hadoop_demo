package com.liu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * @author Liush
 * @description
 * @date 2019/3/20 0020 9:17
 **/
public class ReadTest {




    //用URL读取hdfs文件方法打开
    @Test
    public void readFromURL() {

        Configuration conf = new Configuration();
        InputStream in = null;
        try {
            URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
            in = new URL("hdfs://node1:9000/user/root/liu.txt").openStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(in));
            String line=null;
            while ((line=reader.readLine())!=null){
                 System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //用api读取文件
    @Test
    public void readFromFileSystem()throws Exception{

        String uri="hdfs://192.168.1.10:9000/user/root/liu.txt";
        Configuration configuration=new Configuration();
        FileSystem fileSystem=FileSystem.get(URI.create(uri),configuration);
        InputStream in=fileSystem.open(new Path(uri));
        BufferedReader reader =new BufferedReader(new InputStreamReader(in));
        String line=null;
        while ((line=reader.readLine())!=null){
            System.out.println(line);
        }

    }

    //用api写入文件
    @Test
    public void createFile()throws Exception{
        String uri="hdfs://192.168.1.10:9000/user/root/liu.txt";
        Configuration configuration=new Configuration();
        FileSystem fileSystem=FileSystem.get(URI.create(uri),configuration);
        OutputStream out=fileSystem.create(new Path(uri), new Progressable() {
            @Override
            public void progress() {
                System.out.println(".");
            }
        });
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\1.TXT")));
        String line=null;
        while ((line=reader.readLine())!=null){
            writer.write(line,0,line.length());
        }
        writer.flush();


    }


}
