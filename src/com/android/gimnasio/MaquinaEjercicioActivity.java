package com.android.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.RequerimientoEjercicio;
import com.android.gimnasio.api.TipoEjercicio;
import com.android.gimnasio.api.TipoEjercicioUsuario;
import com.android.gimnasio.api.Usuario;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
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

@SuppressLint("NewApi")
public class MaquinaEjercicioActivity extends Activity {

 
	private int indice_maqui_selec;
	private ScrollView scroll;
	private ArrayList<Integer> ids_maquinas_seleccionadas,ids_ejercicios_seleccionados;
	private Maquina tabla_maquina;
	private Usuario usuario;
	private TipoEjercicio tabla_tipo_ejercicio;
	private TipoEjercicioUsuario tabla_tipoEjercicio_usuario;
	private RequerimientoEjercicio tabla_requerimiento_ejercicio;
	private ArrayList<ScrollView> Layout_contenedor;
	private HashMap<Integer, ArrayList<Integer>> ejercicios_seleccionados_por_maquina;
	public boolean entro_en_on_create=false;
	private static String[] param_edit_text={"Peso","Repeticiones","Series"};
	private static String[] dias={"Lun","Mar","Mie","Jue","Vie","Sab","Dom"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabla_maquina=new Maquina(this);
        tabla_tipo_ejercicio=new TipoEjercicio(this);
        tabla_tipoEjercicio_usuario=new TipoEjercicioUsuario(this);
        tabla_requerimiento_ejercicio=new RequerimientoEjercicio(this);
        usuario=new Usuario(this);
        ejercicios_seleccionados_por_maquina=new HashMap<Integer, ArrayList<Integer>>();
        Layout_contenedor=new ArrayList<ScrollView>();
        ids_ejercicios_seleccionados=new ArrayList<Integer>();
        ids_maquinas_seleccionadas=getIntent().getIntegerArrayListExtra("ids_maquinas_seleccionadas");
        entro_en_on_create=true;
        indice_maqui_selec=0;
        scroll=new ScrollView(this);
        scroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		scroll.addView(crearContenedor2(ids_maquinas_seleccionadas.get(indice_maqui_selec)));
		Log.d("pase scroll","pase scroll");
		setContentView(scroll);

	}

	public void onBackPressed() {

        //indice_maqui_selec=(indice_maqui_selec==1)?(indice_maqui_selec-1):(indice_maqui_selec-2);
		if(indice_maqui_selec==ids_maquinas_seleccionadas.size())
			indice_maqui_selec-=2;
		else
		indice_maqui_selec-=1;
		if(indice_maqui_selec>=0)
		{

		Log.d("finalizo layout",""+indice_maqui_selec);
		eliminarDatosEnBd(ids_maquinas_seleccionadas.get(indice_maqui_selec));
		LinearLayout l=crearContenedor2(ids_maquinas_seleccionadas.get(indice_maqui_selec));
		scroll.removeAllViews();
		scroll.addView(l);
		setContentView(scroll);

		}else{
			this.finish();
		}
		return;
	}
	
	public void onResume()
	{
		super.onResume();


	}
	public void onPause()
	{
		super.onPause();
		Log.d("entro en ","pause");
		indice_maqui_selec-=1;
		if(indice_maqui_selec>=0)
		{
			this.ejercicios_seleccionados_por_maquina.remove(ids_maquinas_seleccionadas.get(indice_maqui_selec));
			ids_ejercicios_seleccionados.clear();//borra todos los elementos del array que contiene los ejercicios seleccionados
			scroll.removeAllViews();//borra todos los hijos de scroll
			scroll.addView(crearContenedor2(ids_maquinas_seleccionadas.get(indice_maqui_selec)));
			setContentView(scroll);
			
		}
		Log.d("salgo en ","pause");

		
	}
	public void clickEnBoton()
	{
		Log.d("clic","entre en clic");
		if(validarCampos(ids_maquinas_seleccionadas.get(indice_maqui_selec)))
		{
			indice_maqui_selec+=1;
			if(indice_maqui_selec<ids_maquinas_seleccionadas.size())
			{				

				//ingreo la maquina y los ejercicios realizados por maquina
				this.ejercicios_seleccionados_por_maquina.put(ids_maquinas_seleccionadas.get(indice_maqui_selec-1),new ArrayList<Integer>(ids_ejercicios_seleccionados));
				ids_ejercicios_seleccionados.clear();//borra todos los elementos del array que contiene los ejercicios seleccionados
				scroll.removeAllViews();//borra todos los hijos de scroll
				scroll.addView(crearContenedor2(ids_maquinas_seleccionadas.get(indice_maqui_selec)));
				setContentView(scroll);
				
			}else{
					Intent i=new Intent(getApplicationContext(),SeleccionarDiaActivity.class);
					i.putExtra("ultima_maquina_seleccionada",""+ids_maquinas_seleccionadas.get((indice_maqui_selec-1)));
					startActivity(i);
			}
		}
			
		
	}
	public LinearLayout crearContenedor2(int id_maquina)
	{
		LinearLayout l1=new LinearLayout(this);
		l1.setOrientation(LinearLayout.VERTICAL);
		l1.addView(desloggear());
		TextView titulo=new TextView(this);
		titulo.setText("Selecciona Ejercicios");
		LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(30, 5, 5, 5);
		titulo.setLayoutParams(lp);
		titulo.setTextColor(Color.parseColor("#210B61"));
		titulo.setTextSize(25);
		l1.addView(titulo);
		l1.addView(crearImageView(tabla_maquina.getImagen(id_maquina, this),200,200,40,0,40,5));
		l1.addView(crearLayout(id_maquina));
		l1.addView(crearBoton());
		return l1;
		
	}
	
	public Button crearBoton()
	{
		Button b=new Button(this);
		b.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		b.setText("Siguiente Maquina");
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
	    ArrayList<Integer> ids_tipo_ejercicios=tabla_tipo_ejercicio.getIdsTipoEjercicios(id_maquina);

		int num_filas=ids_tipo_ejercicios.size(),indice_imagen=0,indice_checkbox=0;
		LinearLayout linear_vertical=new LinearLayout(this);
		
		linear_vertical.setOrientation(LinearLayout.VERTICAL);
		linear_vertical.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		//int tipos_de_ejercicios
		for(int fila=0;fila<num_filas;fila++)
		{
			LinearLayout linear_horizontal=new LinearLayout(this);
			linear_horizontal.setOrientation(LinearLayout.VERTICAL);
			linear_horizontal.setId(ids_tipo_ejercicios.get(indice_imagen));
			linear_horizontal.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT));
			for(int col=0;col<2;col++)
			{
				switch (col) {
					case 0:	//checkbox que indica si se quiere realizar o no algun ejercicio
							//si lo selecciona se despliegan las opciones del ejercicio.
							String texto=tabla_tipo_ejercicio.getNombre(ids_tipo_ejercicios.get(indice_checkbox));
							int id_ejercicio=ids_tipo_ejercicios.get(indice_checkbox);
							linear_horizontal.addView(crearCheckbox(id_ejercicio,texto, 250, 50, false, 4, 1, 0, 0));
							indice_checkbox+=1;
							break;
					case 1:		//imagen del ejercicio
							Bitmap imagen=tabla_tipo_ejercicio.getImagen(ids_tipo_ejercicios.get(indice_imagen), id_maquina, this);
							ImageView img=crearImageView(imagen,300,150,1,1,1,5);
							img.setId(ids_tipo_ejercicios.get(indice_imagen));
							linear_horizontal.addView(img);
							indice_imagen+=1;
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
		String texto_parseado=texto.toLowerCase();
		c.setText(texto_parseado.substring(0, 1).toUpperCase()+texto_parseado.substring(1));
		c.setTextSize(15);
		c.setTextColor(Color.GRAY);
		c.setChecked(checked);
		c.setId(id_ejercicio);
		c.setOnClickListener(new View.OnClickListener()
		{
		      @Override
		      public void onClick(View v) 
		      {
		        if (((CheckBox) v).isChecked())
		        {
		        	 //Toast.makeText(getBaseContext(),  "checkeado "+v.getId(),Toast.LENGTH_SHORT).show();
	                	 LinearLayout l=(LinearLayout) v.getParent().getParent();
	                	 
	            			for(int i=0;i<l.getChildCount();i++)
	            			{
	            				if(l.getChildAt(i).getId()==v.getId())
	            				{
	                			ids_ejercicios_seleccionados.add(v.getId());//selecciona el ejercicio
	                			 l.addView(crearTablaDatos(v.getId(), 5, 0, 2, 1), i+1);
	        	                 break;
	            				}
	            			}          	 
	                 
		        }else {
		        		//Toast.makeText(getBaseContext(),  "Descheckeado "+v.getId(),Toast.LENGTH_SHORT).show();
		        		LinearLayout l=(LinearLayout) v.getParent().getParent();
		        		for(int i=0;i<l.getChildCount();i++)
		        		{
		        			if(l.getChildAt(i).getId()==v.getId())
		        			{
		        				l.removeViewAt(i+1);
				        		ids_ejercicios_seleccionados.remove((Object)v.getId());

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
	public LinearLayout crearTablaDatos(int id_ejercicio,int left,int top,int right,int bottom)
	{
		Log.d(""+this.getClass().toString(),"crearTablaDatos(int id_ejercicio,int left,int top,int right,int bottom)");
		LinearLayout tabla = new LinearLayout(this);
		tabla.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		tableParams.setMargins(left, top, right, bottom);		
		//se le asigna el id_ejercicio multiplicado por 10,ya que este id ya esta ocupado por otro objeto checkbox
		tabla.setId(id_ejercicio*10);
		tabla.setLayoutParams(tableParams);
		for(int i=0;i<4;i++)
		{
			LinearLayout tableRow = new LinearLayout(this);
			tableRow.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			tableRow.setLayoutParams(lp);
			 
			switch (i) {
					case 0://textview que contiene el texto de repeticiones,peso,series
							for(int j=0;j<3;j++)
							{
									TextView tv=crearTexto(param_edit_text[j],100,20,1,0,0,0);
									tableRow.addView(tv);
							}
							break;

					case 1://Edit text donde se ingresara el peso,repeticiones y series
							for(int j=0;j<3;j++)
							{
									EditText et=crearEditText(id_ejercicio,param_edit_text[j], 100,40,1,0,0,0);
									tableRow.addView(et);
							}
							
							break;
					case 2://7 textview que contiene los dias de la semana lunes-domingo
							for(int j=0;j<7;j++)
							{
								TextView et=crearTexto(dias[j], 40, 20, 3, 5, 0, 0);
								tableRow.addView(et);
							}
							
							break;
					case 3://7 edittext que contiene los dias de la semana lunes-domingo
							for(int j=0;j<7;j++)
							{
								CheckBox et=crearCheckboxDias(id_ejercicio,dias[j], 40, 40, false, 3, 5, 0, 0);
								tableRow.addView(et);
							}
				
							break;
				}
		tabla.addView(tableRow);	
		}

		return tabla;
	}
	/*Checkbox que representan los dias de la semana del lunes a domingo*/
	public CheckBox crearCheckboxDias(int id_ejercicio,String texto,int w,int h,boolean checked,int left,int top,int right,int bottom)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		CheckBox c=new CheckBox(this);
		//c.setText(texto);
		c.setTextSize(10);
		c.setTextColor(Color.GRAY);
		c.setChecked(checked);
		c.setTag(texto+id_ejercicio);
		c.setOnClickListener(new View.OnClickListener()
		{
		      @Override
		      public void onClick(View v) 
		      {
		    	if(((CheckBox)v).isChecked()){
		    		LinearLayout t=(LinearLayout)v.getParent().getParent();
		    		int id_ejercicio=t.getId()/10;
		    		String mensaje= "Dia seleccionado = "+(v.getTag()+"").replaceAll("[0-9]","")+" en maquina "+tabla_tipo_ejercicio.getNombre(id_ejercicio);
		    		//Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG).show();
		    	} else{
		    		LinearLayout t=(LinearLayout)v.getParent().getParent();
		    		int id_ejercicio=t.getId()/10;
		    		//Toast.makeText(getApplicationContext(), "Dia deseleccionado = "+v.getId()+" en maquina "+tabla_tipo_ejercicio.getNombre(id_ejercicio), Toast.LENGTH_LONG).show();
		    
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
		text.setTextSize(15);
		text.setTextColor(Color.GRAY);
		return text;
	}
	public boolean validarCampos(int id_maquina)
	{
		/*
		 * -Valida que el usuario seleccione un ejercicio por lo menos
		 * -Valida que el o los ejercicios seleccionados no posean los campos(parametros) edit text vacios
		 * -Valida que seleccione por lo menos un dia en el checkbox por maquina seleccionada
		 */

		HashMap<Integer, ArrayList<String>> dias_selec_por_ejer=new HashMap<Integer, ArrayList<String>>();
		HashMap<Integer, ArrayList<String>> param_ingre_por_ejer=new HashMap<Integer, ArrayList<String>>();
		Log.d("validar maquina ","id maquina "+id_maquina);
		if(ids_ejercicios_seleccionados.size()==0)//valida que un ejercicio este seleccionado
		{
			Toast.makeText(this,"Seleccione por lo menos un ejercicio",Toast.LENGTH_SHORT).show();
			return false;
		}else{
			for(int id_ejercicio:ids_ejercicios_seleccionados)//por cada ejercicio seleccionado
			{
				ArrayList<String> param_ingresados=new ArrayList<String>();
				//busco los textos segun los ejercicios
					for(String param:param_edit_text){
						//saco los parametros edit text
						EditText parm_edit_text=(EditText)scroll.findViewWithTag(param+id_ejercicio);
						if(parm_edit_text.getText().toString().length()==0){
							String alerta="Ingresa un valor en "+param+" en el ejercicio "+tabla_tipo_ejercicio.getNombre(id_ejercicio);
							Toast.makeText(this,alerta,Toast.LENGTH_SHORT).show();
							return false;
						}
						param_ingresados.add(parm_edit_text.getText().toString());
					}
					ArrayList<String> dias_selecionados=new ArrayList<String>();
				//busco los checkbox dias seleccionados 
					for(String dia:dias)
					{
						//saco los parametros edit text
						CheckBox dia_check_box=(CheckBox)scroll.findViewWithTag(dia+id_ejercicio);
						if(dia_check_box.isChecked())
						{
							dias_selecionados.add(dia);
						}	
					}
					//valido que por lo menos un checbox dia este seleccionado
					if(dias_selecionados.size()==0)
						{
						String alerta="Selecciona por lo menos un dia en el ejercicio "+tabla_tipo_ejercicio.getNombre(id_ejercicio);
						Toast.makeText(this,alerta,Toast.LENGTH_SHORT).show();
						return false;
						}
					dias_selec_por_ejer.put(id_ejercicio, dias_selecionados);
					param_ingre_por_ejer.put(id_ejercicio, param_ingresados);
			}
	
		}
	   this.insertarDatosEnBd(dias_selec_por_ejer, param_ingre_por_ejer, id_maquina);
		return true;
		
	}

	public void insertarDatosEnBd(HashMap<Integer, ArrayList<String>>dias_selec_por_ejer,HashMap<Integer, ArrayList<String>> param_ingre_por_ejer, int id_maquina)
	{
		/*	Por cada ejercicio seleccionado inserto los parametros del ejercicio como peso,repeticiones y
			los dias seleccionados para realizar esos ejercicios
		*/
		int id_usuario_loggeado=usuario.getIdUsuarioLoggeado();
		Iterator<Integer> ejercicios= dias_selec_por_ejer.keySet().iterator();
		while(ejercicios.hasNext())
		{
			int id_ejercicio=ejercicios.next();
			for(String dia:dias_selec_por_ejer.get(id_ejercicio))
			{
				tabla_tipoEjercicio_usuario.crearTipoEjercicioUsuario(0, id_ejercicio, id_usuario_loggeado,dia);
				ArrayList<String> array_param=param_ingre_por_ejer.get(id_ejercicio);
				int id_tipo_ejercicio_usuario=tabla_tipoEjercicio_usuario.getUltimoIdInsertado();
				for(int ind=0;ind<array_param.size();ind++)
				{					
					tabla_requerimiento_ejercicio.crearRequerimiento(0, id_tipo_ejercicio_usuario,param_edit_text[ind], array_param.get(ind));
				}
			}
		}
		Log.d("tabla tipo_ejercicio_usuario",tabla_tipoEjercicio_usuario.getConsultaToString("select * from "+TipoEjercicioUsuario.nombreTabla));
		Log.d("tabla requerimientos ",tabla_requerimiento_ejercicio.getConsultaToString("select * from "+RequerimientoEjercicio.nombreTabla));

	}
public void eliminarDatosEnBd(int id_maquina)
{
/*
 * Metodo que elimina todos los parametros de los ejercicios para esta maquina,elimina:
 *  - dado un id maquina debe encontrar todos los ejercicios seleccionados de esa maquina
 *  -Por cada ejercicio seleccionado encontrar todos los id tipo ejercicio usuario que coincidan con
 *  el id tipo ejercicio
 *  -para cada uno de los id tipo ejercicio usuario, eliminar los registros que coincidan con la tabla requerimiento
 *  ejercicio.

 */
	int id_usuario_loggeado=usuario.getIdUsuarioLoggeado();
	Log.d(this.getClass().toString(),"eliminarDatosEnBd(int id_maquina)");
	ArrayList<Integer> ids_tipo_ejercicios=this.ejercicios_seleccionados_por_maquina.get(id_maquina);
	for(int id_tipo_ejer:ids_tipo_ejercicios)
	{
		ArrayList<Integer> ids_tipo_ejercicio_usuario=tabla_tipoEjercicio_usuario.getIdsTypeEjerUserByTypeEjer(id_tipo_ejer);
		for(int id_tipo_ejer_user:ids_tipo_ejercicio_usuario)
		{
			tabla_requerimiento_ejercicio.eliminarReqEjerPorTipoEjerUser(id_tipo_ejer_user);
		}
		tabla_tipoEjercicio_usuario.eliminarTipoEjerUsuarioPorTipoEjer(id_tipo_ejer,id_usuario_loggeado);
	}
}
	public EditText crearEditText(int id,String texto,int w,int h,int left,int top,int right,int bottom)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		EditText edit=new EditText(this);
		edit.setLayoutParams(lp);
		edit.setInputType(InputType.TYPE_CLASS_NUMBER);
		edit.setTag(texto+id);
		edit.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
		return edit;
	}

	public TextView desloggear()
	{
		TextView text=new TextView(this);
		Usuario u=new Usuario(this);
		int id_usuario_loggeado=u.getIdUsuarioLoggeado();
		String nombre=u.getNombre(id_usuario_loggeado);
		text.setText(nombre.substring(0,1).toUpperCase()+nombre.substring(1)+"\tX Cerrar Sesion");
		LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		lp.setMargins(100, 5, 0, 5);
		text.setLayoutParams(lp);
		text.setTextSize(18);
		text.setTextColor(Color.parseColor("#DF0101"));
		text.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//elimino todas las maquinas seleccionadas
				for(int ids:ids_maquinas_seleccionadas)
				{
				eliminarDatosEnBd(ids);
				}
				//lo envio a login activity
				Toast.makeText(getApplicationContext(),"Usuario desloggeado",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(getApplicationContext(), LoginActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		return text;
		
	}
}

