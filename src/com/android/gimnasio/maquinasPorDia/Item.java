package com.android.gimnasio.maquinasPorDia;

public class Item {

    public  int Id;
    public  String IconFile;
    public  String Name;

    public Item(int id, String iconFile, String name) {

        Id = id;
        IconFile = iconFile;
        Name = name;

    }
    public String getIconFile()
    {
    	return this.IconFile;
    }

}
