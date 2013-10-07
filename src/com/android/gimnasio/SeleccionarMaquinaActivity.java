package com.android.gimnasio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.TipoEjercicio;
import com.android.gimnasio.api.Usuario;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.DropBoxManager.Entry;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class SeleccionarMaquinaActivity extends Activity{

	private Maquina maquina;
	private Usuario usuario;
	private TipoEjercicio te;
	private ImageView titulo;
	private ArrayList<Integer> ids,idste,ids2;
	private LinearLayout l1;
	private ImageView img;
	private int num_row=0;
	private Button siguiente;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seleccionar_maquina);
		l1=(LinearLayout)findViewById(R.id.linear);
		maquina=new Maquina(this);
		usuario=new Usuario(this);
		ids=maquina.getIdsMaquinas();
		titulo=new ImageView(this);
		siguiente=new Button(this);
		siguiente.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		siguiente.setBackgroundResource(R.drawable.boton_tipo_ejercicio);
		siguiente.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), MaquinaEjercicioActivity.class);
				startActivity(i);
				
			}
		});
		int divisible=ids.size()/2;
		int resto=ids.size()%2;
		int cnt=0,i=0;
		this.llenar_encabezado();
	   
		for (int fila = 0; fila <divisible; fila++) 
		{
			 LinearLayout l2=new LinearLayout(this);
			    l2.setOrientation(LinearLayout.HORIZONTAL);
			    l2.addView(new TextView(this));
		     for(int col=0;col<2;col++)
		     {
				CheckBox checkBox = new CheckBox(this);
				checkBox.setGravity(Gravity.CENTER);
				 checkBox.setId(ids.get(i));
				l2.addView(checkBox);
		        ImageView addBtn = new ImageView(this);
		        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 300);
		        addBtn.setLayoutParams(layoutParams);
		        addBtn.setImageBitmap(maquina.getImagen(ids.get(i), this));
		        i+=1;
		        l2.addView(addBtn);
			}
			 l1.addView(l2,num_row);
			 num_row+=1;
		}
		LinearLayout l2=new LinearLayout(this);
	    l2.setOrientation(LinearLayout.HORIZONTAL);
	    l2.addView(new TextView(this));

		for (int col = 0; col <resto; col++) {
			CheckBox checkBox = new CheckBox(this);
	        ImageView addBtn = new ImageView(this);
	        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 300);
	        addBtn.setLayoutParams(layoutParams);
	        addBtn.setImageBitmap(maquina.getImagen(ids.get(i), this));
	        i+=1;
			l2.addView(checkBox);
	        l2.addView(addBtn);
			
		}
		 l1.addView(l2,num_row);
		 num_row+=1;
		 l1.addView(new TextView(this),num_row);
		 num_row+=1;
		 l1.addView(siguiente,num_row);
		 num_row+=1;

	}

	
	public void llenar_encabezado()
	{
		HashMap<String, String> datos_usuario= this.usuario.getInfoUsuario(1);
		datos_usuario.remove(Usuario.id_primaryKey_0);
		datos_usuario.remove(Usuario.nombre_str_1);

		datos_usuario.remove(Usuario.imc_float_8);
		Iterator<String> i =datos_usuario.keySet().iterator();
		
		TextView tv_nombre=new TextView(this);
		TextView tv_imc=new TextView(this);
		ImageView im_imc=new ImageView(this);
		im_imc.setImageResource(R.drawable.imc);
		im_imc.setLayoutParams(new LinearLayout.LayoutParams(100, 60));
		tv_nombre.setText("\t"+usuario.getNombre(1));
		tv_nombre.setTextColor(Color.parseColor("#08088A"));
		tv_nombre.setTextSize(30);
		tv_imc.setText(""+usuario.getImc(1).substring(0, usuario.getImc(1).length()-2));
		tv_imc.setTextColor(Color.parseColor("#08088A"));
		tv_imc.setTextSize(20);
		titulo.setImageResource(R.drawable.titulo_seleccion_maquinas);
		l1.addView(titulo,num_row);
		num_row+=1;
		l1.addView(tv_nombre,num_row);
		num_row+=1;
		LinearLayout l=new LinearLayout(this);
		l.setOrientation(LinearLayout.HORIZONTAL);
		l.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		l.addView(new TextView(this));
		l.addView(im_imc);
		l.addView(new TextView(this));
		l.addView(tv_imc);
		l1.addView(l,num_row);
		num_row+=1;
		l1.addView(new TextView(this),num_row);
		num_row+=1;
		Log.d("sali", "encabezado sali");
	   
	    
	    
		
	}
	
}
