//
//package tiepdvph30311.fpoly.android2.demo2;
//
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//import tiepdvph30311.fpoly.android2.R;
//
//public class Adapter2 extends RecyclerView.Adapter<Adapter2.ModelViewHolder> {
//    private List<Model> modelList;
//    private Dao2 dao2;
//    private Context context;
//
//    public Adapter2(Context context, List<Model> modelList) {
//        this.modelList = modelList;
//        this.context = context;
//        dao2 = new Dao2(context);
//    }
//
//    @NonNull
//    @Override
//    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_model, parent, false);
//        return new ModelViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
//        Model model = modelList.get(position);
//        holder.titleTextView.setText(model.getTitle());
//        holder.dateTextView.setText(model.getDate());
//
//        holder.editButton.setOnClickListener(v -> showEditDialog(model));
//        holder.deleteButton.setOnClickListener(v -> {
//            dao2.deleteModel(model.getId());
//            modelList.remove(position);
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(position, modelList.size());
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return modelList.size();
//    }
//
//    public void addItem(Model model) {
//        modelList.add(model);
//        notifyItemInserted(modelList.size() - 1);
//    }
//
//    public void updateItem(Model model) {
//        for (int i = 0; i < modelList.size(); i++) {
//            if (modelList.get(i).getId() == model.getId()) {
//                modelList.set(i, model);
//                notifyItemChanged(i);
//                break;
//            }
//        }
//    }
//
//    private void showEditDialog(Model model) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_edit, null);
//        builder.setView(view);
//
//        EditText titleEditText = view.findViewById(R.id.titleEditText);
//        EditText contentEditText = view.findViewById(R.id.contentEditText);
//        EditText dateEditText = view.findViewById(R.id.dateEditText);
//        EditText typeEditText = view.findViewById(R.id.typeEditText);
//        Button saveButton = view.findViewById(R.id.saveButton);
//
//        titleEditText.setText(model.getTitle());
//        contentEditText.setText(model.getContent());
//        dateEditText.setText(model.getDate());
//        typeEditText.setText(model.getType());
//
//        AlertDialog dialog = builder.create();
//        saveButton.setOnClickListener(v -> {
//            String title = titleEditText.getText().toString().trim();
//            String content = contentEditText.getText().toString().trim();
//            String date = dateEditText.getText().toString().trim();
//            String type = typeEditText.getText().toString().trim();
//
//            if (!title.isEmpty() && !content.isEmpty() && !date.isEmpty() && !type.isEmpty()) {
//                model.setTitle(title);
//                model.setContent(content);
//                model.setDate(date);
//                model.setType(type);
//
//                dao2.updateModel(model);
//                updateItem(model);
//                dialog.dismiss();
//            } else {
//                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        dialog.show();
//    }
//
//    static class ModelViewHolder extends RecyclerView.ViewHolder {
//        CheckBox checkBox;
//        TextView titleTextView;
//        TextView dateTextView;
//        ImageButton editButton;
//        ImageButton deleteButton;
//
//        public ModelViewHolder(@NonNull View itemView) {
//            super(itemView);
//            checkBox = itemView.findViewById(R.id.checkBox);
//            titleTextView = itemView.findViewById(R.id.titleTextView);
//            dateTextView = itemView.findViewById(R.id.dateTextView);
//            editButton = itemView.findViewById(R.id.editButton);
//            deleteButton = itemView.findViewById(R.id.deleteButton);
//        }
//    }
//}
package tiepdvph30311.fpoly.lab2.lab2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;
import java.util.List;

import tiepdvph30311.fpoly.lab2.R;


public class Adapter2 extends RecyclerView.Adapter<Adapter2.ModelViewHolder> {
    private List<Model> modelList;
    private Dao2 dao2;
    private Context context;

    public Adapter2(Context context, List<Model> modelList) {
        this.modelList = modelList;
        this.context = context;
        dao2 = new Dao2(context);
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_model, parent, false);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        Model model = modelList.get(position);
        holder.titleTextView.setText(model.getTitle());
        holder.dateTextView.setText(model.getDate());

        holder.editButton.setOnClickListener(v -> showEditDialog(model));
        holder.deleteButton.setOnClickListener(v -> {
            dao2.deleteModel(model.getId());
            modelList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, modelList.size());
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void addItem(Model model) {
        modelList.add(model);
        notifyItemInserted(modelList.size() - 1);
    }

    public void updateItem(Model model) {
        for (int i = 0; i < modelList.size(); i++) {
            if (modelList.get(i).getId() == model.getId()) {
                modelList.set(i, model);
                notifyItemChanged(i);
                break;
            }
        }
    }

    private void showEditDialog(Model model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_edit, null);
        builder.setView(view);

        EditText titleEditText = view.findViewById(R.id.titleEditText);
        EditText contentEditText = view.findViewById(R.id.contentEditText);
        EditText dateEditText = view.findViewById(R.id.dateEditText);
        EditText typeEditText = view.findViewById(R.id.typeEditText);
        Button saveButton = view.findViewById(R.id.saveButton);

        titleEditText.setText(model.getTitle());
        contentEditText.setText(model.getContent());
        dateEditText.setText(model.getDate());
        typeEditText.setText(model.getType());

        dateEditText.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                    (view1, year1, monthOfYear, dayOfMonth) -> dateEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            datePickerDialog.show();
        });

        AlertDialog dialog = builder.create();
        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String content = contentEditText.getText().toString().trim();
            String date = dateEditText.getText().toString().trim();
            String type = typeEditText.getText().toString().trim();

            if (!title.isEmpty() && !content.isEmpty() && !date.isEmpty() && !type.isEmpty()) {
                model.setTitle(title);
                model.setContent(content);
                model.setDate(date);
                model.setType(type);

                dao2.updateModel(model);
                updateItem(model);
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    static class ModelViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView titleTextView;
        TextView dateTextView;
        ImageButton editButton;
        ImageButton deleteButton;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
