package com.android.gimnasio;


import java.util.ArrayList;

import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.RequerimientoEjercicio;
import com.android.gimnasio.api.TipoEjercicio;
import com.android.gimnasio.api.TipoEjercicioUsuario;
import com.android.gimnasio.api.Usuario;
import com.android.gimnasio.maquinasPorDia.MaquinasPorDiaActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SeleccionarDiaActivity extends ListActivity {

	private ArrayList<String> dias;
	private ListView lview;
	private TipoEjercicioUsuario ejercicio_usuario;
	private TipoEjercicio tipo_ejercicio;
	private Usuario usuario;
	private RequerimientoEjercicio tabla_requerimiento_ejer;
	private LinearLayout linear;
	private String ultima_maquina_seleccionada;
	private int id_usuario_loggeado;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		usuario=new Usuario(this);
		ejercicio_usuario=new TipoEjercicioUsuario(this);
		tipo_ejercicio=new TipoEjercicio(this);
		tabla_requerimiento_ejer=new RequerimientoEjercicio(this);
		linear=(LinearLayout)findViewById(R.id.linear);
		Log.d("entro en actividad",getApplication().toString());
		id_usuario_loggeado=usuario.getIdUsuarioLoggeado();
		ultima_maquina_seleccionada=getIntent().getStringExtra("ultima_maquina_seleccionada");
		Log.d("ultima_maquina_seleccionada",""+ultima_maquina_seleccionada);
		dias=getDiasOrdenados(ejercicio_usuario.getDiasInsertados(id_usuario_loggeado));
		
		Log.d("dias res",dias.toString());
		Log.d("tabla Usuarios",usuario.getConsultaToString("select * from "+Usuario.nombreTabla));
	setTheme(CONTEXT_INCLUDE_CODE);
		
	setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_seleccionar_dia, android.R.id.text1, dias));
	Log.d("usuario con rutina"+id_usuario_loggeado,""+usuario.getTieneRutinaCreada(id_usuario_loggeado));
	if(ultima_maquina_seleccionada!=null || (!usuario.getTieneRutinaCreada(id_usuario_loggeado)) )
	{
		validarRutinaDeEjercicios();
	}
	}

	public void onBackPressed() 
	{
		Log.d("usuario= rutina",""+usuario.getTieneRutinaCreada(1));

		if(!usuario.getTieneRutinaCreada(id_usuario_loggeado))
		{
			if(ultima_maquina_seleccionada!=null )
			{
				ContentValues columnas=new ContentValues();
				columnas.put(Usuario.rutina_int_9,0);
				usuario.editarUsuario(id_usuario_loggeado, columnas);
				if(Integer.parseInt(ultima_maquina_seleccionada)!=-1)
				{
					ArrayList<Integer> ejercicios=tipo_ejercicio.getIdsTipoEjercicios(Integer.parseInt(ultima_maquina_seleccionada));
			
					for(int id_tipo_ejer:ejercicios)
					{
						Log.d("tipo_ejercicio = =>",id_tipo_ejer+"");
			
						ArrayList<Integer> ids_tipo_ejercicio_usuario=ejercicio_usuario.getIdsTypeEjerUserByTypeEjer(id_tipo_ejer);
						Log.d("eliminarDatosEnB","ids ids_tipo_ejercicios_usuario.llamada a la api "+ids_tipo_ejercicio_usuario.toString());
						for(int id_tipo_ejer_user:ids_tipo_ejercicio_usuario)
						{
							tabla_requerimiento_ejer.eliminarReqEjerPorTipoEjerUser(id_tipo_ejer_user);
							Log.d("eliminando = ","id tipo ejercicio = "+id_tipo_ejer_user);
						}
						ejercicio_usuario.eliminarTipoEjerUsuarioPorTipoEjer(id_tipo_ejer,id_usuario_loggeado);
						Log.d("eliminando = ","id tipo ejercicio usuario = "+id_tipo_ejer);
					}
				}
			}

		}else{
			Intent i=new Intent(this, HomeActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		}
		finish();

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	protected void onListItemClick(ListView list,View view,int position,long id){
		super.onListItemClick(list,view,position,id);
		String nombre_lista=dias.get(position);
		try{
			 Intent i = new Intent(this, MaquinasPorDiaActivity.class );
			 i.putExtra("dia", nombre_lista);
		     startActivity(i);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	public void validarRutinaDeEjercicios()
	{
		final String[] opciones={"Si, validar","No, editar"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Deceas validar la rutina de Ejercicios?");
		builder.setItems(opciones, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item) 
			{
				String elemento=opciones[item];
				if(elemento.contains("Si"))
					{ 
					ContentValues columnas=new ContentValues();
					columnas.put(Usuario.rutina_int_9,1);
					usuario.editarUsuario(id_usuario_loggeado, columnas);
					dialog.cancel();
					}else{
						onBackPressed();
					}
			
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
		
	}
	public ArrayList<String> getDiasOrdenados(ArrayList<String> dias)
	{
		String[] dias_ordenados={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
		ArrayList<String> dias_aux=new ArrayList<String>();
		
		for(String dia:dias_ordenados)
		{
			if(dias.contains((Object)dia))
				{
				dias_aux.add(dia);
				}
		}
		return dias_aux;
	}
    
}
