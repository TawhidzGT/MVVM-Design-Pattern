package com.example.mvvmdesignpattern.recyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdesignpattern.R;
import com.example.mvvmdesignpattern.model.SampleApi;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ApiRecyclerViewAdapter extends RecyclerView.Adapter<ApiRecyclerViewAdapter.Todo_View_Holder> {
    private Context context;
    private List<SampleApi> sampleApiList;

    public ApiRecyclerViewAdapter(List<SampleApi> apiList, Context c) {
        sampleApiList = apiList;
        context = c;
    }

    static class Todo_View_Holder extends RecyclerView.ViewHolder {
        private TextView titleCard,idCard,albumIdCard;
        private ImageView uriCard;

        Todo_View_Holder(View view) {
            super(view);
            titleCard=view.findViewById(R.id.titleCard);
            idCard=view.findViewById(R.id.idCard);
            albumIdCard=view.findViewById(R.id.albumIdCard);
            uriCard=view.findViewById(R.id.uriCard);
        }
    }

    @NonNull
    @Override
    public Todo_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card, parent, false);

        return new Todo_View_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final Todo_View_Holder holder, int position) {
        final SampleApi apiResult = sampleApiList.get(position);
        holder.titleCard.setText(apiResult.getTitle());
        holder.idCard.setText(String.valueOf(apiResult.getId()));
        holder.albumIdCard.setText(String.valueOf(apiResult.getAlbumId()));
        //Glide.with(context).load(apiResult.getUrl()).apply(RequestOptions.circleCropTransform()).into(holder.uriCard);
        Picasso.get().load(apiResult.getUrl()).resize(100, 100).centerCrop().into(holder.uriCard);
      /*  Glide.with(context)
                .load(apiResult.getUrl())
                .disallowHardwareConfig()
                .into(holder.uriCard);*/
    }

    @Override
    public int getItemCount() {
        return sampleApiList.size();
    }
}