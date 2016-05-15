package nav.com.nfproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by naveensingh on 15/05/16.
 */
public class MovieDatabase extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "MovieDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String MOVIE_TABLE_NAME = "MovieList";
    private static final String MOVIE_TITLE = "Title";
    private static final String MOVIE_DESCRIPTION = "Description";
    private static final String MOVIE_YEAR = "Year";
    private static final String MOVIE_IMAGE = "Image";


    String CREATE_MOVIE_TABLE = "CREATE TABLE if not exists " + MOVIE_TABLE_NAME +
            "(" + MOVIE_TITLE + " TEXT,"
            + MOVIE_DESCRIPTION + " TEXT," + MOVIE_YEAR + " TEXT," + MOVIE_IMAGE + " TEXT)";

    public MovieDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addMovie(MovieDataModel data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIE_TITLE, data.getTitle());
        values.put(MOVIE_DESCRIPTION, data.getDescription());
        values.put(MOVIE_YEAR, data.getYear());
        values.put(MOVIE_IMAGE, data.getImageUrl());
        db.insert(MOVIE_TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<MovieDataModel> getAllMovieData(){
        ArrayList<MovieDataModel> list = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + MOVIE_TABLE_NAME  ;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    MovieDataModel data = new MovieDataModel();
                    data.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
                    data.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION)));
                    data.setYear(cursor.getString(cursor.getColumnIndex(MOVIE_YEAR)));
                    data.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE)));
                    list.add(data);
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
        // TODO: handle exception
        }
        return list;
    }
}
