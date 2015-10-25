package com.example.fabio.exercicio1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText mNome;
    Button mNext;

    String nome;
    String mData = "";
    String mSexo = "";

    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mNome = (EditText) findViewById(R.id.s_nome);
        mNext = (Button) findViewById(R.id.next_id);
        mNext.setOnClickListener(mNext_b);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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

            if (data.hasExtra("data") && data.hasExtra("sexo")){
                String data_s = data.getExtras().getString("data");
                String sexo_s = data.getExtras().getString("sexo");

                mData = data_s;
                mSexo = sexo_s;

                Log.d("Myactivity2", "activityResult " + data_s +" "+ sexo_s );

                finish();

            }


        }
    }
    @Override
    public void finish() {



            Intent i = new Intent();
            i.putExtra("nome", nome);
            i.putExtra("data", mData);
            i.putExtra("sexo", mSexo);

            setResult(RESULT_OK, i);

            super.finish();

    }

    private View.OnClickListener mNext_b = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            nome = mNome.getText().toString();

            if (nome == null ||nome == "") {
                Toast.makeText(Main2Activity.this, "Tens de escrever o nome", Toast.LENGTH_SHORT).show();
            } else {

                Log.d("My2Activity", "nome escrito " + mNome.getText().toString());
                Intent i = new Intent(Main2Activity.this, MainActivity3.class);
                startActivityForResult(i,REQUEST_CODE);
            }


        }
    };
}
