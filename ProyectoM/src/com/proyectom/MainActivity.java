package com.proyectom;



import com.example.proyectom.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toast;

public class MainActivity extends Activity {
	private Button Registrar;
	private EditText PassEdit;
	private EditText NameEdit;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        PassEdit = (EditText) findViewById(R.id.passEdit);
        NameEdit = (EditText) findViewById(R.id.nameEdit);
        Registrar = (Button) findViewById(R.id.button1);
        
        
        Registrar.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v){
        	//login provisional	
//        	if(NameEdit.getText().toString().equals(getString(R.string.name))
//        			&& PassEdit.getText().toString().equals(getString(R.string.pass))){
//  
        		Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
        		startActivity(intent);
        		finish();
        		
//        	} else {
//    			Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
//    		}
        	}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
