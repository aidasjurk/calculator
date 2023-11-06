package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private double currentResult = 0;
    private String currentOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
    }

    public void onButtonClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "+":
            case "-":
            case "×":
            case "÷":
            case "√":
                handleOperator(buttonText);
                break;
            case "C":
                clear();
                break;
            case "←":
                deleteLast();
                break;
            case "=":
                calculate();
                break;
            default:
                appendToInput(buttonText);
                break;
        }
    }

    private void appendToInput(String text) {
        currentInput += text;
        display.setText(currentInput);
    }

    private void handleOperator(String operator) {
        if (!currentInput.isEmpty()) {
            if (!currentOperator.isEmpty()) {
                calculate();
            }
            currentResult = Double.parseDouble(currentInput);
            currentInput = "";
            currentOperator = operator;
        }
    }

    private void calculate() {
        if (!currentOperator.isEmpty() && !currentInput.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            switch (currentOperator) {
                case "+":
                    currentResult += secondOperand;
                    break;
                case "-":
                    currentResult -= secondOperand;
                    break;
                case "×":
                    currentResult *= secondOperand;
                    break;
                case "÷":
                    if (secondOperand != 0) {
                        currentResult /= secondOperand;
                    }
                    break;
                case "√":
                    currentResult = Math.sqrt(currentResult);
                    break;
            }
            currentOperator = "";
            currentInput = String.valueOf(currentResult);
            display.setText(currentInput);
        }
    }

    private void clear() {
        currentInput = "";
        currentResult = 0;
        currentOperator = "";
        display.setText("");
    }

    private void deleteLast() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            display.setText(currentInput);
        }
    }
}
