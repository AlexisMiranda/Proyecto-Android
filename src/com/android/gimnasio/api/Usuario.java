package com.android.gimnasio.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Usuario {

	private Context context;
	public static final String nombreTabla="usuario";
	public static final String id_primaryKey_0="id";//};
	public static final String  nombre_str_1="nombre";//,"text"};
	public static final String  passwd_str_2="passwd";//,"text"};
	public static final String edad_int_3="edad";//,"integer"};
	public static final String estatura_float_4="estatura";//,"real"};
	public static final String sexo_int_5="sexo";//,"integer"};
	public static final String peso_float_6="peso";//,"real"};
	public static final String  puntaje_float_7="puntaje";//,"integer"};
	public static final String imc_float_8="imc";//,"real"};
	public static final String rutina_int_9="rutina";
	public static final String loggeado_int_10="loggeado";




	public Usuario(Context context){
		
		this.context=context;
		
	}
	public void crearUsuario(int id_usuario,String nombre,String passwd,int edad,float estatura,float peso,int sexo)
	{
		
		ContentValues values=new ContentValues();
		if(id_usuario>0)
			values.put(Usuario.id_primaryKey_0, id_usuario);
		values.put(Usuario.nombre_str_1, nombre);
		values.put(Usuario.passwd_str_2, passwd);
		values.put(Usuario.edad_int_3, edad);
		values.put(Usuario.estatura_float_4, estatura);
		values.put(Usuario.peso_float_6, peso);	
		values.put(Usuario.sexo_int_5, sexo);
		Float imc=peso/(estatura*estatura);
		values.put(Usuario.imc_float_8, imc);
		values.put(Usuario.puntaje_float_7,0);
		values.put(Usuario.rutina_int_9,0);
		values.put(Usuario.loggeado_int_10,0);

		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.insert(Usuario.nombreTabla, null, values);
		bd.close();
		;	
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
		;	
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
		bd.update(Usuario.nombreTabla, columnas, Usuario.id_primaryKey_0+"="+id_usuario, null);
		bd.close();
		;
		}
	public String getNombre(int id_usuario){
		
		return getConsultaToString("select "+Usuario.nombre_str_1+
								" from "+Usuario.nombreTabla+
								" where "+Usuario.id_primaryKey_0+"="+id_usuario).replace("\n","").replace("\t","");
		
	}

	public String getSexo(int id_usuario){

		String sexo = getConsultaToString("select "+Usuario.sexo_int_5+
							" from "+Usuario.nombreTabla+
							" where "+Usuario.id_primaryKey_0+"="+id_usuario);
		if(sexo.contains("0")){
			return "Hombre";
		}
		return "Mujer";
		
	}
	public String getEdad(int id_usuario){
		
		return getConsultaToString("select "+Usuario.edad_int_3+
				" from "+Usuario.nombreTabla+
				" where "+Usuario.id_primaryKey_0+"="+id_usuario).replace("\n","").replace("\t","");
	}
	public float getEstatura(int id_usuario){

		return Float.parseFloat(getConsultaToString("select "+Usuario.estatura_float_4 +
							" from "+Usuario.nombreTabla+
							" where "+Usuario.id_primaryKey_0+"="+id_usuario));
	}
	public float getPeso(int id_usuario){

		return Float.parseFloat(getConsultaToString("select "+Usuario.peso_float_6+
							" from "+Usuario.nombreTabla+
							" where "+Usuario.id_primaryKey_0+"="+id_usuario));
	}
	public int getPuntaje(int id_usuario){

		return Integer.parseInt(getConsultaToString("select "+Usuario.puntaje_float_7+
							" from "+Usuario.nombreTabla+
							" where "+Usuario.id_primaryKey_0+"="+id_usuario));
	}
	public String getImc(int id_usuario){

		return getConsultaToString("select "+Usuario.imc_float_8+
							" from "+Usuario.nombreTabla+
							" where "+Usuario.id_primaryKey_0+"="+id_usuario).replace("\n","").replace("\t","");
	}
	public boolean getTieneRutinaCreada(int id_usuario)
	{
		Log.d("getTieneRutinaCreada",getClass().toString());
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String query=" select "+Usuario.id_primaryKey_0+
				" from "+Usuario.nombreTabla+
				" where "+Usuario.id_primaryKey_0+" = "+id_usuario+
				" and "+Usuario.rutina_int_9+" = 1 ";
		Log.d("query",query);
		Cursor resultado=bd.rawQuery(query,null);
		boolean tiene_rutina=false;
		if(resultado.getCount()>0)
		{
			while(resultado.moveToNext()){
			tiene_rutina=true;
			}
		}
		bd.close();
		resultado.close();

		return tiene_rutina;
	}
	
	public HashMap<String, String> getInfoUsuario(int id_usuario)
	{
		HashMap<String, String> info=new HashMap<String, String>();
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultado=bd.rawQuery("select *"+
									" from "+Usuario.nombreTabla+
									" where "+Usuario.id_primaryKey_0+"="+id_usuario,null);
		if(resultado.getCount()>0)
		{
			
			while(resultado.moveToNext())
			{
				
					for(int i=0;i<resultado.getColumnCount();i++)
						{			
						info.put(resultado.getColumnName(i),resultado.getString(i));
						}				
			}	
			
		}
		bd.close();
		;
		resultado.close();

		return info;
		
	}
	public ArrayList<Integer> getIdsUsuarios()
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor resultado=bd.rawQuery("select "+Usuario.id_primaryKey_0+" from "+Usuario.nombreTabla,null);
		ArrayList<Integer> ids_usuarios=new ArrayList<Integer>();
		if (resultado.getCount()>0)
		{
			while(resultado.moveToNext())
			{
				ids_usuarios.add(Integer.parseInt(resultado.getString(0)));
				
			}	
		}
		bd.close();
		resultado.close();

		;
		return ids_usuarios;
	}
	
	@SuppressLint("UseSparseArrays")
	public static final HashMap<String, String> getColumnas()
	{
		HashMap<String, String>columnas_tipo=new HashMap<String, String>();
		Usuario usuario=new Usuario(null);
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
			if(columna.indexOf("_")!=-1)
			{
				int indice=columna.indexOf("_");
				columnas_tipo.put(columna.substring(0,indice),getTipoDeDato(columna.substring(indice+1)));
			}
		}
		Log.d("Hash map = ",columnas_tipo.toString());
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

	public boolean existeUsuario(int id_usuario)
	{
		Log.d("existeUsuario",getClass().toString());

		String query=getConsultaToString("select * from usuario where "+Usuario.id_primaryKey_0+" = "+id_usuario);
		if(query.equals(""))
		{
			return false;
		}
		return true;
	}
	
	public boolean existeUsuarioEnBd()
	{
		Log.d("existeUsuarioEnBd",getClass().toString());

		String query=getConsultaToString("select * from usuario");
		if(query.equals(""))
		{
			return false;
		}
		return true;
	}
	public boolean existeUsuarioLoggeado()
	{
		Log.d("existeUsuarioLoggeado",getClass().toString());
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		Cursor resultados = bd.rawQuery(" select "+Usuario.id_primaryKey_0+
										" from "+Usuario.nombreTabla+
										" where "+Usuario.loggeado_int_10+" = 1", null);
		boolean existe=false;
		if (resultados.getCount()>0) 
		{
			existe=true;
		}
		bd.close();
		resultados.close();
		Log.d("existe usuario loggeado",existe+"");
		return existe;
	}
	public int getIdUsuario(String nombre,String passwd)
	{
		Log.d("getIdUsuario",getClass().toString());
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		Cursor resultados = bd.rawQuery(" select "+Usuario.id_primaryKey_0+
										" from "+Usuario.nombreTabla+
										" where "+Usuario.nombre_str_1+" = '"+nombre+"' "+
										" and "+Usuario.passwd_str_2+" = '"+passwd+"' ", null);
		int id=0;
		if (resultados.getCount()>0) 
		{
			while(resultados.moveToNext())
			{
				id=Integer.parseInt(resultados.getString(0));
			}
			bd.close();		
			resultados.close();

			return id;
		}
		bd.close();
		resultados.close();
		return -1;
	}
	public int getIdUsuario(String nombre)
	{
		Log.d("getIdUsuario",getClass().toString());
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		Cursor resultados = bd.rawQuery(" select "+Usuario.id_primaryKey_0+
										" from "+Usuario.nombreTabla+
										" where "+Usuario.nombre_str_1+" = '"+nombre+"' ", null);
		int id=0;
		if (resultados.getCount()>0) 
		{
			while(resultados.moveToNext())
			{
				id=Integer.parseInt(resultados.getString(0));
			}
			bd.close();		
			resultados.close();

			return id;
		}
		bd.close();
		resultados.close();
		return -1;
	}
	public int getIdUsuarioLoggeado()
	{
		Log.d("***getIdUsuarioLoggeado",getClass().toString());
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
		String query=" select "+Usuario.id_primaryKey_0+
				" from "+Usuario.nombreTabla+
				" where "+Usuario.loggeado_int_10+" = 1";
		Log.d("Usuarios loggeados",this.getConsultaToString(" select "+Usuario.id_primaryKey_0+
																" from "+Usuario.nombreTabla+
																" where "+Usuario.loggeado_int_10+"= 1 "));
		Cursor resultados = bd.rawQuery(query, null);
		Log.d("**pase cursor","pase");
		int res=-1;
		if (resultados.getCount()>0) 
		{
			while(resultados.moveToNext())
			{
			Log.d("getIdUsuarioLoggeado es =",resultados.getString(0));
			res=Integer.parseInt(resultados.getString(0));
			}
		}
		bd.close();
		resultados.close();
		Log.d("getIdUsuarioLoggeado",res+"");
		return res;
	}
	public String getConsultaToString(String query)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,null, 1);
		SQLiteDatabase bd = admin.getReadableDatabase();
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

		;
		return "";
			
	}
	
	public void eliminarUsuario(int id_usuario)
	{
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.context,null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		bd.execSQL("delete from "+nombreTabla+" where "+Usuario.id_primaryKey_0+"="+id_usuario);
		bd.close();
		;
	}

}
