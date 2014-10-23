import com.sun.istack.internal.Nullable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalcMachine extends JFrame {
   //constants
    public static final String LINE = "___________________";



    //Output
    JLabel in1 = new JLabel();
    JLabel in2 = new JLabel();
    JLabel litline = new JLabel();
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
    String operator = "";

    String[] inputs = {"", ""};

    int editing = 0;


    public CalcMachine() {
        //init
        pnlUp = new JPanel(new GridLayout(4, 1));
        pnlDown = new JPanel(new GridLayout(5, 4));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(pnlUp, BorderLayout.NORTH);
        this.getContentPane().add(pnlDown, BorderLayout.SOUTH);

        //add things to pnlUp;
        pnlUp.add(in1);
        pnlUp.add(in2);
        pnlUp.add(litline);
        pnlUp.add(out);

        litline.setText(LINE);

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


        ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JButton b = (JButton) arg0.getSource();
                addText(b);
                System.out.println("did stuff");
                finalizeText();

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


        ActionListener back = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                clear1();
                finalizeText();
            }
        };
        inBack.addActionListener(back);



        ActionListener clearLine = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                clearLn();
                finalizeText();
            }
        };
        inClear.addActionListener(clearLine);


        ActionListener clearAll = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                clearAll();
                finalizeText();
            }
        };
        inClearAll.addActionListener(clearAll);


        ActionListener solveIt = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                out.setText("" + solve(inputs[0],inputs[1],operator));
                finalizeText();
            }
        };
        equals.addActionListener(solveIt);


        ActionListener operations = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JButton b = (JButton) arg0.getSource();
                operate(b);
                finalizeText();
            }
        };
        operationX.addActionListener(operations);
        operationDiv.addActionListener(operations);
        operationAdd.addActionListener(operations);
        operationSub.addActionListener(operations);


        this.setVisible(true);

    }


    public void addText(JButton b) {
        String tempText = b.getText();
        if(tempText == ".")
        {
            boolean yarr = false;

            for(int x = 0; x < inputs[editing].length(); x++)
            {
                if(inputs[editing].charAt(x) == '.')
                {
                    yarr = true;
                }
            }
            if(yarr)
            {
                //do nothing.
            }
            else
            {
                inputs[editing] += tempText;
            }

        }
        else
        {
            inputs[editing] += tempText;
        }
    }

    public void operate(JButton b) {
        String tempText = b.getText();
        operator = tempText.substring(0);
        editing++;
        editing %= 2;
    }


    public void clear1() {
        if (inputs[editing].length() > 0) {
            inputs[editing] = inputs[editing].substring(0, inputs[editing].length() - 1);
        } else if (editing == 1) {
            editing = 0;
            operator = "";
        }
    }

    public void clearAll()
    {
        editing = 1;
        clearLn();
        editing = 0;
        clear1();
        out.setText(" ");

    }


    public void clearLn()
    {
        inputs[editing] = "";
        if(editing == 1)
        {
            operator = "";
        }
    }


    public void finalizeText()
    {
        in1.setText(inputs[0]);
        in2.setText(operator + " " + inputs[1]);
    }




    public double solve(String i1, String i2, String op)
    {
        switch(op.charAt(0)) {
            case '+':
                return Double.parseDouble(i1) + Double.parseDouble(i2);
            case '-':
                return Double.parseDouble(i1) - Double.parseDouble(i2);
            case '/':
                return Double.parseDouble(i1) / Double.parseDouble(i2);
            case 'x':
                return Double.parseDouble(i1) * Double.parseDouble(i2);
        }
        return -12345;

    }


}