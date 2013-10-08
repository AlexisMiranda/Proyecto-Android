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
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FormularioUsuarioActivity extends Activity {

	
	private LinearLayout linear_layout;
	private LinearLayout titulo_layout;
	private Button submit;
	private Usuario usuario;
	private ContentValues valores_a_insertar;
	private HashMap<String, String> hash_columnas=new HashMap<String, String>();	
	private HashMap<String, EditText> edit_text_hash=new HashMap<String, EditText>();
	private int sexo, edad;
	private String nombre;
	private float  estatura, peso;
	private RadioButton hombre,mujer;
	private ImageView titulo;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_usuario);
		linear_layout=(LinearLayout)findViewById(R.id.linear_layout);
		submit=new Button(this);
		submit.setText("Ingresar");
		submit.setHeight(30);
		linear_layout.setPadding(20, 0, 20, 0);
		titulo_layout=new LinearLayout(this);
		titulo_layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		titulo=new ImageView(this);
		titulo.setPadding(100, 0, 100,10);
		titulo.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		titulo.setImageResource(R.drawable.titulo_tu_informacion);
		linear_layout.addView(titulo);
		usuario=new Usuario(this);

		hash_columnas=usuario.getColumnas();
		//elimino puntaje, imc y id_usuario
		hash_columnas.remove(Usuario.id_primaryKey_0);
		hash_columnas.remove(Usuario.imc_float_8);
		hash_columnas.remove(Usuario.puntaje_float_7);
		
		Iterator<String> key_columnas=hash_columnas.keySet().iterator();
		int cnt_edit=hash_columnas.size(),i=0;
		while(key_columnas.hasNext())
		{
			
			TextView text_view=new TextView(this);
			String columna=key_columnas.next();
			text_view.setText(columna.substring(0,1).toUpperCase()+columna.substring(1));
			text_view.setPadding(10, 0, 100, 10);
			text_view.setId(i=+1);
			text_view.setTextColor(Color.parseColor("#585858"));
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
				if(columna.contains(Usuario.sexo_int_5)){
					this.logicaSexo();
					break;//siguiente columna
					
				}else{
				edit_text.setInputType(InputType.TYPE_CLASS_NUMBER);
				edit_text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});

				}
			}else if ((hash_columnas.get(columna).contains("text"))) {
				edit_text.setInputType(InputType.TYPE_CLASS_TEXT);
				edit_text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(50)});
			}else if ((hash_columnas.get(columna).contains("date"))) {
				edit_text.setInputType(InputType.TYPE_CLASS_DATETIME);
			}else if((hash_columnas.get(columna).contains("real")))
					{
				edit_text.setHorizontallyScrolling(true);
				edit_text.setInputType(EditorInfo.TYPE_CLASS_NUMBER|EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);
				edit_text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});

					}
			
			
			linear_layout.addView(edit_text);
		}
		linear_layout.addView(new TextView(this));
		linear_layout.addView(submit);
		submit.setOnClickListener(new View.OnClickListener()
		{
			
            public void onClick(View v) 
            {
            	if(validadores())
            	{
        			Log.d("Pase validadores","CRE");

            		if(usuario.existeUsuario(1))//si existe lo edito
            		{
            			Log.d("edito USUARIO","CRE");
            			ContentValues valores_columnas=new ContentValues();
            			valores_columnas.put(Usuario.nombre_str_1,nombre);
            			valores_columnas.put(Usuario.edad_int_3,edad);
            			valores_columnas.put(Usuario.estatura_float_4,estatura);
            			valores_columnas.put(Usuario.peso_float_6,peso);
            			valores_columnas.put(Usuario.sexo_int_5,sexo); 
            			valores_columnas.put(Usuario.imc_float_8, peso/(estatura*estatura));
            			Log.d("Usuario actualizado",""+valores_columnas.toString());
            		usuario.editarUsuario(1, valores_columnas);
            		}else{//si no existe lo creo
            			Log.d("CREO USUARIO","CRE");
            			usuario.crearUsuario(1, nombre,edad, estatura, peso, sexo);
            		}
            		Intent intent =new Intent(getApplicationContext(),SeleccionarMaquinaActivity.class);
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
			int caracteres_minimos;
			if(this.edit_text_hash.get(columna).getText().toString().equals(""))//si el campo esta vacio esta vacia
			{
				Log.d("error vacio ",""+columna);

				Toast.makeText(this, "Ingresa tu "+columna,Toast.LENGTH_SHORT).show();
				return false;
			}
			
			
		}

		Log.d("Nombre= ",""+this.edit_text_hash.get("nombre").getText().toString());
    	this.nombre=this.edit_text_hash.get("nombre").getText().toString();
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
		//LinearLayout linear_hombre=new LinearLayout(this);
		LinearLayout linear_mujer=new LinearLayout(this);
		linear_mujer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
		TextView text_hombre=new TextView(this);
		TextView text_mujer=new TextView(this);
		text_hombre.setText("Hombre   ");
		text_mujer.setText("Mujer       ");
		linear_mujer.setOrientation(LinearLayout.HORIZONTAL);
		linear_mujer.addView(hombre);
		linear_mujer.addView(text_hombre);
		linear_mujer.addView(mujer);
		linear_mujer.addView(text_mujer);
		linear_layout.addView(linear_mujer);
		
	}

	
}
