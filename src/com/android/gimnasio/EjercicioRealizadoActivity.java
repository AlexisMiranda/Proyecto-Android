package com.android.gimnasio;

import java.util.ArrayList;

import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.RequerimientoEjercicio;
import com.android.gimnasio.api.TipoEjercicio;
import com.android.gimnasio.api.TipoEjercicioUsuario;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class EjercicioRealizadoActivity extends Activity {

	
	private LinearLayout linear;//,linear2;
	private Button boton;
	private int numero_de_maquinas,num_maquina_selec;
	private String dia;
	private Maquina tabla_maquina;
	private TipoEjercicioUsuario tabla_teu;
	private TipoEjercicio tabla_te;
	private RequerimientoEjercicio tabla_rqe;
	private ArrayList<Integer> ejercicios_por_maquina_Selec;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		num_maquina_selec=Integer.parseInt(getIntent().getStringExtra("num_maquina_selec"));
		dia=getIntent().getStringExtra("dia");
		setContentView(R.layout.activity_ejercicio_realizado);
		tabla_maquina=new Maquina(this);
		tabla_teu=new TipoEjercicioUsuario(this);
		tabla_te=new TipoEjercicio(this);
		tabla_rqe=new RequerimientoEjercicio(this);
	
		ejercicios_por_maquina_Selec=tabla_teu.getEjerciciosSeleccionados(num_maquina_selec,dia.substring(0, 3));
		linear=(LinearLayout)findViewById(R.id.linear);
		TextView titulo=new TextView(this);
		titulo.setText("\n\n\tReporte Para el dia "+dia);
		titulo.setTextColor(Color.parseColor("#0B0B61"));
		titulo.setTextSize(20);
		titulo.setTextAppearance(this,R.style.AppBaseTheme);
		linear.addView(titulo);
		ImageView imagen_maquina=crearImageViewBitmap(tabla_maquina.getImagen(num_maquina_selec, this),200,200,50,0,0,0);
		linear.addView(imagen_maquina);
		TextView nombre_maquina=new TextView(this);
		nombre_maquina.setText("\t\t\t"+tabla_maquina.getNombre(num_maquina_selec).toUpperCase());
		nombre_maquina.setTextColor(Color.parseColor("#1C1C1C"));
		nombre_maquina.setTextSize(15);
		nombre_maquina.setTextAppearance(this,R.style.AppBaseTheme);

		linear.addView(nombre_maquina);
		linear.addView(crearLayout(ejercicios_por_maquina_Selec.size()));
		Button b=new Button(this);
		b.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		b.setText("Puntaje");
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				calcularPuntaje();
			}
		});
		linear.addView(b);
		calcularPuntaje();

	}

	public LinearLayout crearLayout(int num_filas)
	{ 
		int contedit=0,prom_por=0,id=0;
		LinearLayout linear_vertical=new LinearLayout(this);

		linear_vertical.setOrientation(LinearLayout.VERTICAL);
		linear_vertical.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		//-------------------------------------------------------------------------------------
		LinearLayout linear_horizontal1=new LinearLayout(this);
		linear_horizontal1.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.setMargins(110, 10, 2, 5);
		linear_horizontal1.setLayoutParams(lp);
		ArrayList<String> params_ejercicios=tabla_rqe.getNombres(tabla_teu.getIdTipoEjercicioUsuario(ejercicios_por_maquina_Selec.get(0), 1, dia.substring(0,3)));
		for(int col=0;col<params_ejercicios.size();col++)
		{
		         TextView t2= new TextView(this);//crearTextViewMathParent("param_"+params_ejercicios.get(col),params_ejercicios.get(col),1, 5, 2, 2);
			    t2.setText(params_ejercicios.get(col)+" ");
		         linear_horizontal1.addView(t2);
		}
		
		linear_vertical.addView(linear_horizontal1);
		for(int fila=0;fila<num_filas;fila++)
		{

			LinearLayout linear_horizontal=new LinearLayout(this);
			linear_horizontal.setOrientation(LinearLayout.HORIZONTAL);
			linear_horizontal.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Log.d("params de ejercicios",params_ejercicios.toString());
			Bitmap img_ejer=tabla_te.getImagen(ejercicios_por_maquina_Selec.get(fila), num_maquina_selec, this);
			ImageView img=crearImageViewBitmap(img_ejer, 120, 120, 10, 0, 5, 10);
			linear_horizontal.addView(img);
			for(int col=0;col<params_ejercicios.size();col++)
			{

						TextView txt=crearTextView("text"+contedit,"", 19,20,0, 2, 0, 2);
						//TextView txt=new TextView(this);
						txt.setText(""+tabla_rqe.getValor(tabla_teu.getIdTipoEjercicioUsuario(ejercicios_por_maquina_Selec.get(fila), 1, dia.substring(0, 3)), params_ejercicios.get(col)));
						//txt.setTag("text"+contedit);
						//txt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
						EditText edit=crearEditText(40,40,1, 2, 0, 2);
						/*edit.setOnKeyListener(new View.OnKeyListener() {
						});*/

						edit.setTag("edit"+contedit);
						contedit++;
						edit.setInputType(InputType.TYPE_CLASS_NUMBER);
						edit.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
						linear_horizontal.addView(txt);
						linear_horizontal.addView(edit);
	
			}

			linear_vertical.addView(linear_horizontal);
			
			LinearLayout linear_horizontal2=new LinearLayout(this);
			linear_horizontal2.setOrientation(LinearLayout.HORIZONTAL);
			linear_horizontal2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			TextView por = new TextView(this);
			por.setText("Porcentaje   ");
			linear_horizontal2.addView(por);
				for(int i=0;i<params_ejercicios.size();i++){				
					TextView porcentaje = new TextView(this);
					int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
					int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
					porcentaje.setLayoutParams(new LinearLayout.LayoutParams(width,heigth));
					porcentaje.setInputType(InputType.TYPE_CLASS_NUMBER);
					porcentaje.setText("");
					porcentaje.setTag("porcentaje"+id);
					id++;
					linear_horizontal2.addView(porcentaje);
				}
				
		linear_vertical.addView(linear_horizontal2);
		LinearLayout linear_horizontal3=new LinearLayout(this);
		linear_horizontal3.setOrientation(LinearLayout.HORIZONTAL);
		linear_horizontal3.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			for(int i=0;i<1;i++){
				switch(i) {
				case 0:
					TextView promedio_puntaje = new TextView(this);
					por.setText("   Promedio/Puntaje   ");
					linear_horizontal3.addView(promedio_puntaje);
					
					break;
					
				default:
						
				TextView promedioporcentaje = new TextView(this);
				int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
				int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
				promedioporcentaje.setLayoutParams(new LinearLayout.LayoutParams(width,heigth));
				promedioporcentaje.setInputType(InputType.TYPE_CLASS_NUMBER);
				promedioporcentaje.setTag("promedioporcentaje"+prom_por);
				prom_por++;
				linear_horizontal3.addView(promedioporcentaje);
			break;
				}
			}
			
		
			linear_vertical.addView(linear_horizontal3);
		
		}

		TextView acum = new TextView(this);
		acum.setTag("acumulador");
		linear_vertical.addView(acum);
		return linear_vertical;
		
	}
	public void calcularPuntaje()
	{
		int actual=0,anterior=0;
		int acumulador=0;
        double porciento;
		for(int num_edit=0;num_edit<(3*ejercicios_por_maquina_Selec.size());num_edit++)
		{      
			Log.d("indice",""+num_edit);
			TextView por =(TextView)linear.findViewWithTag("porcentaje"+(num_edit));
			EditText edit=(EditText)linear.findViewWithTag("edit"+num_edit);
			TextView rsp =(TextView)linear.findViewWithTag("text"+(num_edit));
	        Log.d("edit",edit.getText().toString());
	        actual=Integer.parseInt(edit.getText().toString());
			Log.d("text",rsp.getText().toString());
			anterior=Integer.parseInt(rsp.getText().toString());
			 /*
			  * anterior 100
			  * actual   x
			  */
			 if(actual!=0){
				 porciento=Math.abs((100*actual))/anterior;
			 }else{
				 porciento=0;
			 }
			 Log.d("pociento",porciento+"");
			 por.setText(" "+porciento+"%  ");
			 acumulador= (int) (porciento+acumulador);					
		}
		acumulador=acumulador/(3*ejercicios_por_maquina_Selec.size());
		TextView acumul =(TextView)linear.findViewWithTag("acumulador");
		acumul.setText("   progreso="+acumulador+"% ");
		
	}
	/*
	public boolean eventoKeyEditText()
	{
		Log.d("hijo ",linear.toString());
		int promedio=0;
		for(int num_edit=1;num_edit<5;num_edit++)
		{
			EditText edit=(EditText)linear.findViewWithTag("edit"+num_edit);
			Log.d("edit",edit.getText().toString());
			TextView text=(TextView)linear.findViewWithTag("text"+num_edit);
			Log.d("texto ",""+text.getText());
			int valor_edit=0;
			if(edit.getTag().toString().contains(""))
				valor_edit=1;
			else
				valor_edit=Integer.parseInt(edit.getText().toString());
			int res1=(Integer.parseInt(""+text.getText()));
			promedio+=Math.abs((res1/valor_edit))*100;
			Log.d("edit "+edit.getTag()+" valor edit = "+valor_edit,"promedio "+promedio);	
					
		}
		Log.d("puntaje total= ",""+(promedio));
		return true;
	}*/
	private TextView crearTextView(String tag,String texto,int w,int h,int left,int top,int right,int bottom) {
		
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		TextView txt=new TextView(this);
		txt.setLayoutParams(lp);
		txt.setInputType(InputType.TYPE_CLASS_NUMBER);
		txt.setText(texto);
		txt.setTag(tag);
		return txt;
	}

	public ImageView crearImageViewBitmap(Bitmap imagen,int w,int h,int left,int top,int right,int bottom)
	{
	
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		ImageView img=new ImageView(this);
		img.setLayoutParams(lp);
		img.setImageBitmap(imagen);
		return img;
	}
	public ImageView crearImageViewResdid(int resdid,int w,int h,int left,int top,int right,int bottom)
	{
	
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		ImageView img=new ImageView(this);
		img.setLayoutParams(lp);
		img.setImageResource(resdid);
		return img;
	}
	public EditText crearEditText(int w,int h,int left,int top,int right,int bottom)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		EditText edit=new EditText(this);
		edit.setLayoutParams(lp);
		edit.setInputType(InputType.TYPE_CLASS_NUMBER);
		edit.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
		edit.setText("0");
		return edit;
	}
	
	
	public boolean onCreateOptions(Menu menu)
	{
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
		
	}
	
}
