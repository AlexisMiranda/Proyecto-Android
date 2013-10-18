package com.android.gimnasio.api;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

public class Maquina {
	
	
	public static Context context;
	public static final String nombreTabla="maquina";
	public static final String id_primaryKey_0="id";;
	public static final String  nombre_str_1="nombre";
	public static final String  tipoMaquina_str_2="tipoMaquina";
	public static final String  rutaImagen_str_3="rutaImagen";
	
	public Maquina(Context context) {
			Maquina.context=context;

	}

	public void crearMaquina(int id_maquina,String nombre,String tipo_de_maquina,String ruta_imagen){
		ContentValues values=new ContentValues();
		values.put(Maquina.nombre_str_1, nombre);
		values.put(Maquina.tipoMaquina_str_2,tipo_de_maquina);
		values.put(Maquina.rutaImagen_str_3,ruta_imagen);
		if(id_maquina>0)
			values.put(id_primaryKey_0,id_maquina);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert(Maquina.nombreTabla, null, values);
		bd.close();
		;
	}
	public static final ContentValues insertarMaquina(int id_maquina,String nombre,String tipo_de_maquina,String ruta_imagen)
	{
		ContentValues values=new ContentValues();
		values.put( Maquina.nombre_str_1, nombre);
		values.put(Maquina.tipoMaquina_str_2,tipo_de_maquina);
		values.put(Maquina.rutaImagen_str_3,ruta_imagen);
		if(id_maquina>0)
			values.put(id_primaryKey_0,id_maquina);
		Log.d("insertando maquina",values.toString());
		return values;
		
	}
	public void editarMaquina(int id_maquina,ContentValues columnas)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		
		bd.update(Maquina.nombreTabla, columnas, id_primaryKey_0+"='"+id_maquina+"'", null);
		bd.close();
		;
	}
	public String getNombre(int id_maquina)
	{
		return getConsultaToString("select "+Maquina.nombre_str_1+
									" from "+Maquina.nombreTabla+
									" where "+Maquina.id_primaryKey_0+" = "+id_maquina);
	}
	public String getTipoDeMaquina(int id_maquina)
	{

		return getConsultaToString("select "+Maquina.tipoMaquina_str_2
								+" from "+Maquina.nombreTabla+
								" where "+Maquina.id_primaryKey_0+" = "+id_maquina);

	}

	public ArrayList<Integer> getIdsMaquinas()
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultado=bd.rawQuery("select "+Maquina.id_primaryKey_0+" from "+Maquina.nombreTabla,null);
		Log.d("resultado === "+resultado.toString(),"");
		ArrayList<Integer> ids_maquinas=new ArrayList<Integer>();
		if (resultado.getCount()>0)
		{
			while(resultado.moveToNext())
			{
				ids_maquinas.add(Integer.parseInt(resultado.getString(0)));
				
			}	
		}
		bd.close();
		;
		resultado.close();
		return ids_maquinas;
	}
	
	@SuppressLint("UseSparseArrays")
	public static final HashMap<String, String> getColumnas()
	{
		HashMap<String, String>columnas_tipo=new HashMap<String, String>();
		Maquina usuario=new Maquina(null);
		HashMap<Integer, String> columas_ordenadas=new HashMap<Integer, String>();
		for(Field columna : usuario.getClass().getDeclaredFields())
		{
			Log.d("columna "+Maquina.nombreTabla+" = ",columna.getName());
			if(columna.getName().lastIndexOf("_")!=-1 )
			{
				int indice=columna.getName().lastIndexOf("_");

					columas_ordenadas.put(Integer.parseInt(columna.getName().substring(indice+1)), columna.getName().substring(0,indice));
					Log.d("clave ="+Integer.parseInt(columna.getName().substring(indice+1)),"valor = "+columna.getName().substring(0,indice));

			}
		}
		Log.d("Lista de llaves ",columas_ordenadas.toString());
		List<Integer> keys = new ArrayList<Integer>(columas_ordenadas.keySet());
		for(int i=keys.size()-1;i>=0;i--)
		{
			String columna=columas_ordenadas.get(keys.get(i));
			if(columna.indexOf("_")!=-1)
			{
				int indice=columna.indexOf("_");
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
		}
		return "";
		
	}
	public String getrutaImagen(int id_maquina)
	{
		
		String url=getConsultaToString("select "+Maquina.rutaImagen_str_3
								+" from "+Maquina.nombreTabla+
								" where "+Maquina.id_primaryKey_0+" = "+id_maquina);
		String url2="";
		//url al final tiene un salto de linea por ello se parsea y se le quita el salto de linea
		for(int i=0;i<url.length();i++)
		{
			if(i+1<url.length())
			if(url.substring(i,i+1).matches("[a-z]|[A-Z]|[/]|[.]|[0-9]|[_]"))
			{
				url2+=url.substring(i,i+1);
			}
		}
		return url2;
	}
	public Bitmap getImagen(int id_maquina,Context c)
	{
		InputStream is = null;
		try {
			is = context.getResources().getAssets().open(this.getrutaImagen(id_maquina));
			//is = c.getResources().getAssets().open(s);
		} catch (IOException e) {
			;
		}
		return  BitmapFactory.decodeStream(is);
		
	}
	public boolean existeMaquina(int id_maquina)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		Cursor resultados = bd.rawQuery("select * from "+Maquina.nombreTabla+
										" where "+Maquina.id_primaryKey_0+"="+id_maquina, null);
		bd.close();
		;
		if (resultados.getCount()>0){
			resultados.close();

			return true;
		}	
		resultados.close();

		return false;
	}
	public String getConsultaToString(String query)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
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
	public void elminarMaquina(int id_maquina)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+Maquina.nombreTabla+" where "+Maquina.id_primaryKey_0+"="+id_maquina);
		bd.close();
		;

	}



}
