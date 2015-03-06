package com.ak.encuesta;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;

public class RowPregunta extends TableRow {

	LinearLayout linearLayout;
	private TextView pregunta;
	private ArrayList<RadioButton> respuestas;
	private RadioButton respuesta;
	private Button bttnFinalizar;
	private boolean bttnFin = false;
	
	public RowPregunta(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		linearLayout = new LinearLayout(this.getContext());
		pregunta = new TextView(this.getContext());
		respuestas = new ArrayList<RadioButton>();
	}	
	
	public void setPregunta(String p){
		pregunta.setText(p);
		pregunta.setTextColor(Color.WHITE);
	}
	
	public void setRespuesta(String r){
		respuesta = new RadioButton(this.getContext());
		respuesta.setText(r);
		respuesta.setX(8);
		respuesta.setY(4);
		respuesta.setHeight(40);
		respuesta.setWidth(200);
		respuesta.setBackgroundColor(Color.argb(255, 227, 243, 219));
		respuestas.add(respuesta);
	}

	public void setRespuesta(String r, int color){
		respuesta = new RadioButton(this.getContext());
		respuesta.setText(r);
		respuesta.setX(8);
		respuesta.setY(4);
		respuesta.setHeight(40);
		respuesta.setWidth(200);
		respuesta.setBackgroundColor(color);
		respuestas.add(respuesta);
	}
	
	public void colocarBttnFinalizar(int x, int y, int width, int height, OnClickListener evt){
		bttnFin = true;
		bttnFinalizar = new Button(this.getContext());
		bttnFinalizar.setOnClickListener(evt);
		bttnFinalizar.setHeight(height);
		bttnFinalizar.setWidth(width);
		//bttnFinalizar.setX(x);
		//bttnFinalizar.setY(y);
		bttnFinalizar.setText("Finalizar");
	}
	
	public void colocarElementos(){
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setMinimumWidth(600);
		
		pregunta.setX(8);
		pregunta.setY(4);
		pregunta.setHeight(40);
		pregunta.setWidth(500);
		pregunta.setBackgroundColor(Color.DKGRAY);

		linearLayout.addView(pregunta);
		for(int i=0; i<respuestas.size(); i++){
			linearLayout.addView(respuestas.get(i));
		}
		if(bttnFin)linearLayout.addView(bttnFinalizar);
		this.addView(linearLayout);
	}
}
