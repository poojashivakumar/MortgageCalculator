package com.example.user.mortgagecalculator;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private EditText amount;
    private Button calculate;
    private TextView result;
    private TextView seekValue;
    private RadioGroup loanTermGroup;
    private RadioButton loanTermButton;
    private CheckBox selected;

    private double amountBorrowed, extras, interest, check;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        amount = (EditText) findViewById(R.id.editText);
        String input = amount.getText().toString();
        if (input.isEmpty())
            amount.setText("0.0");
        calculate = (Button) findViewById(R.id.calculate);
        loanTermGroup = (RadioGroup) findViewById(R.id.loanRadioGroup);
        result = (TextView) findViewById(R.id.textView4);
        selected = (CheckBox) findViewById(R.id.taxesandinsurance);

        SeekBar seekBar = (SeekBar) findViewById(R.id.interestRate);
        seekValue = (TextView) findViewById(R.id.seekBarValue);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                interest = (double) progress / 10;
                seekValue.setText(String.valueOf(interest));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check = 1;
                }
                else
                {
                    check = 0;
                }
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                int selectedId = loanTermGroup.getCheckedRadioButtonId();
                loanTermButton = (RadioButton) findViewById(selectedId);
                final double loanTerm = (Integer.parseInt(loanTermButton.getText().toString())) * 12;
                if (check == 1)
                {
                    amountBorrowed = Double.parseDouble(amount.getText().toString());
                    extras = amountBorrowed * (0.1 / 100);
                }
                else
                {
                    extras = 0;
                }
                if (interest == 0)
                {
                    amountBorrowed = Double.parseDouble(amount.getText().toString());
                    result.setText(Double.toString(Math.round(((amountBorrowed / loanTerm) + extras) * 100.0) / 100.0));

                }
                else
                {
                    amountBorrowed = Double.parseDouble(amount.getText().toString());
                    result.setText(Double.toString(Math.round(((amountBorrowed * (interest / 1200) / (1 - Math.pow((1 + (interest / 1200)), -loanTerm))) + extras) * 100.0) / 100.0));

                }
            }
        });
    }
}










