package com.example.luishernandez.c05.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luishernandez.c05.R;
import com.example.luishernandez.c05.models.Board;

import java.util.List;

/**
 * Created by luishernandez on 24/03/18.
 */


//1. Extender del base adapter
    //2. Generar los metodos con click en el globo rojo
public class BoardAdapter extends BaseAdapter {
    //3. declarar los miembros
    private Context context;
    private List<Board> lista;
    private int layout;
    //4. Crear el metodo con todos los miembros
    public BoardAdapter(Context context, List<Board> lista, int layout) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewInterface vi;

        if (view == null){
            view = LayoutInflater.from(context).inflate(layout, null);
            vi = new ViewInterface();
            vi.title = (TextView)view.findViewById(R.id.tvBoardTitle);
            vi.CreatedAt = (TextView)view.findViewById(R.id.tvBoardDate);
            view.setTag(vi);
        } else {
            vi = (ViewInterface) view.getTag();
        }
        Board board = lista.get(i);
        vi.title.setText(board.getTitle().toString());
        vi.CreatedAt.setText(board.getCreatedAt().toString());
        return view;
    }

    public class ViewInterface {
        TextView title;
        TextView CreatedAt;
    }
}
