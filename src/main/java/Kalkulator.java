import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalkulator extends JFrame implements ActionListener {

    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[10];
    private JButton bDodawanie, bOdejmowanie, bMnozenie, bDzielenie, bUsuwanie, bCzyszczenie, bKropka, bRowny, bBinarny, bDecimal;
    private JTextField textField;
    private JPanel panel;

    private double number1 = 0;
    private double namber2 = 0;
    private double result = 0;
    private char operator;

    public Kalkulator() {
        setSize(420, 600);
        setTitle("Kalkulator");
        setLayout(null);
        textField = new JTextField();
        textField.setBounds(50, 20, 300, 50);
        add(textField);

        bDodawanie = new JButton("+");
        bOdejmowanie = new JButton("-");
        bMnozenie = new JButton("*");
        bDzielenie = new JButton("/");
        bKropka = new JButton(".");
        bRowny = new JButton("=");
        bCzyszczenie = new JButton("Clear");
        bUsuwanie = new JButton("Delete");
        bBinarny = new JButton("Binary");
        bDecimal = new JButton("Decimal");

        functionButtons[0] = bDodawanie;
        functionButtons[1] = bOdejmowanie;
        functionButtons[2] = bMnozenie;
        functionButtons[3] = bDzielenie;
        functionButtons[4] = bKropka;
        functionButtons[5] = bRowny;
        functionButtons[6] = bUsuwanie;
        functionButtons[7] = bCzyszczenie;
        functionButtons[8] = bBinarny;
        functionButtons[9] = bDecimal;

        for (int i = 0; i < 10; i++) {
            functionButtons[i].addActionListener(this);
        }
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }
        bCzyszczenie.setBounds(50, 500, 145, 45);
        bUsuwanie.setBounds(205, 500, 145, 45);
        bBinarny.setBounds(50, 430, 145, 45);
        bDecimal.setBounds(205, 430, 145, 45);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.lightGray);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(bDodawanie);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(bOdejmowanie);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(bDzielenie);
        panel.add(bMnozenie);
        panel.add(numberButtons[0]);
        panel.add(bRowny);
        panel.add(bKropka);

        add(panel);
        add(bCzyszczenie);
        add(bUsuwanie);
        add(bBinarny);
        add(bDecimal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        try {
            for (int i = 0; i < 10; i++) {
                if (zrodlo == numberButtons[i]) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
            if (zrodlo == bKropka) {
                textField.setText(textField.getText().concat("."));
            }
            if (zrodlo == bDodawanie) {
                number1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
            if (zrodlo == bOdejmowanie) {
                number1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }
            if (zrodlo == bMnozenie) {
                number1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
            if (zrodlo == bDzielenie) {
                number1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
            if (zrodlo == bRowny) {
                namber2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = number1 + namber2;
                        break;
                    case '-':
                        result = number1 - namber2;
                        break;
                    case '*':
                        result = number1 * namber2;
                        break;
                    case '/':
                        if (namber2 == 0)
                            throw new ArithmeticException("Nie mozna dzielic przez 0");
                        result = number1 / namber2;
                        break;
                }
                textField.setText(String.valueOf(result));
                number1 = result;
            }
            if (zrodlo == bCzyszczenie) {
                textField.setText("");
            }
            if (zrodlo == bUsuwanie) {
                String string = textField.getText();
                textField.setText("");
                for (int i = 0; i < string.length() - 1; i++) {
                    textField.setText(textField.getText() + string.charAt(i));
                }
            }
            if (zrodlo == bBinarny) {
                int liczba = Integer.parseInt(textField.getText());
                textField.setText(String.valueOf(toBinary(liczba)));
            }
            if (zrodlo == bDecimal) {
                String liczba = textField.getText();
                textField.setText(String.valueOf(toDecimal(liczba)));
            }
        } catch (ArithmeticException ex) {
            textField.setText(ex.getMessage());
        }
    }

    public static String toBinary(int decimalNumber) {
        String result = "";
        while (decimalNumber > 0) {
            result = (decimalNumber % 2) + result;
            decimalNumber = decimalNumber / 2;
        }
        return result;
    }

    public static int toDecimal(String binaryNumber) {
        int potega = 0;
        int resulat = 0;
        for (int i = binaryNumber.length() - 1; i >= 0; i--) {
            int liczba = Character.getNumericValue(binaryNumber.charAt(i));
            resulat += (liczba * (Math.pow(2, potega)));
            potega++;
        }
        return resulat;
    }
}