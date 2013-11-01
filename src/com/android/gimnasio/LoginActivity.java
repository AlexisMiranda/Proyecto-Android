package com.android.gimnasio;

import java.util.ArrayList;

import com.android.gimnasio.api.Usuario;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	
	private TextView text_user,text_passwd,titulo,text_crear_usuario;
	private EditText edit_user,edit_passwd;
	private LinearLayout linear;
	private Button ingresar;
	private Usuario usuario;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		text_passwd=new TextView(this);
		edit_passwd=new EditText(this);
		usuario=new Usuario(this);
		linear=(LinearLayout)findViewById(R.id.linear_logn);
		titulo=crearTextView(1, "Loggin",33,70, 10, 10,10);
		text_user=crearTextView(2, "Ingresa tu Usuario :",22,20, 0, 0,0);
		text_passwd=crearTextView(3, "Ingresa tu password :",22, 20, 0, 0,0);
		edit_user=crearEditText(4, "user", 300, 50, 5, 10, 30,0);
		edit_passwd=crearEditText(5,"passwd", 300, 50, 5, 10, 30,10);
		text_crear_usuario=crearTextView(6, "Crea un usuario en el sistema >>", 15, 20, 0, 5, 10);
		text_crear_usuario.setTextColor(Color.GREEN);
		text_crear_usuario.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),FormularioUsuarioActivity.class);
				startActivity(i);
			}
		});
		ingresar=new Button(this);
		ingresar.setText("Ingresar ");
		ingresar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText passwd=(EditText)linear.findViewWithTag("passwd");
				EditText user=(EditText)linear.findViewWithTag("user");
				
				if(passwd.getText().toString().equals("") ){
					Toast.makeText(getApplicationContext(),"Ingresa tu password", Toast.LENGTH_SHORT).show();
				}else if (user.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),"Ingresa tu usuario", Toast.LENGTH_SHORT).show();
				}else if (!usuario.existeUsuario(usuario.getIdUsuario(user.getText().toString(), passwd.getText().toString()))) {
					//no existe un usuario
					Toast.makeText(getApplicationContext(),"Password o contrasenia incorrectos", Toast.LENGTH_SHORT).show();
				}else if (usuario.getTieneRutinaCreada(usuario.getIdUsuario(user.getText().toString(), passwd.getText().toString()))) {
					ContentValues columnas=new ContentValues();
					columnas.put(Usuario.loggeado_int_10,1);
					usuario.editarUsuario(usuario.getIdUsuario(user.getText().toString(), passwd.getText().toString()), columnas);
					Intent i=new Intent(getApplicationContext(),SeleccionarDiaActivity.class);
					startActivity(i);
				}else{
					ContentValues columnas=new ContentValues();
					columnas.put(Usuario.loggeado_int_10,1);
					usuario.editarUsuario(usuario.getIdUsuario(user.getText().toString(), passwd.getText().toString()), columnas);
					Log.d("tabla usuario",usuario.getConsultaToString("select * from "+Usuario.nombreTabla));
					Intent i=new Intent(getApplicationContext(),SeleccionarMaquinaActivity.class);
					startActivity(i);
				}
				
			}
		});
		linear.addView(titulo);
		linear.addView(text_user);
		linear.addView(edit_user);
		linear.addView(text_passwd);
		linear.addView(edit_passwd);
		linear.addView(text_crear_usuario);
		linear.addView(ingresar);
	}
	public void onStart()
	{
		super.onStart();
		//ningun usuario loggeado
		ArrayList<Integer> ids=usuario.getIdsUsuarios();
		for(int id:ids)
		{
			ContentValues columnas=new ContentValues();
			columnas.put(Usuario.loggeado_int_10,0);
			usuario.editarUsuario(id, columnas);
		}
	}
	public TextView crearTextView(int id,String texto,float tamanio,int left,int top,int right,int bottom)
	{
		//int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		//int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		lp.setMargins(left, top, right, bottom);
		TextView text=new TextView(this);
		text.setLayoutParams(lp);
		text.setTag(texto+id);
		text.setTextSize(tamanio);
		text.setText(texto);
		text.setTextColor(Color.parseColor("#0B0B61"));
		return text;
	}
	public EditText crearEditText(int id,String texto,int w,int h,int left,int top,int right,int bottom)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		//tipo de campo passwd u otro
		EditText edit=new EditText(this);
		edit.setLayoutParams(lp);
		edit.setTag(texto);
		return edit;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
}
