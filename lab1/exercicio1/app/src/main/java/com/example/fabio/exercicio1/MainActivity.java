package com.example.fabio.exercicio1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button mPreencher;
    static final int REQUEST_CODE = 1;

    TextView mNome;
    TextView mData;
    TextView mSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreencher = (Button) findViewById(R.id.id_preencher_b);
        mPreencher.setOnClickListener(mPreencher_f);

        mNome = (TextView) findViewById(R.id.r_nome);
        mData = (TextView) findViewById(R.id.r_data);
        mSexo = (TextView) findViewById(R.id.r_sexo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



            if (data.hasExtra("data") && data.hasExtra("nome") && data.hasExtra("sexo")){

                String data_s = data.getExtras().getString("data");
                String sexo_s = data.getExtras().getString("sexo");
                String nome = data.getExtras().getString("nome");


                mNome.setText(nome);
                mData.setText(data_s);
                mSexo.setText(sexo_s);

                Log.d("Myactivity", "A data é: " + data_s +"O sexo: " + sexo_s +"O nome: "+ nome );
            }
            else{

                Log.d("Myactivity", "Erro");

            }

        }

    }

    private Button.OnClickListener mPreencher_f = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(MainActivity.this,Main2Activity.class);
            startActivityForResult(i,REQUEST_CODE);
        }
    };
}
