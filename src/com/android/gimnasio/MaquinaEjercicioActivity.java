package com.android.gimnasio;

import java.util.ArrayList;

import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.TipoEjercicio;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MaquinaEjercicioActivity extends Activity {

 
	private LinearLayout linear,linear2;
	private Button boton;
	private int indice_maqui_selec=1,indice_layout_contenedor=0;
	private ScrollView scroll;
	private ArrayList<Integer> ids_maquinas_seleccionadas;
	private Maquina maquina;
	private TipoEjercicio tipo_ejercicio;
	private LinearLayout LLL;
	private ArrayList<ScrollView> Layout_contenedor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina_ejercicio);
        maquina=new Maquina(this);
        tipo_ejercicio=new TipoEjercicio(this);
        Layout_contenedor=new ArrayList<ScrollView>();
        ids_maquinas_seleccionadas=getIntent().getIntegerArrayListExtra("ids_maquinas_seleccionadas");
        scroll=(ScrollView)findViewById(R.id.scrollView1);
        LLL=new LinearLayout(this);
		linear=(LinearLayout)findViewById(R.id.linear);
		linear2=new LinearLayout(this);
		Log.d("entre en clase",getClass().toString());
		Log.d("id maquinas selecionadas",ids_maquinas_seleccionadas.toString());
		linear2.setOrientation(LinearLayout.VERTICAL);
		linear.addView(crearTitulo(R.drawable.titulo_seleccion_de_maquinas,200,80,100,10,100,0));
		linear.addView(crearImageViewConMargen(ids_maquinas_seleccionadas.get(0),200,200,70,0,0,0));
		Log.d("pase crear image con margen","imagen con margenes");
		linear.addView(crearLayout(ids_maquinas_seleccionadas.get(0)));
		boton=crearBoton();
		linear.addView(boton);
		
		Log.d("ids_maquinas_seleccionadas",ids_maquinas_seleccionadas.toString());
		boton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
					Log.d("clic","entre en clic");
					if(indice_maqui_selec<ids_maquinas_seleccionadas.size())
					{
							crearContenedor();
							
					}else{
								Intent i=new Intent(getApplicationContext(),SeleccionarDiaActivity.class);
								startActivity(i);
						}
					indice_maqui_selec+=1;

			}
		});
	}

	public void onBackPressed() {
		if(Layout_contenedor.size()>0)
		{
			Log.d("cambiar layout",""+Layout_contenedor.toString());			
			setContentView(Layout_contenedor.get(Layout_contenedor.size()-1));
			Layout_contenedor.remove(Layout_contenedor.size()-1);
			Log.d("finalizo layout",""+Layout_contenedor.toString());

		}else{
			this.finish();
		}
		return;
	}
	public void crearContenedor()
	{
		ArrayList<LinearLayout> linear_aux=new ArrayList<LinearLayout>();
		ArrayList<Button> boton_aux=new ArrayList<Button>();
		ArrayList<ImageView> img_aux=new ArrayList<ImageView>();
		//agrego el layout contenedor para guardarlo
		Layout_contenedor.add(scroll);
		Log.d("Contenedor",Layout_contenedor.toString());
		indice_layout_contenedor+=1;
		if(linear.getChildCount()==0)
		{
			linear=linear2;
			
		}
		
		for(int i=0;i<linear.getChildCount();i++)
		{
			View hijo= linear.getChildAt(i);
			Log.d("hijo",""+hijo.toString());
			if(hijo.toString().contains("LinearLayout"))
			{
				linear_aux.add(crearLayout(ids_maquinas_seleccionadas.get(indice_maqui_selec)));	
				//linear_aux.add((LinearLayout)hijo);
			}else if (hijo.toString().contains("Button"))
			{
				boton_aux.add((Button)hijo);
			}else if (hijo.toString().contains("ImageView"))
			{
				
				ImageView imagen=(ImageView)hijo;
				if(imagen.getId()!=-1)//si no es la imagen de titulo
				{
					img_aux.add(crearImageViewConMargen(ids_maquinas_seleccionadas.get(indice_maqui_selec),200,200,70,0,0,0));
				}else{	
				img_aux.add((ImageView)hijo);
				}
			}
		 }
		linear.removeAllViews();
		scroll.removeAllViews();
		for(int i=0;i<img_aux.size();i++)
		{
			Log.d("entre en textview","textview");
			linear2.addView(img_aux.get(i));
		}
		for(int i=0;i<linear_aux.size();i++)
		{
			Log.d("entre en textview","textview");
			linear2.addView(linear_aux.get(i));
		}
	
		Log.d("agrego boton","boton");
		linear2.addView(boton_aux.get(0));
		//linear.
		
		scroll.addView(linear2);
		
		setContentView(scroll);	
		
	}
	public Button crearBoton()
	{
		Button b=new Button(this);
		b.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		b.setText("SiguienteMaquina");
		return b;
	}

	public LinearLayout crearLayout(int id_maquina)
	{
		Log.d("Entre en crear linearlayout","id maquina="+id_maquina);
	    ArrayList<Integer> ids_tipo_ejercicios=tipo_ejercicio.getIdsTipoEjercicios(id_maquina);
		Log.d("ids tipos de ejercicios",ids_tipo_ejercicios.toString());

		int num_filas=ids_tipo_ejercicios.size(),indice=0;
		LinearLayout linear_vertical=new LinearLayout(this);
		
		linear_vertical.setOrientation(LinearLayout.VERTICAL);
		linear_vertical.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		//int tipos_de_ejercicios
		for(int fila=0;fila<num_filas;fila++)
		{
			LinearLayout linear_horizontal=new LinearLayout(this);
			linear_horizontal.setOrientation(LinearLayout.HORIZONTAL);
			linear_horizontal.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			for(int col=0;col<5;col++)
			{
				switch (col) {
				case 0:
					Bitmap imagen=tipo_ejercicio.getImagen(ids_tipo_ejercicios.get(indice), id_maquina, this);
						ImageView img=crearImageView(imagen,100,100);
						linear_horizontal.addView(img);
						indice+=1;
					break;
					default:
						EditText edit=crearEditText();
						linear_horizontal.addView(edit);
						break;
				}
			}
			linear_vertical.addView(linear_horizontal);
			
		}
		return linear_vertical;
	}
	public ImageView crearTitulo(int resId,int w,int h,int left,int top,int right,int bottom)
	{
	
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		ImageView img=new ImageView(this);
		img.setId(-1);
		img.setLayoutParams(lp);
		img.setImageResource(resId);
		return img;
	}	
	public ImageView crearImageViewConMargen(int id_maquina,int w,int h,int left,int top,int right,int bottom)
	{
	
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		ImageView img=new ImageView(this);
		img.setLayoutParams(lp);
		img.setId(id_maquina);
		img.setImageBitmap(maquina.getImagen(id_maquina,this));
		return img;
	}
	public ImageView crearImageView(Bitmap imagen,int w,int h)
	{
	
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		ImageView img=new ImageView(this);
		img.setLayoutParams(new LinearLayout.LayoutParams(width, heigth));
		img.setImageBitmap(imagen);

		return img;
	}
	public EditText crearEditText()
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
		EditText edit=new EditText(this);
		edit.setLayoutParams(new LinearLayout.LayoutParams(width,heigth));
		edit.setInputType(InputType.TYPE_CLASS_NUMBER);
		edit.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
		return edit;
	}
	
	
	
}
