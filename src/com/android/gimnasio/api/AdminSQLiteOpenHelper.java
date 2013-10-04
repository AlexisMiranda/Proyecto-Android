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

	private HashMap<String, String> columnas_usuario,columnas_maquina;
	
	public AdminSQLiteOpenHelper(Context context,CursorFactory factory, int version) 
	{
		super(context,nombre_db , factory, version);
		 
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(this.getCreateTable(Maquina.nombre_tabla, Maquina.getColumnas()));
		db.execSQL(this.getCreateTable(Usuario.nombre_tabla, Usuario.getColumnas()));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) 
	{
		db.execSQL("drop table if exists maquina");
		db.execSQL("drop table if exists usuario");
		db.execSQL(this.getCreateTable(Usuario.nombre_tabla, Usuario.getColumnas()));
		db.execSQL(this.getCreateTable(Maquina.nombre_tabla, Maquina.getColumnas()));
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
