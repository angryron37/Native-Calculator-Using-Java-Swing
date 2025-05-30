package calc;

import javax.swing.*;
import java.awt.event.*;

public class CalculatorUI extends JFrame implements ActionListener {
    private final JLabel expressionLabel;
    private final JTextField resultField;
    private final CalculatorLogic logic;
    private boolean isNewInput = true;
    private String currentExpression = "";

    public CalculatorUI() {
        logic = new CalculatorLogic();
        setLayout(null);

        // Label to show current expression
        expressionLabel = new JLabel("");
        expressionLabel.setBounds(30, 20, 280, 30);
        expressionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(expressionLabel);

        // Text field to show result/output
        resultField = new JTextField();
        resultField.setBounds(30, 55, 280, 30);
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        add(resultField);

        // Buttons for digits and operators
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        int x = 30, y = 100;
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setBounds(x, y, 60, 40);
            add(btn);
            btn.addActionListener(this);
            x += 70;
            if (x > 250) {
                x = 30;
                y += 50;
            }
        }

        // Clear button
        JButton clear = new JButton("C");
        clear.setBounds(30, y, 280, 40);
        add(clear);
        clear.addActionListener(e -> {
            expressionLabel.setText("");
            resultField.setText("");
            currentExpression = "";
            isNewInput = true;
        });

        setTitle("Calculator");
        setSize(360, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (isNewInput) {
                resultField.setText(command);
                isNewInput = false;
            } else {
                resultField.setText(resultField.getText() + command);
            }
        } else if (command.equals("=")) {
            // Prevent repeated "=" without new input
            if (currentExpression.isEmpty() || isNewInput) {
                return;
            }
            double num2 = Double.parseDouble(resultField.getText());
            double result = logic.calculate(num2);
            currentExpression += resultField.getText();
            expressionLabel.setText(currentExpression);
            resultField.setText(String.valueOf(result));
            currentExpression = "";
            isNewInput = true;
        } else {
            // Operator pressed
            logic.setNum1(Double.parseDouble(resultField.getText()));
            logic.setOperator(command.charAt(0));
            currentExpression = resultField.getText() + " " + command + " ";
            expressionLabel.setText(currentExpression);
            isNewInput = true;
        }
    }
}
