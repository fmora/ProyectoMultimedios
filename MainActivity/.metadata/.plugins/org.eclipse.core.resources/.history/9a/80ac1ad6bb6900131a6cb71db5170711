package com.proyectom;

import com.example.proyectom.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MenuPrincipal extends Activity {
	
	private Button IngresarViaje;
	private Button BuscarViaje;
	ciudades ListCiudades;
	String[] aux;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        new load_city().execute();
        
        IngresarViaje = (Button) findViewById(R.id.button1);
        IngresarViaje.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v){
        	
        		Bundle b = new Bundle();
        		int lenght = ListCiudades.ciudades.size();
        		b.putInt("lenght", lenght);
        		for(int i = 1 ; i < lenght ; i++)
        			b.putString(Integer.toString(i),ListCiudades.ciudades.elementAt(i));;
        		Intent intent = new Intent(getApplicationContext(), RegisterTravel2.class);
        		intent.putExtras(b);
        		startActivity(intent);
        
        	}
        });
        BuscarViaje = (Button) findViewById(R.id.button2);
        BuscarViaje.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v){
        	
        		Bundle b = new Bundle();
        		int lenght = ListCiudades.ciudades.size();
        		b.putInt("lenght", lenght);
        		for(int i = 1 ; i < lenght ; i++)
        			b.putString(Integer.toString(i),ListCiudades.ciudades.elementAt(i));;
        		Intent intent = new Intent(getApplicationContext(), SearchTravel.class);
        		intent.putExtras(b);
        		startActivity(intent);
        
        	}
        });
    }

    class load_city extends AsyncTask<Void,Void,Boolean>{
		@Override
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//obtener datos a registrar
			ListCiudades = new ciudades();
			return true;
		}
		protected void onPostExecute(Boolean result){
			if(result)
				Toast.makeText(getApplicationContext(),R.string.es_loaded, Toast.LENGTH_SHORT).show();
		}
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    
}