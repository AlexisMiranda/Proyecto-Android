package com.android.gimnasio.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RequerimientoEjercicio {

	
	private Context context;
	public static final String nombreTabla="requerimientoEjercicio";
	public static final String id_primaryKey_0="id";
	public static final String  fkteu_tipoEjercicioUsuario_3="fkteu";
	public static final String  nombre_str_1="nombre";
	public static final String  valor_str_2="valor";
	
	public RequerimientoEjercicio(Context context)
	{
		this.context=context;
	}
	public void crearRequerimiento(int id_requerimiento_ejercicio,int id_tipo_ejercicio_usuario,String nombre,String valor)
	{
		ContentValues values=new ContentValues();
		values.put(RequerimientoEjercicio.nombre_str_1, nombre);
		values.put(RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3,id_tipo_ejercicio_usuario);
		values.put(RequerimientoEjercicio.valor_str_2, valor);
		if(id_requerimiento_ejercicio>=0)
			values.put(RequerimientoEjercicio.id_primaryKey_0,id_requerimiento_ejercicio);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert(RequerimientoEjercicio.nombreTabla, null, values);
		bd.close();
		
	}
	public void editarRequerimiento(int id_requerimiento_ejercicio,ContentValues columnas)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.update(RequerimientoEjercicio.nombreTabla, columnas, RequerimientoEjercicio.id_primaryKey_0+"='"+id_requerimiento_ejercicio+"'", null);
		bd.close();
	}
	
	public int getIdTipoEjercicioUsuario(int id_requerimiento_ejercicio)
	{
		return Integer.parseInt(getConsultaToString("select "+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+
													" from "+RequerimientoEjercicio.nombreTabla+
													" "+"where "+RequerimientoEjercicio.id_primaryKey_0+"="+id_requerimiento_ejercicio));	
	}
	public String getNombre(int id_requerimiento_ejercicio)
	{
		return getConsultaToString("select "+RequerimientoEjercicio.nombre_str_1+
				" from "+RequerimientoEjercicio.nombreTabla+
				" "+"where "+RequerimientoEjercicio.id_primaryKey_0+"="+id_requerimiento_ejercicio);	
	}
	public String getValor(int id_requerimiento_ejercicio)
	{
		return getConsultaToString("select "+RequerimientoEjercicio.valor_str_2+
				" from "+RequerimientoEjercicio.nombreTabla+
				" "+"where "+RequerimientoEjercicio.id_primaryKey_0+"="+id_requerimiento_ejercicio);
	}

	public static final HashMap<String, String> getColumnas()
	{
		HashMap<String, String>columnas_tipo=new HashMap<String, String>();
		RequerimientoEjercicio usuario=new RequerimientoEjercicio(null);
		HashMap<Integer, String> columas_ordenadas=new HashMap<Integer, String>();
		for(Field columna : usuario.getClass().getDeclaredFields())
		{
			if(columna.getName().lastIndexOf("_")!=-1 )
			{
				int indice=columna.getName().lastIndexOf("_");

					columas_ordenadas.put(Integer.parseInt(columna.getName().substring(indice+1)), columna.getName().substring(0,indice));
					Log.d("columna = ",columna.getName());
				
			}
		}
		Log.d("Lista de llaves ",columas_ordenadas.toString());
		List<Integer> keys = new ArrayList<Integer>(columas_ordenadas.keySet());
		for(int i=keys.size()-1;i>=0;i--)
		{
			String columna=columas_ordenadas.get(keys.get(i));
			if(columna.indexOf("_")!=-1 )
			{
			
				int indice=columna.indexOf("_");
				Log.d(columna.substring(0,indice),getTipoDeDato(columna.substring(indice+1)));
				columnas_tipo.put(columna.substring(0,indice),getTipoDeDato(columna.substring(indice+1)));
			
			}
		}

		return columnas_tipo;
 
	}
	public static final String getTipoDeDato(String tipo)
	{

		if(tipo.contains("int"))
		{
			return "integer";
		}else if (tipo.contains("str")) {
			return  "text";
		}else if (tipo.contains("float")) {
			return  "real";
		}else if(tipo.contains("primaryKey")){
			return "integer primary key AUTOINCREMENT";
		}else if(tipo.contains("tipoEjercicioUsuario")){
			return "integer  REFERENCES "+TipoEjercicioUsuario.nombreTabla+"("+TipoEjercicioUsuario.id_primaryKey_0+")";
			//return "integer,FOREIGN KEY("+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+") REFERENCES "+
				//				TipoEjercicioUsuario.nombreTabla+"("+TipoEjercicioUsuario.id_primaryKey_0+")";
		}
		Log.d("gettipodedato ***","");
		
		return "";
		
	}
	public String getConsultaToString(String query)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultados = bd.rawQuery(query, null);
		if (resultados.getCount()>0)  
		{
			String res="";
			while(resultados.moveToNext())
			{
			for(int i=0;i<resultados.getColumnCount();i++)
				{			
				res+=resultados.getString(i)+"\t";
				}
			res+="\n";
			}
			bd.close();
			return res;
		}
		bd.close();
		return "";
			
	}
	public void eliminarRequerimientoEjercicio(int id_requerimiento_ejercicio)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+RequerimientoEjercicio.nombreTabla+" where "+id_primaryKey_0+"="+id_requerimiento_ejercicio);
	}
	
}
