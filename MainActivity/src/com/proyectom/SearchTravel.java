package com.proyectom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.example.proyectom.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchTravel extends Activity {
	private Button button;
	Spinner citySource, cityDest;
	EditText fecha;
	TextView a1,a2,a3;
	private List<String> aux;
	private Vector<String> ciudades;
	Vector<viaje> ViajesDisponibles;
	private List<ModelViaje> list = new ArrayList<ModelViaje>();
	private ListView listView;
	int choose_Viaje;
	
	@SuppressLint("SetJavaScriptEnabled")
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_travel);
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
		fecha = (EditText) findViewById(R.id.editText1);
		listView = (ListView) findViewById(R.id.listView1);
		a1 = (TextView) findViewById(R.id.textView1);
		a2 = (TextView) findViewById(R.id.textView2);
		a3 = (TextView) findViewById(R.id.TextView01);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,(String[]) toShow);
		citySource.setAdapter(adaptador);
		cityDest.setAdapter(adaptador);
		button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	new sea_travel().execute();
            }
        });
	}
       
    class sea_travel extends AsyncTask<Void,Void,Boolean>{
		@Override
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//obtener datos a registrar
						
			int par3 = ciudades.indexOf(citySource.getSelectedItem().toString());
        	int par4 = ciudades.indexOf(cityDest.getSelectedItem().toString());
        	String par5 = fecha.getText().toString();
        	
        	viaje Viajes = new viaje();
        	Viajes.setidOrigen(par3, citySource.getSelectedItem().toString());
        	Viajes.setidDestino(par4, cityDest.getSelectedItem().toString());
        	Viajes.setfecha(par5);
        	
        	try {
				readIt(Viajes.searchDB());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return true;
		}
		protected void onPostExecute(Boolean result){
			citySource.setVisibility(View.INVISIBLE);
			cityDest.setVisibility(View.INVISIBLE);
			button.setVisibility(View.INVISIBLE);
			fecha.setVisibility(View.INVISIBLE);
			a1.setVisibility(View.INVISIBLE);
			a2.setVisibility(View.INVISIBLE);
			a3.setVisibility(View.INVISIBLE);
			loadList();
			RSSAdapter_viaje adapter = new RSSAdapter_viaje(getApplicationContext(), R.string.app_name, list);
			listView.setAdapter(adapter);
			
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapter, View view, int position,
						long value) {
					choose_Viaje = position;
					new ins_travel().execute();
				}
			});
		}
		
		public void loadList(){
			ModelViaje aux;
			int lenght = ViajesDisponibles.size();
			
			while(lenght > 0){
				aux = new ModelViaje();
				aux.idViaje = ViajesDisponibles.elementAt(lenght-1).idViaje;
				aux.idConductor = ViajesDisponibles.elementAt(lenght-1).idConductor;
				aux.ciudadOrigen = ViajesDisponibles.elementAt(lenght-1).ciudadOrigen;
				aux.ciudadDestino = ViajesDisponibles.elementAt(lenght-1).ciudadDestino;
				aux.fecha = ViajesDisponibles.elementAt(lenght-1).fecha;
				aux.ptoPartida = ViajesDisponibles.elementAt(lenght-1).ptoPartida;
				aux.ptoPartidagps = ViajesDisponibles.elementAt(lenght-1).ptoPartidagps;
				aux.ptoLlegada = ViajesDisponibles.elementAt(lenght-1).ptoLlegada;
				aux.ptoLlegadagps = ViajesDisponibles.elementAt(lenght-1).ptoLlegadagps;
				list.add(aux);
				lenght--;
			}
			
		}
			
		public void readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
			ViajesDisponibles = new Vector<viaje>();
			Reader reader = null;
			reader = new InputStreamReader(stream, "UTF-8");     
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			while(!line.equals("</body>")){
		   		int idViaje = Integer.parseInt(line);
				line = bufferedReader.readLine();
				String idConductor = line;
				line = bufferedReader.readLine();
				int idOrigen = Integer.parseInt(line);
				line = bufferedReader.readLine();
				int idDestino = Integer.parseInt(line);
				line = bufferedReader.readLine();
				String fecha = line;
				line = bufferedReader.readLine();
				String ptopartida = line;
				line = bufferedReader.readLine();
				String ptopartidagps = line;
				line = bufferedReader.readLine();
				String ptollegada = line;
				line = bufferedReader.readLine();
				String ptollegadagps = line;
				line = bufferedReader.readLine();
				
				viaje aux = new viaje(idViaje,idConductor,idOrigen,idDestino,fecha,ptopartida,ptopartidagps,ptollegada,ptollegadagps);
				aux.setidOrigen(idOrigen,citySource.getSelectedItem().toString());
				aux.setidDestino(idDestino, cityDest.getSelectedItem().toString());
				
				ViajesDisponibles.add(aux);
		   }
		}
	}
    class ins_travel extends AsyncTask<Void,Void,Boolean>{
    	String consult;
		protected Boolean doInBackground(Void... params) {
			consult = ViajesDisponibles.elementAt(0).registerpass(list.get(choose_Viaje).idViaje,2);
			return true;
		}
		protected void onPostExecute(Boolean Result){
			Toast.makeText(getApplicationContext(),consult, Toast.LENGTH_SHORT).show();
		}
    }
}