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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class SeleccionarMaquinaActivity extends Activity{

	private Maquina maquina;
	private Usuario usuario;
	private TipoEjercicio te;
	private TextView titulo;
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
		titulo=new TextView(this);
		siguiente=new Button(this);
		siguiente.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
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
				 checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {


						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
						       if (buttonView.isChecked()) {
		                             Toast.makeText(getBaseContext(),  "checkeado "+buttonView.getId(),Toast.LENGTH_SHORT).show();
		                          }						
						}
	                  });
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
		titulo.setBackgroundResource(R.drawable.titulo_seleccion_de_maquinas);
		titulo.setTextSize(50);
		titulo.setPadding(20, 20,0,0);
		l1.addView(titulo,num_row,new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		num_row+=1;
		l1.addView(new TextView(this),num_row);
		num_row+=1;
	   
	    
	    
		
	}
	
}
