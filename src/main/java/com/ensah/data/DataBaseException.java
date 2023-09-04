package com.ensah.data;

public class DataBaseException extends  Exception{
    public DataBaseException(){
        super("Erreur base de données");
    }
    public DataBaseException(Throwable ex){
        super(ex);
    }
}
