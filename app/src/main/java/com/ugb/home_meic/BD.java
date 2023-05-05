package com.ugb.home_meic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {
    public static final String dbname="db_agenda";
    public static final int v=1;
    static final String sqlDb = "CREATE TABLE agenda(id text, rev text, idUnico text, nombre text, direccion text, telefono text, email text, urlfoto text, stock text, actualizado text)";
    public BD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, v);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlDb);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public String administrar_agenda(String id, String rev, String idUnico, String nom, String dir, String tel, String em, String urlfoto,String stock, String accion,  String actualizado){
        try{
            SQLiteDatabase db = getWritableDatabase();
            if(accion.equals("nuevo")){
                db.execSQL("INSERT INTO agenda(id, rev, idUnico, nombre,direccion,telefono,email, urlfoto, stock, actualizado) VALUES('"+id+"', '"+rev+"','"+idUnico+"', '"+nom+"','"+dir+"','"+tel+"','"+em+"','"+urlfoto+"', '"+stock+"', '"+actualizado+"')");
            } else if (accion.equals("modificar")) {
                db.execSQL("UPDATE agenda SET id = '"+id+"',rev= '"+rev+"', nombre='"+nom+"', direccion='"+dir+"', telefono='"+tel+"', email='"+em+"', urlfoto='"+urlfoto+"', stock = '"+stock+"', actualizado = '"+actualizado+"' WHERE idUnico='"+idUnico+"'");
            }else if(accion.equals("eliminar")){
                db.execSQL("DELETE FROM agenda WHERE idUnico='"+idUnico+"'");
            }
            return "ok";
        }catch (Exception e){
            return "Error: "+ e.getMessage();
        }
    }
    public Cursor consultar_agenda(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM agenda ORDER BY nombre";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public Cursor pendienteSincronizar() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from agenda where actualizado!='si'", null);
        return  cursor;
    }
}
