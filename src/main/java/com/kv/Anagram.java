package com.kv;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.kv.mapper.AnagramMapper;
import com.kv.reducer.AnagramReducer;


public class Anagram extends Configured implements Tool{
	
	
	public static void main(String[] args) throws Exception{
		int exitCode = ToolRunner.run(new Anagram(), args);
		System.exit(exitCode);
	}
	
	public int run(String[] args) throws Exception {
		String argg[] = {"/Users/karan.verma/Documents/backups/h/hadoop/input/anagram",
		"/Users/karan.verma/Documents/backups/h/hadoop/output/anagram6"}; 
		
		if (argg.length != 2) {
			System.err.printf("Usage: %s needs two arguments <input> <output> files\n",
					getClass().getSimpleName());
			return -1;
		}
		Configuration configuration  = getConf();
		
		//configuration.set("fs.defaultFS", "hdfs://192.168.202.29");
		//configuration.set("mapred.job.tracker", "jobtracker:jtPort");
//        configuration.set("mapreduce.jobtracker.address", "localhost:54311");
//        configuration.set("mapreduce.framework.name", "yarn");
//        configuration.set("yarn.resourcemanager.address", "127.0.0.1:8032");
//        
//        configuration.set("yarn.resourcemanager.webapp.address", "127.0.0.1:8032");
	
		//Initialize the Hadoop job and set the jar as well as the name of the Job
		Job job = new Job(configuration);
		
		
		//job.setJarByClass(getClass());
		job.setJobName("Anagram");
		
		
		
		//Add input and output file paths to job based on the arguments passed
		FileInputFormat.addInputPath(job, new Path(argg[0]));
		FileOutputFormat.setOutputPath(job, new Path(argg[1]));
	
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(IntWritable.class);
//		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
//		
		
		//Set the MapClass and ReduceClass in the job
		job.setMapperClass(AnagramMapper.class);
		job.setReducerClass(AnagramReducer.class);
	
		//Wait for the job to complete and print if the job was successful or not
		int returnValue = job.waitForCompletion(true) ? 0:1;
		
		if(job.isSuccessful()) {
			System.out.println("Job was successful");
		} else if(!job.isSuccessful()) {
			System.out.println("Job was not successful");			
		}
		
		return returnValue;
	}


}
