package com.android.gimnasio.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

	private static String nombre_db="gimnasio";
	private static String[] columnas_maquina={"id_maquina","nombre","tipo_de_maquina"};
	private static String[] columnas_usuario={"id_usuario","nombre","apellido","sexo",
												"estatura","peso","edad","imc","puntaje"};
	private HashMap<String, String> columnas_usuario2,columnas_maquina2;
	
	public AdminSQLiteOpenHelper(Context context,CursorFactory factory, int version) 
	{
		super(context,nombre_db , factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(this.getCreateMaquina());
		db.execSQL(this.getCreateUsuario());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) 
	{
		db.execSQL("drop table if exists maquina");
		db.execSQL("drop table if exists usuario");
		db.execSQL(this.getCreateMaquina());
		db.execSQL(this.getCreateUsuario());
	}
	public HashMap<String, String> getColumnasUsuario2()
	{
		columnas_usuario2=new HashMap<String, String>();
		columnas_usuario2.put("id_usuario"," integer primary key AUTOINCREMENT");
		columnas_usuario2.put("nombre","text");
		columnas_usuario2.put("apellido","text");
		columnas_usuario2.put("sexo","integer");
		columnas_usuario2.put("edad","integer");
		columnas_usuario2.put("estatura","real");
		columnas_usuario2.put("peso","real");
		columnas_usuario2.put("imc","real");
		columnas_usuario2.put("puntaje","integer");
		return columnas_usuario2;

	}
	public HashMap<String, String> getColumnasMaquina2()
	{
		columnas_maquina2=new HashMap<String, String>();
		columnas_maquina2.put("id_maquina","integer primary key AUTOINCREMENT");
		columnas_maquina2.put("nombre","text");
		columnas_maquina2.put("tipo_de_maquina","text");
		return columnas_maquina2;

	}
	public String getCreateTable(String tabla,HashMap<String,String> columnas)
	{
		Iterator<String> clave_iterador=columnas.keySet().iterator();
		String create_table="create table "+tabla+" ( ";
		while(clave_iterador.hasNext())
		{
			String clave=clave_iterador.next();
		create_table+=clave+" "+columnas.get(clave)+",";	
		}
		create_table=create_table.substring(0, create_table.length()-1)+")";
		return create_table;
	}
	public String getCreateMaquina()
	{
		return "create table maquina(id_maquina integer primary key AUTOINCREMENT,nombre text,tipo_de_maquina text)";	
	}
	public String getCreateUsuario()
	{
		return "create table usuario(id_usuario integer primary key AUTOINCREMENT,nombre text,apellido text," +
				"sexo integer,edad integer,estatura real,peso real,imc real,puntaje integer)";	
	}
	public String[] getColumnasUsuario()
	{
		return columnas_usuario;
	}
	public String[] getColumnasMaquina()
	{
		return columnas_maquina;
	}
	public String getNombreDb()
	{
		return nombre_db;
		
	}
}
