package com.ak.encuesta;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import DB.ConexionBD;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;

public class SeleccionEncuesta extends Activity{

	private RadioButton selectEncuesta;
	private RadioGroup radioGroup;
	private TableLayout layout;
	private SoapObject resSoap;
	private final String NAME_SPACE = "http://encuestas.org/";
	private final String URL = "http://10.30.138.123/Encuestas/ws/Test.asmx";
	//private final String URL = "http://10.30.138.235/Encuestas/ws/Test.asmx";
	//private final String URL = "http://172.20.10.12/Encuestas/ws/Test.asmx";
	
	private int idEncuesta;
	private ConexionBD conexion;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_encuesta);
		conexion = new ConexionBD(this, getBaseContext().getFilesDir().getPath());
		idEncuesta = conexion.getEncuestaSeleccionada();
		layout = (TableLayout)findViewById(R.id.table_main);
		ConsultaEncuestas encuestas = new ConsultaEncuestas();
		encuestas.execute("");
	}
	
	class ConsultaEncuestas extends AsyncTask<String, Integer, Integer>{

		final String METHOD_NAME = "getEncuestas";
		final String SOAP_ACTION = "http://encuestas.org/getEncuestas";
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}public ConsultaEncuestas() {
			// TODO Auto-generated constructor stub
		}
		@Override
		protected Integer doInBackground(String... params) {
			
			SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
			
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE trasport = new HttpTransportSE(URL);
			try{
				trasport.call(SOAP_ACTION, envelope);
				//SoapPrimitive resSoap = (SoapPrimitive)envelope.getResponse();
				resSoap = (SoapObject)envelope.getResponse();
			}catch(Exception e){
	  			System.out.println("Error: " + e.getLocalizedMessage()
	  					+"\n"+e.getMessage()
	  					+"\n"+e.getCause()
	  					+"\n"+e.getStackTrace());
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			SoapObject preguntas;
			radioGroup = new RadioGroup(getBaseContext());
			for(int i=0; i<resSoap.getPropertyCount(); i++){
				preguntas = (SoapObject)resSoap.getProperty(i);
				selectEncuesta = new RadioButton(getBaseContext());
				selectEncuesta.setText((String)preguntas.getPropertyAsString("Nombre"));
				selectEncuesta.setId(Integer.parseInt((String)preguntas.getPropertyAsString("IdEncuesta")));
				if(idEncuesta == Integer.parseInt((String)preguntas.getPropertyAsString("IdEncuesta"))){
					selectEncuesta.setChecked(true);
				}
				checkRadioBttn evtCheck = new checkRadioBttn();
				selectEncuesta.setOnCheckedChangeListener(evtCheck);
				radioGroup.addView(selectEncuesta);
			}
			layout.addView(radioGroup);
		}	
	}
	
	class checkRadioBttn implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (isChecked)
				conexion.setEncuestaSeleccionada(idEncuesta, buttonView.getId(), (String)buttonView.getText());
		}
	}
}
