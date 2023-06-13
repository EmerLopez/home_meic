package com.ugb.home_meic;
public class DataClass {
    private String dataproducto; //nombre
    private String dataprecio; //Precio
    private String dataMarca; //Marca
    private String dataDesc; //descripcion
    private String dataespecs; //especificaciones
    private String datadisp; //disponibilidad

    private String dataImage; //imagen

    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getDataproducto() {return dataproducto;}
    public String getDataprecio() {
        return dataprecio;
    }
    public String getDataMarca() {
        return dataMarca;
    }

    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataespecs() {
        return dataespecs;
    }
    public String getDatadisp() {
        return datadisp;
    }

    public String getDataImage() {
        return dataImage;
    }
    public DataClass(String dataproducto, String dataprecio, String dataMarca, String dataDesc, String dataespecs, String datadisp, String dataImage) {
        this.dataproducto = dataproducto;
        this.dataprecio = dataprecio;
        this.dataMarca = dataMarca;
        this.dataDesc = dataDesc;
        this.dataespecs = dataespecs;
        this.datadisp = datadisp;
        this.dataImage = dataImage;



    }
    public DataClass(){
    }
}
