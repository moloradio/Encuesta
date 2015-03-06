package WS;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import Etity.Clientes;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class AgregarRespuesta extends AsyncTask<Object, String, String> {

	private Clientes cliente;
	private Context context;
	private SoapObject resSoap;
	private final String NAME_SPACE = "http://encuestas.org/";
	private final String URL = "http://10.30.138.123/Encuestas/ws/Test.asmx";
	
	@Override
	protected String doInBackground(Object... params) {
		cliente = (Clientes)params[0];
		context = (Context) params[1];
		agregarRespuesta();
		return null;
	}
	
	public void agregarRespuesta(){
		final String METHOD_NAME = "agregarRespuesta";
		final String SOAP_ACTION = "http://encuestas.org/agregarRespuesta";
		
		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		request.addProperty("nombre", cliente.getNombre());
		request.addProperty("aMaterno", cliente.getaMaterno());
		request.addProperty("aPaterno", cliente.getaPaterno());
		request.addProperty("email", cliente.getEmail());
		request.addProperty("tCasa", cliente.getTel_casa());
		request.addProperty("tCelular", cliente.getTel_celular());
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		
		HttpTransportSE trasport = new HttpTransportSE(URL);
		try{
			trasport.call(SOAP_ACTION, envelope);
			SoapPrimitive resSoap = (SoapPrimitive)envelope.getResponse();
			//this.resSoap = (SoapObject)envelope.getResponse();
		}catch(Exception e){
  			System.out.println("Error: " + e.getLocalizedMessage()+"\n"+e.getMessage()
  					+"\n"+e.getCause()
  					+"\n"+e.getStackTrace());
		}
	}

	@Override
	protected void onPostExecute(String result) {
		Toast.makeText(context, "Su encuesta ha sido enviada", Toast.LENGTH_SHORT).show();
	}
	
}
