package com.android.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.gimnasio.api.Usuario;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FormularioUsuarioActivity extends Activity {

	
	private LinearLayout linear_layout;
	private FrameLayout frame_layout;
	private Button submit;
	private Usuario usuario;
	private ContentValues valores_a_insertar;
	private ArrayList<String> columnas=new ArrayList<String>();	
	private HashMap<String, EditText> edit_text_hash=new HashMap<String, EditText>();
	//private TextView tv_nombre,tv_apellido,tv_sexo,tv_estatura,tv_edad,tv_peso;
	//private EditText et_nombre,et_apellido,et_sexo,et_estatura,et_edad,_peso;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_usuario);
		linear_layout=(LinearLayout)findViewById(R.id.linear_layout);
		submit=new Button(this);
		submit.setText("Ingresar");
		submit.setHeight(30);
		linear_layout.setPadding(20, 0, 20, 0);
		frame_layout=new FrameLayout(this);
		frame_layout.setPadding(400, 100, 100, 100);
		linear_layout.addView(frame_layout);
		usuario=new Usuario(this);
		
		columnas=usuario.getColumnas();
		//elimino puntaje, imc y id_usuario
		columnas.remove("imc");
		columnas.remove("puntaje");
		columnas.remove("id_usuario");
		int top=5,cnt_edit=columnas.size();		
		for(int i=0;i<columnas.size();i++)
		{
			
			TextView text_view=new TextView(this);
			Log.d("Columna agregada",""+columnas.get(i));
			text_view.setText(columnas.get(i).substring(0,1).toUpperCase()+columnas.get(i).substring(1));
			text_view.setPadding(10, top=+2, 100, 10);
			text_view.setId(i);
			EditText edit_text=new EditText(this);
			edit_text.setId(cnt_edit=+1);
			edit_text.setFocusable(true);
			edit_text.setWidth(400);
			edit_text.setHeight(1);
			//text view
			linear_layout.addView(text_view);
			//edit text
			edit_text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
			linear_layout.addView(edit_text);
			edit_text_hash.put(columnas.get(i),edit_text);
		}
		linear_layout.addView(new TextView(this));
		linear_layout.addView(new TextView(this));
		linear_layout.addView(submit);
		submit.setOnClickListener(new View.OnClickListener()
		{
            public void onClick(View v) 
            {
            	Log.d("Nombre= ",""+edit_text_hash.get("nombre").getText().toString());
            	String nombre=edit_text_hash.get("nombre").getText().toString();
            	Log.d("apellido= ",""+edit_text_hash.get("apellido").getText().toString());
            	String apellido=edit_text_hash.get("apellido").getText().toString();
            	Log.d("edad= ",""+edit_text_hash.get("edad").getText().toString());
            	int edad=Integer.parseInt(edit_text_hash.get("edad").getText().toString());
            	Log.d("estatura= ",""+edit_text_hash.get("estatura").getText().toString());
            	float estatura=Float.parseFloat(edit_text_hash.get("estatura").getText().toString());
            	Log.d("peso= ",""+edit_text_hash.get("peso").getText().toString());
            	float peso=Float.parseFloat(edit_text_hash.get("peso").getText().toString());
            	Log.d("sexo= ",""+edit_text_hash.get("sexo").getText().toString());            	
            	int sexo=Integer.parseInt(edit_text_hash.get("sexo").getText().toString());
            	usuario.crearUsuario(1, nombre, apellido, edad, estatura, peso, sexo);
            	Intent intent =new Intent(getApplicationContext(),PrincipalActivity.class);
            	startActivity(intent);
            }
        });
		


	}

	
}
