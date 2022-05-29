import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BmiCalculatorBuilder extends JFrame {
    private JPanel mainPanel;
    private JTextField fnTxt;
    private JTextField lastNameTxt;

    private JTextField actualTxt;
    private JTextField ageTxt;
    private JTextField weightTxt;
    private JRadioButton maleRb;
    private JRadioButton femaleRb;
    private JRadioButton smallRb;
    private JRadioButton mediumRb;
    private JRadioButton largeRb;
    private JSlider slider;
    private JButton checkBtn;
    private JButton clearBtn;
    private JLabel fnLbl;
    private JLabel genderLbl;
    private JLabel bodyFrameLbl;
    private JLabel heightLbl;
    private JLabel ageLbl;
    private JLabel actualWeightLbl;
    private JLabel lnLbl;
    private JLabel sliderInput;
    private double slimness;

    // help functions
    //round the bmi and ideal wight values
    private static double round (double value) {
        int scale = (int) Math.pow(10, 2);
        return (double) Math.round(value * scale) / scale;
    }


    public BmiCalculatorBuilder() {
        //Listeners
        //Gender listeners- to avoid from choosing both genders at the same time
        maleRb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maleRb.isSelected()){
                    femaleRb.setSelected(false);
                }
            }

        });

        femaleRb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (femaleRb.isSelected()){
                    maleRb.setSelected(false);
                }

            }
        });


        //JradioBtns
        //Small
        smallRb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (smallRb.isSelected()){
                    slimness=0.9;
                    mediumRb.setSelected(false);
                    largeRb.setSelected(false);
                }
            }
        });
        //Medium
        mediumRb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mediumRb.isSelected()){
                    slimness=1;
                    smallRb.setSelected(false);
                    largeRb.setSelected(false);
                }

            }
        });
        //Large
        largeRb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (largeRb.isSelected()){
                    slimness=1.1;
                    smallRb.setSelected(false);
                    mediumRb.setSelected(false);
                }
            }
        });



        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //bmi=W/H*H
              double bmi=Double.parseDouble(weightTxt.getText())/Math.pow((slider.getValue()/100.0),2);
              double idealWeight=(slider.getValue()-100+(Double.parseDouble(ageTxt.getText())/10.0))*0.9*slimness;
              String weightStatusOutput = null;
                  if(bmi<15.0) {
                      weightStatusOutput = "Anorexic";
                  }
                  else if (bmi>15&& bmi<=18.5) {
                      weightStatusOutput = "Underweight";

                  } else if (idealWeight>18.5&&bmi<=24.9) {
                      weightStatusOutput = "Normal";
                  }
             else if (bmi>24.9&&bmi<=29.9) {
                weightStatusOutput = "Overweight";
            }
                  else if (bmi>29.9&&bmi<=35) {
                      weightStatusOutput = "Obese";
                  }
                  else if (bmi>35) {
                      weightStatusOutput = "Extreme Obese";
                  }

                JOptionPane.showMessageDialog(checkBtn,"Your BMI is: "+round(bmi)+"\nThe ideal weight is: "+round(idealWeight)+"\nYour Weight Status: "+weightStatusOutput);


            }
        });

            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    sliderInput.setText("Entered Height: " + String.valueOf(slider.getValue() + "cm"));
                }
            });


        //Clear Form
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fnTxt.setText("");
                lastNameTxt.setText("");
                ageTxt.setText("");
                weightTxt.setText("");
                maleRb.setSelected(false);
                femaleRb.setSelected(false);
                smallRb.setSelected(false);
                mediumRb.setSelected(false);
                largeRb.setSelected(false);
                slider.setValue(0);


            }
        });


    }

    public static void main(String[] args) {
        BmiCalculatorBuilder builder=new BmiCalculatorBuilder();
        builder.setContentPane(builder.mainPanel);
        builder.pack();
        builder.setTitle("BMI Calculator");
        builder.setVisible(true);
        builder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
