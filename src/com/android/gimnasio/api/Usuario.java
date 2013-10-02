package com.android.gimnasio.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Usuario {

	private Context context;
	public Usuario(Context context){
		
		this.context=context;
		//this.crearUsuario(0,"alexis","miranda",21,(float)1.78,70,0);
	}
	public void crearUsuario(int id_usuario,String nombre,String apellido,int edad,float estatura,float peso,int sexo)
	{
		
		ContentValues values=new ContentValues();
		if(id_usuario>=0)
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

	public void setNombreApellido(int  id_usuario,String nombre,String apellido){
		
		ContentValues update=new ContentValues();
		if(!nombre.equals(""))
			update.put("nombre",nombre);
		if(!apellido.equals(""))
			update.put("apellido",apellido);
		if(update.size()>0){
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
			SQLiteDatabase bd = admin.getWritableDatabase();
			bd.update("usuario", update, "id_usuario='"+id_usuario+"'", null);
			//bd.execSQL("update usuario set nombre='"+nombre+"' where  id_usuario='"+ id_usuario+"'");		
			bd.close();
		}
	}

	public void setSexo(int  id_usuario,int sexo){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("update usuario set sexo='"+sexo+"' where  id_usuario='"+ id_usuario+"'");		
	    bd.close();
	}
	public void setEdad(int  id_usuario,int edad){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("update usuario set edad='"+edad+"' where  id_usuario='"+ id_usuario+"'");		
	    bd.close();
	}
	public void setEstatura(int  id_usuario,float estatura){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("update usuario set estatura='"+estatura+"' where  id_usuario='"+ id_usuario+"'");		
	    bd.close();
	}
	public void setPeso(int  id_usuario,float peso){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("update usuario set peso='"+peso+"' where  id_usuario='"+ id_usuario+"'");		
	    bd.close();
	}
	public void setPuntaje(int  id_usuario,int puntaje){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("update usuario set puntaje='"+puntaje+"' where  id_usuario='"+ id_usuario+"'");		
	    bd.close();
	}
	public String getNombre(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String nombre=bd.rawQuery("select nombre from usuario where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return nombre;
	}
	public String getApellido(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String apellido=bd.rawQuery("select apellido from usuario where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return apellido;
	}
	public int getSexo(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String sexo=bd.rawQuery("select sexo from usuario where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return Integer.parseInt(sexo);
	}
	public int getEdad(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String edad=bd.rawQuery("select edad from usuario where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return Integer.parseInt(edad);
	}
	public float getEstatura(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String estatura=bd.rawQuery("select estatura from usuario where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return Float.parseFloat(estatura);
	}
	public float getPeso(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String peso=bd.rawQuery("select peso from usuario where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return Float.parseFloat(peso);
	}
	public int getPuntaje(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String puntaje=bd.rawQuery("select puntaje from usuario where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return Integer.parseInt(puntaje);
	}
	public float getImc(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String imc=bd.rawQuery("select imc from usuario where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return Float.parseFloat(imc);
	}

	public ArrayList<Integer> getIdsUsuarios()
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultado=bd.rawQuery("select id_usuario from usuario",null);
		ArrayList<Integer> ids_usuarios=new ArrayList();
		if (resultado.moveToFirst())
		{
			while(resultado.moveToNext())
			{
				ids_usuarios.add(Integer.parseInt(resultado.getString(0)));
				
			}	
		}
		return ids_usuarios;
	}
	
	public ArrayList<String> getColumnas()
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();

		ArrayList<String> r_columnas=new ArrayList<String>();
		String[] columnas=admin.getColumnasUsuario();
		for(int i=0;i<columnas.length;i++)
		{
			r_columnas.add(columnas[i]);
		}
		return r_columnas;
		
	}
	public String getConsultaToString(String query)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
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
			bd.close();
			return res;
		}
		bd.close();
		return "";
			
	}
	public void eliminarUsuario(int id_usuario)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from usuario where id_usuario="+id_usuario);
	}

}