package com.android.gimnasio.api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class InsertarDatos {

	
	public static void setDeDatos(Context context, int version)
	{
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(context, null, version);
		SQLiteDatabase db =admin.getWritableDatabase();
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
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(1,2, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(2,2, "Polea al pecho", "Polea al pecho", "ejercicios/multifuncional/poleaAlPecho.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(3,2, "remoPoleaBaja", "remoPoleaBaja", "ejercicios/multifuncional/remoPoleaBaja.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(4,2, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(5,2, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(6,2, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		                                                                               
		//maquina 3                                                                    
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(7,3, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(8,3, "Polea al pecho", "Polea al pecho", "ejercicios/multifuncional/poleaAlPecho.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(9,3, "remoPoleaBaja", "remoPoleaBaja", "ejercicios/multifuncional/remoPoleaBaja.png"));
		//maquina 4                                                                    

		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(10,4, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(11,4, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(12,4, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(13,4, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		//maquina 5

		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(14,5, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(15, 5, "remoPoleaBaja", "remoPoleaBaja", "ejercicios/multifuncional/remoPoleaBaja.png"));
		//maquina 6                                                                    

		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(16,6, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(17, 6, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(18, 6, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(19,6, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		//maquina 7                                                                    

		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(20,7, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(21, 7, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(22, 7, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(23,7, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(24, 7, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		//maquina 8                                                                    

		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(25,8, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(26,8, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(27,8, "extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(28,8, "extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(29,8, "triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(30,8,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		//maquina 9                                                                    

		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(31,9, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(32,9,"extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(33,9,"triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(34,9,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		//maquina 10                                                                   

		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(35,10, "prueba", "prueba", "prueba.jpg"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(36,10,"triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(37,10,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(38,10,"extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(39,10,"triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(40,10,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(41,10,"extensionesDePiernas", "extensionesDePiernas", "ejercicios/multifuncional/extensionesDePiernas.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(42,10,"triceptsPuchDown", "triceptsPuchDown", "ejercicios/multifuncional/triceptsPuchDown.png"));
		db.insert(TipoEjercicio.nombreTabla, null, TipoEjercicio.insertarTipoEjercicio(43,10,"extensionesDeTricepts", "extensionesDeTricepts", "ejercicios/multifuncional/extensionesDeTricepts.png"));
	                                                                                       
		db.close();
	}
}
