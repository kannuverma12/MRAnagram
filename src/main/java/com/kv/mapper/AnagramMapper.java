package com.kv.mapper;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AnagramMapper extends Mapper<org.apache.hadoop.io.LongWritable, Text, Text, Text> {

//	public void map(org.apache.hadoop.io.LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
//			throws IOException {
//		String word = value.toString().toLowerCase();
//		char[] wordChars = word.toCharArray();
//		Arrays.sort(wordChars);
//		String sortedWord = new String(wordChars);
//		output.collect(new Text(sortedWord), new Text(word));
//		
//	}
	
	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		
		String word = value.toString().toLowerCase();
		char[] wordChars = word.toCharArray();
		Arrays.sort(wordChars);
		String sortedWord = new String(wordChars);
		context.write(new Text(sortedWord), new Text(word));
		
	}

}