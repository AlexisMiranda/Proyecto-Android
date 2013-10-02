package com.android.gimnasio.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

	private static String nombre_db="gimnasio";
	/*private static String[] columnas_maquina={"id_maquina","nombre","tipo_de_maquina"};
	private static String[] columnas_usuario={"id_usuario","nombre","apellido","sexo",
												"estatura","peso","edad","imc","puntaje"};
												*/
	private HashMap<String, String> columnas_usuario,columnas_maquina;
	
	public AdminSQLiteOpenHelper(Context context,CursorFactory factory, int version) 
	{
		super(context,nombre_db , factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(this.getCreateTable("usuario", this.getColumnasUsuario()));
		db.execSQL(this.getCreateTable("maquina", this.getColumnasMaquina()));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) 
	{
		db.execSQL("drop table if exists maquina");
		db.execSQL("drop table if exists usuario");
		db.execSQL(this.getCreateTable("usuario", this.getColumnasUsuario()));
		db.execSQL(this.getCreateTable("maquina", this.getColumnasMaquina()));
	}
	public HashMap<String, String> getColumnasUsuario()
	{
		/*
		 * Segun el orden en el que se agregan al hash es en e orden en que se mostraran en el 
		 * formulario
		 */
		
		columnas_usuario=new HashMap<String, String>();
		columnas_usuario.put("id_usuario"," integer primary key AUTOINCREMENT");
		columnas_usuario.put("peso","real");
		columnas_usuario.put("edad","integer");
		columnas_usuario.put("estatura","real");
		columnas_usuario.put("apellido","text");
		columnas_usuario.put("sexo","integer");
		columnas_usuario.put("nombre","text");
		columnas_usuario.put("imc","real");
		columnas_usuario.put("puntaje","integer");
		return columnas_usuario;

	}
	public HashMap<String, String> getColumnasMaquina()
	{
		columnas_maquina=new HashMap<String, String>();
		columnas_maquina.put("id_maquina","integer primary key AUTOINCREMENT");
		columnas_maquina.put("nombre","text");
		columnas_maquina.put("tipo_de_maquina","text");
		return columnas_maquina;

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

	public String getNombreDb()
	{
		return nombre_db;
		
	}
}
