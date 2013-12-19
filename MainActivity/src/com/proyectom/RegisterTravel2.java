package com.proyectom;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.example.proyectom.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterTravel2 extends Activity {
	private Button button;
	EditText dayTravel, startPoint, endPoint, startPointGPS, endPointGPS;
	Spinner citySource, cityDest;
	private List<String> aux;
	private Vector<String> ciudades;
	String resulthttp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_travel);
		//setear ciudades para mostrar y registrar
		Bundle b = new Bundle(); 
		b = getIntent().getExtras();
		int lenght = b.getInt("lenght");
		ciudades = new Vector<String>();
		ciudades.addElement(" ");
		aux = new ArrayList<String>();
		for(int i = 1; i < lenght; i++){
			ciudades.addElement(b.getString(Integer.toString(i)));
			aux.add(b.getString(Integer.toString(i)));
		}
		
		String[] toShow = aux.toArray(new String[0]);
		
		button = (Button) findViewById(R.id.button1);
		citySource = (Spinner) findViewById(R.id.spinner1);
		cityDest = (Spinner) findViewById(R.id.spinner2);
		dayTravel =  (EditText) findViewById(R.id.editText3);
		startPoint =  (EditText) findViewById(R.id.editText5);
		endPoint =  (EditText) findViewById(R.id.editText6);
		startPointGPS =  (EditText) findViewById(R.id.EditText01);
		endPointGPS =  (EditText) findViewById(R.id.EditText02);
		
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,(String[]) toShow);
		citySource.setAdapter(adaptador);
		cityDest.setAdapter(adaptador);
		button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	new reg_travel().execute();
            }
        });
	}
       
    class reg_travel extends AsyncTask<Void,Void,Boolean>{
		@Override
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//obtener datos a registrar
						
			int par3 = ciudades.indexOf(citySource.getSelectedItem().toString());
        	int par4 = ciudades.indexOf(cityDest.getSelectedItem().toString());
        	String par5 = dayTravel.getText().toString();
        	String par6 = startPoint.getText().toString();
        	String par7 = startPointGPS.getText().toString();
        	String par8 = endPoint.getText().toString();
        	String par9 = endPointGPS.getText().toString();
        	viaje viajeRegistrar = new viaje(0,1,par3,par4,par5,par6,par7,par8,par9);
        	resulthttp = viajeRegistrar.registerDB();
			return true;
		}
		protected void onPostExecute(Boolean result){
			if(result)
				Toast.makeText(getApplicationContext(), resulthttp, Toast.LENGTH_SHORT).show();
		}
	}
}
    