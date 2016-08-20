package Filters;

import Helper.IgnoreHelper;
import Pipes.Pipe;
import Pipes.StringArrayPipe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class CircularShift implements Filters<String[], String[]>{

	public String[] data;
	public StringArrayPipe inputChannel; 
	public StringArrayPipe outputChannel; 

	@Override
	public String[] read(Pipe inputChannel) throws InterruptedException {
		return (String[]) inputChannel.pop();
	}

	@Override
	public void write(Pipe outputChannel, String[] output) {
		outputChannel.push(output);
	}
	
	public String combineStrings(List<String> tmpArr){
		String ans = "";
		for (String tmpStr : tmpArr){
			ans += tmpStr;
		}
		return ans;
	}
	
	public String[] CircularShiftForString(String str){
		String[] strings = str.split(" ");
		List<String> tmpArr =new LinkedList<String>();
		int length = 0;
		for (String tmpStr: strings){
			length++;
			tmpArr.add(tmpStr);
		}
		List<String> ans = new ArrayList<String>();
		for (int i = 0; i < length;i++){
			if (IgnoreHelper.ifIgnore(tmpArr.get(0))){
				ans.add(combineStrings(tmpArr));
			}
			String first = tmpArr.get(0);
			tmpArr.remove(0);
			tmpArr.add(first);
		}
		IgnoreHelper.ifIgnore(str);
		return strings;
	}
	
	public String[] CircularShiftForStrings(){
		
		return data;
		
	}
	
	@Override
	public void run() {
		while (true){
			try {
				data = read(inputChannel);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}

}
