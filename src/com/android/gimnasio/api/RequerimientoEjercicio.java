package com.android.gimnasio.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RequerimientoEjercicio {

	
	private Context context;
	public static final String nombreTabla="requerimientoEjercicio";
	public static final String id_primaryKey_0="id";
	public static final String  fkteu_tipoEjercicioUsuario_3="fkteu";
	public static final String  nombre_str_1="nombre";
	public static final String  valor_str_2="valor";
	public static final String  valornew_str_4="valornew";
	public static final String puntaje_float_5="puntaje";
	public RequerimientoEjercicio(Context context)
	{
		this.context=context;
	}
	public void crearRequerimiento(int id_requerimiento_ejercicio,int id_tipo_ejercicio_usuario,String nombre,String valor)
	{
		ContentValues values=new ContentValues();
		values.put(RequerimientoEjercicio.nombre_str_1, nombre);
		values.put(RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3,id_tipo_ejercicio_usuario);
		values.put(RequerimientoEjercicio.valor_str_2, valor);
		values.put(RequerimientoEjercicio.valornew_str_4,"0");
		values.put(RequerimientoEjercicio.puntaje_float_5,0);

		if(id_requerimiento_ejercicio>0)
			values.put(RequerimientoEjercicio.id_primaryKey_0,id_requerimiento_ejercicio);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert(RequerimientoEjercicio.nombreTabla, null, values);
		bd.close();
		
	}
	public void editarRequerimiento(int id_requerimiento_ejercicio,ContentValues columnas)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.update(RequerimientoEjercicio.nombreTabla, columnas, RequerimientoEjercicio.id_primaryKey_0+"='"+id_requerimiento_ejercicio+"'", null);
		bd.close();
	}

	public int getIdTipoEjercicioUsuario(int id_tipo_ejercicio_usuario)
	{
		return Integer.parseInt(getConsultaToString("select "+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+
													" from "+RequerimientoEjercicio.nombreTabla+
													" "+"where "+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+"="+id_tipo_ejercicio_usuario));	
	}
	public int getIdRequerimientoEjercicio(int id_ejercicio_usuario,String nombre)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String query="select "+RequerimientoEjercicio.id_primaryKey_0+
				" from "+RequerimientoEjercicio.nombreTabla+
				" where "+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+" = "+id_ejercicio_usuario+
				" and "+RequerimientoEjercicio.nombre_str_1+" = '"+nombre+"'";
		Log.d("query getIdRequerimientoEjercicio",query);
		Cursor res=bd.rawQuery(query, null);
		int id=0;
		if(res.getCount()>0)
		{
			while(res.moveToNext())
			{
				id=Integer.parseInt(res.getString(0));
			}
			bd.close();
			res.close();
			return id;
		}
		bd.close();
		res.close();
		return id;
	}

	public ArrayList<String> getNombres(int id_tipo_ejercicio_usuario)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String query="select "+RequerimientoEjercicio.nombre_str_1+
				" from "+RequerimientoEjercicio.nombreTabla+
				" "+"where "+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+" = "+id_tipo_ejercicio_usuario+""+
				" group by "+RequerimientoEjercicio.nombre_str_1;
		Log.d("query getNombres",query);
		Cursor res=bd.rawQuery(query, null);
		ArrayList<String> array=new ArrayList<String>();
		if(res.getCount()>0)
		{
			while(res.moveToNext())
			{
				array.add(res.getString(0));
			}
			bd.close();
			res.close();
			Log.d("nombres = ",array.toString());
			return array;
		}
		bd.close();
		res.close();
		return new ArrayList<String>();
	}
	public String getValor(int id_tipo_ejercicio_usuario,String nombre)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String query="select "+RequerimientoEjercicio.valor_str_2+
				" from "+RequerimientoEjercicio.nombreTabla+
				" where "+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+" = "+id_tipo_ejercicio_usuario+
				" and "+RequerimientoEjercicio.nombre_str_1+" = '"+nombre+"'";
		Log.d("query getNombres",query);
		Cursor res=bd.rawQuery(query, null);
		String valor="";
		if(res.getCount()>0)
		{
			while(res.moveToNext())
			{
				valor=res.getString(0);
			}
			bd.close();
			res.close();
			Log.d("valor = ",valor);
			return valor;
		}
		bd.close();
		res.close();
		return "";
	}
	public String getValorNew(int id_tipo_ejercicio_usuario,String nombre)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String query="select "+RequerimientoEjercicio.valornew_str_4+
				" from "+RequerimientoEjercicio.nombreTabla+
				" where "+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+" = "+id_tipo_ejercicio_usuario+
				" and "+RequerimientoEjercicio.nombre_str_1+" = '"+nombre+"'";
		Log.d("query getNombres",query);
		Cursor res=bd.rawQuery(query, null);
		String valor="";
		if(res.getCount()>0)
		{
			while(res.moveToNext())
			{
				valor=res.getString(0);
				Log.d("****valor",valor);
			}
			bd.close();
			res.close();
			Log.d("valor = ",valor);
			return valor;
		}
		bd.close();
		res.close();
		return "";
	}
	public String getpuntaje()
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String query="select u."+Usuario.nombre_str_1+",avg(r."+RequerimientoEjercicio.puntaje_float_5+") as puntaje,"+
				    "u."+Usuario.id_primaryKey_0	+",u."+Usuario.imc_float_8+
				" from "+Usuario.nombreTabla+" u left join "+TipoEjercicioUsuario.nombreTabla+" teu "+
				" on (u."+Usuario.id_primaryKey_0+" = teu."+TipoEjercicioUsuario.fku_usuario_2+") "+
				" join "+RequerimientoEjercicio.nombreTabla+" r on(teu."+TipoEjercicioUsuario.id_primaryKey_0+
				" = r."+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+" ) "+
				" group by u."+Usuario.nombre_str_1+",u."+Usuario.id_primaryKey_0+",u."+Usuario.imc_float_8+
				" order by puntaje desc";
		Log.d("query getpuntaje",query);
		Cursor res=bd.rawQuery(query, null);
		String nombre="  Nombre             ",puntaje=" Puntaje ";
		String valor=nombre+puntaje+"   Sexo   Imc\n\n\n";
		Usuario u=new Usuario(this.context);
		if(res.getCount()>0)
		{
			while(res.moveToNext())
			{
				int tam=nombre.length()-res.getString(0).length();
				String nom=res.getString(0);
				if(tam>0)
					for(int i=0;i<tam;i++)
					{
						nom+=" ";
					}
				Log.d("nombre "+tam,"*"+nom+"*");
				valor+="  "+nom+" "+res.getString(1)+"     "+u.getSexo(Integer.parseInt(res.getString(2)))
						+"     "+res.getString(3)+"\n\n";
			}
			bd.close();
			res.close();
			return valor;
		}
		bd.close();
		res.close();
		return valor;	
	}
	public static final HashMap<String, String> getColumnas()
	{
		HashMap<String, String>columnas_tipo=new HashMap<String, String>();
		RequerimientoEjercicio usuario=new RequerimientoEjercicio(null);
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
		}else if(tipo.contains("tipoEjercicioUsuario")){
			return "integer  REFERENCES "+TipoEjercicioUsuario.nombreTabla+"("+TipoEjercicioUsuario.id_primaryKey_0+")";
			//return "integer,FOREIGN KEY("+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+") REFERENCES "+
				//				TipoEjercicioUsuario.nombreTabla+"("+TipoEjercicioUsuario.id_primaryKey_0+")";
		}
		Log.d("gettipodedato ***","");
		
		return "";
		
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
			resultados.close();
			return res;
		}
		bd.close();
		resultados.close();
		return "";
			
	}
	public void eliminarRequerimientoEjercicio(int id_requerimiento_ejercicio)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+RequerimientoEjercicio.nombreTabla+
				" where "+RequerimientoEjercicio.id_primaryKey_0+"="+id_requerimiento_ejercicio);
	}
	public void eliminarReqEjerPorTipoEjerUser(int id_tipo_ejercicio_usuario)
	{
		Log.d(RequerimientoEjercicio.nombreTabla,"eliminarReqEjerPorTipoEjerUser(int id_tipo_ejercicio_usuario)");
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+RequerimientoEjercicio.nombreTabla+
					" where "+RequerimientoEjercicio.fkteu_tipoEjercicioUsuario_3+" = "+id_tipo_ejercicio_usuario);
		bd.close();
	}
	
}
