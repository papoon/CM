package com.example.fabio.exercicio1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {


    DatePicker mDatePicker;
    Button mNext;

    String mSexo = "";

    String mdata = "";

    static final int REQUEST_CODE = 1;

    private int mYear;
    private int mMonth;
    private int mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Calendar today = Calendar.getInstance();

        mYear = today.get(Calendar.YEAR);
        mMonth = today.get(Calendar.MONTH);
        mDay = today.get(Calendar.DAY_OF_MONTH);

        mDatePicker = (DatePicker) findViewById(R.id.date_p_id);
        mNext = (Button) findViewById(R.id.next_id);
        mNext.setOnClickListener(mNext_b);
        mDatePicker.init(mYear,mMonth,mDay,datePick);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            if (data.hasExtra("sexo")){
                int id_sexo = data.getExtras().getInt("sexo");


                switch (id_sexo){
                    case 0:
                        mSexo = "Masculino";
                        break;
                    case 1:
                        mSexo = "Femenino";
                        break;
                }

                Log.d("Myactivity3", "activityResult " + mSexo);
                finish();
            }


        }
    }
    @Override
    public void finish() {


        if (mdata == null) {
            Toast.makeText(MainActivity3.this, "Tens de selecionar a data", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent();
            i.putExtra("data", mdata);
            i.putExtra("sexo", mSexo);

            setResult(RESULT_OK, i);

            super.finish();
        }
    }

    private View.OnClickListener mNext_b = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if (mdata == null || mdata == "") {
                Toast.makeText(MainActivity3.this, "Tens de selecionar a data", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("Myactivity3", "A data é: "+mdata);
                Intent i = new Intent(MainActivity3.this, MainActivity4.class);
                startActivityForResult(i,REQUEST_CODE);
            }


        }
    };




    private DatePicker.OnDateChangedListener datePick = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;


            mdata = new StringBuilder().append(mDay)
                    .append("-").append(mMonth +1).append("-").append(mYear)
                    .append(" ").toString();


        }
    };

}
