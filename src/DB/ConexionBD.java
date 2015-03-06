package DB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ConexionBD extends SQLiteOpenHelper{
	
	private String DB_PATH;
    private static String DB_NAME = "encuestas.db";
    private SQLiteDatabase myDataBase; 
    private final Context myContext;
    
    SQLiteDatabase dataBase = null;

    public ConexionBD(Context context, String path) {
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
        this.DB_PATH = path+"/";
    }	
 
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    	}else{
    		this.getReadableDatabase();
        	try {
    			copyDataBase();
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}
    }

    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
    	}
    	if(checkDB != null){
    		checkDB.close();
    	}
    	return checkDB != null ? true : false;
    }
    
    private void copyDataBase() throws IOException{
 
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
    	String outFileName = DB_PATH + DB_NAME;
 
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public SQLiteDatabase openDataBase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	return myDataBase;
    }
    
    public SQLiteDatabase openToEditDataBase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    	return myDataBase;
    }
    
    //Llamado desde ConsumoWS
    public int getEncuestaSeleccionada(){
    	dataBase = openDataBase();
		int encuestaSeleccionada = 0;
		Cursor cursor = dataBase.rawQuery("SELECT idEncuesta FROM encuesta_selec", null);
		cursor.moveToNext();
		do{
			encuestaSeleccionada = cursor.getInt(0);
		}while(cursor.moveToNext());
		close();
		return encuestaSeleccionada;
	}
 
    public void setEncuestaSeleccionada(int idEncuesta, int idNEncuesta, String nomEncuesta){
    	dataBase = openToEditDataBase();
    	ContentValues contentValue = new ContentValues();
    	contentValue.put("idEncuesta", idNEncuesta);
    	int res = dataBase.update("encuesta_selec", contentValue, "idEncuesta = "+idEncuesta, null);
    	Toast.makeText(myContext, "Encuesta '"+ nomEncuesta +"' Seleccionada ", Toast.LENGTH_LONG).show();
    	System.out.print(res);
    }
    
    @Override
	public synchronized void close() {
	    if(myDataBase != null)
		    myDataBase.close();
	    super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
