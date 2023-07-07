package com.example.fibasketfood.ViewHolder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Database.OrderContract;
import com.example.fibasketfood.R;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public CartAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public TextView txtFoodName;
        public TextView tvCount;

        public CartViewHolder(View itemView) {
            super(itemView);

            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            tvCount = itemView.findViewById(R.id.tvCount);
        }
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

            String name = mCursor.getString(mCursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME));
            int amount = mCursor.getInt(mCursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_AMOUNT));

            holder.txtFoodName.setText(name);
            holder.tvCount.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
