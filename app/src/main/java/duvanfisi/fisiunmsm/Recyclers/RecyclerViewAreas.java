package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Fragments.CArea;
import duvanfisi.fisiunmsm.R;

public class RecyclerViewAreas extends RecyclerView.Adapter<RecyclerViewAreas.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private HashMap<Integer, CArea> cAreaHashMap;
    private Context mContext;

    public RecyclerViewAreas(Context context, HashMap<Integer, CArea> cAreaHashMap) {
       this.cAreaHashMap= cAreaHashMap;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.areas_list,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

      Picasso.with(mContext)
                .load(cAreaHashMap.get(position).getImg())
                .fit()
                .centerCrop()
                .into(holder.image_services);

    }

    @Override
    public int getItemCount() {
        return cAreaHashMap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_services;


        public ViewHolder(View itemView) {
            super(itemView);
            image_services = itemView.findViewById(R.id.image_area);

        }
    }
}