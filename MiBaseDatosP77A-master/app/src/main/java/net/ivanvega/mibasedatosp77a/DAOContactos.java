package net.ivanvega.mibasedatosp77a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DAOContactos {
    SQLiteDatabase _sqLiteDatabase;
    Context ctx;


    public DAOContactos(Context ctx) {
        this.ctx = ctx;
        _sqLiteDatabase =
                new MiDB(ctx).getWritableDatabase();
    }


    public long insert(Contacto contacto){
        ContentValues contentValues
                = new ContentValues();

        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[1],
                contacto.getUsuario());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[2],
                contacto.getEmail());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[3],
                contacto.getTel());

        return  _sqLiteDatabase.insert(MiDB.TABLE_NAME_CONTACTOS,
                null, contentValues);

    }
  //este codigo lo añadi yop
    public void deleteContacto(Contacto id)
    {
     String where = MiDB.COLUMNS_NAME_CONTACTO+"= ?";
    _sqLiteDatabase.delete(MiDB.TABLE_NAME_CONTACTOS, where, new String[]{String.valueOf(id)});

    }
    public Contacto buscar (Contacto usuario)
    {
        Contacto contacto = new Contacto();
        String where = MiDB.COLUMNS_NAME_CONTACTO+"=?";
        String[] whereArgs = {String.valueOf(usuario)};
        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS, null, where, whereArgs, null, null, null);
        if( c != null || c.getCount() <=0) {
            c.moveToFirst();
            contacto.setId(c.getInt(0));
            contacto.setUsuario(c.getString(1));
            contacto.setTel(c.getString(2));
            contacto.setEmail(c.getString(3));
            c.close();
        }

        return contacto;

    }
    //hasta aqui es codigo mio
    public List<Contacto> getAll (){
        List<Contacto> lst=null;

        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);

           if (c.moveToFirst() ){
               lst = new ArrayList<Contacto>();
               do {
                  Contacto contacto =
                          new Contacto(c.getInt(0), c.getString(1),
                                  c.getString(2), c.getString(3));
                   lst.add(contacto);

               }while(c.moveToNext());
           }
           return  lst;

    }

    public Cursor getAllCursor (){


        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);


        return  c;

    }
    public Cursor getAllByUsuario(String criterio){

        Cursor c = _sqLiteDatabase.query(
                MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                "usuario like %?%",
                new String[]{criterio},
                null,
                null,null

        );

        return c;
    }

}
