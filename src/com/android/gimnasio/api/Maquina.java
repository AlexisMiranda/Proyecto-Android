package com.android.gimnasio.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Maquina {
	
	private Context context;
	
	public Maquina(Context context) {
		this.context=context;
	}
	public void crearMaquina(int id_maquina,String nombre,String tipo_de_maquina){
		ContentValues values=new ContentValues();
		values.put("nombre", nombre);
		values.put("tipo_de_maquina",tipo_de_maquina);
		if(id_maquina!=-1)
			values.put("id_maquina",id_maquina);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert("maquina", null, values);
		bd.close();
	}
	public void setNombre(int id_maquina,String nombre)
	{
		
	}
	public void setTipoDeMaquina(int id_maquina,String tipo_de_maquina)
	{
		
	}
	public String getNombre(int id_maquina)
	{
		return "";
	}
	public String getTipoDeMaquina(int id_maquina)
	{
		return "";
	}
	public void elminarMaquina(int id_maquina)
	{
		
	}
	public String[] getColumnas()
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultado=bd.rawQuery("select * from maquina",null);
		return resultado.getColumnNames();
	
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
	public void eliminar(){
		
	}
	public void setNombre(){
		
	}
	public void setTipoDeMaquina(){
		
	}


}
