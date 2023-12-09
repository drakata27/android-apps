package com.example.firebase_crud_app;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.MyViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull MainModel model) {
        // Your existing code to bind data
        holder.name.setText(model.getName());
        holder.course.setText(model.getCourse());
        holder.email.setText(model.getEmail());

        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.ic_launcher_background)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(v -> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                    .setContentHolder(new ViewHolder(R.layout.update_popup))
                    .setExpanded(true, 1200)
                    .create();

            View view = dialogPlus.getHolderView();

            EditText name = view.findViewById(R.id.txtName);
            EditText course = view.findViewById(R.id.txtCourse);
            EditText email = view.findViewById(R.id.txtEmail);
            EditText surl = view.findViewById(R.id.txtImageUrl);

            Button btnUpdate = view.findViewById(R.id.btnUpdate);

            name.setText(model.getName());
            course.setText(model.getCourse());
            email.setText(model.getEmail());
            surl.setText(model.getSurl());

            dialogPlus.show();

            btnUpdate.setOnClickListener(v1 -> {
                Map<String, Object> map = new HashMap<>();
                map.put("name", name.getText().toString());
                map.put("course", course.getText().toString());
                map.put("email", email.getText().toString());
                map.put("surl", surl.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("students")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(holder.name.getContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                            dialogPlus.dismiss();
                        })
                        .addOnFailureListener(e -> Toast.makeText(holder.name.getContext(), "Error while updating", Toast.LENGTH_SHORT).show());
            });

            Toast.makeText(holder.name.getContext(), "Position is " + position, Toast.LENGTH_SHORT).show();
        });

        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
            builder.setTitle("Are you sure you want to delete?");
            builder.setMessage("Deleted data can't be recovered");

            builder.setPositiveButton("Delete", (dialog, which) -> FirebaseDatabase.getInstance().getReference().child("students")
                    .child(Objects.requireNonNull(getRef(position).getKey())).removeValue());

            builder.setNegativeButton("Cancel", (dialog, which) -> Log.d("Cancel", "Cancelled"));

            builder.show();
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        Log.d("AdapterItemCount", "ItemCount: " + count);
        return count;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name, course, email;

        Button btnEdit, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nameText);
            course = (TextView) itemView.findViewById(R.id.courseText);
            email = (TextView) itemView.findViewById(R.id.emailText);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }
    }
}
