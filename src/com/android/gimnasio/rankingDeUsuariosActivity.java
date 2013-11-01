package com.android.gimnasio;

import java.util.ArrayList;

import com.android.gimnasio.api.RequerimientoEjercicio;
import com.android.gimnasio.api.TipoEjercicioUsuario;
import com.android.gimnasio.api.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class rankingDeUsuariosActivity extends Activity {

	
	LinearLayout layout;
	Usuario usuario;
	private TipoEjercicioUsuario teu;
	private RequerimientoEjercicio re;
	private ArrayList<Integer> ids_usuarios;
	private String tabla_ranking="";
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking_de_usuarios);
		layout=(LinearLayout)findViewById(R.id.linear_layout_ranking);
		layout.addView(desloggear());
		layout.addView(crearTextView("Ranking de Usuarios ", 30, "#210B61", 10, 10, 10, 10));
		usuario=new Usuario(this);
		teu=new TipoEjercicioUsuario(this);
		re=new RequerimientoEjercicio(this);
		layout.addView(crearTextView(re.getpuntaje()+"", 15, "#210B61", 2, 5, 2, 2));
		Log.d(teu.getColumnas().toString(),teu.getConsultaToString("select * from "+TipoEjercicioUsuario.nombreTabla));
		
		
	}
	
	
	public TextView crearTextView(String texto,float tam,String color,int left,int top,int right,int bottom)
	{
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		lp.setMargins(left, top, right, bottom);
		TextView text=new TextView(this);
		text.setLayoutParams(lp);
		text.setTextColor(Color.parseColor(color));
		text.setText(texto);
		text.setTextSize(tam);
		return text;
	}

	public TextView desloggear()
	{
		TextView text=new TextView(this);
		Usuario u=new Usuario(this);
		int id_usuario_loggeado=u.getIdUsuarioLoggeado();
		String nombre=u.getNombre(id_usuario_loggeado);
		text.setText(nombre.substring(0,1).toUpperCase()+nombre.substring(1)+"\tX Cerrar Sesion");
		LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		lp.setMargins(100, 5, 0, 5);
		text.setLayoutParams(lp);
		text.setTextSize(18);
		text.setTextColor(Color.parseColor("#DF0101"));
		text.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//lo envio a login activity
				Toast.makeText(getApplicationContext(),"Usuario desloggeado",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(getApplicationContext(), LoginActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		return text;
		
	}
	public boolean onCreateOptions(Menu menu)
	{
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
		
	}
}
