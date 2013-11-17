package com.proyectom;

import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterTravel extends Activity {
	private Button button;
	EditText citySource, cityDest, dayTravel, startPoint, endPoint;
	String cityS,cityD,hourT,dayT,startP,endP;
	int id=1;
	//private string startPointGPS, endPointGPS;
	private Vector<String> ciudades;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_travel);
			
		button = (Button) findViewById(R.id.button1);
		citySource = (EditText) findViewById(R.id.editText1);
		cityDest = (EditText) findViewById(R.id.editText2);
		dayTravel =  (EditText) findViewById(R.id.editText3);
		startPoint =  (EditText) findViewById(R.id.editText5);
		endPoint =  (EditText) findViewById(R.id.editText6);
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new reg_travel().execute();
			}
		});
	}
	
	class reg_travel extends AsyncTask<Void,Void,Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//obtener datos a registrar
			ciudades = new Vector<String>();
			ciudades.add("Arica"); ciudades.add("Iquique"); ciudades.add("Antofagasta");
			ciudades.add("Copiapo"); ciudades.add("La Serena"); ciudades.add("Coquimbo");
			ciudades.add("Valparaiso"); ciudades.add("Vi�a del Mar"); ciudades.add("Rancagua");
			ciudades.add("Talca"); ciudades.add("Concepcion"); ciudades.add("Temuco");
			ciudades.add("Puerto Montt"); ciudades.add("Coyhaique"); ciudades.add("Magallanes");
			ciudades.add("Santiago"); ciudades.add("Valdivia");
			cityS = citySource.getText().toString();
			cityD = cityDest.getText().toString();
			dayT = dayTravel.getText().toString();
			startP = startPoint.getText().toString();
			endP = endPoint.getText().toString();
			int idorigen = ciudades.indexOf(cityS.toString());
			int iddestino = ciudades.indexOf(cityD.toString());
						
			viaje Viaje = new viaje(0,id,idorigen,iddestino,dayT.toString(),startP.toString(),"probando",endP.toString(),"probando");
			Viaje.registerDB();
			return true;
		}
		protected void onPostExecute(Boolean result){
			if(result)
				Toast.makeText(getApplicationContext(), getString(R.string.es_registered), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_travel, menu);
		return true;
	}
	
	
}
