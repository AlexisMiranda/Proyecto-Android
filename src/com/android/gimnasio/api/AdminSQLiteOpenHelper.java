package com.android.gimnasio.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

	private static String nombre_db="gimnasio";
	private static String[] columnas_maquina={"id_maquina","nombre","tipo_de_maquina"};
	private static String[] columnas_usuario={"id_usuario","nombre","apellido","sexo",
												"estatura","peso","edad","imc","puntaje"};
	
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
