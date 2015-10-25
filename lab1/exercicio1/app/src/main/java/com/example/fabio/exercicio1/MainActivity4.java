package com.example.fabio.exercicio1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    RadioGroup radioGroup;

    Button mFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group_id);

        mFinish = (Button) findViewById(R.id.finish_id);
        mFinish.setOnClickListener(mFinish_b);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity4, menu);
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
    public void finish()
    {
        int id_radio_button = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(id_radio_button);
        int id = radioGroup.indexOfChild(radioButton);

        if(id == -1){
            Toast.makeText(MainActivity4.this, "Tens de selecionar o sexo", Toast.LENGTH_SHORT).show();
        }else{

            Log.d("Myactivity4", "O sexo é: " + id);


            Intent i = new Intent();
            i.putExtra("sexo", id);

            setResult(RESULT_OK, i);

            super.finish();
        }



    }

    private View.OnClickListener mFinish_b = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            finish();

        }
    };
}
