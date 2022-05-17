package com.example.myapp4.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapp4.logic.cars.Car;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper implements Repository_data_car {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "carBD";


    //Car
    public static final String TABLE_CAR = "CAR";
    public static final String ID_CAR = "ID_car";
    public static final String MARK = "Mark";
    public static final String MODEL = "Model";
    public static final String MILEAGE = "Mileage";
    public static final String YEAR = "Year";
    public static final String SERIES_STS = "Series_sts";
    public static final String NUMBER_STS = "Number_sts";

    //STO
    public static final String TABLE_STO = "STO";
    public static final String ID_STO = "ID_sto";
    public static final String DATE = "Date";
    public static final String NAME_COMPANY = "Name_company";
    public static final String TOTAL_PRICE = "Total_price";
    public static final String TEXT_DESCRIPTION = "Text_description";

    //Type work how table
    public static final String TABLE_TYPE_WORK = "TYPE_WORK";
    public static final String TYPE_WORK = "Type_work";
    public static final String ID_WORK = "ID_work";

    //Item
    public static final String TABLE_ITEM = "ITEM";
    public static final String ID_ITEM = "ID_item";
    public static final String NAME_ITEM = "Name_item";
    public static final String COUNT_ITEM = "Count_item";
    public static final String PRICE_ITEM = "Price_item";

    //OSAGO
    public static final String TABLE_OSAGO = "OSAGO";
    public static final String ID_OSAGO = "ID_osago";
    public static final String NAME_COMPANY_OSAGO = "Name_company_osago";
    public static final String SERIES = "Series";
    public static final String NUMBER = "Number";
    public static final String DATE_VALIDITY = "Date_validity";
    public static final String FIO_OWNER = "FIO_owner";

    //Query
    private static final String CREATE_TABLE_CAR = "CREATE TABLE " + TABLE_CAR + " (\n" +
            ID_CAR + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            MARK + " TEXT,\n" +
            MODEL + " TEXT,\n" +
            MILEAGE + " INTEGER,\n" +
            YEAR + " INTEGER,\n" +
            SERIES_STS + " TEXT,\n" +
            NUMBER_STS + " INTEGER\n" +
            ");";
    private static final String CREATE_TABLE_TYPE_WORK = "CREATE TABLE " + TABLE_TYPE_WORK + " (\n" +
            ID_WORK + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            TYPE_WORK + " TEXT\n" +
            ");";
    private static final String CREATE_TABLE_STO = "CREATE TABLE " + TABLE_STO + " (\n" +
            ID_STO + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ID_CAR + " INTEGER,\n" +
            ID_WORK + " INTEGER,\n" +
            DATE + " TEXT,\n" +
            NAME_COMPANY + " TEXT,\n" +
            TEXT_DESCRIPTION + " TEXT,\n" +
            TOTAL_PRICE + " INTEGER,\n" +
            " FOREIGN KEY( " + ID_CAR + ") REFERENCES " + TABLE_CAR + "(" + ID_CAR + "),\n" +
            " FOREIGN KEY( " + ID_WORK + ") REFERENCES " + TYPE_WORK + "(" + ID_WORK + ")\n" +
            ");";
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE " + TABLE_ITEM + " (\n" +
            ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ID_STO + " INTEGER,\n" +
            ID_WORK + " INTEGER,\n" +
            NAME_ITEM + " TEXT,\n" +
            COUNT_ITEM + " INTEGER,\n" +
            PRICE_ITEM + " INTEGER,\n" +
            " FOREIGN KEY(" + ID_WORK + ") REFERENCES " + TYPE_WORK + "(" + ID_WORK + "),\n" +
            " FOREIGN KEY(" + ID_STO + ") REFERENCES " + TABLE_STO + "(" + ID_STO + ")\n" +
            ");";
    private static final String CREATE_TABLE_OSAGO = "CREATE TABLE " + TABLE_OSAGO + " (\n" +
            ID_OSAGO + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ID_CAR + " INTEGER,\n" +
            NAME_COMPANY_OSAGO + " TEXT,\n" +
            SERIES + " TEXT,\n" +
            NUMBER + " INTEGER,\n" +
            DATE_VALIDITY + " TEXT,\n" +
            FIO_OWNER + " TEXT,\n" +
            " FOREIGN KEY(" + ID_CAR + ") REFERENCES " + TABLE_CAR + "(" + ID_CAR + ")\n" +
            ");";

    public DBhelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CAR);
        db.execSQL(CREATE_TABLE_TYPE_WORK);
        db.execSQL(CREATE_TABLE_STO);
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_OSAGO);

        db.execSQL("INSERT INTO " + TABLE_TYPE_WORK + " (" + ID_WORK + ", "+ TYPE_WORK +") VALUES (" + 1 + ", 'Расходники')");
        db.execSQL("INSERT INTO " + TABLE_TYPE_WORK + " (" + ID_WORK + ", "+ TYPE_WORK +") VALUES (" + 2 + ", 'Ремонт')");
        db.execSQL("INSERT INTO " + TABLE_TYPE_WORK + " (" + ID_WORK + ", "+ TYPE_WORK +") VALUES (" + 3 + ", 'Ремонт своими рукми')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    @Override
    public ArrayList<Car> getData() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorCar;
        ArrayList<Car> listCar = new ArrayList<>();
        //String GET_COUNT_CAR_QUERY = "SELECT COUNT(*) FROM " + TABLE_CAR;

        String GET_CAR_QUERY = "SELECT \n" +
                ID_CAR + ",\n" +
                MARK + ",\n" +
                MODEL + ",\n" +
                MILEAGE + ",\n" +
                YEAR + ",\n" +
                "FROM" + TABLE_CAR + "\n";

        try {
            cursorCar = db.rawQuery(GET_CAR_QUERY, null);
            if (cursorCar.moveToFirst()){

                do {
                    int ID = cursorCar.getColumnIndex(ID_CAR);
                    int Mark = cursorCar.getColumnIndex(MARK);
                    int Model = cursorCar.getColumnIndex(MODEL);
                    int Mileage = cursorCar.getColumnIndex(MILEAGE);
                    int Year = cursorCar.getColumnIndex(YEAR);
                    int SerSts = cursorCar.getColumnIndex(SERIES_STS);
                    int NumSts = cursorCar.getColumnIndex(NUMBER_STS);
                    Car car = new Car(
                            cursorCar.getInt(ID),
                            cursorCar.getString(Mark),
                            cursorCar.getString(Model),
                            cursorCar.getInt(Mileage),
                            cursorCar.getInt(Year),
                            cursorCar.getString(SerSts),
                            cursorCar.getInt(NumSts)
                    );
                }
                while (cursorCar.moveToNext());
            }
            else {
                //row count 0
            }
        }
        catch (Exception ex){

        }
        return listCar;
    }

    @Override
    public void addCar() {

    }

    @Override
    public void addSTO() {

    }

    @Override
    public void addItem() {

    }

    @Override
    public void addPolicy() {

    }

    @Override
    public void updateCar() {

    }

    @Override
    public void updateSTO() {

    }

    @Override
    public void updateItem() {

    }

    @Override
    public void updatePolicy() {

    }

    @Override
    public void dropCar() {

    }

    @Override
    public void dropSTO() {

    }

    @Override
    public void dropItem() {

    }

    @Override
    public void dropPolicy() {

    }
}
