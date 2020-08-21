package com.education.problemsolve;

import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class castomadapter extends  RecyclerView.Adapter<castomadapter.MyviewHolder>  {
    private castomadapter2.onItemclicklistner listner;




    private Context context2;
    private List<upload> uploadList;



    public castomadapter(Context context, List<upload> uploadList) {
        this.context2 = context;
        this.uploadList = uploadList;

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context2);
        View view=layoutInflater.inflate(R.layout.res,parent,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        upload up=uploadList.get(position);

        if (!((up.getAns().isEmpty() & up.getImagename().isEmpty())))
        {
            holder.textView.setText("Question: "+up.getImagename());
            holder.textView2.setText("Ans: "+up.getAns());

        }
        if(!(up.getImagename().isEmpty()) & (up.getAns().isEmpty())){
            holder.textView.setText("Question: "+up.getImagename());

        }
        if((up.getImagename().isEmpty()) & !(up.getAns().isEmpty())){

            holder.textView2.setText("Ans: "+up.getAns());

        }



            Picasso.with(context2).load(up.getImageurl())
                    .placeholder(R.drawable.ld)
                    .fit()
                    .into(holder.imageView);
            Picasso.with(context2).load(up.getAnspicurl())
                    .placeholder(R.drawable.ld)
                    .fit()
                    .into(holder.imageView2);









    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }


    public  class MyviewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener ,View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener {
        TextView textView;
        TextView textView2;
        ImageView imageView;
        ImageView imageView2;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardaboutid);
            textView2 = itemView.findViewById(R.id.cardaboutid2);
            imageView = itemView.findViewById(R.id.cardimageveiw);
          imageView2 = itemView.findViewById(R.id.cardimageveiw2);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);




            // itemView.setOnClickListener(this);
           // itemView.setOnClickListener(this);
           // itemView.setOnCreateContextMenuListener(this);
            // itemView.setOnCreateContextMenuListener(this);


        }


        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            int position=getAdapterPosition();
            if(position!=RecyclerView.NO_POSITION){

                switch (menuItem.getItemId()){
                    case 1:
                        listner.ondoanytaskk(position);
                        return  true;


                    case 2:
                        listner.ondelete(position);
                        return true;




                }
            }


            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem doanytaskk=contextMenu.add(Menu.NONE,1,1,"Solve this");
            MenuItem delete=contextMenu.add(Menu.NONE,2,2,"Zoom");
            doanytaskk.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);


        }
    }
    public interface onItemclicklistner{
        void onItemclick(int position);
        void  ondoanytaskk(int position);
        void ondelete(int position);
    }
    public void setonitemclicklistner(castomadapter2.onItemclicklistner listner){

        this.listner=listner;
    }






}
