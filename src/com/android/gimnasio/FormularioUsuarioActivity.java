package com.android.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.android.gimnasio.api.Usuario;

import android.R.bool;
import android.R.menu;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FormularioUsuarioActivity extends Activity {

	
	private LinearLayout linear_layout;
	private FrameLayout frame_layout;
	private Button submit;
	private Usuario usuario;
	private ContentValues valores_a_insertar;
	private HashMap<String, String> hash_columnas=new HashMap<String, String>();	
	private HashMap<String, EditText> edit_text_hash=new HashMap<String, EditText>();
	private int sexo, edad;
	private String nombre, apellido;
	private float  estatura, peso;
	private RadioButton hombre,mujer;
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

		hash_columnas=usuario.getColumnas();
		//elimino puntaje, imc y id_usuario
		hash_columnas.remove("id_usuario");
		hash_columnas.remove("imc");
		hash_columnas.remove("puntaje");
		
		Iterator<String> key_columnas=hash_columnas.keySet().iterator();
		int top=5,cnt_edit=hash_columnas.size(),i=0;
		Log.d("empiezo while","inicio "+cnt_edit);
		while(key_columnas.hasNext())
		{
			
			TextView text_view=new TextView(this);
			Log.d("cree text view","view "+cnt_edit);

			String columna=key_columnas.next();
			Log.d("Columna agregada","");
			text_view.setText(columna.substring(0,1).toUpperCase()+columna.substring(1));
			text_view.setPadding(10, top=+2, 100, 10);
			text_view.setId(i=+1);
			EditText edit_text=new EditText(this);
			edit_text.setId(cnt_edit=+1);
			edit_text.setFocusable(true);
			edit_text.setWidth(400);
			edit_text.setHeight(1);
			//text view
			//edit text
			edit_text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
			edit_text_hash.put(columna,edit_text);
			linear_layout.addView(text_view);
			//seteo el tipo de campo input
			if((hash_columnas.get(columna).contains("integer")))
			{
				if(columna.contains("sexo")){
					this.logicaSexo();
					break;//siguiente columna
					
				}else{
				edit_text.setInputType(InputType.TYPE_CLASS_NUMBER);
				}
			}else if ((hash_columnas.get(columna).contains("text"))) {
				edit_text.setInputType(InputType.TYPE_CLASS_TEXT);
			}else if ((hash_columnas.get(columna).contains("date"))) {
				edit_text.setInputType(InputType.TYPE_CLASS_DATETIME);
			}else if((hash_columnas.get(columna).contains("real")))
					{
				edit_text.setHorizontallyScrolling(true);
				edit_text.setInputType(EditorInfo.TYPE_CLASS_NUMBER|EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);
					}
			
			
			linear_layout.addView(edit_text);
		}
		linear_layout.addView(new TextView(this));
		linear_layout.addView(new TextView(this));
		linear_layout.addView(submit);
		submit.setOnClickListener(new View.OnClickListener()
		{
            public void onClick(View v) 
            {
            	if(validadores())
            	{
            		if(usuario.existeUsuario(1))//si existe lo edito
            		{
            			ContentValues valores_columnas=new ContentValues();
            			valores_columnas.put("nombre",nombre);
            			valores_columnas.put("apellido",apellido);
            			valores_columnas.put("edad",edad);
            			valores_columnas.put("estatura",estatura);
            			valores_columnas.put("peso",peso);
            			valores_columnas.put("sexo",sexo);            			
            		usuario.editarUsuario(1, valores_columnas);
            		}else{//si no existe lo creo
            			usuario.crearUsuario(1, nombre, apellido, edad, estatura, peso, sexo);
            		}
            		Intent intent =new Intent(getApplicationContext(),PrincipalActivity.class);
            		startActivity(intent);
            	}
            }
        });
		


	}
	


	public boolean validadores()
	{
		HashMap<String, String> hash_columnas2= hash_columnas;
		hash_columnas2.remove("sexo");//viene por defecto con 0
		Iterator<String> key_columnas=hash_columnas2.keySet().iterator();
		while(key_columnas.hasNext())
		{
			String columna=key_columnas.next();
			Log.d("validador***",""+columna);
			if(this.edit_text_hash.get(columna).getText().toString().equals(""))//si el campo esta vacio esta vacia
			{
				Log.d("error vacio ",""+columna);

				Toast.makeText(this, "Ingresa tu "+columna,Toast.LENGTH_SHORT).show();
				return false;
			}
			
			
		}

		Log.d("Nombre= ",""+this.edit_text_hash.get("nombre").getText().toString());
    	this.nombre=this.edit_text_hash.get("nombre").getText().toString();
    	Log.d("apellido= ",""+this.edit_text_hash.get("apellido").getText().toString());
    	this.apellido=this.edit_text_hash.get("apellido").getText().toString();
    	Log.d("edad= ",""+this.edit_text_hash.get("edad").getText().toString());
    	this.edad=Integer.parseInt(this.edit_text_hash.get("edad").getText().toString());
    	Log.d("estatura= ",""+this.edit_text_hash.get("estatura").getText().toString());
    	this.estatura=Float.parseFloat(this.edit_text_hash.get("estatura").getText().toString());
    	Log.d("peso= ",""+this.edit_text_hash.get("peso").getText().toString());
    	this.peso=Float.parseFloat(edit_text_hash.get("peso").getText().toString());
    	Log.d("sexo= ",""+sexo); 
    	return true;
		
	}
	public void logicaSexo()
	{
		hombre=new RadioButton(this);
		mujer=new RadioButton(this);
		hombre.setChecked(true);
		mujer.setChecked(false);
		hombre.setWidth(10);
		hombre.setHeight(10);
		mujer.setWidth(10);
		mujer.setHeight(10);						
		hombre.setOnClickListener(new RadioButton.OnClickListener(){
            @Override
            public void onClick(View v) {sexo=0;mujer.setChecked(false);hombre.setChecked(true);}});
		mujer.setOnClickListener(new RadioButton.OnClickListener(){
            @Override
            public void onClick(View v) {sexo=1;hombre.setChecked(false);mujer.setChecked(true);}});
		Log.d("Linear empezo","edueduede");
		LinearLayout linear_hombre=new LinearLayout(this);
		LinearLayout linear_mujer=new LinearLayout(this);
		linear_hombre.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
		linear_mujer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
		TextView text_hombre=new TextView(this);
		TextView text_mujer=new TextView(this);
		text_hombre.setText("Hombre   ");
		text_mujer.setText("Mujer       ");
		linear_hombre.setOrientation(LinearLayout.HORIZONTAL);
		linear_hombre.addView(text_hombre);
		linear_hombre.addView(hombre);
		linear_mujer.setOrientation(LinearLayout.HORIZONTAL);
		linear_mujer.addView(text_mujer);
		linear_mujer.addView(mujer);
		linear_layout.addView(linear_hombre);
		linear_layout.addView(linear_mujer);
		
	}

	
}
