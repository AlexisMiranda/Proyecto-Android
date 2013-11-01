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
import android.widget.LinearLayout;

public class HomeActivity extends Activity {

	private Usuario usuario;
	private Maquina maquina;
	private Button b_crear_rutina;
	private boolean usuario_loggeado=false,usuario_con_rutina=false,existe_un_usuario=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		b_crear_rutina=(Button)findViewById(R.id.b_crear_rutina);
		usuario=new Usuario(this);
		maquina=new Maquina(this);
	}

	public void onStart()
	{
		super.onStart();
		if(!maquina.existeMaquina(2))
		{
			InsertarDatos.setDeDatos(this, 1);
		}
		if(usuario.existeUsuarioEnBd())
		{
			existe_un_usuario=true;
			if(usuario.existeUsuarioLoggeado())
			{
				int id_usuario=usuario.getIdUsuarioLoggeado();
				usuario_loggeado=true;
				if(usuario.getTieneRutinaCreada(id_usuario))
				{
					b_crear_rutina.setText("Ver rutina");
					usuario_con_rutina=true;
				}else{
					b_crear_rutina.setText("Crear rutina");
					usuario_con_rutina=false;
				}
				
			}else{
				b_crear_rutina.setText("Ingresar");
				usuario_loggeado=false;
			}
	}else{
		b_crear_rutina.setText("Crear usuario");
		existe_un_usuario=false;
	}

	}
	public void crearRutina(View view)
	{
		Log.d("crearRutina",getClass().toString());
		if(existe_un_usuario)
		{
			if(usuario_loggeado)
			{
				int id_usuario=usuario.getIdUsuarioLoggeado();
				if(usuario_con_rutina)
				{
					Intent i=new Intent(getApplicationContext(), SeleccionarDiaActivity.class);
					startActivity(i);
				}else{
					Intent i=new Intent(getApplicationContext(), SeleccionarMaquinaActivity.class);
					startActivity(i);	
				}
				
			}else{
				Intent i=new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);
			}
		}else{
			Intent i=new Intent(getApplicationContext(), FormularioUsuarioActivity.class);
			startActivity(i);
		}
	}

	
	public void enviar(View view){

		Intent intent=new Intent(this,FormularioUsuarioActivity.class);

			if(usuario.getTieneRutinaCreada(1))
			{
				intent=new Intent(this,SeleccionarDiaActivity.class);

			}
			startActivity(intent);

	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}