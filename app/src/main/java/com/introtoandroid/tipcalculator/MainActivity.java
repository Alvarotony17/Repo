package com.introtoandroid.tipcalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends Activity
{
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private static final NumberFormat integerFormat = NumberFormat.getIntegerInstance();

    private double billAmount = 0.0;
    private double percent = 0.15;
    private double tax = .09;

    private int bitches  = 2;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView taxedTextView;

    private TextView splitTextView;
    private TextView peopleTextView;

    //private TextView


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        taxedTextView = (TextView) findViewById(R.id.taxedTextView);
        splitTextView = (TextView) findViewById(R.id.splitTextView);
        peopleTextView = (TextView) findViewById(R.id.peopleTextView);


        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));
        taxedTextView.setText(currencyFormat.format(0));
        splitTextView.setText(currencyFormat.format(0));


        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

        SeekBar peopleSeekBar = (SeekBar) findViewById(R.id.peopleSeekBar);
        peopleSeekBar.setOnSeekBarChangeListener(peopleSeekBarListener);

    }
    private void calculate()
    {
        percentTextView.setText(percentFormat.format(percent));
        peopleTextView.setText(integerFormat.format(bitches));

        double tip = billAmount * percent;
        double total = billAmount + tip;
        double taxed = (total * tax);
        double taxedTotal = taxed + total;

        double split = taxedTotal/bitches;


        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
        taxedTextView.setText(currencyFormat.format(taxedTotal));

        splitTextView.setText(currencyFormat.format(split));

    }
    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b)
        {
            percent = i/100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }
    };

    private final OnSeekBarChangeListener peopleSeekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            bitches = i+1;  //
            calculate();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {
            try
            {
                billAmount = Double.parseDouble(charSequence.toString())/100.0;
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException e)
            {
                amountTextView.setText("");
                billAmount = 0.0;
            }
        }
        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}
