package com.alanger.peruappstest.postDetail.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alanger.peruappstest.R;
import com.alanger.peruappstest.models.VO.ComentVO;

import java.util.List;


public class RViewAdapterComentsList
        extends RecyclerView.Adapter<RViewAdapterComentsList.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener onClickListener;
    private List<ComentVO> comentVOList;

    public RViewAdapterComentsList(List<ComentVO> comentVOList){
        this.comentVOList = comentVOList;
    }

    @Override
    public void onClick(View v) {
        if(onClickListener!=null){
            onClickListener.onClick(v);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public final View myView;

        TextView apdci_tViewComent;
        TextView apdci_tViewDateTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView;

            apdci_tViewComent = myView.findViewById(R.id.apdci_tViewComent);
            apdci_tViewDateTime= myView.findViewById(R.id.apdci_tViewDateTime);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_post_detail_coment_item,null,false);

        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    final int maxiCharacters = 80;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ComentVO item = comentVOList.get(position);

        if(item.getBody().length()> maxiCharacters){
            holder.apdci_tViewComent.setText(item.getBody().substring(0, maxiCharacters)+"..."); // cut body legth
        }else {
            holder.apdci_tViewComent.setText(item.getBody());
        }


        holder.apdci_tViewDateTime.setText(""+item.getDateTime());

    }


    public void setOnClicListener(View.OnClickListener listener){
        this.onClickListener=listener;

    }
    @Override
    public int getItemCount() {
        return comentVOList.size();
    }
}
