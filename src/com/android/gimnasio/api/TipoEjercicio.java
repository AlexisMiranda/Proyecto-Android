package com.android.gimnasio.api;

import android.content.Context;

public class TipoEjercicio {

	
		private Context context;
		public TipoEjercicio(Context context){
				this.context=context;
		}
		public void crearTipoEjercicio(int id_tipo_ejercicio, int id_maquina,String nombre,String descripcion,String ruta_imagenes)
		{	
		}
		public void setIdMaquina(int id_tipo_ejercicio,int id_maquina)
		{	
		}
		public void setNombre(int id_tipo_ejercicio,String nombre)
		{	
		}
		public void setDescripcion(int id_tipo_ejercicio,String descripcion)
		{	
		}
		public void setRutaImagenes(int id_tipo_ejercicio,String ruta_imagenes)
		{	
		}
		
		public int getIdMaquina(int id_tipo_ejercicio)
		{
			return -1;
		}
	
		public String getNombre(int id_tipo_ejercicio)
		{
			return "";
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
