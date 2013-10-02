package com.android.gimnasio;

import com.android.gimnasio.api.AdminSQLiteOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
	}

	public void enviar(View view){
		Log.d("Create table", "Entre en la shit");

		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,null,0);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Log.d("Create table", "Entre en la shit");

		Log.d("Create table usuario", ""+admin.getCreateTable("usuario", admin.getColumnasUsuario2()));
		//Log.d("Create table", ""+admin.getCreateTable("maquina", admin.getColunasMaquina()));
		/*Intent intent=new Intent(this,FormularioUsuarioActivity.class);
		startActivity(intent);
	*/	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}