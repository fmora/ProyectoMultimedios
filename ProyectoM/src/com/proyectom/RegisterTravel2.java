package com.proyectom;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import com.example.proyectom.R;
import com.proyectom.RegisterTravel.reg_travel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterTravel2 extends Activity {
	private Button button;
	EditText citySource, cityDest, dayTravel, startPoint, endPoint;
	
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
            public void onClick(View v) {

                String  cityS = citySource.getText().toString();
                String  cityD = cityDest.getText().toString();
                String  dayT = dayTravel.getText().toString();
                String  startP = startPoint.getText().toString();
                String  endP = endPoint.getText().toString();

                connectToDB(cityS,cityD,dayT,startP,endP);
            }
        });
	}

    protected void connectToDB(String cityS, String cityD, String dayT, String startP, String endP)
    {           
       HttpURLConnection connection;
       OutputStreamWriter request = null;

            URL url = null;   
            String response = null;         
            String parameters = "cityS="+cityS+"&cityD="+cityD+"&dayT="+dayT+"&startP="+startP+"&endP="+endP;   

            try
            {
                url = new URL("localhost\\ingresaViaje.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");    

                request = new OutputStreamWriter(connection.getOutputStream());
                request.write(parameters);
                request.flush();
                request.close();            
                String line = "";               
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                // Response from server after login process will be stored in response variable.                
                response = sb.toString();
                // You can perform UI operations here
                Toast.makeText(this,"Message from Server: \n"+ response, 0).show();             
                isr.close();
                reader.close();

            }
            catch(IOException e)
            {
                // Error
            }
    }
}
