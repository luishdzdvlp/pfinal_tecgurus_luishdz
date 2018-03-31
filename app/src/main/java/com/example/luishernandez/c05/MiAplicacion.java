package com.example.luishernandez.c05;

import android.app.Application;
import android.util.Log;

import com.example.luishernandez.c05.models.Board;

import io.realm.Realm;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;


/**
 * Created by luishernandez on 24/03/18.
 */

public class MiAplicacion extends Application {

    public static AtomicInteger BoardId = new AtomicInteger();


    public void onCreate() {
        super.onCreate();
        Log.d("Realm", "Esta instruccion se ejecuta antes de que los activities se inicien");

        //Inicializar realm
        Realm.init(this);
        setUpRealmConfig();
        //Obtener la instancia por defecto, osea la bd
        Realm realm = Realm.getDefaultInstance();
        //Obtener el nuevo id de la clase board
        BoardId = getIdByTable(realm, Board.class);
        realm.close();

    }

    private void setUpRealmConfig() {
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);


    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
