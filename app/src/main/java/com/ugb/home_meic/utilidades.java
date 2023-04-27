package com.ugb.home_meic;

public class utilidades {
    static String url_consulta= "http://localhost:5984/db_amigos/_design/amigos/_view/amigos";
    static  String url_mto = "http://localhost:5984/db_amigos/ ";

    public String generarIdUnico(){
        return java.util.UUID.randomUUID().toString();
    }

}
