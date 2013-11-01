package com.android.gimnasio;

import java.util.ArrayList;

import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.RequerimientoEjercicio;
import com.android.gimnasio.api.TipoEjercicio;
import com.android.gimnasio.api.TipoEjercicioUsuario;
import com.android.gimnasio.api.Usuario;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class EjercicioRealizadoActivity extends Activity {

	
	private LinearLayout linear;//,linear2;
	private Button boton;
	private int numero_de_maquinas,num_maquina_selec,id_usuario_loggeado;
	private String dia;
	private Maquina tabla_maquina;
	private TipoEjercicioUsuario tabla_teu;
	private TipoEjercicio tabla_te;
	private RequerimientoEjercicio tabla_rqe;
	private ArrayList<Integer> ejercicios_por_maquina_Selec;
	private Usuario usuario;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d("++Entro en ",getClass().toString());
		num_maquina_selec=Integer.parseInt(getIntent().getStringExtra("num_maquina_selec"));
		dia=getIntent().getStringExtra("dia");
		setContentView(R.layout.activity_ejercicio_realizado);
		tabla_maquina=new Maquina(this);
		tabla_teu=new TipoEjercicioUsuario(this);
		tabla_te=new TipoEjercicio(this);
		tabla_rqe=new RequerimientoEjercicio(this);
		usuario=new Usuario(this);
		id_usuario_loggeado=usuario.getIdUsuarioLoggeado();
		ejercicios_por_maquina_Selec=tabla_teu.getEjerciciosSeleccionados(num_maquina_selec,dia.substring(0, 3));
		linear=(LinearLayout)findViewById(R.id.linear);
		linear.addView(desloggear());
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
		Button b_comparar_usuarios=new Button(this);
		b_comparar_usuarios.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		b_comparar_usuarios.setText("Ranking de usuarios");
		b_comparar_usuarios.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("entre en en","b_comparar_usuarios");
				Intent i=new Intent(getApplicationContext(),rankingDeUsuariosActivity.class);
				startActivity(i);
			}
		});
		linear.addView(b_comparar_usuarios);

	}

	public LinearLayout crearLayout(int num_filas)
	{ 
		int contedit=0,prom_por=0,id=0;
		LinearLayout linear_vertical=new LinearLayout(this);

		linear_vertical.setOrientation(LinearLayout.VERTICAL);
		linear_vertical.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LinearLayout linear_horizontal1=new LinearLayout(this);
		linear_horizontal1.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.setMargins(110, 10, 2, 5);
		linear_horizontal1.setLayoutParams(lp);
		ArrayList<String> params_ejercicios=tabla_rqe.getNombres(tabla_teu.getIdTipoEjercicioUsuario(ejercicios_por_maquina_Selec.get(0), id_usuario_loggeado, dia.substring(0,3)));
		for(int col=0;col<params_ejercicios.size();col++)
		{
		         TextView t2= new TextView(this);//crearTextViewMathParent("param_"+params_ejercicios.get(col),params_ejercicios.get(col),1, 5, 2, 2);
			    t2.setText(params_ejercicios.get(col)+" ");
			    t2.setTag(params_ejercicios.get(col));
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
						txt.setText(""+tabla_rqe.getValor(tabla_teu.getIdTipoEjercicioUsuario(ejercicios_por_maquina_Selec.get(fila), id_usuario_loggeado, dia.substring(0, 3)), params_ejercicios.get(col)));
						int id_ejercicio=ejercicios_por_maquina_Selec.get(fila);
						int id_tipo_ejercicio_usuario=tabla_teu.getIdTipoEjercicioUsuario(id_ejercicio, id_usuario_loggeado, dia.substring(0,3));
						int valor=Integer.parseInt(tabla_rqe.getValorNew(id_tipo_ejercicio_usuario, params_ejercicios.get(col)));
						Log.d("id_ejercicio",id_ejercicio+"");
						Log.d("id_tipo_ejercicio_usuario",id_tipo_ejercicio_usuario+"");
						Log.d("valor",valor+"");

						EditText edit=crearEditText(valor,40,40,1, 2, 0, 2);

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
		ArrayList<String> params_ejercicios=tabla_rqe.getNombres(tabla_teu.getIdTipoEjercicioUsuario(ejercicios_por_maquina_Selec.get(0), id_usuario_loggeado, dia.substring(0,3)));
		int actual=0,anterior=0,cnt_ejercicio=0,cnt_param=0,num_edit=0;
		double puntaje_por_ejercicio=0;
		int acumulador=0;
        double porciento;
		for(int fila=0;fila<ejercicios_por_maquina_Selec.size();fila++)
		{

			
			for(int col=0;col<params_ejercicios.size();col++)
			{
				TextView por =(TextView)linear.findViewWithTag("porcentaje"+(num_edit));
				EditText edit=(EditText)linear.findViewWithTag("edit"+num_edit);
				TextView rsp =(TextView)linear.findViewWithTag("text"+(num_edit));
				num_edit+=1;
				Log.d("edit",edit.getText().toString());
		        actual=Integer.parseInt(edit.getText().toString());
				Log.d("text",rsp.getText().toString());
				anterior=Integer.parseInt(rsp.getText().toString());
		
				 if(actual!=0){
					 porciento=Math.abs((100*actual))/anterior;
				 }else{
					 porciento=0;
				 }
				 Log.d("pociento",porciento+"");
				 por.setText(" "+porciento+"%  ");
				 acumulador= (int) (porciento+acumulador);	
				 puntaje_por_ejercicio=puntaje_por_ejercicio+porciento;
				 int id_tipo_ejercicio_usuario=tabla_teu.getIdTipoEjercicioUsuario(ejercicios_por_maquina_Selec.get(fila), id_usuario_loggeado, dia.substring(0,3));
				 int id_requerimiento_ejercicio=tabla_rqe.getIdRequerimientoEjercicio(id_tipo_ejercicio_usuario,params_ejercicios.get(col));
				 ContentValues colum=new ContentValues();
				 Log.d("++edit text value",edit.getText().toString());
				 colum.put(RequerimientoEjercicio.valornew_str_4, edit.getText().toString());
				 colum.put(RequerimientoEjercicio.puntaje_float_5, porciento);
				 Log.d("id_tipo_ejercicio_usuario",""+id_tipo_ejercicio_usuario);
				 Log.d("id_requerimiento_ejercicio",""+id_requerimiento_ejercicio);

				 tabla_rqe.editarRequerimiento(id_requerimiento_ejercicio, colum);
				 Log.d("tabla TipoEjercicioUsuario",tabla_teu.getConsultaToString("select * from "+TipoEjercicioUsuario.nombreTabla));
				  Log.d("tabla Requerimiento",tabla_teu.getConsultaToString("select * from "+RequerimientoEjercicio.nombreTabla));
			}
			
		}

		
	}
	public boolean es_multiplo(int a,int b)
	{
		/*
		 * a es multiplo de b
		 * ej: 6 multiplo de 3 =true
		 * 		5 multiplo de 3=false
		 */
		Log.d("es_multiplo",a+" "+b);
		boolean es_mul=false;
		for(int i=1;i<=a;i++)
		{
			if((b*i)==a)
			{
				es_mul=true;
				Log.d("si es multiplo",a+" "+b);
				break;
			}
		}
		return es_mul;
	}
	
	
	
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
	public EditText crearEditText(int valor,int w,int h,int left,int top,int right,int bottom)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		EditText edit=new EditText(this);
		edit.setLayoutParams(lp);
		edit.setInputType(InputType.TYPE_CLASS_NUMBER);
		edit.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
		edit.setText(""+valor);
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
				//lo envio a login activity
				Toast.makeText(getApplicationContext(),"Usuario desloggeado",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(getApplicationContext(), LoginActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		return text;
		
	}
	
	public boolean onCreateOptions(Menu menu)
	{
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
		
	}
	
}
