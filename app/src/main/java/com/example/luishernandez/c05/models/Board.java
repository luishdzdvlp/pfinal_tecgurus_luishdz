package com.example.luishernandez.c05.models;

import android.widget.TextView;

import com.example.luishernandez.c05.MiAplicacion;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by luishernandez on 24/03/18.
 */

public class Board extends RealmObject {

    @PrimaryKey
    private int id;

    @Required
    private String title;

    @Required
    private Date CreatedAt;

    public Board() {}

    public Board(String title) {
        this.id = MiAplicacion.BoardId.incrementAndGet();
        this.title = title;
        this.CreatedAt = new Date();
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }
}
