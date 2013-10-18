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

public class TipoEjercicio {

	
		private Context context;
		//nombreColumna_tipoDeDato_Orden
		public static final String nombreTabla="tipoEjercicio";
		public static final String id_primaryKey_0="id";
		public static final String  fkm_maquina_4="fkm";
		public static final String  nombre_str_2="nombre";
		public static final String  descripcion_str_3="descripcion";
		public static final String  rutaImagenes_str_1="rutaImagenes";

		public TipoEjercicio(Context context){
				this.context=context;
				//this.crearTipoEjercicio(id_tipo_ejercicio, id_maquina, nombre, descripcion, ruta_imagenes);
		}
		public void crearTipoEjercicio(int id_tipo_ejercicio, int id_maquina,String nombre,String descripcion,String ruta_imagenes)
		{	
			ContentValues values=new ContentValues();
			values.put(TipoEjercicio.nombre_str_2, nombre);
			values.put(TipoEjercicio.fkm_maquina_4,id_maquina);
			values.put(TipoEjercicio.descripcion_str_3, descripcion);
			values.put(TipoEjercicio.rutaImagenes_str_1, ruta_imagenes);
			if(id_maquina>0)
				values.put(TipoEjercicio.id_primaryKey_0,id_tipo_ejercicio);
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
			SQLiteDatabase bd = admin.getWritableDatabase();
			bd.insert(TipoEjercicio.nombreTabla, null, values);
			bd.close();
			;
		}
		public static final ContentValues insertarTipoEjercicio(int id_tipo_ejercicio, int id_maquina,String nombre,String descripcion,String ruta_imagenes)
		{
			ContentValues values=new ContentValues();
			values.put( TipoEjercicio.fkm_maquina_4, id_maquina);
			values.put(TipoEjercicio.descripcion_str_3,descripcion);
			values.put(TipoEjercicio.nombre_str_2,nombre);
			values.put(TipoEjercicio.rutaImagenes_str_1,ruta_imagenes);

			if(id_tipo_ejercicio>0)
				values.put(id_primaryKey_0,id_tipo_ejercicio);
			Log.d("insertando tipoejercicio",values.toString());

			return values;
			
		}
		public void editarTipoEjercicio(int id_tipo_ejercicio,ContentValues columnas)
		{
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
			SQLiteDatabase bd = admin.getWritableDatabase();
			bd.update(TipoEjercicio.nombreTabla, columnas, TipoEjercicio.id_primaryKey_0+"='"+id_tipo_ejercicio+"'", null);
			bd.close();
			;
		}
		public int getIdMaquina(int id_tipo_ejercicio)
		{
			return Integer.parseInt(getConsultaToString("select "+TipoEjercicio.fkm_maquina_4+" from "+TipoEjercicio.nombreTabla+
										" "+"where "+TipoEjercicio.id_primaryKey_0+"="+id_tipo_ejercicio));	
		}

		public String getNombre(int id_tipo_ejercicio)
		{
			return getConsultaToString("select "+TipoEjercicio.nombre_str_2+" from "+TipoEjercicio.nombreTabla+
										" "+"where "+TipoEjercicio.id_primaryKey_0+"="+id_tipo_ejercicio);	
		}
		public String getDescripcion(int id_tipo_ejercicio)
		{
			return getConsultaToString("select "+TipoEjercicio.descripcion_str_3+" from "+TipoEjercicio.nombreTabla+
					" "+"where "+TipoEjercicio.id_primaryKey_0+"="+id_tipo_ejercicio);	
		}
		public String getRutaImagenes(int id_tipo_ejercicio)
		{
			return getConsultaToString("select "+TipoEjercicio.rutaImagenes_str_1+" from "+TipoEjercicio.nombreTabla+
					" "+"where "+TipoEjercicio.id_primaryKey_0+"="+id_tipo_ejercicio);	
		}
		

		@SuppressLint("UseSparseArrays")
		public static final HashMap<String, String> getColumnas()
		{
			HashMap<String, String>columnas_tipo=new HashMap<String, String>();
			TipoEjercicio usuario=new TipoEjercicio(null);
			HashMap<Integer, String> columas_ordenadas=new HashMap<Integer, String>();
			for(Field columna : usuario.getClass().getDeclaredFields())
			{
				if(columna.getName().lastIndexOf("_")!=-1 )
				{
					int indice=columna.getName().lastIndexOf("_");

						columas_ordenadas.put(Integer.parseInt(columna.getName().substring(indice+1)), columna.getName().substring(0,indice));
						Log.d("columna = ",columna.getName());
					
				}
			}
			Log.d("Lista de llaves ",columas_ordenadas.toString());
			List<Integer> keys = new ArrayList<Integer>(columas_ordenadas.keySet());
			for(int i=keys.size()-1;i>=0;i--)
			{
				String columna=columas_ordenadas.get(keys.get(i));
				if(columna.indexOf("_")!=-1 )
				{
				
					int indice=columna.indexOf("_");
					Log.d(columna.substring(0,indice),getTipoDeDato(columna.substring(indice+1)));
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
			}else if(tipo.contains("maquina")){
				return "integer,FOREIGN KEY("+TipoEjercicio.fkm_maquina_4+") REFERENCES "+
									Maquina.nombreTabla+"("+Maquina.id_primaryKey_0+")";
			}
			
			return "";
			
		}
		public String getrutaImagen(int id_tipo_ejercicio,int id_maquina)
		{
			
			String url=getConsultaToString("select "+TipoEjercicio.rutaImagenes_str_1
									+" from "+TipoEjercicio.nombreTabla+
									" where "+TipoEjercicio.id_primaryKey_0+" = "+id_tipo_ejercicio+
									" and " +TipoEjercicio.fkm_maquina_4+" = "+id_maquina);
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
		public Bitmap getImagen(int id_tipo_ejercicio,int id_maquina,Context c)
		{
			InputStream is = null;
			try {
				is = context.getResources().getAssets().open(this.getrutaImagen(id_tipo_ejercicio,id_maquina));
				//is = c.getResources().getAssets().open(s);
			} catch (IOException e) {
				;
			}
			return  BitmapFactory.decodeStream(is);
			
		}
		public String getConsultaToString(String query)
		{
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
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
	
	public ArrayList<Integer> getIdsTipoEjercicios(int id_maquina)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Log.d("nombre maquina",""+id_maquina);
		String query=" select "+TipoEjercicio.id_primaryKey_0+ 
						" from "+TipoEjercicio.nombreTabla+
						" where "+TipoEjercicio.fkm_maquina_4 +" = "+id_maquina;	
		
		Cursor resultado=bd.rawQuery(query,null);
		Log.d("resultado === "+resultado.toString(),"");
		ArrayList<Integer> ids_tipo_ejercicio=new ArrayList<Integer>();
		if (resultado.getCount()>0)
		{
			while(resultado.moveToNext())
			{
				ids_tipo_ejercicio.add(Integer.parseInt(resultado.getString(0)));
				
			}	
		}
		bd.close();
		;
		resultado.close();


		return ids_tipo_ejercicio;
	}
	
		public void eliminarTipoEjercicio(int id_tipo_ejercicio)
		{
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
			SQLiteDatabase bd = admin.getWritableDatabase();
			bd.execSQL("delete from "+TipoEjercicio.nombreTabla+" where "+id_primaryKey_0+"="+id_tipo_ejercicio);
			bd.close();
			;
		}
}
