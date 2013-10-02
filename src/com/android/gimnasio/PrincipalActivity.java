package com.android.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.android.gimnasio.R.*;
import com.android.gimnasio.api.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PrincipalActivity extends Activity {

	private TextView text;
	private HashMap<String,String> t_maquina_columnas;
	final static String ACT_INFO = "com.android.gimnasio.PrincipalActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		text=(TextView)findViewById(R.id.text);
		Usuario u=new Usuario(this);
		String resultados=u.getConsultaToString("select * from usuario");
		text.setText("Usuarios creados=> \n"+resultados);
		
	}

	public void enviar(View view){
		

		//tabla maquina
		Usuario u=new Usuario(this);
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
