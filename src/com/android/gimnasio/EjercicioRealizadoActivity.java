package com.android.gimnasio;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class EjercicioRealizadoActivity extends Activity {

	int acumulador=0;
	private LinearLayout linear;//,linear2;
	private Button boton;
	private int numero_de_maquinas;
	//private ScrollView scroll;
	//private ScrollView scroll2;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ejercicio_realizado);
		//scroll=(ScrollView)findViewById(R.id.scrollView1);
		//scroll2=(ScrollView)findViewById(R.id.scrollView2);
		linear=(LinearLayout)findViewById(R.id.linear);
		//linear2=new LinearLayout(this);
		//linear2.setOrientation(LinearLayout.VERTICAL);
		linear.addView(crearImageViewConMargen(R.drawable.titulo_seleccion_de_maquinas,200,80,100,10,100,0));
		ImageView imagen_maquina=crearImageViewConMargen(R.drawable.muscle_home,200,200,70,0,0,0);
		imagen_maquina.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				    
						//Log.d("hijo ",linear.toString());
						for(int num_edit=1;num_edit<16;num_edit++)
						{      
							   int res1=0,res2=0;
						        
						        float porciento;
							
							TextView por =(TextView)linear.findViewWithTag("porcentaje"+(num_edit));
							EditText edit=(EditText)linear.findViewWithTag("edit"+num_edit);
							TextView rsp =(TextView)linear.findViewWithTag("text"+(num_edit-1));
							
						
							res1=Integer.parseInt(edit.getText().toString());
							 res2=Integer.parseInt(rsp.getText().toString());
							Log.d("edittag "+edit.getTag()," portag = "+por.getTag());
							 Log.d("res1 "+res1," res2 = "+res2);	
							 
							 porciento=((float)res1*(float)100)/(float)res2;	 
							 por.setText(" "+(int)porciento+"%  ");
							
							 acumulador= (int) (porciento+acumulador);
							 
							 //edit.getText().toString().
							
							//Log.d("edit "+edit.getTag(),"valor edit = "+edit.getText().toString());						
						}
						acumulador=acumulador/15;
						TextView acumul =(TextView)linear.findViewWithTag("acumulador");
						acumul.setText("progreso="+acumulador+"% ");
			/*for(int i=1;i<4;i++){
				
				TextView promediopor=(TextView)linear.findViewWithTag("promedioporcentaje"+i);
				
				
				promediopor.setText(text);
			}	
			*/
			
			}
		});
		linear.addView(imagen_maquina);
		linear.addView(crearLayout(5));
		boton=crearBoton();
		linear.addView(boton);
		boton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				boton.setText("hola");
					
			}
		});
		
	}
	public Button crearBoton()
	{
		Button b=new Button(this);
		b.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		b.setText("Anterior");
		return b;
	}

	public LinearLayout crearLayout(int num_filas)
	{  int contedit=0;
		LinearLayout linear_vertical=new LinearLayout(this);
		TextView txtV= new TextView(this);
		EditText edito=new EditText(this);
		String sprueba;
		linear_vertical.setOrientation(LinearLayout.VERTICAL);
		linear_vertical.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		//-------------------------------------------------------------------------------------
		LinearLayout linear_horizontal1=new LinearLayout(this);
		linear_horizontal1.setOrientation(LinearLayout.HORIZONTAL);
		linear_horizontal1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		for(int col=0;col<4;col++){
			switch (col) {
			case 0:
			      TextView t= new TextView(this);
			      t.setText("                        peso   ");
				  linear_horizontal1.addView(t);
		    break;
			case 1:
		         TextView t1= new TextView(this);
		         t1.setText("  repeticion   ");
			    linear_horizontal1.addView(t1);
	    break;
			case 2:
		         TextView t2= new TextView(this);
		         t2.setText("   series   ");
			    linear_horizontal1.addView(t2);
	    break;
			case 3:
		         TextView t3= new TextView(this);
		         t3.setText("   dia%");
			    linear_horizontal1.addView(t3);
	    break;
			}
		}
		
		linear_vertical.addView(linear_horizontal1);
		int id=1;
		int idd=1;
		for(int fila=0;fila<num_filas;fila++)
		{
			LinearLayout linear_horizontal=new LinearLayout(this);
			linear_horizontal.setOrientation(LinearLayout.HORIZONTAL);
			linear_horizontal.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
			
			for(int col=0;col<4;col++)
			{
				switch (col) {
				case 0:
						ImageView img=crearImageView(R.drawable.ic_launcher,60,60);
						linear_horizontal.addView(img);
					break;
					
					
				
					 
				
					
				default:
						TextView txt=crearTextView(contedit);
						EditText edit=crearEditText();
						/*edit.setOnKeyListener(new View.OnKeyListener() {
							
							@Override
							public boolean onKey(View v, int keyCode, KeyEvent event) {
								if(event.getAction()==KeyEvent.ACTION_DOWN)
								eventoKeyEditText();
								return false;
							}
						});*/
					    contedit++;
						//edit.setId(contedit);
						edit.setTag("edit"+contedit);
						//edit.setText("");
						edit.setInputType(InputType.TYPE_CLASS_NUMBER);
						linear_horizontal.addView(txt);
						linear_horizontal.addView(edit);
						break;
				
				
				
				
				}
			}
			
			
			
			
			
			
			
			linear_vertical.addView(linear_horizontal);
			
			
			
			LinearLayout linear_horizontal2=new LinearLayout(this);
			linear_horizontal2.setOrientation(LinearLayout.HORIZONTAL);
			linear_horizontal2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
				for(int i=0;i<4;i++){
					switch(i) {
					case 0:
						TextView por = new TextView(this);
						por.setText("Porcentaje   ");
						linear_horizontal2.addView(por);
						
						break;
						
					default:
							
					TextView porcentaje = new TextView(this);
					int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
					int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
					System.out.print("hola");
					porcentaje.setLayoutParams(new LinearLayout.LayoutParams(width,heigth));
					porcentaje.setInputType(InputType.TYPE_CLASS_NUMBER);
					//txt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
					//porcentaje.setText("90%");
					porcentaje.setTag("porcentaje"+id);
					id++;
					linear_horizontal2.addView(porcentaje);
				break;
					}
				}
				
		linear_vertical.addView(linear_horizontal2);
		
		
		
		LinearLayout linear_horizontal3=new LinearLayout(this);
		linear_horizontal3.setOrientation(LinearLayout.HORIZONTAL);
		linear_horizontal3.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
			for(int i=0;i<1;i++){
				switch(i) {
				case 0:
					TextView por = new TextView(this);
					por.setText("Promedio/Puntaje   ");
					linear_horizontal3.addView(por);
					
					break;
					
				default:
						
				TextView promedioporcentaje = new TextView(this);
				int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
				int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
				
				promedioporcentaje.setLayoutParams(new LinearLayout.LayoutParams(width,heigth));
				promedioporcentaje.setInputType(InputType.TYPE_CLASS_NUMBER);
				//txt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
				//porcentaje.setText("90%");
				promedioporcentaje.setTag("promedioporcentaje"+idd);
				idd++;
				linear_horizontal3.addView(promedioporcentaje);
			break;
				}
			}
			
		
		
		
		}
		
		
		//edito.
		//sprueba=edito.getText().toString();
		
		//txtV.setText(sprueba);
		
		//linear_vertical.addView(txtV);
		TextView acum = new TextView(this);
		acum.setTag("acumulador");
		linear_vertical.addView(acum);
		return linear_vertical;
		
	}
	
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
	}
	private TextView crearTextView(int id) {
		
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
		TextView txt=new TextView(this);
		txt.setLayoutParams(new LinearLayout.LayoutParams(width,heigth));
		txt.setInputType(InputType.TYPE_CLASS_NUMBER);
		//txt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
		txt.setText("15");
		txt.setTag("text"+id);
		return txt;
		
		
		
	}
	public ImageView crearImageViewConMargen(int resdid,int w,int h,int left,int top,int right,int bottom)
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
	public ImageView crearImageView(int resdid,int w,int h)
	{
	
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		ImageView img=new ImageView(this);
		img.setLayoutParams(new LinearLayout.LayoutParams(width, heigth));
		img.setImageResource(resdid);
		return img;
	}
	public EditText crearEditText()
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
		EditText edit=new EditText(this);
		edit.setLayoutParams(new LinearLayout.LayoutParams(width,heigth));
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
