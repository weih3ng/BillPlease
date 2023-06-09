package sg.edu.rp.c346.id22005564.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amt;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.editInputAmount);
        numPax = findViewById(R.id.editInputNumPax);
        totalBill = findViewById(R.id.totalBill);
        eachPays = findViewById(R.id.eachPays);
        svs = findViewById(R.id.toggleButtonSvs);
        gst = findViewById(R.id.toggleButtonGst);
        split = findViewById(R.id.splitButton);
        reset = findViewById(R.id.resetButton);
        discount = findViewById(R.id.editInputDiscount);

        split.setOnClickListener(new View.OnClickListener() {

            String amtToString = amt.getText().toString();
            String numPaxToString = numPax.getText().toString();
            boolean svsChecked = svs.isChecked();
            boolean gstChecked = gst.isChecked();
            @Override
            public void onClick(View v) {
                if(amtToString.trim().length() != 0 && numPaxToString.trim().length() != 0) {
                    double newAmount = 0.0;
                    double amtConvertDouble = Double.parseDouble(amt.getText().toString());

                    if (!svsChecked && !gstChecked) {
                        newAmount = amtConvertDouble;
                    } else if (svsChecked && !gstChecked) {
                        newAmount = amtConvertDouble * 1.1;
                    } else if (!svsChecked && gstChecked) {
                        newAmount = amtConvertDouble * 1.07;
                    } else {
                        newAmount = amtConvertDouble * 1.17;
                    }

                    double discConvertDouble = Double.parseDouble(discount.getText().toString());
                    String discountToString = discount.getText().toString();
                    if (discountToString.trim().length()!= 0) {
                        newAmount *= 1-discConvertDouble/100;
                    }

                    totalBill.setText("Total bill: $" + String.format("%.2f", newAmount));
                    int numberPerson = Integer.parseInt(numPax.getText().toString());
                    eachPays.setText("Each Pays: $" + String.format("%.2f",newAmount/numberPerson));

                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amt.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });
    }
}
