package com.hadoop.demo.write;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class App 
{
	
	private static final Logger logger = LogManager.getLogger(App.class);
	
    public static void main( String[] args ) throws IOException
    {
    	logger.info("Starting...");
    	System.setProperty("HADOOP_USER_NAME", "hadoop");
        Configuration conf = new Configuration();
        
        //https://github.com/sardetushar/hadooponwindows/archive/master.zip
        System.setProperty("hadoop.home.dir", "C:/Tools/hadoop/hadooponwindows-master");

        conf.addResource(new Path("src/main/resources/hadoop/core-site.xml"));
        conf.addResource(new Path("src/main/resources/hadoop/hdfs-site.xml"));
        
        String dirName = "hdfs://hadoop:9000/user/testJava/demo2.txt";
        FileSystem fileSystem = FileSystem.get(conf);


        Path path = new Path(dirName);
        fileSystem.setReplication(path, (short)1);
        BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fileSystem.create(path, true)));
        
        String data = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww";
        
        for (int i=0; i<36000; i++) {
        	br.write(data);
        }
        
        br.flush();
        br.close();
       
        fileSystem.close();
    	logger.info("Done...");
    }
}
