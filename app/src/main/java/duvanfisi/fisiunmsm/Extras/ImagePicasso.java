package duvanfisi.fisiunmsm.Extras;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import duvanfisi.fisiunmsm.R;

public class ImagePicasso {

    public static void setImageCenterCop(Context context, int image, ImageView imageView){
        Picasso.with(context).load(image).fit().centerCrop().into(imageView);
    }

    public static void setImageCenterCop(Context context, String image, ImageView imageView){
        Picasso.with(context).load(image).fit().centerCrop().into(imageView);
    }

    public static void setImageCenterInside(Context context, String image, ImageView imageView){
        Picasso.with(context).load(image).fit().centerInside().into(imageView);
    }

    public static void setImageCenterInsideWrap(Context context, String imagen, ImageView imageView){
        Picasso.with(context).load(imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fit()
                .centerInside()
                .into(imageView);
    }
    public static void setImageCenterInsideWrap(Context context, int imagen, ImageView imageView){
        Picasso.with(context).load(imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fit()
                .centerInside()
                .into(imageView);
    }

    public static void setImageCenterInside(Context context, int image, ImageView imageView){
        Picasso.with(context).load(image).fit().centerInside().into(imageView);
    }

}
