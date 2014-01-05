package com.proyectom;



import java.util.Arrays;

import com.example.proyectom.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.OnErrorListener;
//import android.widget.EditText;
//import android.widget.Toast;

public class MainActivity extends Activity {
	private Button Registrar;
	int user;
	String username;
	String cta_facebook;
	int tel;
	//private EditText PassEdit;
	//private EditText NameEdit;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //PassEdit = (EditText) findViewById(R.id.passEdit);
        //NameEdit = (EditText) findViewById(R.id.nameEdit);
        Registrar = (Button) findViewById(R.id.button1);
        Registrar.setVisibility(View.INVISIBLE);        
        Registrar.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v){
        	//login provisional	
//        	if(NameEdit.getText().toString().equals(getString(R.string.name))
//        			&& PassEdit.getText().toString().equals(getString(R.string.pass))){
        		Bundle b = new Bundle();
        		b.putInt("iduser",user);
        		Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
        		intent.putExtras(b);
        		startActivity(intent);
        		finish();
        		
//        	} else {
//    			Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
//    		}
        	}
        });
        
        LoginButton authButton = (LoginButton) findViewById(R.id.loginButton1);
        authButton.setOnErrorListener(new OnErrorListener() {
         
         @Override
         public void onError(FacebookException error) {
           
         }
      });
      authButton.setReadPermissions(Arrays.asList("basic_info","email"));
      authButton.setSessionStatusCallback(new Session.StatusCallback() {
    	  @Override
    	  public void call(Session session, SessionState state, Exception exception) {
    		  if (session.isOpened()) {                    
    			  Request.executeMeRequestAsync(session,new Request.GraphUserCallback() {
                  @Override
                  public void onCompleted(GraphUser user,Response response) {
                	  if (user != null) {
                		  cta_facebook = user.getUsername();
                		  username = user.getFirstName() + " " + user.getLastName();
                		  tel = 11111111;
                		  new reg_user().execute();
                	  }
                  }
    			  });
    		  }else if(session.isClosed()) {
    			  Registrar.setVisibility(View.INVISIBLE);   
    			  cta_facebook = "none";
    			  username = "none";
    			  user = -1;
    		  }   
    	  }
    	  });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
         Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
     }
    class reg_user extends AsyncTask<Void,Void,Boolean>{
		@Override
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//obtener datos a registrar
						
        	usuario Registrar = new usuario();
        	Registrar.setcuentaFacebook(cta_facebook);
        	Registrar.setnombre(username);
        	Registrar.settelefono(tel);
        	user = Registrar.registerDB();
			return true;
		}
		protected void onPostExecute(Boolean result){
			Registrar.setVisibility(View.VISIBLE);
		}
	}
}
