package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewFunction {

    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;

    public static void recyclerview(RecyclerView recyclerView, Context context, int orientation, RecyclerView.Adapter adapter){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, orientation, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
    }

}
