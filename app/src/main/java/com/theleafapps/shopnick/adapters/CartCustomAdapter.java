package com.theleafapps.shopnick.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.theleafapps.shopnick.R;
import com.theleafapps.shopnick.models.CartItem;
import com.theleafapps.shopnick.models.multiples.CartItems;
import com.theleafapps.shopnick.utils.MySingleton;

import java.util.List;

/**
 * Created by aviator on 08/07/16.
 */
public class CartCustomAdapter extends RecyclerView.Adapter<CartCustomAdapter.MyViewHolder>  implements View.OnClickListener{

    Context mContext;
    CartItems cartItems;
    List<CartItem> cartItemList;
    LayoutInflater inflater;
    private ImageLoader mImageLoader;
    int position;

    public CartCustomAdapter(Context context, CartItems cartItems) {
        inflater            =   LayoutInflater.from(context);
        this.mContext       =   context;
        this.cartItems      =   cartItems;

        if(this.cartItems!=null)
            this.cartItemList   =   cartItems.cartItemList;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =  inflater.inflate(R.layout.single_row_cart,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CartItem current = cartItemList.get(position);

        mImageLoader = MySingleton.getInstance(mContext).getImageLoader();

        if(!TextUtils.isEmpty(current.product.image_url)){
            String image_url = current.product.image_url;
            image_url = image_url.replace(".jpg","s.jpg");
            current.product.image_url = image_url;
        }

        holder.cart_product_image.setImageUrl(current.product.image_url,mImageLoader);
        holder.product_name.setText(current.product.product_name);
        holder.product_size.setText(current.variant);
        holder.product_qty.setText(String.valueOf(current.quantity));
        holder.product_mrp.setText(String.valueOf(current.product.unit_mrp));
        holder.multiply_product_qty.setText(String.valueOf(current.quantity));
        holder.net_cost.setText(String.valueOf(current.product.unit_mrp));
        holder.cart_item_id.setText(String.valueOf(current.cart_item_id));

    }

    @Override
    public int getItemCount() {
        if(cartItems!=null && cartItems.cartItemList.size() != 0) {
            return cartItems.cartItemList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView cart_product_image;
        TextView product_name,product_size,product_qty,unit_shipping;
        TextView net_cost,product_mrp,multiply_product_qty;
        TextView cart_item_id;

        public MyViewHolder(View itemView) {
            super(itemView);

            cart_product_image      =   (NetworkImageView) itemView.findViewById(R.id.cartItemImageView);
            product_name            =   (TextView) itemView.findViewById(R.id.product_name_value);
            product_size            =   (TextView) itemView.findViewById(R.id.size_value);
            product_qty             =   (TextView) itemView.findViewById(R.id.product_quantity_value);
            product_mrp             =   (TextView) itemView.findViewById(R.id.mrp_value);
            multiply_product_qty    =   (TextView) itemView.findViewById(R.id.multiply_product_qty);
            net_cost                =   (TextView) itemView.findViewById(R.id.net_cost);
            unit_shipping           =   (TextView) itemView.findViewById(R.id.unit_shipping_value);
            cart_item_id            =   (TextView) itemView.findViewById(R.id.cart_item_id);
        }
    }
}
