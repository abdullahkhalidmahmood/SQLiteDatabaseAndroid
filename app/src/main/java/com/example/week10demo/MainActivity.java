package com.example.week10demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private List<Book> bookList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView=findViewById(R.id.grid_layout);
        bookList = new ArrayList<>();
        databaseHandler=new DatabaseHandler(this,null,null,1);
        populateDB();
        populateBookList();

        final GridViewAdapter gridViewAdapter = new GridViewAdapter(bookList,this);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int result = databaseHandler.deleteBook(bookList.get(position));
                System.out.println(result);
                populateBookList();
                gridViewAdapter.setBookList(bookList);
                gridViewAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void populateBookList() {
        if(bookList!=null){
            bookList.clear();
            bookList=databaseHandler.getAllBook();
        }
    }

    private void populateDB() {
        databaseHandler.addBook(new Book("AAA","Horror",R.drawable.cover1));
        databaseHandler.addBook(new Book("BBB","Romatic",R.drawable.cover2));
        databaseHandler.addBook(new Book("CCC","Fiction",R.drawable.cover3));
        databaseHandler.addBook(new Book("DDD","Mystery",R.drawable.cover4));
        databaseHandler.addBook(new Book("EEE","Thriller",R.drawable.cover5));
        databaseHandler.addBook(new Book("FFF","Sci-Fi",R.drawable.cover6));
    }
}
