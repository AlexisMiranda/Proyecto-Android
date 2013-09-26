package com.android.gimnasio.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Usuario {

	private Context context;
	public Usuario(Context context){
		this.context=context;
		
	}
	public void crearUsuario(int id_usuario,String nombre,String apellido,int edad,float estatura,float peso,int sexo)
	{
		ContentValues values=new ContentValues();
		values.put("id_usuario", id_usuario);
		values.put("nombre", nombre);
		values.put("apellido", apellido);
		values.put("edad", edad);
		values.put("estatura", estatura);
		values.put("peso", peso);	
		values.put("sexo", sexo);
		values.put("imc", (peso/(estatura*estatura)));
		values.put("puntaje",0);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert("usuario", null, values);
		bd.close();
		
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
	
	
	public void setNombre(String nombre){
		
	}
	public void setApellido(String apellido){
		
	}
	public void setSexo(Boolean sexo){
		
	}
	public void setEdad(int edad){
		
	}
	public void setEstatura(float estatura){
		
	}
	public void setPeso(float peso){
		
	}
	public void setPuntaje(int puntaje){
		
	}
	public String getNombre(){
		return "";
	}
	public String getApellido(){
		return "";
	}
	public boolean getSexo(){
		return false;
	}
	public int getEdad(){
		return -1;
	}
	public float getEstatura(){
		return 0;
	}
	public float getPeso(){
		return 0;
	}
	public int getPuntaje(){
		return -1;
	}
	public float getImc(){
		return this.getPeso()/(this.getEstatura()*this.getEstatura());
	}
	public void eliminarUsuario(int id_usuario)
	{
		
	}

}