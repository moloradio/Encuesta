package com.ak.encuesta;

import WS.ConsumoWS;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

//click view
public class MainActivity extends Activity {
	
	TableLayout layoutContent;
	TextView titlePregunta;
	RadioButton respuesta1;
	RadioButton respuesta2;
	RadioButton respuesta3;
	RadioButton respuesta4;
	
	ConsumoWS encuestasWS;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutContent = (TableLayout)findViewById(R.id.table_main);
        encuestasWS = new ConsumoWS(this,getBaseContext().getFilesDir().getAbsolutePath());
        //encuestasWS.consultarEncuesta();
        encuestasWS.execute(layoutContent, this);
    }     

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	abrirSeleccionEncuestas();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void abrirSeleccionEncuestas(){
    	Intent selectEncuesta = new Intent(getBaseContext(), SeleccionEncuesta.class);
    	startActivity(selectEncuesta);
    }
    
}
