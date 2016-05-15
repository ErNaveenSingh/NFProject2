package nav.com.nfproject2;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by naveensingh on 15/05/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int MOVIE_TYPE1 = 1;
    private final int MOVIE_TYPE2 = 2;

    private List<MovieDataModel> moviesList;
    private Context mContext;

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        public TextView title, description, year;

        public MyViewHolder1(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.row_type1_title);
            description = (TextView) view.findViewById(R.id.row_type1_description);
            year = (TextView) view.findViewById(R.id.row_type1_year);
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        public TextView title, description, year;
        public ImageView image;

        public MyViewHolder2(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.row_type2_title);
            description = (TextView) view.findViewById(R.id.row_type2_description);
            year = (TextView) view.findViewById(R.id.row_type2_year);
            image = (ImageView) view.findViewById(R.id.row_type2_image);
        }
    }


    public MoviesAdapter(Context context, List<MovieDataModel> moviesList) {
        mContext = context;
        this.moviesList = moviesList;
    }

    @Override
    public int getItemViewType(int position) {
        if (moviesList.get(position).getImageUrl().length()==0){
            return MOVIE_TYPE1;
        }else {
            return MOVIE_TYPE2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case MOVIE_TYPE1: {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_row_type1, parent, false);
                return new MyViewHolder1(itemView);
            }
            case MOVIE_TYPE2: {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_row_type2, parent, false);
                return new MyViewHolder2(itemView);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieDataModel dataModel = moviesList.get(position);
        if (holder.getItemViewType()==MOVIE_TYPE1){
            MyViewHolder1 myViewHolder1 = (MyViewHolder1)holder;
            myViewHolder1.title.setText(dataModel.getTitle());
            myViewHolder1.description.setText(dataModel.getDescription());
            myViewHolder1.year.setText(dataModel.getYear());
        }else{
            MyViewHolder2 myViewHolder2 = (MyViewHolder2)holder;
            myViewHolder2.title.setText(dataModel.getTitle());
            myViewHolder2.description.setText(dataModel.getDescription());
            myViewHolder2.year.setText(dataModel.getYear());
            Glide.with(mContext)
                    .load(dataModel.getImageUrl())
                    .centerCrop()
                    .crossFade()
                    .into(myViewHolder2.image);
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
