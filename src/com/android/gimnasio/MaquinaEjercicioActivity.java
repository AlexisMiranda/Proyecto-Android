package com.android.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.TipoEjercicio;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MaquinaEjercicioActivity extends Activity {

 
	private int indice_maqui_selec=1;
	private ScrollView scroll;
	private ArrayList<Integer> ids_maquinas_seleccionadas;
	private Maquina maquina;
	private TipoEjercicio tipo_ejercicio;
	private ArrayList<ScrollView> Layout_contenedor;
	HashMap<Integer, ArrayList<Integer>> dias_por_ejercicio;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maquina=new Maquina(this);
        tipo_ejercicio=new TipoEjercicio(this);
        Layout_contenedor=new ArrayList<ScrollView>();
        dias_por_ejercicio=new HashMap<Integer, ArrayList<Integer>>();
        ids_maquinas_seleccionadas=getIntent().getIntegerArrayListExtra("ids_maquinas_seleccionadas");
        /*ids_maquinas_seleccionadas=new ArrayList<Integer>();
        ids_maquinas_seleccionadas.add(2);
        ids_maquinas_seleccionadas.add(3);
        ids_maquinas_seleccionadas.add(4);
        ids_maquinas_seleccionadas.add(5);
        */
        scroll=new ScrollView(this);
        scroll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		scroll.addView(crearContenedor2(ids_maquinas_seleccionadas.get(0)));
		Log.d("pase scroll","pase scroll");
		setContentView(scroll);

	}

	public void onBackPressed() {
		if(indice_maqui_selec==1)
		{
			indice_maqui_selec-=1;
		}else{
			indice_maqui_selec-=2;
		}
		if(indice_maqui_selec>=0)
		{

		Log.d("finalizo layout",""+indice_maqui_selec);

		LinearLayout l=crearContenedor2(ids_maquinas_seleccionadas.get(indice_maqui_selec));
		scroll.removeAllViews();
		scroll.addView(l);
		setContentView(scroll);

		}else{
			this.finish();
		}
		return;
	}
	public void clickEnBoton()
	{
		Log.d("clic","entre en clic");
		if(indice_maqui_selec<ids_maquinas_seleccionadas.size())
		{
			if(validarCampos((LinearLayout)((LinearLayout)scroll.getChildAt(0)).getChildAt(2)))
			{
			Layout_contenedor.add(scroll);
			scroll.removeAllViews();
			scroll.addView(crearContenedor2(ids_maquinas_seleccionadas.get(indice_maqui_selec)));
			Log.d("pase scroll","pase scroll");
			setContentView(scroll);
			indice_maqui_selec+=1;
			}
				
		}else{
					Intent i=new Intent(getApplicationContext(),SeleccionarDiaActivity.class);
					startActivity(i);
			}
			
		
	}
	public LinearLayout crearContenedor2(int id_maquina)
	{
		LinearLayout l1=new LinearLayout(this);
		l1.setOrientation(LinearLayout.VERTICAL);
		l1.addView(crearTitulo(R.drawable.titulo_seleccion_de_maquinas,200,80,100,10,100,0));
		l1.addView(crearImageView(maquina.getImagen(id_maquina, this),200,200,70,0,0,10));
		Log.d("pase crear image con margen","imagen con margenes");
		l1.addView(crearLayout(id_maquina));
		l1.addView(crearBoton());
		return l1;
		
	}
	
	public Button crearBoton()
	{
		Button b=new Button(this);
		b.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		b.setText("SiguienteMaquina");
		b.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				clickEnBoton();				
			}
		});
		return b;
	}

	
	/*Crea un layout con las imagenes de los ejercicios y sus correspondientes checkbox para
	 * que sean seleccionados*/
	public LinearLayout crearLayout(int id_maquina)
	{
		Log.d("Entre en crear linearlayout","id maquina="+id_maquina);
	    ArrayList<Integer> ids_tipo_ejercicios=tipo_ejercicio.getIdsTipoEjercicios(id_maquina);
		Log.d("ids tipos de ejercicios",ids_tipo_ejercicios.toString());

		int num_filas=ids_tipo_ejercicios.size(),indice_imagen=0,indice_checkbox=0;
		LinearLayout linear_vertical=new LinearLayout(this);
		
		linear_vertical.setOrientation(LinearLayout.VERTICAL);
		linear_vertical.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		//int tipos_de_ejercicios
		for(int fila=0;fila<num_filas;fila++)
		{
			LinearLayout linear_horizontal=new LinearLayout(this);
			linear_horizontal.setOrientation(LinearLayout.HORIZONTAL);
			linear_horizontal.setId(ids_tipo_ejercicios.get(indice_imagen));
			linear_horizontal.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT));
			for(int col=0;col<2;col++)
			{
				switch (col) {
				case 0://imagen del ejercicio
					Bitmap imagen=tipo_ejercicio.getImagen(ids_tipo_ejercicios.get(indice_imagen), id_maquina, this);
						ImageView img=crearImageView(imagen,150,200,20,0,0,20);
						img.setId(ids_tipo_ejercicios.get(indice_imagen));
						
						linear_horizontal.addView(img);
						indice_imagen+=1;
					break;
				case 1://checkbox que indica si se quiere realizar o no algun ejercicio
						//si lo selecciona se despliegan las opciones del ejercicio.
					String texto=tipo_ejercicio.getNombre(ids_tipo_ejercicios.get(indice_checkbox));
					int id_ejercicio=ids_tipo_ejercicios.get(indice_checkbox);
					linear_horizontal.addView(crearCheckbox(id_ejercicio,texto, 100, 50, false, 5, 100, 0, 0));
					indice_checkbox+=1;
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

	public ImageView crearImageView(Bitmap imagen,int w,int h,int left,int top,int right,int bottom)
	{
	
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		ImageView img=new ImageView(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		img.setLayoutParams(lp);
		img.setImageBitmap(imagen);
		return img;
	}
	public CheckBox crearCheckbox(int id_ejercicio,String texto,int w,int h,boolean checked,int left,int top,int right,int bottom)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		CheckBox c=new CheckBox(this);
		c.setText(texto);
		c.setTextSize(10);
		c.setTextColor(Color.GRAY);
		c.setChecked(checked);
		Log.d("checbox","checkbox");

		c.setId(id_ejercicio);
		c.setOnClickListener(new View.OnClickListener()
		{
		      @Override
		      public void onClick(View v) 
		      {
		        if (((CheckBox) v).isChecked())
		        {
		        	 Toast.makeText(getBaseContext(),  "checkeado "+v.getId(),Toast.LENGTH_SHORT).show();
	                 
	                	 LinearLayout l=(LinearLayout) v.getParent().getParent();
	            			for(int i=0;i<l.getChildCount();i++)
	            			{
	            				if(l.getChildAt(i).getId()==v.getId())
	            				{
	                			Log.d("crear tabla de datos",""+v.getId());
	                			 l.addView(crearTablaDatos(v.getId(),400, 200, 15, 5, 0, 5), i+1);
	        	                 break;
	            				}
	            			}          	 
	                 
		        }else {
		        		Toast.makeText(getBaseContext(),  "Descheckeado "+v.getId(),Toast.LENGTH_SHORT).show();
		        		LinearLayout l=(LinearLayout) v.getParent().getParent();
		        		for(int i=0;i<l.getChildCount();i++)
		        		{
		        			if(l.getChildAt(i).getId()==v.getId())
		        			{
		        				l.removeViewAt(i+1);	                 
		        			}
		        		}
		       	 
		        	}

		      }
		});
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		c.setLayoutParams(lp);
		return c;
	}
	public TableLayout crearTablaDatos(int id_ejercicio,int w,int h,int left,int top,int right,int bottom)
	{
		TableLayout tabla = new TableLayout(this);
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(width,heigth);
		tableParams.setMargins(left, top, right, bottom);
		int width2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 44, getResources().getDisplayMetrics());
		TableRow.LayoutParams rowParams = new TableRow.LayoutParams(width2, TableRow.LayoutParams.WRAP_CONTENT);
		//se le asigna el id_ejercicio multiplicado por 10,ya que este id ya esta ocupado por otro objeto checkbox
		tabla.setId(id_ejercicio*10);
		Log.d("id tabla",""+tabla.getId());

		//tabla.setId(id_ejercicio);
		String[] texto={"Peso","Repeticiones","Series"};
		String[] dias={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
		for(int i=0;i<4;i++)
		{
			TableRow tableRow = new TableRow(this);
			tableRow.setLayoutParams(tableParams);
			 
			switch (i) {
			case 0://textview que contiene el texto de repeticiones,peso,series
				int cnt=0;
				for(int j=0;j<5;j++)
				{
					if(j%2==0){
						TextView tv=crearTexto(texto[cnt],20,10,0,0,0,0);
						tv.setLayoutParams(rowParams);
						tableRow.addView(tv);
						cnt+=1;
					}else{
						TextView tv=crearTexto("",20,10,0,0,0,0);
						tv.setLayoutParams(rowParams);
						tableRow.addView(tv);
					}
				}
				break;

			case 1://Edit text donde se ingresara el peso,repeticiones y series
				int cnt2=0;
				for(int j=0;j<5;j++)
				{
					if(j%2==0)
					{
						EditText et=crearEditText(cnt2);
						et.setLayoutParams(rowParams);
						tableRow.addView(et);
						cnt2+=1;
					}else{
						TextView tv=crearTexto("",20,10,0,0,0,0);
						tv.setLayoutParams(rowParams);
						tableRow.addView(tv);
					} 
				}
				
				break;
			case 2://7 textview que contiene los dias de la semana lunes-domingo
				for(int j=0;j<7;j++)
				{
					TextView et=crearTexto(dias[j], 10, 5, 1, 5, 0, 5);
					et.setLayoutParams(rowParams);
					tableRow.addView(et);
				}
				
				break;
			case 3://7 edittext que contiene los dias de la semana lunes-domingo
				for(int j=0;j<7;j++)
				{
					CheckBox et=crearCheckboxDias(j, "", 5, 5, false, 1, 5, 0, 5);
					et.setLayoutParams(rowParams);
					tableRow.addView(et);
				}
				
				break;
			}
		tabla.addView(tableRow);	
		}

		return tabla;
	}
	/*Checkbox que representan los dias de la semana del lunes a domingo*/
	@SuppressLint("ShowToast")
	public CheckBox crearCheckboxDias(int id_ejercicio,String texto,int w,int h,boolean checked,int left,int top,int right,int bottom)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		CheckBox c=new CheckBox(this);
		c.setText(texto);
		c.setTextSize(10);
		c.setTextColor(Color.GRAY);
		c.setChecked(checked);
		Log.d("checbox","checkbox");
		c.setId(id_ejercicio);
		c.setOnClickListener(new View.OnClickListener()
		{
		      @Override
		      public void onClick(View v) 
		      {
		    	if(((CheckBox)v).isChecked()){
		    		TableLayout t=(TableLayout)v.getParent().getParent();
		    		int id_ejercicio=t.getId()/10;
		    		Toast.makeText(getApplicationContext(), "Dia seleccionado = "+v.getId()+" en maquina "+tipo_ejercicio.getNombre(id_ejercicio), Toast.LENGTH_LONG).show();
		    		
		    	} else{
		    		TableLayout t=(TableLayout)v.getParent().getParent();
		    		int id_ejercicio=t.getId()/10;
		    		Toast.makeText(getApplicationContext(), "Dia deseleccionado = "+v.getId()+" en maquina "+tipo_ejercicio.getNombre(id_ejercicio), Toast.LENGTH_LONG).show();
		    
		    	} 
		      }
		});
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		c.setLayoutParams(lp);
		return c;
	}
	public TextView crearTexto(String texto,int w,int h,int left,int top,int right,int bottom)
	{
		TextView text=new TextView(this);
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		text.setLayoutParams(lp);
		text.setText(texto);
		text.setTextSize(7);
		text.setTextColor(Color.GRAY);
		return text;
	}
	public boolean validarCampos(LinearLayout nieto_de_scroll)
	{
		String[] texto={"Peso","Repeticiones","Series"};
		//HashMap<Integer, ArrayList<Integer>> 
		
		for(int i=0;i<nieto_de_scroll.getChildCount();i++)//saco los hijos del linearlayout
		{
			View hijo =nieto_de_scroll.getChildAt(i);
			Log.d("Hijo ",hijo.toString());
			if(hijo.toString().contains("TableLayout"))//saco los tablelayouts
			{
				TableLayout t=(TableLayout)hijo;
				for(int ind_row=0;ind_row<t.getChildCount();ind_row++)//recorro los tablerow del tablelayots
				{
					TableRow row=(TableRow)t.getChildAt(ind_row);
					for(int ind_hijo_row=0;ind_hijo_row<row.getChildCount();ind_hijo_row++)
					{
						View hijo_row=row.getChildAt(ind_hijo_row);
						Log.d("Hijo row =",hijo_row.toString());
						if(hijo_row.toString().contains("Edit"))//si es un edittext valido que no este vacio
						{
							
							EditText edit_text=(EditText)hijo_row;
							Log.d("Entre en edit text ="+edit_text.getText().toString(),"Entre en edit text = "+edit_text.getText().toString().equals(""));
							if(edit_text.getText().toString().equals(""))//que no este vacio
							{
								String alerta="Ingresa un valor en "+texto[edit_text.getId()]+" en la maquina"+tipo_ejercicio.getNombre((t.getId()/10));
								Log.d("Esta vacio",alerta);
								Toast.makeText(this,alerta, Toast.LENGTH_LONG).show();
								return false;
							}
						}else if (hijo_row.toString().contains("Check")) 
							{//si es un checkbox
								Log.d("Entre checkbox ="+hijo_row.toString(),"Entre en edit text = checkbox");

							}
					}
				}
			}
		}
		return true;
		
	}

	public EditText crearEditText(int id)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
		EditText edit=new EditText(this);
		edit.setLayoutParams(new LinearLayout.LayoutParams(width,heigth));
		edit.setInputType(InputType.TYPE_CLASS_NUMBER);
		edit.setId(id);
		edit.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
		return edit;
	}
	
	
	
}
