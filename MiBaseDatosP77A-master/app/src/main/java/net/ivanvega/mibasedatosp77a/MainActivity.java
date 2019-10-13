package net.ivanvega.mibasedatosp77a;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtID, edtUsuario, edtEmail, edtTelefono;
    Button btnInsertar, btnBuscar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Record");

        edtID = findViewById(R.id.edtID);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefono = findViewById(R.id.edtTelefono);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnEliminar=findViewById(R.id.btnEliminar);
        final DAOContactos dao= new DAOContactos(this);

        btnInsertar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    dao.insert(new Contacto(0,
                            edtUsuario.getText().toString().trim(),
                            edtEmail.getText().toString().trim(),
                            edtTelefono.getText().toString().trim()));
                    Toast.makeText(MainActivity.this, "Se inserto de manera correcta", Toast.LENGTH_SHORT).show();
                    edtID.setText("");
                    edtUsuario.setText("");
                    edtEmail.setText("");
                    edtTelefono.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    dao.deleteContacto(new Contacto(0,
                            edtUsuario.getText().toString().trim(),
                            edtEmail.getText().toString().trim(),
                            edtTelefono.getText().toString().trim()));
                    Toast.makeText(MainActivity.this, "Se elimino de manera correcta", Toast.LENGTH_SHORT).show();
                    edtID.setText("");
                    edtUsuario.setText("");
                    edtEmail.setText("");
                    edtTelefono.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    dao.buscar(new Contacto(0,
                            edtUsuario.getText().toString().trim(),
                            edtEmail.getText().toString().trim(),
                            edtTelefono.getText().toString().trim()));
                    Toast.makeText(MainActivity.this, "Se cargaron los datos de manera correcta", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
/*
        DAOContactos dao = new DAOContactos(this);

        dao.insert(new Contacto(0, "perronegro",
                "perronegro@","445"));
        dao.insert(new Contacto(0, "perroblanco",
                "perroblanco@","544"));
         for (Contacto c : dao.getAll()){
             Toast.makeText(this,
                     c.usuario,
                     Toast.LENGTH_SHORT).show();
         }



        SimpleCursorAdapter adp =
                new SimpleCursorAdapter(
                        this,
                        android.R.layout.simple_list_item_2,
                        dao.getAllCursor(),
                        new String[]{"usuario","email"},
                        new int[]{android.R.id.text1, android.R.id.text2
                        },
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE

                );
        listView.setAdapter(adp);

*/

    }
}
