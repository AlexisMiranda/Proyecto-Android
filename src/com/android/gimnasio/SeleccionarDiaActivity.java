package com.android.gimnasio;


import java.util.ArrayList;

import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.RequerimientoEjercicio;
import com.android.gimnasio.api.TipoEjercicio;
import com.android.gimnasio.api.TipoEjercicioUsuario;
import com.android.gimnasio.maquinasPorDia.MaquinasPorDiaActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
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

	private ArrayList<String> dias;//={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	private ListView lview;
	private TipoEjercicioUsuario ejercicio_usuario;
	private TipoEjercicio tipo_ejercicio;
	private RequerimientoEjercicio tabla_requerimiento_ejer;
	private LinearLayout linear;
	private String ultima_maquina_seleccionada;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		ejercicio_usuario=new TipoEjercicioUsuario(this);
		tipo_ejercicio=new TipoEjercicio(this);
		tabla_requerimiento_ejer=new RequerimientoEjercicio(this);
		linear=(LinearLayout)findViewById(R.id.linear);
		Log.d("entro en actividad",getApplication().toString());
		ultima_maquina_seleccionada=getIntent().getStringExtra("ultima_maquina_seleccionada");
		Log.d("ultima_maquina_seleccionada",""+ultima_maquina_seleccionada);
		/*
		 * eliminar los parametros ingresados por la ultima maquina seleccionada
		 */
		dias=ejercicio_usuario.getDiasInsertados();
		Log.d("dias res",dias.toString());
	setTheme(CONTEXT_INCLUDE_CODE);
		
	setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_seleccionar_dia, android.R.id.text1, dias));
	}

	public void onBackPressed() 
	{
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
				ejercicio_usuario.eliminarTipoEjerUsuarioPorTipoEjer(id_tipo_ejer);
				Log.d("eliminando = ","id tipo ejercicio usuario = "+id_tipo_ejer);
			}
		}
		this.finish();
		
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
    
}
