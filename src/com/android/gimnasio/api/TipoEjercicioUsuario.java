package com.android.gimnasio.api;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TipoEjercicioUsuario {

	private Context context;
	public static final String nombreTabla="tipoEjercicioUsuario";
	public static final String id_primaryKey_0="id";
	public static final String  fkte_tipoEjercicio_1="fkte";
	public static final String  fku_usuario_2="fku";
	public static final String dia_str_3="dia";
	//public static final String puntaje_float_4="puntaje";
	
	public TipoEjercicioUsuario(Context context)
	{
	this.context=context;	
	}
	public void crearTipoEjercicioUsuario(int id_tipo_ejercicio_usuario,int id_tipo_ejercicio,int id_usuario,String dia)
	{
		ContentValues values=new ContentValues();
		values.put(TipoEjercicioUsuario.fkte_tipoEjercicio_1, id_tipo_ejercicio);
		values.put(TipoEjercicioUsuario.fku_usuario_2,id_usuario);
		values.put(TipoEjercicioUsuario.dia_str_3,dia);
		//values.put(TipoEjercicioUsuario.puntaje_float_4,0);
		if(id_tipo_ejercicio_usuario>0)
			values.put(TipoEjercicioUsuario.id_primaryKey_0,id_tipo_ejercicio_usuario);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert(TipoEjercicioUsuario.nombreTabla, null, values);
		bd.close();
		
	}
	public void editarTipoEjercicioUsuario(int id_tipo_ejercicio_usuario,ContentValues columnas)
	{
		Log.d("editarTipoEjercicioUsuario",""+id_tipo_ejercicio_usuario);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.update(TipoEjercicioUsuario.nombreTabla, columnas, TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario, null);
		bd.close();
		;
		}
	public int getIdTipoEjercicioUsuario(int id_ejercicio,int id_usuario,String dia)
	{
		Log.d("getIdTipoEjercicioUsuario","id_u="+id_usuario+" id_ejer ="+id_ejercicio+" dia="+dia);
		String query="select "+TipoEjercicioUsuario.id_primaryKey_0+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.fku_usuario_2+" = "+id_usuario+
				" and "+TipoEjercicioUsuario.fkte_tipoEjercicio_1+" = "+id_ejercicio+
				" and "+TipoEjercicioUsuario.dia_str_3+" = '"+dia+"'";
		Log.d("query getIdTipoEjercicioUsuario ",query);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultado=bd.rawQuery(query, null);
		Log.d("res","cursor");
		int id=0;
		if(resultado.getCount()>0)
		{
			while(resultado.moveToNext())
			{
				id=Integer.parseInt(resultado.getString(0));
			}
			Log.d("id",""+id);
			bd.close();
			resultado.close();
			return id;
		}
		bd.close();
		resultado.close();
		Log.d("id",""+-1);
		return -1;
	}
	public int getIdEjercicio(int id_tipo_ejercicio_usuario)
	{
		return Integer.parseInt(getConsultaToString("select "+TipoEjercicioUsuario.fkte_tipoEjercicio_1+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario));
	}
	public int getIdUsuario(int id_tipo_ejercicio_usuario)
	{
		return Integer.parseInt(getConsultaToString("select "+TipoEjercicioUsuario.fku_usuario_2+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario));
	}
	public String getDia(int id_tipo_ejercicio_usuario)
	{
		return getConsultaToString("select "+TipoEjercicioUsuario.dia_str_3+
				" from "+TipoEjercicioUsuario.nombreTabla+
				" "+"where "+TipoEjercicioUsuario.id_primaryKey_0+"="+id_tipo_ejercicio_usuario);
	}
	public ArrayList<Integer> getIdEjercicioPorDia(String dia)
	{
		ArrayList<Integer> ids=new ArrayList<Integer>();
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Log.d("entre en getIdEjercicioPorDia","dia= "+dia);
		Cursor resultado=bd.rawQuery(" select "+TipoEjercicioUsuario.fkte_tipoEjercicio_1+
									" from "+TipoEjercicioUsuario.nombreTabla+
									" where "+TipoEjercicioUsuario.dia_str_3+" ='"+dia+"'",null);
		Log.d("res","cursor");
		if(resultado.getCount()>0)
		{
			while(resultado.moveToNext())
			{
				Log.d("resultado=",resultado.getString(0));
				ids.add(Integer.parseInt(resultado.getString(0)));
			}
		}else{
			bd.close();
			resultado.close();
			return new ArrayList<Integer>();
		}
		bd.close();
		resultado.close();
		return ids;
	}

	public int getUltimoIdInsertado()
	{
		Log.d(TipoEjercicioUsuario.nombreTabla,"open getUltimoIdInsertado");
		String res=getConsultaToString("select "+id_primaryKey_0+
									" from "+TipoEjercicioUsuario.nombreTabla+
									" order by "+id_primaryKey_0+" desc limit 1");
		Log.d(TipoEjercicioUsuario.nombreTabla,"open getUltimoIdInsertado= *"+res+"*");
		String res2="";
		for(int i=0;i<res.length();i++)
		{
			if(i+1<res.length())
			if(res.substring(i,i+1).matches("[0-9]"))
			{
				res2+=res.substring(i,i+1);
			}
		}
		return Integer.parseInt(res2);
	}
	
	public ArrayList<Integer> getIdsTypeEjerUserByTypeEjer(int id_tipo_ejercicio)
	{
		Log.d(""+TipoEjercicioUsuario.nombreTabla,"ArrayList<Integer> getIdsTypeEjerUserByTypeEjer(int id_tipo_ejercicio)");
		ArrayList<Integer> ids=new ArrayList<Integer>();
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultado=bd.rawQuery(" select "+TipoEjercicioUsuario.id_primaryKey_0+
									" from "+TipoEjercicioUsuario.nombreTabla+
									" where "+TipoEjercicioUsuario.fkte_tipoEjercicio_1+" = "+id_tipo_ejercicio,null);
		Log.d("resultado === "+resultado.toString(),"");
		if (resultado.getCount()>0)
		{
			while(resultado.moveToNext())
			{
				ids.add(Integer.parseInt(resultado.getString(0)));
				
			}	
		}
		bd.close();
		resultado.close();
		return ids;
		
	}
	public ArrayList<Integer> getEjerciciosSeleccionados(int id_maquina,String dia)
	{
		Log.d("entre en ","getEjerciciosSeleccionados");
		Log.d("dia",dia);
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this.context, null,1);
		SQLiteDatabase bd=admin.getWritableDatabase();
		String query=" select te."+TipoEjercicio.id_primaryKey_0+
				" from "+TipoEjercicioUsuario.nombreTabla +" teu join "+TipoEjercicio.nombreTabla+" te"+
				" on teu."+TipoEjercicioUsuario.fkte_tipoEjercicio_1+" = te."+TipoEjercicio.id_primaryKey_0+
				" where te."+TipoEjercicio.fkm_maquina_4+" = "+id_maquina+
				" and teu."+TipoEjercicioUsuario.dia_str_3+" = '"+dia+"' "+
				" group by te."+TipoEjercicio.id_primaryKey_0;
		Log.d("query",query);
		Cursor res=bd.rawQuery(query,null);
		ArrayList<Integer> ids_ejer_selec=new ArrayList<Integer>();
		if(res.getCount()>0)
		{
			while(res.moveToNext())
			{
				Log.d("fila =",res.getString(0));
			ids_ejer_selec.add(Integer.parseInt(res.getString(0)));
			}
			bd.close();
			res.close();
			Log.d("ids ejer sele",ids_ejer_selec.toString());
			return ids_ejer_selec;
		}
		return new ArrayList<Integer>();
		
	}
	public ArrayList<String> getDiasInsertados(int id_usuario)
	{
		/*
		 * Obtiene los dias que existen en la base de datos
		 */
		Log.d("entre en ","getDiasInsertados");
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String query=" select teu."+TipoEjercicioUsuario.dia_str_3+
				" from "+TipoEjercicioUsuario.nombreTabla+" teu"+
				" where teu."+TipoEjercicioUsuario.fku_usuario_2+" = "+id_usuario+
				" group by teu."+TipoEjercicioUsuario.dia_str_3;
		Cursor resultados = bd.rawQuery(query, null);
		Log.d("res query = ",resultados.toString());
		String[] dias={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
		ArrayList<String> res_dias=new ArrayList<String>();
		if(resultados.getCount()>0)
		{
			while(resultados.moveToNext())
			{
				for(String dia:dias)
				{
					
						Log.d(dia,""+resultados.getString(0));
						if(dia.contains(resultados.getString(0)))
						{
							res_dias.add(dia);
						}
				}
			}
			
		}else{
			Log.d("vacio","vacio");
			bd.close();
			resultados.close();
			return new ArrayList<String>();
		}
		Log.d("sali de  ","getDiasInsertados");

		bd.close();
		resultados.close();
		return res_dias;
	}
	public static final HashMap<String, String> getColumnas()
	{
		HashMap<String, String>columnas_tipo=new HashMap<String, String>();
		TipoEjercicioUsuario usuario=new TipoEjercicioUsuario(null);
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
		}else if(tipo.contains("tipoEjercicio")){
			return "integer not null REFERENCES "+ TipoEjercicio.nombreTabla+" ( "+TipoEjercicio.id_primaryKey_0+" )";

		}else if(tipo.contains("usuario")){
			return "integer not null REFERENCES "+Usuario.nombreTabla+" ( "+Usuario.id_primaryKey_0+" )";

}
		
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
	public void eliminarTipoEjercicioUsuario(int id_tipo_ejercicio_usuario)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+TipoEjercicioUsuario.nombreTabla+" where "+id_primaryKey_0+"="+id_tipo_ejercicio_usuario);
		
		bd.close();
	}
	public void eliminarTipoEjerUsuarioPorTipoEjer(int id_tipoEjercicio,int id_usuario)
	{
		Log.d(TipoEjercicioUsuario.nombreTabla,"eliminarTipoEjerUsuarioPorTipoEjer(int id_tipoEjercicio)");
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+TipoEjercicioUsuario.nombreTabla+
					" where "+TipoEjercicioUsuario.fkte_tipoEjercicio_1+" = "+id_tipoEjercicio+
					" and "+TipoEjercicioUsuario.fku_usuario_2+" = "+id_usuario	);
		bd.close();
	}
}
