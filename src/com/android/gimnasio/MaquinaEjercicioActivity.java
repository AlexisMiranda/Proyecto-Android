package com.android.gimnasio;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MaquinaEjercicioActivity extends Activity {

    TableLayout tabla;
    TableLayout cabecera;
    TableRow.LayoutParams layoutFila;
    TableRow.LayoutParams layoutId;
    TableRow.LayoutParams layoutTexto;

    private int MAX_FILAS = 20;

    Resources rs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina_ejercicio);
        rs = this.getResources();
        tabla = (TableLayout)findViewById(R.id.tabla);
        cabecera = (TableLayout)findViewById(R.id.cabecera);
 //layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
 layoutId = new TableRow.LayoutParams(160,TableRow.LayoutParams.WRAP_CONTENT);
 layoutTexto = new TableRow.LayoutParams(160,TableRow.LayoutParams.WRAP_CONTENT);
 agregarCabecera();
 agregarFilasTabla();
    }

    public void agregarCabecera(){
     TableRow fila;
     TextView txtId;
     TextView txtNombre;
     TextView txtSerie;
     TextView txtPeso;
     
     fila = new TableRow(this);
     //fila.setLayoutParams(layoutFila);

 txtId = new TextView(this);
 txtNombre = new TextView(this);
 txtSerie = new TextView(this);
 txtPeso = new TextView(this);
  
 txtId.setText("texto1");
 txtId.setGravity(Gravity.CENTER_HORIZONTAL);
 txtId.setTextAppearance(this,R.style.AppBaseTheme);
 txtId.setBackgroundResource(R.drawable.tabla_celda_cabecera);
 txtId.setLayoutParams(layoutId);

 txtNombre.setText(rs.getString(R.string.hello_world));
 txtNombre.setGravity(Gravity.CENTER_HORIZONTAL);
 txtNombre.setTextAppearance(this,R.style.AppBaseTheme);
 txtNombre.setBackgroundResource(R.drawable.tabla_celda_cabecera);
 txtNombre.setLayoutParams(layoutTexto);
 
 txtSerie.setText(rs.getString(R.string.app_name));
 txtSerie.setGravity(Gravity.CENTER_HORIZONTAL);
 txtSerie.setTextAppearance(this,R.style.AppBaseTheme);
 txtSerie.setBackgroundResource(R.drawable.tabla_celda_cabecera);
 txtSerie.setLayoutParams(layoutTexto);
 
 txtPeso.setText("peso");
 txtPeso.setGravity(Gravity.CENTER_HORIZONTAL);
 txtPeso.setTextAppearance(this,R.style.AppBaseTheme);
 txtPeso.setBackgroundResource(R.drawable.tabla_celda_cabecera);
 txtPeso.setLayoutParams(layoutTexto);
 
 fila.addView(txtId);
 fila.addView(txtNombre);
 fila.addView(txtSerie);
 fila.addView(txtPeso);
  
 cabecera.addView(fila);
    }

    public void agregarFilasTabla(){

     TableRow fila;
     TextView txtId;
     TextView txtNombre;
     EditText edtxt;
     EditText edtxt1;
     EditText edtxt2;
     for(int i = 0;i<MAX_FILAS;i++){
         int posicion = i + 1;
         fila = new TableRow(this);
        

         txtId = new TextView(this);
         edtxt= new EditText(this);
         edtxt1= new EditText(this);
         edtxt2= new EditText(this);
      

         txtId.setText(String.valueOf(posicion));
         txtId.setGravity(Gravity.CENTER_HORIZONTAL);
         txtId.setTextAppearance(this,R.style.AppBaseTheme);
         txtId.setBackgroundResource(R.drawable.tabla_celda);
         txtId.setLayoutParams(layoutId);

        
         edtxt.setGravity(Gravity.CENTER_HORIZONTAL);
         edtxt.setTextAppearance(this,R.style.AppBaseTheme);
         edtxt.setBackgroundResource(R.drawable.tabla_celda);
         edtxt.setLayoutParams(layoutTexto);
         
         edtxt1.setGravity(Gravity.CENTER_HORIZONTAL);
         edtxt1.setTextAppearance(this,R.style.AppBaseTheme);
         edtxt1.setBackgroundResource(R.drawable.tabla_celda);
         edtxt1.setLayoutParams(layoutTexto);
         
         edtxt2.setGravity(Gravity.CENTER_HORIZONTAL);
         edtxt2.setTextAppearance(this,R.style.AppBaseTheme);
         edtxt2.setBackgroundResource(R.drawable.tabla_celda);
         edtxt2.setLayoutParams(layoutTexto);
         
         fila.addView(txtId);
         fila.addView(edtxt);
         fila.addView(edtxt1);
         fila.addView(edtxt2);
         tabla.addView(fila);
     }
    }
}