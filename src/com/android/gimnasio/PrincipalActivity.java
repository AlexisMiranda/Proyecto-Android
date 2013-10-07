package com.android.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.android.gimnasio.R.*;
import com.android.gimnasio.api.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PrincipalActivity extends Activity {

	private TextView text;
	private HashMap<String,String> t_maquina_columnas;
	//linea de ejemplo con branches
	final static String ACT_INFO = "com.android.gimnasio.PrincipalActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		text=(TextView)findViewById(R.id.text);
		Usuario u=new Usuario(this);
		Maquina m=new Maquina(this);
		String resultados_u=u.getConsultaToString("select * from usuario where id=1");
		//String resultados_m=m.getConsultaToString("select * from maquina");
		//datos_usuario.put("nombre",this.usuario.getConsultaToString("select * from usuario"));
		//datos_usuario.put("Nombre",""+u.getNombre(ids_usuarios.get(0)));
		Log.d("todo",resultados_u);
		Log.d("nombre", "entredede= "+u.getNombre(1));
		Log.d("Peso", ""+u.getPeso(1));
		Log.d("Estatura", ""+u.getEstatura(1));
		Log.d("Edad", ""+u.getEdad(1));
		Log.d("Imc", " "+u.getImc(1));
		text.setText("Usuarios creados=> \n"+resultados_u);
		
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}
