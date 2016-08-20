package UI;  
  
import java.awt.FlowLayout;  
import java.awt.GridLayout;  
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;  
import javax.swing.JButton;  
import javax.swing.JCheckBox;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Filters.Alphabetizer;
import Filters.CircularShift;
import Filters.Input;
import Filters.Output;
import Helper.IgnoreHelper;
import Pipes.StringArrayPipe;  
  
public class KWICUI extends JFrame{  
  
    JPanel row1 = new JPanel();  

    JPanel row2 = new JPanel();  
    JLabel inputLabel = new JLabel("Input" , JLabel.CENTER);  
    JTextArea input = new JTextArea();  
      
    JPanel row3 = new JPanel();  
    JButton enter = new JButton("Enter");  
    JButton clear = new JButton("Clear");  
      
    JPanel row4 = new JPanel();  
    JLabel outputLabel = new JLabel("Output" , JLabel.CENTER);  
    JTextArea output = new JTextArea(20,100);  
  
    Input inputFilter;
    Output outputFilter;
    
    public void setFilter(Input inputFilter, Output outputFilter){
		this.inputFilter = inputFilter;
		this.outputFilter = outputFilter;
		this.outputFilter.setJTextArea(output);
    }    
    public KWICUI() throws HeadlessException {
        super("Key Word In Context");  
        setSize(500 , 800);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        GridLayout gridLayout = new GridLayout(4, 1, 10, 10);  
        setLayout(gridLayout);  
          
        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.CENTER , 10 , 10);  
          
        GridLayout gridLayout1 = new GridLayout(2, 7, 10, 10);  
        row2.setLayout(gridLayout1);  
        row2.add(inputLabel);  
        row2.add(input);
        add(row2);  
          
        FlowLayout flowLayout2 = new FlowLayout(FlowLayout.CENTER);  
        row3.setLayout(flowLayout2);  
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	String[] inputStrings = input.getText().split("\n");
            	inputFilter.write(inputStrings);
            	//output.setText("asd");
            }
        }
        );
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	output.setText("");
            }
        }
        );
        row3.add(enter);  
        row3.add(clear);  

        add(row3);  
        GridLayout gridLayout2 = new GridLayout();  
        row4.setLayout(gridLayout2);  
        row4.add(outputLabel);
        JScrollPane jslp = new JScrollPane(output); 
        jslp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        jslp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //row4.add(output);
        output.setAutoscrolls(true);
        add(row4);  
        add(output);
        setVisible(true);  
    }  
      
      
    public static void main(String[] args) {
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
    	thread1.start();
    	thread2.start();
    	thread3.start();
    	thread4.start();
    	System.out.println("run!");
        KWICUI kwicUI = new KWICUI();
        IgnoreHelper.init(new String[]{"is", "the", "of", "and", "as", "a", "after"});
        kwicUI.setFilter(input, output);
    }  
}