package com.android.gimnasio.api;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TipoEjercicioUsuario {

	private Context context;
	public static final String nombreTabla="tipoEjercicioUsuario";
	public static final String id_primaryKey_0="id";
	public static final String  repeticiones_int_1="repeticiones";
	public static final String  series_int_2="series";
	public static final String  dia_str_2="dia";
	public static final String  fkte_tipoEjercicio_4="fkte";
	public static final String  fku_usuario_5="fku";
	
	public TipoEjercicioUsuario(Context context)
	{
	this.context=context;	
	}
	public void crearTipoEjercicioUsuario(int id_tipo_ejercicio_usuario,int id_tipo_ejercicio,int id_usuario,int repeticiones,int series,int dia)
	{
		ContentValues values=new ContentValues();
		values.put(TipoEjercicioUsuario.fkte_tipoEjercicio_4, id_tipo_ejercicio);
		values.put(TipoEjercicioUsuario.fku_usuario_5,id_usuario);
		values.put(TipoEjercicioUsuario.repeticiones_int_1, repeticiones_int_1);
		values.put(TipoEjercicioUsuario.series_int_2, series);
		values.put(TipoEjercicioUsuario.dia_str_2, dia);
		if(id_tipo_ejercicio_usuario>=0)
			values.put(TipoEjercicioUsuario.id_primaryKey_0,id_tipo_ejercicio_usuario);
		
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert(TipoEjercicioUsuario.nombreTabla, null, values);
		bd.close();
		;
	}
	
	public int getIdEjercicio(int id_tipo_ejercicio_usuario)
	{
		return Integer.parseInt(getConsultaToString("select "+TipoEjercicioUsuario.fkte_tipoEjercicio_4+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario));
	}
	public int getIdUsuario(int id_tipo_ejercicio_usuario)
	{
		return Integer.parseInt(getConsultaToString("select "+TipoEjercicioUsuario.fku_usuario_5+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario));
	}
	public int getRepeticiones(int id_tipo_ejercicio_usuario)
	{
		return Integer.parseInt(getConsultaToString("select "+TipoEjercicioUsuario.repeticiones_int_1+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario));
	}
	public int getSeries(int id_tipo_ejercicio_usuario)
	{
		return Integer.parseInt(getConsultaToString("select "+TipoEjercicioUsuario.series_int_2+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario));
	}
	public String getDia(int id_tipo_ejercicio_usuario)
	{
		return getConsultaToString("select "+TipoEjercicioUsuario.dia_str_2+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario);
	}
	public static final HashMap<String, String> getColumnas()
	{
		HashMap<String, String>columnas_tipo=new HashMap<String, String>();
		TipoEjercicioUsuario usuario=new TipoEjercicioUsuario(null);
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
		}else if(tipo.contains("tipoEjercicio")){
			return "integer not null REFERENCES "+ TipoEjercicio.nombreTabla+" ( "+TipoEjercicio.id_primaryKey_0+" )";

		}else if(tipo.contains("usuario")){
			return "integer not null REFERENCES "+Usuario.nombreTabla+" ( "+Usuario.id_primaryKey_0+" )";

}
		
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
			;
			resultados.close();		
			return res;
		}
		bd.close();
		;
		resultados.close();
		return "";
			
	}
	public void eliminarTipoEjercicioUsuario(int id_tipo_ejercicio_usuario)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+TipoEjercicioUsuario.nombreTabla+" where "+id_primaryKey_0+"="+id_tipo_ejercicio_usuario);
		;
		bd.close();
	}
}
