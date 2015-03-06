package com.ak.encuesta;

import Etity.Clientes;
import WS.AgregarRespuesta;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DatoUsuario extends Activity {

	private Button bttnEnviar;
	private Button bttnRegresar;
	
	private EditText nombre;
	private EditText apMaterno;
	private EditText apPaterno;
	private EditText email;
	private EditText telCasa;
	private EditText telCelular;
	
	private Clientes cliente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datos_usuario);
		cargarEditText();
		cargarBottones();
	}
	
	public void cargarBottones(){
		EventoRegresar evtRegresar = new EventoRegresar();
		bttnRegresar = (Button)findViewById(R.id.bttnRegresar);
		bttnRegresar.setOnClickListener(evtRegresar);
		
		EventoEnviar evtEnviar = new EventoEnviar();
		bttnEnviar = (Button)findViewById(R.id.bttnEnviar);
		bttnEnviar.setOnClickListener(evtEnviar);
		
	}
	
	public void cargarEditText(){
		nombre = (EditText)findViewById(R.id.editNombre);
		apMaterno = (EditText)findViewById(R.id.editMaterno);
		apPaterno = (EditText)findViewById(R.id.editPaterno);
		email = (EditText)findViewById(R.id.editEmail);
		telCasa = (EditText)findViewById(R.id.editTelCasa);
		telCelular = (EditText)findViewById(R.id.editTelCel);
	}
	
	class EventoRegresar implements OnClickListener{
		@Override
		public void onClick(View v) {
			finish();
		}
	}
	
	class EventoEnviar implements OnClickListener{
		@Override
		public void onClick(View v) {
			cliente = new Clientes();
			cliente.setNombre(nombre.getText().toString());
			cliente.setaMaterno(apMaterno.getText().toString());
			cliente.setaPaterno(apPaterno.getText().toString());
			cliente.setEmail(email.getText().toString());
			cliente.setTel_casa(telCasa.getText().toString());
			cliente.setTel_celular(telCelular.getText().toString());
			AgregarRespuesta agregarRespuesta = new AgregarRespuesta();
			agregarRespuesta.execute(cliente, getBaseContext());
			Toast.makeText(getBaseContext(), cliente.getNombre()+"  "+cliente.getaMaterno(), Toast.LENGTH_SHORT).show();
		}
	}
	
	
	
}
