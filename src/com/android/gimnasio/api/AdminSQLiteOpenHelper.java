package com.android.gimnasio.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

	private static String nombre_db="gimnasio";

	private HashMap<String, String> columnas_usuario,columnas_maquina;
	
	public AdminSQLiteOpenHelper(Context context,CursorFactory factory, int version) 
	{
		super(context,nombre_db , factory, version);
		 
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		Log.d("Create uSUARIO","");
		db.execSQL(this.getCreateTable(Usuario.nombreTabla, Usuario.getColumnas()));
		Log.d("Create maquina","");
		db.execSQL(this.getCreateTable(Maquina.nombreTabla, Maquina.getColumnas()));
		Log.d("Create tipoejercicio","");
		
		db.execSQL(this.getCreateTable(TipoEjercicio.nombreTabla, TipoEjercicio.getColumnas()));
		
		db.execSQL(this.getCreateTable(TipoEjercicioUsuario.nombreTabla, TipoEjercicioUsuario.getColumnas()));
		db.execSQL(this.getCreateTable(RequerimientoEjercicio.nombreTabla, RequerimientoEjercicio.getColumnas()));

		//this.setDeDatos(db);
		
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) 
	{
		db.execSQL("drop table if exists "+RequerimientoEjercicio.nombreTabla);
		db.execSQL("drop table if exists "+TipoEjercicioUsuario.nombreTabla);
		db.execSQL("drop table if exists "+TipoEjercicio.nombreTabla);
		db.execSQL("drop table if exists "+Usuario.nombreTabla);
		db.execSQL("drop table if exists "+Maquina.nombreTabla);
		db.execSQL(this.getCreateTable(Usuario.nombreTabla, Usuario.getColumnas()));
		db.execSQL(this.getCreateTable(Maquina.nombreTabla, Maquina.getColumnas()));
		db.execSQL(this.getCreateTable(TipoEjercicio.nombreTabla, TipoEjercicio.getColumnas()));
		db.execSQL(this.getCreateTable(TipoEjercicioUsuario.nombreTabla, TipoEjercicioUsuario.getColumnas()));
		db.execSQL(this.getCreateTable(RequerimientoEjercicio.nombreTabla, RequerimientoEjercicio.getColumnas()));

		

	}
	public void setDeDatos(SQLiteDatabase db)
	{
		
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(1,"prueba","prueba","prueba"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(2,"multifuncional","multifuncional","maquinas/multifuncional.jpg"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(3,"press banca","banca","maquinas/press_banca.jpg"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(4,"mancuernas","pesas","maquinas/mancuernas.jpg"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(5,"banco plano","banca","maquinas/banco_plano.jpg"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(6,"maquina abdominales","banca","maquinas/maquina_abdominales.jpg"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(7,"maquina piernas","pesas","maquinas/maquina_piernas.gif"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(8,"barra","barra","maquinas/barra.jpg"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(9,"trotadora","Trotadora","maquinas/trotadora.jpg"));
		db.insert(Maquina.nombreTabla, null, Maquina.insertarMaquina(10,"bicicleta","spinnig","maquinas/bicicleta_spinning.jpg"));
		
		//maquina 2
		//db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(1, 2, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(2, 2, "Polea al pecho", "Polea al pecho", "ejercicios/multifuncional/poleaAlPecho.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(3, 2, "remoPoleaBaja", "remoPoleaBaja", "ejercicios/multifuncional/remoPoleaBaja.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(4, 2, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(5, 2, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(6, 2, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		
		//maquina 3
		
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(7, 3, "Polea al pecho", "Polea al pecho", "ejercicios/multifuncional/poleaAlPecho.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(8, 3, "remoPoleaBaja", "remoPoleaBaja", "ejercicios/multifuncional/remoPoleaBaja.png"));
		//maquina 4
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(9,4, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(10,4, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(11,4, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		//maquina 5
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(12, 5, "remoPoleaBaja", "remoPoleaBaja", "ejercicios/multifuncional/remoPoleaBaja.png"));
		//maquina 6
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(13, 6, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(14, 6, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(15,6, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		//maquina 7
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(16, 7, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(17, 7, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(18,7, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(19, 7, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		//maquina 8
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(20,8, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(21,8, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(22,8, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(23,8, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(24,8,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		//maquina 9
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(25,9,"extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(26,9,"triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(27,9,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		//maquina 10
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(28,10,"triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(29,10,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(30,10,"extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(31,10,"triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(32,10,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(33,10,"extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(34,10,"triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(35,10,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
	
	}

	public String getCreateTable(String tabla,HashMap<String,String> columnas)
	{

		Iterator<String> clave_iterador=columnas.keySet().iterator();
		String create_table="create table "+tabla+" ( ";
		List<String> fks=new ArrayList<String>();
		while(clave_iterador.hasNext())
		{
			String clave=clave_iterador.next();
			if(!(columnas.get(clave).contains("FOREIGN") || columnas.get(clave).contains("foreign")))
				{
				create_table+=clave+" "+columnas.get(clave)+",";	
				}else{
				fks.add(clave);	
				}
		}
		for(int i=0;i<fks.size();i++)
		{
			create_table+=fks.get(i)+" "+columnas.get(fks.get(i))+",";
		}
		create_table=create_table.substring(0, create_table.length()-1)+")";
		Log.d("Create table "+tabla+"= ",create_table);
		return create_table;
	}

	public String getNombreDb()
	{
		return nombre_db;
		
	}
}
