package com.android.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.android.gimnasio.R.*;
import com.android.gimnasio.api.*;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PrincipalActivity extends Activity {

	private TextView text;
	private HashMap<String,String> t_maquina_columnas;
	private Maquina m;
	private TipoEjercicio te;
	private Button b;
	//linea de ejemplo con branches
	final static String ACT_INFO = "com.android.gimnasio.PrincipalActivity";
	private LinearLayout linear,linear2;
	private RequerimientoEjercicio tabla_requerimiento_ejercicio;
	private TipoEjercicioUsuario tabla_tipoEjercicio_usuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		Usuario u=new Usuario(this);
		tabla_requerimiento_ejercicio=new RequerimientoEjercicio(this);
		tabla_tipoEjercicio_usuario=new TipoEjercicioUsuario(this);
		//u.crearUsuario(1, "alexis",22,(float)22.6,(float)70.3,1);
		linear=(LinearLayout)findViewById(R.id.linear);
		text=new TextView(this);
		linear.addView(text);
		m=new Maquina(this);
		te=new TipoEjercicio(this);
		Log.d("Entre a la acttividad","");	
		b=new Button(this);
		b.setText("consulta");
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
				String res="";
				res=tabla_tipoEjercicio_usuario.getConsultaToString("select * from "+TipoEjercicioUsuario.nombreTabla);
				res+="\n **\n"+tabla_requerimiento_ejercicio.getConsultaToString("select * from "+RequerimientoEjercicio.nombreTabla);
				//m.getConsultaToString("select * from maquina" );
				/*res+="\n\n****tipos de maquinas";
				res+="\n"+te.getConsultaToString("select * from "+TipoEjercicio.nombreTabla);
				res+="\n"+te.getIdsTipoEjercicios(3).toString();*/

				text.setText(res);				
			}
		});
		linear.addView(b);
		
		/*text=(TextView)findViewById(R.id.text);
		Usuario u=new Usuario(this);
		Maquina m=new Maquina(this);
		String resultados_u=u.getConsultaToString("select * from usuario where id=1");

		Log.d("todo",resultados_u);
		Log.d("nombre", "entredede= "+u.getNombre(1));
		Log.d("Peso", ""+u.getPeso(1));
		Log.d("Estatura", ""+u.getEstatura(1));
		Log.d("Edad", ""+u.getEdad(1));
		Log.d("Imc", " "+u.getImc(1));
		text.setText("Usuarios creados=> \n"+resultados_u);*/
		
	}

	public void enviar(View view){

		
		//tabla maquina
		//Usuario u=new Usuario(this);
		
		/*Maquina maquina=new Maquina(this);
		for(int i=0;i<2;i++)		
			u.crearUsuario(-1, "usuario "+i, "apellido "+i, i, 1,i, 0);

		List<Integer>ids=u.getIdsUsuarios();
		Iterator<Integer> i=ids.iterator();
		while(i.hasNext())
		{
			u.setNombreApellido(i.next(), "juanito ","");
			
		}
		for(int j=0;j<10;j++)
			u.eliminarUsuario(j);
		*/
		
		
	}
	
	@Override
	public void onBackPressed() {
			text.setText("dejdedendnejnde");
	return;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}
