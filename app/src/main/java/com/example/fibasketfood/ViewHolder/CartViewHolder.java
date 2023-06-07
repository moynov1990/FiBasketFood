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
import com.example.fibasketfood.Model.Cart;
import com.example.fibasketfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;


public class CartViewHolder extends FirebaseRecyclerAdapter<Cart, CartViewHolder.cHolder> {


    private final ItemClickListener itemClickListener;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param cOptions
     */
    public CartViewHolder(@NonNull FirebaseRecyclerOptions<Cart> cOptions, ItemClickListener itemClickListener) {
        super(cOptions);

        this.itemClickListener = itemClickListener;
    }

    @Override
    protected void onBindViewHolder(CartViewHolder.cHolder holder, int position, @NonNull Cart model) {
        holder.txtFoodName.setText(model.getName());
        Picasso.get().load(model.getImage())
                .into(holder.imgFoodView);
    }

    @NonNull
    @Override
    public CartViewHolder.cHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new cHolder(view);
    }


    class cHolder extends RecyclerView.ViewHolder {

        public TextView txtFoodName;
        public ImageView imgFoodView, imgAddToBasket;
        public ElegantNumberButton numBtnFood;
        public String foodID = "";


        public cHolder(@NonNull View itemView) {
            super(itemView);

            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            imgFoodView = itemView.findViewById(R.id.imgFoodView);
            numBtnFood = itemView.findViewById(R.id.numBtnFood);
            imgAddToBasket = itemView.findViewById(R.id.imgAddToBasket);

            imgAddToBasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();

                    itemClickListener.onClick(pos);
                }
            });
        }
    }
}
