package com.android.gimnasio.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Usuario {

	private Context context;
	public static final String nombre_tabla="usuario";
	public static final String[] primary_key={"id_usuario","integer primary key AUTOINCREMENT"};
	public static final String[]  nombre={"nombre","text"};
	public static final String[]  apellido={"apellido","text"};
	public static final String[]  edad={"edad","integer"};
	public static final String[]  peso={"peso","real"};
	public static final String[] estatura={"estatura","real"};
	public static final String[]  sexo={"sexo","integer"};
	public static final String[]  puntaje={"puntaje","integer"};
	public static final String[]  imc={"imc","real"};

	private static final String[][] columnas={primary_key,puntaje,edad,
											peso,estatura,sexo,apellido,nombre,imc};
	
	public Usuario(Context context){
		
		this.context=context;		
	}
	public void crearUsuario(int id_usuario,String nombre,String apellido,int edad,float estatura,float peso,int sexo)
	{
		
		ContentValues values=new ContentValues();
		if(id_usuario>0)
			values.put(Usuario.primary_key[0], id_usuario);
		Log.d("entro a crear","CRE");
		values.put(Usuario.nombre[0], nombre);
		values.put(Usuario.apellido[0], apellido);
		values.put(Usuario.edad[0], edad);
		values.put(Usuario.estatura[0], estatura);
		values.put(Usuario.peso[0], peso);	
		values.put(Usuario.sexo[0], sexo);
		values.put(Usuario.imc[0], (peso/(estatura*estatura)));
		values.put("puntaje",0);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert("usuario", null, values);
		bd.close();
	}
	public boolean crearUsuario(int id_usuario,ContentValues cv_columnas)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Iterator<String> i=getColumnas().keySet().iterator();
		while(i.hasNext())
		{
			if(!(cv_columnas.containsKey(i.next()))){
				return false;
			}
		}
		bd.insert("usuario", null, cv_columnas);
		bd.close();
		return true;
	}

	/*Ejemplo
	 * Usuario u = new Usuario(this);
	 * ContenValues columnas=new ContentValues();
	 * columnas.put("nombre","juanito");
	 * columnas.put("edad",24)
	 * u.update(1,columnas);
	 */
	public void editarUsuario(int id_usuario,ContentValues columnas)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.update(this.nombre_tabla, columnas, "id_usuario="+id_usuario, null);
		bd.close();
	}
	public String getNombre(int id_usuario){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String nombre=bd.rawQuery("select nombre from "+this.nombre_tabla+" where id_usuario="+id_usuario,null).getString(0);
		bd.close();
		return nombre;
	}
	public String getApellido(int id_usuario){
		Log.d("Antes de la query","Entre en getApellido");
		String apellido=getConsultaToString("select apellido from usuario where id_usuario="+id_usuario);
		Log.d("despues de la query","Entre en getApellido");
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
		Cursor resultado=bd.rawQuery("select "+primary_key[0]+" from "+nombre_tabla,null);
		ArrayList<Integer> ids_usuarios=new ArrayList<Integer>();
		if (resultado.moveToFirst())
		{
			while(resultado.moveToNext())
			{
				ids_usuarios.add(Integer.parseInt(resultado.getString(0)));
				
			}	
		}
		return ids_usuarios;
	}
	
	public static final HashMap<String, String> getColumnas()
	{
		HashMap<String, String>columnas_usuario=new HashMap<String, String>();
		for(int i=0;i<Usuario.columnas.length;i++)
		{
				columnas_usuario.put(columnas[i][0],columnas[i][1]);
		
		}
		return columnas_usuario;

	}
	public boolean existeUsuario(int id_usuario)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		Cursor resultados = bd.rawQuery("select * from "+nombre_tabla+" where "+primary_key[0]+"="+id_usuario, null);
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
		SQLiteDatabase bd = admin.getReadableDatabase();
		Cursor resultados = bd.rawQuery(query, null);
		if (resultados.getCount()>0) 
		{
			String res="";
			Log.d("entre en if","*****");
			while(resultados.moveToNext())
			{
			for(int i=0;i<resultados.getColumnCount();i++)
				{			
				res+=resultados.getColumnName(i)+resultados.getString(i)+"\t";
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
		bd.execSQL("delete from "+nombre_tabla+" where "+primary_key[0]+"="+id_usuario);
	}

}