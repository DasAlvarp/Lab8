import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalcMachine extends JFrame
{
	//Output
	JLabel in1 = new JLabel();
	JLabel in2 = new JLabel();
	JLabel out = new JLabel();
	
	//Buttons for input
	
	JButton numPad1 = new JButton("1");
	JButton numPad2 = new JButton("2");
	JButton numPad3 = new JButton("3");
	JButton numPad4 = new JButton("4");
	JButton numPad5 = new JButton("5");
	JButton numPad6 = new JButton("6");
	JButton numPad7 = new JButton("7");
	JButton numPad8 = new JButton("8");
	JButton numPad9 = new JButton("9");
	JButton numPad0 = new JButton("0");
	
	JButton operationX = new JButton("x");
	JButton operationDiv = new JButton("/");
	JButton operationAdd = new JButton("+");
	JButton operationSub = new JButton("-");
	
	JButton equals = new JButton("=");
	
	JButton decimal = new JButton(".");
	
	JButton inBack = new JButton("bk");
	JButton inClear = new JButton("c");
	JButton inClearAll = new JButton("AC");
	
	//JPanel
	JPanel pnlUp;
	JPanel pnlDown;
	
	
	
	//variables
	double input1;
	double input2;
	double output;
	String operator = "";
	
	String[] inputs = {"", ""};
	
	int editing = 0;
	
	
	public CalcMachine()
	{
		//init
		pnlUp = new JPanel(new GridLayout(3,1));
		pnlDown = new JPanel(new GridLayout(5,4));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setLayout(new BorderLayout());
		this.getContentPane().add(pnlUp, BorderLayout.NORTH);
		this.getContentPane().add(pnlDown, BorderLayout.SOUTH);
		
		//add things to pnlUp;
		pnlUp.add(in1);
		pnlUp.add(in2);
		pnlUp.add(out);
		
		
		//add things to pnlDown
		//top layer
		pnlDown.add(numPad1);
		pnlDown.add(numPad2);
		pnlDown.add(numPad3);
		pnlDown.add(operationX);
		//2nd
		pnlDown.add(numPad4);
		pnlDown.add(numPad5);
		pnlDown.add(numPad6);
		pnlDown.add(operationDiv);
		//third
		pnlDown.add(numPad7);
		pnlDown.add(numPad8);
		pnlDown.add(numPad9);
		pnlDown.add(operationAdd);
		
		//fourth
		pnlDown.add(numPad0);
		pnlDown.add(decimal);
		pnlDown.add(inBack);
		pnlDown.add(operationSub);
		
		//fifth
		pnlDown.add(inClear);
		pnlDown.add(inClearAll);
		pnlDown.add(equals);

		
		
		
		
		
		ActionListener al = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JButton b = (JButton) arg0.getSource();
				addText(b);
				System.out.println("did stuff");
				
				
				in1.setText(inputs[0]);
				in2.setText(inputs[1]);
			}
		};
		
		numPad1.addActionListener(al);
		numPad2.addActionListener(al);
		numPad3.addActionListener(al);
		numPad4.addActionListener(al);
		numPad5.addActionListener(al);
		numPad6.addActionListener(al);
		numPad7.addActionListener(al);
		numPad8.addActionListener(al);
		numPad9.addActionListener(al);
		numPad0.addActionListener(al);
		decimal.addActionListener(al);
		
		
		ActionListener back = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				clear1();
				in1.setText(inputs[0]);
				in2.setText(operator + inputs[1]);
			}
		};
		inBack.addActionListener(back);
		
		
		
		
		ActionListener operations = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JButton b = (JButton) arg0.getSource();
			operate(b);
			in1.setText(inputs[0]);
			in2.setText(operator + inputs[1]);
		}
	};		
	operationX.addActionListener(operations);
	operationDiv.addActionListener(operations);
	operationAdd.addActionListener(operations);
	operationSub.addActionListener(operations);
		
		
		
		
		
		
		
		this.setVisible(true);

	}
	
	
	public void addText(JButton b)
	{
		String tempText = b.getText();
		inputs[editing] += tempText;
	}
	
	public void operate(JButton b)
	{
		String tempText = b.getText();
		operator = tempText.substring(0); 
		editing++;
		editing %= 2;
	}
	
	
	public void clear1()
	{
		if(inputs[editing].length() > 0)
		{
			inputs[editing] = inputs[editing].substring(0, inputs[editing].length() - 1);
		}
		else if(editing == 1)
		{
			editing = 0;
			clear1();
		}
	}

}