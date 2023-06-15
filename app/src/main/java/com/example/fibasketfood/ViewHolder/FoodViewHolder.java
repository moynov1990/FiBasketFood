package com.example.fibasketfood.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodViewHolder extends FirebaseRecyclerAdapter<Food, FoodViewHolder.fHolder> {

    private final ItemClickListener itemClickListener;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param fOptions
     */
    public FoodViewHolder(@NonNull FirebaseRecyclerOptions<Food> fOptions, ItemClickListener itemClickListener) {
        super(fOptions);

        this.itemClickListener = itemClickListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull FoodViewHolder.fHolder holder, int position, @NonNull Food model) {
        holder.txtFoodName.setText(model.getName());
        Picasso.get().load(model.getImage())
                .into(holder.imgFoodView);
    }

    @NonNull
    @Override
    public FoodViewHolder.fHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new fHolder(view);
    }

    class fHolder extends RecyclerView.ViewHolder {

        public TextView txtFoodName;
        public ImageView imgFoodView, imgAddToBasket;
        public ElegantNumberButton numBtnFood;
        public String foodID = "";


        public fHolder(@NonNull View itemView) {
            super(itemView);

            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            imgFoodView = itemView.findViewById(R.id.imgFoodView);
            numBtnFood = itemView.findViewById(R.id.numBtnFood);
            imgAddToBasket = itemView.findViewById(R.id.imgAddToBasket);

            imgAddToBasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
