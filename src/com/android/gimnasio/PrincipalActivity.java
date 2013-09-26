package com.android.gimnasio;

import java.util.HashMap;

import com.android.gimnasio.R.*;
import com.android.gimnasio.api.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PrincipalActivity extends Activity {

	private TextView text;
	private HashMap<String,String> t_maquina_columnas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		text=(TextView)findViewById(R.id.text);
		
	}

	public void enviar(View view){
		
		//tabla maquina
		Usuario u=new Usuario(this);
		Maquina maquina=new Maquina(this);
		for(int i=0;i<100;i++)
			u.crearUsuario(i, "usuario "+i, "apellido "+i, i, 1,i, 0);
		String resultados=u.getConsultaToString("select * from usuario");
		text.setText("dneudne => \n"+resultados);
		

		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}
