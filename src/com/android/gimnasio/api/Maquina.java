package com.android.gimnasio.api;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Maquina {
	
	private Context context;
	public static final String nombre_tabla="maquina";
	public static final String[] primary_key={"id_maquina","integer primary key AUTOINCREMENT"};
	public static final String[]  nombre={"nombre","text"};
	public static final String[]  tipo_de_maquina={"tipo_de_maquina","text"};
	private static final String[][] columnas={primary_key,nombre,tipo_de_maquina};
	
	public Maquina(Context context) {
		this.context=context;
	}
	public void crearMaquina(int id_maquina,String nombre,String tipo_de_maquina){
		ContentValues values=new ContentValues();
		values.put(Maquina.nombre[0], nombre);
		values.put(Maquina.tipo_de_maquina[0],tipo_de_maquina);
		if(id_maquina!=-1)
			values.put(primary_key[0],id_maquina);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert(Maquina.nombre_tabla, null, values);
		bd.close();
	}
	public void editarMaquina(int id_maquina,ContentValues columnas)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.update(Maquina.nombre_tabla, columnas, primary_key[0]+"='"+id_maquina+"'", null);
		bd.close();
	}
	public String getNombre(int id_maquina)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String nombre=bd.rawQuery("select "+Maquina.nombre[0]+" from "+Maquina.nombre_tabla+" where "+Maquina.primary_key[0]+" = "+id_maquina,null).getString(0);
		bd.close();
		return nombre;
	}
	public String getTipoDeMaquina(int id_maquina)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String nombre=bd.rawQuery("select "+Maquina.tipo_de_maquina[0]+" from "+Maquina.nombre_tabla+" where "+Maquina.primary_key[0]+" = "+id_maquina,null).getString(0);
		bd.close();
		return nombre;
	}

	public ArrayList<Integer> getIdsMaquinas()
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultado=bd.rawQuery("select "+primary_key[0]+" from "+Maquina.nombre_tabla,null);
		ArrayList<Integer> ids_maquinas=new ArrayList<Integer>();
		if (resultado.moveToFirst())
		{
			while(resultado.moveToNext())
			{
				ids_maquinas.add(Integer.parseInt(resultado.getString(0)));
				
			}	
		}
		return ids_maquinas;
	}
	
	public static final HashMap<String, String> getColumnas()
	{
		HashMap<String, String>columnas_usuario=new HashMap<String, String>();
		for(int i=0;i<Maquina.columnas.length;i++)
		{
				columnas_usuario.put(columnas[i][0],columnas[i][1]);
		}
		return columnas_usuario;
	
	}
	public boolean existeMaquina(int id_maquina)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		Cursor resultados = bd.rawQuery("select * from "+Maquina.nombre_tabla+" where "+primary_key[0]+"="+id_maquina, null);
		if (resultados.moveToFirst()){
			Log.d("existe "+Usuario.class,"True");
			return true;
		}	
		Log.d("existe "+Usuario.class,"False");
		return false;
	}
	public String getConsultaToString(String query)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultados = bd.rawQuery(query, null);
		if (resultados.moveToFirst()) 
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
			//bd.close();
			return res;
		}
		//bd.close();
		return "";
			
	}
	public void elminarMaquina(int id_maquina)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+Maquina.nombre_tabla+" where "+primary_key[0]+"="+id_maquina);	
	}



}
