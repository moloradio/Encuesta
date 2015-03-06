package WS;

import java.io.IOException;

import DB.ConexionBD;
import android.sax.StartElementListener;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import com.ak.encuesta.DatoUsuario;
import com.ak.encuesta.MainPagerAdapter;
import com.ak.encuesta.RowPregunta;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;

public class ConsumoWS extends AsyncTask<Object, Integer, String>{

	private RowPregunta fila;
	private TableLayout layout;
	private Context context;
	private SoapObject resSoap;
	private final String NAME_SPACE = "http://encuestas.org/";
	//private final String URL = "http://172.20.10/Encuestas/ws/Test.asmx";
	private final String URL = "http://10.30.138.123/Encuestas/ws/Test.asmx";
	//private final String URL = "http://10.30.138.235/Encuestas/ws/Test.asmx";
	private MainPagerAdapter pageAdapter;
	private int encuestaSeleccionada;
	
	public ConsumoWS(Context context,String path){
		ConexionBD con = new ConexionBD(context, path);
		this.context = context;
		//ConexionBD con = new ConexionBD(path);
		try {
			con.createDataBase();
			encuestaSeleccionada = con.getEncuestaSeleccionada();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void consultarEncuesta(TableLayout layout, Activity activity){
		this.layout = layout;
		final String METHOD_NAME = "getPregunta";
		//final String METHOD_NAME = "getTest";
		final String SOAP_ACTION = "http://encuestas.org/getPregunta";
		//final String SOAP_ACTION = "http://encuestas.org/getTest";
		 
		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		request.addProperty("idEncuesta", encuestaSeleccionada);
		request.addProperty("idPreguntas", 1);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		
		HttpTransportSE trasport = new HttpTransportSE(URL);
		try{
			trasport.call(SOAP_ACTION, envelope);
			//SoapPrimitive resSoap = (SoapPrimitive)envelope.getResponse();
			this.resSoap = (SoapObject)envelope.getResponse();
		}catch(Exception e){
  			System.out.println("Error: " + e.getLocalizedMessage()+"\n"+e.getMessage()
  					+"\n"+e.getCause()
  					+"\n"+e.getStackTrace());
		}
		
	}
	
	public void addRows(TableLayout layout, SoapObject so){
		
		pageAdapter = new MainPagerAdapter();
		ViewPager pages = new ViewPager(layout.getContext());
		int color;
		
		SoapObject preguntas = (SoapObject)so.getProperty("Preguntas");
		for(int i=0; i<preguntas.getPropertyCount(); i++){
			fila = new RowPregunta(layout.getContext());
			SoapObject pregunta = (SoapObject)preguntas.getProperty(i);
			SoapObject respuestas = (SoapObject)pregunta.getProperty("Respuestas");
			fila.setPregunta(pregunta.getPropertyAsString("Pregunta"));
			for(int r=0; r<respuestas.getPropertyCount(); r++){
				SoapObject respuesta = (SoapObject)respuestas.getProperty(r);
				color = (r%2==0)?Color.argb(255, 227, 243, 219):Color.argb(255, 255, 255, 255);
				//fila.setBackgroundColor(color);
				fila.setRespuesta(respuesta.getPropertyAsString("Respuesta"), color);
			}
			if(i+1 == preguntas.getPropertyCount()){
				evtBttnFinalizar evt = new evtBttnFinalizar();
				fila.colocarBttnFinalizar(layout.getWidth()-200, layout.getHeight()-60, 120, 44, evt);
			}
			fila.colocarElementos();
			pageAdapter.addView(fila);
		}
		pages.setAdapter(pageAdapter);
		pages.setCurrentItem(0);
		layout.addView(pages);
	}
	
	class evtBttnFinalizar implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(layout.getContext(), DatoUsuario.class);
			context.startActivity(intent);
		}
	}
	
	
	@Override
	protected String doInBackground(Object... params) {
		
		consultarEncuesta((TableLayout)params[0], (Activity)params[1]);
		
		return "";
	}
	
	@Override
	protected void onPostExecute(String result) {
		addRows(layout, resSoap);
	}
	
	@Override
	protected void onPreExecute() {
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
	}
	
}
