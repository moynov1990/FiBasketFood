package com.example.fibasketfood.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Category;
import com.example.fibasketfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MenuViewHolder extends FirebaseRecyclerAdapter<Category, MenuViewHolder.mHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param mOptions
     */
    public MenuViewHolder(@NonNull FirebaseRecyclerOptions<Category> mOptions) {
        super(mOptions);
    }

    @Override
    protected void onBindViewHolder(@NonNull mHolder holder, int position, @NonNull Category model) {
        holder.txtMenuName.setText(model.getName());
        Picasso.get().load(model.getImage())
                .into(holder.imgView);
        Category clickItem = model;
        mHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

            }
        });
    }

    @NonNull
    @Override
    public mHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new mHolder(view);
    }

    class mHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtMenuName;
        public ImageView imgView;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public mHolder(@NonNull View itemView) {
            super(itemView);

            txtMenuName = itemView.findViewById(R.id.txtMenuName);
            imgView = itemView.findViewById(R.id.imgView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getBindingAdapterPosition(), false);
        }
    }
}
