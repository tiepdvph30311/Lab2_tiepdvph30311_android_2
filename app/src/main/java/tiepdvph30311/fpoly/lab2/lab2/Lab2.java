package tiepdvph30311.fpoly.lab2.lab2;


//import android.widget.Button;
//import android.widget.EditText;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//import tiepdvph30311.fpoly.android2.R;
//
//public class MainActivitydemo2 extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private Adapter2 adapter2;
//    private Dao2 dao2;
//    private Button addButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_demo2);
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        addButton = findViewById(R.id.addButton);
//        dao2 = new Dao2(this);
//        List<Model> modelList = dao2.getAllModels();
//
//        adapter2 = new Adapter2(this, modelList);
//        recyclerView.setAdapter(adapter2);
//
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAddDialog();
//            }
//        });
//    }
//
//    private void showAddDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View view = getLayoutInflater().inflate(R.layout.dialog_add_edit, null);
//        builder.setView(view);
//
//        EditText titleEditText = view.findViewById(R.id.titleEditText);
//        EditText contentEditText = view.findViewById(R.id.contentEditText);
//        EditText dateEditText = view.findViewById(R.id.dateEditText);
//        EditText typeEditText = view.findViewById(R.id.typeEditText);
//        Button saveButton = view.findViewById(R.id.saveButton);
//
//        AlertDialog dialog = builder.create();
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = titleEditText.getText().toString().trim();
//                String content = contentEditText.getText().toString().trim();
//                String date = dateEditText.getText().toString().trim();
//                String type = typeEditText.getText().toString().trim();
//
//                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content) &&
//                        !TextUtils.isEmpty(date) && !TextUtils.isEmpty(type)) {
//
//                    Model model = new Model();
//                    model.setTitle(title);
//                    model.setContent(content);
//                    model.setDate(date);
//                    model.setType(type);
//
//                    long id = dao2.addModel(model);
//                    if (id != -1) {
//                        model.setId((int) id);
//                        adapter2.addItem(model);
//                        dialog.dismiss();
//                    }
//                }
//            }
//        });
//
//        dialog.show();
//    }
//}


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;
import java.util.List;

import tiepdvph30311.fpoly.lab2.R;


public class Lab2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter2 adapter2;
    private Dao2 dao2;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo2);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton = findViewById(R.id.addButton);
        dao2 = new Dao2(this);
        List<Model> modelList = dao2.getAllModels();

        adapter2 = new Adapter2(this, modelList);
        recyclerView.setAdapter(adapter2);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_edit, null);
        builder.setView(view);

        EditText titleEditText = view.findViewById(R.id.titleEditText);
        EditText contentEditText = view.findViewById(R.id.contentEditText);
        EditText dateEditText = view.findViewById(R.id.dateEditText);
        EditText typeEditText = view.findViewById(R.id.typeEditText);
        Button saveButton = view.findViewById(R.id.saveButton);

        dateEditText.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Lab2.this,
                    (view1, year1, monthOfYear, dayOfMonth) -> dateEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            datePickerDialog.show();
        });

        AlertDialog dialog = builder.create();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String content = contentEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();
                String type = typeEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content) &&
                        !TextUtils.isEmpty(date) && !TextUtils.isEmpty(type)) {

                    Model model = new Model();
                    model.setTitle(title);
                    model.setContent(content);
                    model.setDate(date);
                    model.setType(type);

                    long id = dao2.addModel(model);
                    if (id != -1) {
                        model.setId((int) id);
                        adapter2.addItem(model);
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }
}


