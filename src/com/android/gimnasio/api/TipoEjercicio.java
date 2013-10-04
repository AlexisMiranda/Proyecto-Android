package com.android.gimnasio.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TipoEjercicio {

	
		private Context context;
		public static final String nombre_tabla="usuario";
		public static final String[] primary_key={"id_tipo_ejercicio","integer primary key AUTOINCREMENT"};
		public static final String[]  id_maquina={"id_maquina","text"};
		public static final String[]  nombre={"nombre","text"};
		public static final String[]  descripcion={"descripcion","integer"};
		public static final String[]  ruta_imagenes={"ruta_imagenes","real"};

		private static final String[][] columnas={primary_key,id_maquina,nombre,
												 descripcion,ruta_imagenes};
		
		public TipoEjercicio(Context context){
				this.context=context;
		}
		public void crearTipoEjercicio(int id_tipo_ejercicio, int id_maquina,String nombre,String descripcion,String ruta_imagenes)
		{	
			ContentValues values=new ContentValues();
			values.put(TipoEjercicio.nombre[0], nombre);
			values.put(TipoEjercicio.id_maquina[0],id_maquina);
			values.put(TipoEjercicio.descripcion[0], descripcion);
			values.put(TipoEjercicio.ruta_imagenes[0], ruta_imagenes);
			if(id_maquina>=0)
				values.put(primary_key[0],id_tipo_ejercicio);
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
			SQLiteDatabase bd = admin.getWritableDatabase();
			bd.insert(TipoEjercicio.nombre_tabla, null, values);
			bd.close();
		}
		public void editarTipoEjercicio(int id_maquina,ContentValues columnas)
		{
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
			SQLiteDatabase bd = admin.getWritableDatabase();
			bd.update(Maquina.nombre_tabla, columnas, primary_key[0]+"='"+id_maquina+"'", null);
			bd.close();
		}
		public int getIdMaquina(int id_tipo_ejercicio)
		{
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
			SQLiteDatabase bd = admin.getWritableDatabase();
			String resultados=bd.rawQuery("select "+TipoEjercicio.id_maquina[0]+" from "+TipoEjercicio.nombre_tabla+
										" "+"where "+primary_key[0]+"="+id_tipo_ejercicio,null).getString(0);
			return Integer.parseInt(resultados);
		}

		public String getNombre(int id_tipo_ejercicio)
		{
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
			SQLiteDatabase bd = admin.getWritableDatabase();
			return bd.rawQuery("select "+TipoEjercicio.nombre[0]+" from "+TipoEjercicio.nombre_tabla+
										" "+"where "+primary_key[0]+"="+id_tipo_ejercicio,null).getString(0);
			
		}
		public String getDescripcion(int id_tipo_ejercicio)
		{
			return "";
		}
		public String getRutaImagenes(int id_tipo_ejercicio)
		{
			return "";
		}
		public void eliminarTipoEjercicio(int id_tipo_ejercicio)
		{
			
		}
}
