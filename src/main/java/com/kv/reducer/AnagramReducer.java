package com.kv.reducer;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class AnagramReducer extends  Reducer<Text, Text, Text, Text> {

//	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
//			throws IOException {
//		String anastr = "";
//		while (values.hasNext()) {
//			String anagam = values.next().toString();
//			anastr = anastr + anagam + "~";
//		}
//		StringTokenizer outputTokenizer = new StringTokenizer(anastr, "~");
//		if (outputTokenizer.countTokens() >= 2) {
//			anastr = anastr.replace("~", "\t");
//			output.collect(new Text(key), new Text(anastr));
//		}
//		
//	}
	
	protected void reduce(Text key, Iterator<Text> values,
			Context context)
			throws IOException, InterruptedException {
		
		
		String anastr = "";
		while (values.hasNext()) {
			String anagam = values.next().toString();
			anastr = anastr + anagam + "~";
		}
		StringTokenizer outputTokenizer = new StringTokenizer(anastr, "~");
		if (outputTokenizer.countTokens() >= 2) {
			anastr = anastr.replace("~", "\t");
			context.write(new Text(key), new Text(anastr));
		}
	
	}	

}