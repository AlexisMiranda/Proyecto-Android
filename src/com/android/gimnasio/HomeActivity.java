package com.android.gimnasio;

import java.util.HashMap;

import com.android.gimnasio.api.AdminSQLiteOpenHelper;
import com.android.gimnasio.api.InsertarDatos;
import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

	private Usuario usuario;
	private Maquina maquina;
	private Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		b1=(Button)findViewById(R.id.button1);
		usuario=new Usuario(this);
		maquina=new Maquina(this);
		InsertarDatos.setDeDatos(this, 1);
	
	}

	public void enviar(View view){

			Intent intent=new Intent(this,FormularioUsuarioActivity.class);
			startActivity(intent);

	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}