package KWICPipesAndFiltersController;

import Filters.Alphabetizer;
import Filters.CircularShift;
import Filters.Input;
import Filters.Output;
import Helper.IgnoreHelper;
import Pipes.StringArrayPipe;
import UI.KWICUI;

public class KWICPipesAndFiltersController {
	StringArrayPipe pipeForInputCircularShift = new StringArrayPipe();
	StringArrayPipe pipeForCircularShiftAlphabetizer = new StringArrayPipe();
	StringArrayPipe pipeForAlphabetizerOutput = new StringArrayPipe();
	
	Input input = new Input(null, pipeForInputCircularShift);
	CircularShift circularShift = new CircularShift(pipeForInputCircularShift, pipeForCircularShiftAlphabetizer);
	Alphabetizer alphabetizer = new Alphabetizer(pipeForCircularShiftAlphabetizer, pipeForAlphabetizerOutput);
	Output output = new Output(pipeForAlphabetizerOutput, null);
	Thread thread1 = new Thread(input, "Input thread");
	Thread thread2 = new Thread(circularShift, "circularShift");
	Thread thread3 = new Thread(alphabetizer, "circularShift");
	Thread thread4 = new Thread(output, "Output thread");
	public void run(){
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	    //KWICUI kwicUI = new KWICUI();	    
	    //kwicUI.setFilter(input, output);
	}
	public Input getInput(){
		return input;
	}
}
