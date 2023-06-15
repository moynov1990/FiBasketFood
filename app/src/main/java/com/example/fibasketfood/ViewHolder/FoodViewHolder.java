package com.example.fibasketfood.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodViewHolder extends FirebaseRecyclerAdapter<Food, FoodViewHolder.fHolder> {

    private final ItemClickListener itemClickListener;

    private List<Food> foodList;
    private FoodListClickListener clickListener;


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


        holder.imgAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food  = foodList.get(position);
                food.setTotalInCart(1);
                clickListener.onAddToCartClick(food);
                holder.tvCount.setText(food.getTotalInCart()+"");
            }
        });

        holder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food  = foodList.get(position);
                int total = food.getTotalInCart();
                total--;
                if(total > 0 ) {
                    food.setTotalInCart(total);
                    clickListener.onUpdateCartClick(food);
                    holder.tvCount.setText(total +"");
                } else {
                    food.setTotalInCart(total);
                    clickListener.onRemoveFromCartClick(food);
                }
            }
        });

        holder.imageAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food  = foodList.get(position);
                int total = food.getTotalInCart();
                total++;
                if(total <= 10 ) {
                    food.setTotalInCart(total);
                    clickListener.onUpdateCartClick(food);
                    holder.tvCount.setText(total +"");
                }
            }
        });


    }

    @NonNull
    @Override
    public FoodViewHolder.fHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new fHolder(view);
    }

    class fHolder extends RecyclerView.ViewHolder {

        public TextView txtFoodName, tvCount;
        public ImageView imgFoodView, imgAddToBasket, imageMinus, imageAddOne;
//        public ElegantNumberButton numBtnFood;  //кнопка із -1+
        public String foodID = "";


        public fHolder(@NonNull View itemView) {
            super(itemView);

            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            imgFoodView = itemView.findViewById(R.id.imgFoodView);
            tvCount = itemView.findViewById(R.id.tvCount);
            imgAddToBasket = itemView.findViewById(R.id.imgAddToBasket);
            imageMinus = itemView.findViewById(R.id.imageMinus);
            imageAddOne = itemView.findViewById(R.id.imageAddOne);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener !=null) {
                        int pos = getAbsoluteAdapterPosition();

                        itemClickListener.onClick(pos);
                    }
                }
            });



        }
    }
    public interface FoodListClickListener {
        public void onAddToCartClick(Food food);
        public void onUpdateCartClick(Food food);
        public void onRemoveFromCartClick(Food food);
    }
}
