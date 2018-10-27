package assignment.telstra.com.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


import assignment.telstra.com.R;
import assignment.telstra.com.model.RowItem;

public class CountryInfoAdapter extends RecyclerView.Adapter<CountryInfoAdapter.MyViewHolder>  {

    private Context mContext;
    private List<RowItem> rowItems;

    CountryInfoAdapter(Context mContext, List<RowItem> rowItems) {
        this.mContext = mContext;
        this.rowItems = rowItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.list_row_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RowItem item=rowItems.get(position);

        if (!TextUtils.isEmpty(rowItems.get(position).getTitle())){
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(item.getTitle());
        }else {
            holder.title.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(rowItems.get(position).getImageHref())){
            holder.imgIcon.setVisibility(View.VISIBLE);
            Picasso.get().load(item.getImageHref()).into(holder.imgIcon);
        }else {
            holder.imgIcon.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(rowItems.get(position).getDescription())){
            holder.tvDesc.setVisibility(View.VISIBLE);
            holder.tvDesc.setText(item.getDescription());
        }else {
            holder.tvDesc.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public void updateList(List<RowItem> rows) {
        this.rowItems=rows;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView tvDesc;
        private final ImageView imgIcon;

        MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            tvDesc = itemView.findViewById(R.id.text_desc);
            imgIcon = itemView.findViewById(R.id.img_icon);
        }
    }
}
