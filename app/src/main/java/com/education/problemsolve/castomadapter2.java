package com.education.problemsolve;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class castomadapter2 extends  RecyclerView.Adapter<castomadapter2.MyviewHolder2> {
    private Context context;
    private List<upload> uploadList;

     private  onItemclicklistner listner;

    public castomadapter2(Context context, List<upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }
    @NonNull
    @Override
    public MyviewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.res2,parent,false);

        return new MyviewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder2 holder, int position) {
        upload up=uploadList.get(position);
        holder.textView.setText("question: "+up.getImagename());
        Picasso.with(context).load(up.getImageurl())
                .placeholder(R.drawable.ld)
                .fit()
                .into(holder.imageView);


    }




    @Override
    public int getItemCount() {
        return uploadList.size();
    }

  /*  @Override
    public void onClick(View view) {

    }
    */


    public class MyviewHolder2 extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener {
        TextView textView;
        ImageView imageView;
        public MyviewHolder2(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.cardaboutid);
            imageView= itemView.findViewById(R.id.cardimageveiw);
             itemView.setOnClickListener(this);
             itemView.setOnCreateContextMenuListener(this);



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
        public void onClick(View view) {


        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            //contextMenu.setHeaderTitle("chose");
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
    public void setonitemclicklistner(onItemclicklistner listner){

        this.listner=listner;
    }



}
