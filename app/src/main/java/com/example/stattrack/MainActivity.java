package com.example.stattrack;

import android.content.Intent;
import android.os.Bundle;

import com.example.stattrack.controllers.GastoController;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  {

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GastoController gastoController = new GastoController(this);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final GastoListAdapter adapter = new GastoListAdapter(this);
        recyclerView.setAdapter(adapter);
        gastoController.mostrarGastos(recyclerView, adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //permite iniciar el activity
    public void Agregar(View view) {
        Intent intent = new Intent(this , EditarActivity.class);
        startActivity(intent);
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        recreate();
    }
}