package com.example.fibasketfood.ViewHolder;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Database.OrderContract;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class FoodViewHolder extends FirebaseRecyclerAdapter<Food, FoodViewHolder.fHolder> {

    private int mAmount = 1;
    private final ItemClickListener itemClickListener;
    private SQLiteDatabase mDatabase;
    private CartAdapter mAdapter;

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

                if (holder.txtFoodName.getText().toString().trim().length() == 0 || mAmount == 0) {
                    return;
                }

                String nameValue = getItem(position).getName().toString();

                ContentValues values = new ContentValues();
                values.put(OrderContract.OrderEntry.COLUMN_NAME, nameValue);
                values.put(OrderContract.OrderEntry.COLUMN_AMOUNT, mAmount);

                mDatabase.insert(OrderContract.OrderEntry.TABLE_NAME, null, values);
                mAdapter.swapCursor(getAllItems());

            }
        });

        holder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.imageAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                OrderContract.OrderEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                OrderContract.OrderEntry.COLUMN_TIMESTAMP + " DESC"
        );
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

        public fHolder(@NonNull View itemView) {
            super(itemView);

            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            imgFoodView = itemView.findViewById(R.id.imgFoodView);
            tvCount = itemView.findViewById(R.id.tvCount);
            imgAddToBasket = itemView.findViewById(R.id.imgAddToBasket);
            imageMinus = itemView.findViewById(R.id.imageMinus);
            imageAddOne = itemView.findViewById(R.id.imageAddOne);

        }
    }

}
