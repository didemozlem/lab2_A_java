

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 320;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow(Math.cos(Math.exp(y)) + Math.exp(Math.pow(y, 2.0)) + Math.sqrt(1.0 / x), 0.25) / Math.pow(Math.cos(Math.PI * Math.pow(z, 3.0)) + Math.log(Math.pow(1.0 + z, 2.0)), Math.sin(y));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return (1.0 + Math.pow(x, z) + Math.log(Math.pow(y, 2.0)) * (1.0 - Math.sin(y * z))) / Math.sqrt(Math.pow(x, 3.0) + 1.0);
    }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        this.radioButtons.add(button);
        this.hboxFormulaType.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        this.setSize(600, 320);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 400) / 2, (kit.getScreenSize().height - 320) / 2);
        this.hboxFormulaType.add(Box.createHorizontalGlue());
        this.addRadioButton("Формула 1", 1);
        this.addRadioButton("Формула 2", 2);
        this.radioButtons.setSelected(((AbstractButton)this.radioButtons.getElements().nextElement()).getModel(), true);
        this.hboxFormulaType.add(Box.createHorizontalGlue());
        this.hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.yellow));
        JLabel labelForX = new JLabel("X:");
        this.textFieldX = new JTextField("0", 10);
        this.textFieldX.setMaximumSize(this.textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        this.textFieldY = new JTextField("0", 10);
        this.textFieldY.setMaximumSize(this.textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        this.textFieldZ = new JTextField("0", 10);
        this.textFieldZ.setMaximumSize(this.textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
        JLabel labelForResult = new JLabel("Результат:");
        this.textFieldResult = new JTextField("0", 30);
        this.textFieldResult.setMaximumSize(this.textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(this.textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(MainFrame.this.textFieldX.getText());
                    Double y = Double.parseDouble(MainFrame.this.textFieldY.getText());
                    Double z = Double.parseDouble(MainFrame.this.textFieldZ.getText());
                    Double result;
                    if (MainFrame.this.formulaId == 1) {
                        result = MainFrame.this.calculate1(x, y, z);
                    } else {
                        result = MainFrame.this.calculate2(x, y, z);
                    }

                    MainFrame.this.textFieldResult.setText(result.toString());
                } catch (NumberFormatException var6) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 2);
                }

            }
        });
        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Double res = Double.parseDouble(MainFrame.this.textFieldResult.getText());

                try {
                    MainFrame.Formula.sum += res;
                    MainFrame.this.textFieldResult.setText(String.valueOf(MainFrame.Formula.sum));
                } catch (NumberFormatException var4) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 2);
                }

            }
        });
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Double res = Double.parseDouble(MainFrame.this.textFieldResult.getText());

                try {
                    MainFrame.Formula.sum = 0.0;
                    MainFrame.this.textFieldResult.setText(String.valueOf(MainFrame.Formula.sum));
                } catch (NumberFormatException var4) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 2);
                }

            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.textFieldX.setText("0");
                MainFrame.this.textFieldY.setText("0");
                MainFrame.this.textFieldZ.setText("0");
                MainFrame.this.textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonM);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(this.hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        this.getContentPane().add(contentBox, "Center");
    }

    static class Formula {
        private static double sum = 0.0;

    }
}
