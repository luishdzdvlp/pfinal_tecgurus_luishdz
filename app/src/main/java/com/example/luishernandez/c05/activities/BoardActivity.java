package com.example.luishernandez.c05.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luishernandez.c05.R;
import com.example.luishernandez.c05.adapters.BoardAdapter;
import com.example.luishernandez.c05.models.Board;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class BoardActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Board>> {
    private Realm realm;
    private RealmResults<Board> boards;

    private ListView listViewBoard;
    private BoardAdapter adapterBoard;
    private FloatingActionButton fabAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        //Conexion a la db de realm
        realm = Realm.getDefaultInstance();
        boards = realm.where(Board.class).findAll();
        boards.addChangeListener(this);

        adapterBoard = new BoardAdapter(this, boards, R.layout.list_view_board_item);
        listViewBoard =(ListView)findViewById(R.id.listViewBoard);
        listViewBoard.setAdapter(adapterBoard);

        fabAgregar = findViewById(R.id.fabAddBoard);

        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CreateBoard("Tablero1");
                //adapterBoard.notifyDataSetChanged();
                ShowAddBoard("encabezado", "Cuerpo del mesaje");
            }
        });
    }

    private void CreateBoard(String name)
    {
        realm.beginTransaction();
        Board board = new Board(name);
        realm.copyToRealm(board);
        realm.commitTransaction();
    }

    private void UpdateBoard(){

    }

    private void NuevoTablero(){
        CreateBoard("tablero 1");
    }
    @Override
    public void onChange(RealmResults<Board> element) {
        adapterBoard.notifyDataSetChanged();
    }

    public void ShowAddBoard(String nombre, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(nombre);
        builder.setMessage(mensaje);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board, null);
        builder.setView(viewInflated);

        final EditText input = (EditText)viewInflated.findViewById(R.id.etTitulo);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = input.getText().toString().trim();
                if (boardName.length() > 0) {
                    CreateBoard(boardName);
                } else  {
                    Toast.makeText(getApplicationContext(), "Se requiere el nombre", Toast.LENGTH_LONG).show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
