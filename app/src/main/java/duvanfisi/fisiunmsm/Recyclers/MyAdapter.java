package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Interfaces.ILoadMore;
import duvanfisi.fisiunmsm.Modelo.CUsuario;
import duvanfisi.fisiunmsm.R;

class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;
    public LoadingViewHolder(View view){
        super(view);
        progressBar = view.findViewById(R.id.progressbaritem);
    }
}

class itemViewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView nombres;
    TextView puesto;
    TextView nickname;
    TextView escuela;
    TextView facultad;
    Button button;

    public itemViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        nombres = itemView.findViewById(R.id.name_usuario);
        puesto = itemView.findViewById(R.id.puesto);
        nickname = itemView.findViewById(R.id.nickname);
        facultad = itemView.findViewById(R.id.facultad);
        escuela = itemView.findViewById(R.id.escuela);
    }
}

public class MyAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ILoadMore loadMore;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private HashMap<Integer, CUsuario> cUsuarioHashMap;
    private Context mContext;
    public boolean isLoading;
    public int visibleThreshold = 5;
    public int lastVisibleItem;
    public int totalItemCount;

    public MyAdapter(RecyclerView recyclerView, Context context, HashMap<Integer,CUsuario> cUsuarioHashMap) {
        this.cUsuarioHashMap = cUsuarioHashMap;
        mContext = context;
        final LinearLayoutManager linearLayout = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayout.getItemCount();
                lastVisibleItem = linearLayout.findLastVisibleItemPosition();
                if(!isLoading &&  totalItemCount <= (lastVisibleItem + visibleThreshold)){
                    if(loadMore != null){
                        loadMore.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return cUsuarioHashMap.get(position) == null ? VIEW_TYPE_LOADING: VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if(i == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.plantilla_usuarios, parent, false);
           // RecyclerViewUsuarios.ViewHolder holder = new RecyclerViewUsuarios.ViewHolder(view);
            return new itemViewHolder(view);
        }
        else if(i  == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_loading, parent, false);
            return new itemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof itemViewHolder){
                CUsuario usuario =  cUsuarioHashMap.get(position);
                itemViewHolder holder = (itemViewHolder) viewHolder;
            String punto = ".";
            int aux_position = position+1;
            String puesto = (Integer.toString(aux_position))+punto;

            holder.puesto.setText(puesto);
            holder.nombres.setText(cUsuarioHashMap.get(position).getNombre());
            if(cUsuarioHashMap.get(position).getNickname().equalsIgnoreCase("null")){
                holder.nickname.setText("no-nickname");
                ImagePicasso.setImageCenterCop(mContext,R.drawable.ic_nickname, holder.image);
            }else {
                ImagePicasso.setImageCenterCop(mContext,cUsuarioHashMap.get(position).getPhoto(), holder.image);
                holder.nickname.setText(cUsuarioHashMap.get(position).getNickname());
            }
            holder.escuela.setText(cUsuarioHashMap.get(position).getEscuela());
            holder.facultad.setText(cUsuarioHashMap.get(position).getFacultad());
        }
        else if(viewHolder instanceof  LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return cUsuarioHashMap.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}
