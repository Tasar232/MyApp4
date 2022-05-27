package com.example.myapp4.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.ItemStoCar;
import com.example.myapp4.logic.sto.StoCar;

import java.util.ArrayList;

public class SQLiteDBCar extends SQLiteOpenHelper implements Repository_data_car {
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
    public static final String GOS_NUMBER = "Gos_number";

    //STO
    public static final String TABLE_STO = "STO";
    public static final String ID_STO = "ID_sto";
    public static final String DATE = "Date";
    public static final String NAME_COMPANY = "Name_company";
    //public static final String TOTAL_PRICE = "Total_price";
    public static final String TEXT_DESCRIPTION = "Text_description";
    public static final String MILEAGE_NOW = "Mileage_now";

    //Type work how table
    public static final String TABLE_TYPE_WORK = "TYPE_WORK";
    public static final String NAME_TYPE_WORK = "Type_work";
    public static final String ID_TYPE_WORK = "ID_type_work";

    //Item
    public static final String TABLE_ITEM = "ITEM";
    public static final String ID_ITEM = "ID_item";
    public static final String CODE_ITEM = "Code_item";
    public static final String NAME_ITEM = "Name_item";
    public static final String COUNT_ITEM = "Count_item";
    public static final String PRICE_ITEM = "Price_item";
    public static final String PRICE_WORK = "Price_work";

    //Type work how table
    public static final String TABLE_TYPE_ITEM = "TYPE_ITEM";
    public static final String NAME_TYPE_ITEM = "Type_item";
    public static final String ID_TYPE_ITEM = "ID_type_item";

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
            NUMBER_STS + " INTEGER,\n" +
            GOS_NUMBER + " TEXT\n" +
            ");";
    private static final String CREATE_TABLE_TYPE_WORK = "CREATE TABLE " + TABLE_TYPE_WORK + " (\n" +
            ID_TYPE_WORK + " INTEGER PRIMARY KEY,\n" +
            NAME_TYPE_WORK + " TEXT\n" +
            ");";
    private static final String CREATE_TABLE_TYPE_ITEM = "CREATE TABLE " + TABLE_TYPE_ITEM + " (\n" +
            ID_TYPE_ITEM + " INTEGER PRIMARY KEY,\n" +
            NAME_TYPE_ITEM + " TEXT\n" +
            ");";
    private static final String CREATE_TABLE_STO = "CREATE TABLE " + TABLE_STO + " (\n" +
            ID_STO + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ID_CAR + " INTEGER,\n" +
            ID_TYPE_WORK + " INTEGER,\n" +
            DATE + " TEXT,\n" +
            MILEAGE_NOW + " INTEGER, \n" +
            NAME_COMPANY + " TEXT,\n" +
            TEXT_DESCRIPTION + " TEXT,\n" +
            " FOREIGN KEY( " + ID_CAR + ") REFERENCES " + TABLE_CAR + "(" + ID_CAR + ") ON DELETE CASCADE,\n" +
            " FOREIGN KEY( " + ID_TYPE_WORK + ") REFERENCES " + NAME_TYPE_WORK + "(" + ID_TYPE_WORK + ") ON DELETE CASCADE\n" +
            ");";
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE " + TABLE_ITEM + " (\n" +
            ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ID_STO + " INTEGER,\n" +
            ID_TYPE_ITEM + " INTEGER,\n" +
            CODE_ITEM + " TEXT,\n" +
            NAME_ITEM + " TEXT,\n" +
            COUNT_ITEM + " INTEGER,\n" +
            PRICE_ITEM + " INTEGER,\n" +
            PRICE_WORK + " INTEGER,\n" +
            " FOREIGN KEY(" + ID_STO + ") REFERENCES " + TABLE_STO + "(" + ID_STO + ") ON DELETE CASCADE\n" +
            ");";
    private static final String CREATE_TABLE_OSAGO = "CREATE TABLE " + TABLE_OSAGO + " (\n" +
            ID_OSAGO + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ID_CAR + " INTEGER,\n" +
            NAME_COMPANY_OSAGO + " TEXT,\n" +
            SERIES + " TEXT,\n" +
            NUMBER + " INTEGER,\n" +
            DATE_VALIDITY + " TEXT,\n" +
            FIO_OWNER + " TEXT,\n" +
            " FOREIGN KEY(" + ID_CAR + ") REFERENCES " + TABLE_CAR + "(" + ID_CAR + ") ON DELETE CASCADE\n" +
            ");";

    public SQLiteDBCar(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CAR);
        db.execSQL(CREATE_TABLE_TYPE_WORK);
        db.execSQL(CREATE_TABLE_STO);
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_TYPE_ITEM);
        db.execSQL(CREATE_TABLE_OSAGO);

        db.execSQL("INSERT INTO " + TABLE_TYPE_WORK +
                " (" + ID_TYPE_WORK + ", "+ NAME_TYPE_WORK +") " +
                "VALUES (" + 1 + ", 'Расходники'),\n" +
                "(" + 2 + ", 'Ремонт'),\n" +
                "(" + 3 + ", 'Ремонт своими рукми')\n"
        );
        db.execSQL("INSERT INTO " + TABLE_TYPE_ITEM +
                " (" + ID_TYPE_ITEM+ ", "+ NAME_TYPE_ITEM +") " +
                "VALUES (" + 1 + ", 'Запчасть'),\n" +
                "(" + 2 + ", 'Работа')"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("ALTER TABLE " + TABLE_CAR + " ADD COLUMN " + GOS_NUMBER + " TEXT;");
    }


    @Override
    public ArrayList<Car> getAllCarsData() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorCar = null;
        ArrayList<Car> listCar = new ArrayList<>();

        String GET_CAR_QUERY = "SELECT \n" +
                ID_CAR + ",\n" +
                MARK + ",\n" +
                MODEL + ",\n" +
                MILEAGE + ",\n" +
                YEAR + ",\n" +
                SERIES_STS + ",\n" +
                NUMBER_STS + ",\n" +
                GOS_NUMBER + "\n" +
                "FROM " + TABLE_CAR + "\n";

        try {
            cursorCar = db.rawQuery(GET_CAR_QUERY, null);
            if (cursorCar.moveToFirst()){

                do {
                    int ID_column = cursorCar.getColumnIndex(ID_CAR);
                    int Mark_column = cursorCar.getColumnIndex(MARK);
                    int Model_column = cursorCar.getColumnIndex(MODEL);
                    int Mileage_column = cursorCar.getColumnIndex(MILEAGE);
                    int Year_column = cursorCar.getColumnIndex(YEAR);
                    int SerSts_column = cursorCar.getColumnIndex(SERIES_STS);
                    int NumSts_column = cursorCar.getColumnIndex(NUMBER_STS);
                    int GosNum_column = cursorCar.getColumnIndex(GOS_NUMBER);
                    Car car = new Car(
                            cursorCar.getInt(ID_column),
                            cursorCar.getString(Mark_column),
                            cursorCar.getString(Model_column),
                            cursorCar.getInt(Mileage_column),
                            cursorCar.getInt(Year_column),
                            cursorCar.getString(SerSts_column),
                            cursorCar.getInt(NumSts_column),
                            cursorCar.getString(GosNum_column)
                    );

                    car.getListSto().addAll(getListSTO(cursorCar.getInt(ID_column)));
                    listCar.add(car);
                }
                while (cursorCar.moveToNext());
            }
            else {

                //row count 0
            }
        }
        catch (Exception ex){

        } 
        finally {
            if (cursorCar != null && !cursorCar.isClosed()) {
                cursorCar.close();
        }
    }
        
        return listCar;
    }

    private ArrayList<StoCar> getListSTO(int id_car){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorCarSTO = null;
        ArrayList<StoCar> list_sto = new ArrayList<>();
        String GET_CAR_STO_QUERY = "SELECT \n" +
                ID_STO + ",\n" +
                ID_TYPE_WORK + ",\n" +
                DATE + ",\n" +
                MILEAGE_NOW + ",\n" +
                NAME_COMPANY + ",\n" +
                TEXT_DESCRIPTION + "\n" +
                "FROM " + TABLE_STO + "\n" +
                "WHERE " + ID_CAR + " = " + id_car;
        try {
             cursorCarSTO = db.rawQuery(GET_CAR_STO_QUERY, null);
             if(cursorCarSTO.moveToFirst()){
                 do{
                     int id_sto_column = cursorCarSTO.getColumnIndex(ID_STO);
                     int id_work_column = cursorCarSTO.getColumnIndex(ID_TYPE_WORK);
                     int date_column = cursorCarSTO.getColumnIndex(DATE);
                     int mileage_now_column = cursorCarSTO.getColumnIndex(MILEAGE_NOW);
                     int name_company_column = cursorCarSTO.getColumnIndex(NAME_COMPANY);
                     int text_description_column = cursorCarSTO.getColumnIndex(TEXT_DESCRIPTION);

                     StoCar carSTO = new StoCar(
                             cursorCarSTO.getInt(id_sto_column),
                             cursorCarSTO.getInt(id_work_column),
                             cursorCarSTO.getString(date_column),
                             cursorCarSTO.getInt(mileage_now_column),
                             cursorCarSTO.getString(name_company_column),
                             cursorCarSTO.getString(text_description_column)
                     );
                     carSTO.getListItemSTO().addAll(getListItemSTO(cursorCarSTO.getInt(id_sto_column)));
                     list_sto.add(carSTO);
                 }
                 while (cursorCarSTO.moveToNext());
             }
             else {
                 //row 0
             }
        }
        catch (Exception ex){

        }
        finally {
            if (cursorCarSTO != null && !cursorCarSTO.isClosed()) {
                cursorCarSTO.close();
            }
        }
        return list_sto;
    }

    private ArrayList<ItemStoCar> getListItemSTO(int id_sto){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorCarSTOItem = null;
        ArrayList<ItemStoCar> list_item = new ArrayList<>();
        String GET_CAR_STO_ITEM_QUERY = "SELECT \n" +
                ID_ITEM + ",\n" +
                ID_TYPE_ITEM + ",\n" +
                CODE_ITEM + ",\n" +
                NAME_ITEM + ",\n" +
                COUNT_ITEM + ",\n" +
                PRICE_ITEM + ",\n" +
                PRICE_WORK + "\n" +
                "FROM " + TABLE_ITEM + "\n" +
                "WHERE " + ID_STO + " = " + id_sto;

        try{
            cursorCarSTOItem = db.rawQuery(GET_CAR_STO_ITEM_QUERY, null);
            if(cursorCarSTOItem.moveToFirst()) {
                do{
                    int id_itemColumn = cursorCarSTOItem.getColumnIndex(ID_ITEM);
                    int id_typeWorkItemColumn = cursorCarSTOItem.getColumnIndex(ID_TYPE_ITEM);
                    int codeColumn = cursorCarSTOItem.getColumnIndex(CODE_ITEM);
                    int nameColumn = cursorCarSTOItem.getColumnIndex(NAME_ITEM);
                    int countColumn = cursorCarSTOItem.getColumnIndex(COUNT_ITEM);
                    int priceItemColumn = cursorCarSTOItem.getColumnIndex(PRICE_ITEM);
                    int priceWorkColumn = cursorCarSTOItem.getColumnIndex(PRICE_WORK);


                    ItemStoCar itemStoCar = new ItemStoCar(
                            cursorCarSTOItem.getInt(id_itemColumn),
                            cursorCarSTOItem.getInt(id_typeWorkItemColumn),
                            cursorCarSTOItem.getString(codeColumn),
                            cursorCarSTOItem.getString(nameColumn),
                            cursorCarSTOItem.getInt(countColumn),
                            cursorCarSTOItem.getInt(priceItemColumn),
                            cursorCarSTOItem.getInt(priceWorkColumn)
                    );
                    list_item.add(itemStoCar);

                }
                while (cursorCarSTOItem.moveToNext());
            }
            else {
                // row 0
            }
        }
        catch (Exception e){

        }
        finally {
            if (cursorCarSTOItem != null && !cursorCarSTOItem.isClosed()) {
                cursorCarSTOItem.close();
            }
        }


        return list_item;
    }

    @Override
    public void addCar(String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_CAR +
                " (" + MARK + "," + MODEL + "," + MILEAGE + "," + YEAR + "," + SERIES_STS + "," + NUMBER_STS + "," + GOS_NUMBER +") " +
                "VALUES ('" + mark + "', '" + model + "', " + mileage + ", " + year + ", '" + seriesSTS + "', " + numberSTS + ", '" + gosNumber +"')"
        );
    }

    @Override
    public void addSTO(int id_car, int id_work, String date, int mileage_now, String name_company, String description) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_STO +
                " (" + ID_CAR + ", " + ID_TYPE_WORK + ", " + DATE + ", " + MILEAGE_NOW + ", " + NAME_COMPANY + ", " + TEXT_DESCRIPTION + ") " +
                "VALUES (" + id_car + ", " + id_work + ", '" + date + "', " + mileage_now + ", '" + name_company + "', '" + description + "')"
        );
    }

    @Override
    public void addItem(int id_sto, int id_type_item, String code, String name, int count, int priceItem, int priceWork) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_ITEM +
                " (" + ID_STO + ", " + ID_TYPE_ITEM + ", " + CODE_ITEM + ", " + NAME_ITEM + ", " + COUNT_ITEM + ", " + PRICE_ITEM + ", " + PRICE_WORK +") " +
                "VALUES (" + id_sto + ", " + id_type_item + ", '" + code + "', '" + name + "', " + count + ", " + priceItem + ", " + priceWork + ")"
        );
    }

    @Override
    public void addPolicy() {

    }

    @Override
    public void updateCar(int id_car, String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_CAR + " SET\n" +
                MARK + " = '" + mark + "',\n" +
                MODEL + " = '" + model + "',\n" +
                MILEAGE + " = " + mileage + ",\n" +
                YEAR + " = " + year + ",\n" +
                SERIES_STS + " = '" + seriesSTS + "',\n" +
                NUMBER_STS + " = " + numberSTS + ",\n" +
                GOS_NUMBER + " = '" + gosNumber + "'\n" +
                "WHERE " + ID_CAR + " = " + id_car
        );
    }

    @Override
    public void updateSTO(int id_sto, int id_work, String date, int mileage_now, String name_company, String description) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_STO + " SET\n" +
                 ID_TYPE_WORK + " = " + id_work + ",\n" +
                 DATE + " = '" + date + "',\n" +
                 MILEAGE_NOW + " = " + mileage_now + ",\n" +
                 NAME_COMPANY + " = '" + name_company + "',\n" +
                 TEXT_DESCRIPTION + " = '" + description + "'\n" +
                "WHERE " + ID_STO + " = " + id_sto
        );
    }

    @Override
    public void updateItem(int id_item, int id_type_item, String code, String name, int count, int priceItem, int priceWork) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_ITEM + " SET\n" +
                ID_TYPE_ITEM + " = " + id_type_item + ",\n" +
                CODE_ITEM + " = '" + code + "',\n" +
                NAME_ITEM + " = '" + name + "',\n" +
                COUNT_ITEM + " = " + count + ",\n" +
                PRICE_ITEM + " = " + priceItem + ",\n" +
                PRICE_WORK + " = " + priceWork + "\n" +
                "WHERE " + ID_ITEM + " = " + id_item
        );
    }

    @Override
    public void updatePolicy() {

    }

    @Override
    public void deleteCar(int id_car) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CAR + " \n" +
                "WHERE " + ID_CAR + " = " + id_car
                );
    }

    @Override
    public void deleteSTO(int id_sto) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STO + " \n" +
                "WHERE " + ID_STO + " = " + id_sto
        );
    }

    @Override
    public void deleteItem(int id_item) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ITEM + "\n" +
                "WHERE " + ID_ITEM + " = " + id_item
        );
    }

    @Override
    public void deletePolicy() {

    }

    @Override
    public String getTypeWorkName(int id_type_name_work) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorWorkStr = null;
        String resultStrWork = "Не указано";
        String GET_WORK_STRING_QUERY = "SELECT \n" +
                NAME_TYPE_WORK + "\n" +
                "FROM " + TABLE_TYPE_WORK + "\n" +
                "WHERE " + ID_TYPE_WORK + " = " + id_type_name_work;

        try {
            cursorWorkStr = db.rawQuery(GET_WORK_STRING_QUERY, null);
            cursorWorkStr.moveToFirst();
            int workStrColumn = cursorWorkStr.getColumnIndex(NAME_TYPE_WORK);
            resultStrWork = cursorWorkStr.getString(workStrColumn);
        }
        finally {
            if (cursorWorkStr != null && !cursorWorkStr.isClosed()) {
                cursorWorkStr.close();
            }
        }
        return resultStrWork;
    }

    @Override
    public ArrayList<String> getAllTypeWorkName() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorAllTypeWork = null;
        ArrayList<String> resultWork = new ArrayList<>();
        String GET_WORK_STRING_QUERY = "SELECT \n" +
                NAME_TYPE_WORK + "\n" +
                "FROM " + TABLE_TYPE_WORK;

        try {
            cursorAllTypeWork = db.rawQuery(GET_WORK_STRING_QUERY, null);
            if(cursorAllTypeWork.moveToFirst()) {
                do{
                    int workStrColumn = cursorAllTypeWork.getColumnIndex(NAME_TYPE_WORK);
                    resultWork.add(cursorAllTypeWork.getString(workStrColumn));
                }
                while (cursorAllTypeWork.moveToNext());
            }
        }
        finally {
            if (cursorAllTypeWork != null && !cursorAllTypeWork.isClosed()) {
                cursorAllTypeWork.close();
            }
        }
        return resultWork;
    }

    @Override
    public String getTypeItemName(int id_type_name_item) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorTypeItem = null;
        String resultItem = "Не указано";
        String GET_WORK_STRING_QUERY = "SELECT \n" +
                NAME_TYPE_ITEM + "\n" +
                "FROM " + TABLE_TYPE_ITEM+ "\n" +
                "WHERE " + ID_TYPE_ITEM + " = " + id_type_name_item;

        try {
            cursorTypeItem = db.rawQuery(GET_WORK_STRING_QUERY, null);
            cursorTypeItem.moveToFirst();
            int workStrColumn = cursorTypeItem.getColumnIndex(NAME_TYPE_ITEM);
            resultItem = cursorTypeItem.getString(workStrColumn);
        }
        finally {
            if (cursorTypeItem != null && !cursorTypeItem.isClosed()) {
                cursorTypeItem.close();
            }
        }
        return resultItem;
    }

    @Override
    public ArrayList<String> getAllTypeItemName() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorAllTypeItem = null;
        ArrayList<String> resultArrItem = new ArrayList<>();
        String GET_WORK_STRING_QUERY = "SELECT \n" +
                NAME_TYPE_ITEM + "\n" +
                "FROM " + TABLE_TYPE_ITEM;

        try {
            cursorAllTypeItem = db.rawQuery(GET_WORK_STRING_QUERY, null);
            if(cursorAllTypeItem.moveToFirst()) {
                do{
                    int workStrColumn = cursorAllTypeItem.getColumnIndex(NAME_TYPE_ITEM);
                    resultArrItem.add(cursorAllTypeItem.getString(workStrColumn));
                }
                while (cursorAllTypeItem.moveToNext());
            }
        }
        finally {
            if (cursorAllTypeItem != null && !cursorAllTypeItem.isClosed()) {
                cursorAllTypeItem.close();
            }
        }
        return resultArrItem;
    }

    @Override
    public void addColumn(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("ALTER TABLE " + TABLE_ITEM +
                " ADD COLUMN " + ID_TYPE_WORK + ";"
        );
    }
}