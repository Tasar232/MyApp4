package com.example.myapp4.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.ItemServiceMaintenanceCar;
import com.example.myapp4.logic.sto.ServiceMaintenanceCar;

import java.util.ArrayList;

public class SQLiteDBCar extends SQLiteOpenHelper implements Repository_data_car {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "carBD";


    //Car
    public static final String TABLE_CAR = "CAR";
    public static final String ID_CAR = "id_car";
    public static final String MARK_CAR = "mark";
    public static final String MODEL_CAR = "model";
    public static final String MILEAGE_CAR = "mileage";
    public static final String YEAR_CAR = "year";
    //public static final String SERIES_STS = "Series_sts";
    //public static final String NUMBER_STS = "Number_sts";
    public static final String REG_CERTIFICATE_CAR = "reg_certificate";
    public static final String GOS_REG_NUMBER_CAR = "gos_reg_number";

    //STO
    public static final String TABLE_SERVICE_MAINTENANCE_ACT = "SERVICE_TECH_ACT";
    public static final String ID_SERVICE_ART = "id_service_act";
    public static final String DATE_OF_SERVICE_ACT = "date_of_service_act";
    public static final String NAME_COMPANY_SERVICE = "name_company_service";
    //public static final String TOTAL_PRICE = "Total_price";
    public static final String DESCRIPTION_SERVICE_ACT = "description_service_act";
    public static final String MILEAGE_MOMENT_SERVICE = "mileage_moment_service";

    //Type work how table
    public static final String TABLE_TYPE_WORK = "TYPE_WORK";
    public static final String NAME_TYPE_WORK = "type_work";
    public static final String ID_TYPE_WORK = "id_type_work";

    //Item
    public static final String TABLE_WORK_AND_SPARE_PART_ITEM = "WORK_AND_PART_ITEM";
    public static final String ID_ITEM = "id_item";
    public static final String CODE_SPARE_PART_ITEM = "code_spare_part_item";
    public static final String NAME_ITEM = "name_item";
    public static final String COUNT_ITEM = "count_item";
    public static final String PRICE_SPARE_PART_ITEM = "price_spare_part_item";
    public static final String PRICE_WORK_ITEM = "price_work_item";

    //Type work how table
    public static final String TABLE_TYPE_ITEM = "TYPE_ITEM";
    public static final String NAME_TYPE_ITEM = "type_item";
    public static final String ID_TYPE_ITEM = "id_type_item";

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
            MARK_CAR + " TEXT,\n" +
            MODEL_CAR + " TEXT,\n" +
            MILEAGE_CAR + " INTEGER,\n" +
            YEAR_CAR + " INTEGER,\n" +
            REG_CERTIFICATE_CAR + " TEXT,\n" +
            GOS_REG_NUMBER_CAR + " TEXT\n" +
            ");";
    private static final String CREATE_TABLE_TYPE_WORK = "CREATE TABLE " + TABLE_TYPE_WORK + " (\n" +
            ID_TYPE_WORK + " INTEGER PRIMARY KEY,\n" +
            NAME_TYPE_WORK + " TEXT\n" +
            ");";
    private static final String CREATE_TABLE_TYPE_ITEM = "CREATE TABLE " + TABLE_TYPE_ITEM + " (\n" +
            ID_TYPE_ITEM + " INTEGER PRIMARY KEY,\n" +
            NAME_TYPE_ITEM + " TEXT\n" +
            ");";
    private static final String CREATE_TABLE_STO = "CREATE TABLE " + TABLE_SERVICE_MAINTENANCE_ACT + " (\n" +
            ID_SERVICE_ART + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ID_CAR + " INTEGER,\n" +
            ID_TYPE_WORK + " INTEGER,\n" +
            DATE_OF_SERVICE_ACT + " TEXT,\n" +
            MILEAGE_MOMENT_SERVICE + " INTEGER, \n" +
            NAME_COMPANY_SERVICE + " TEXT,\n" +
            DESCRIPTION_SERVICE_ACT + " TEXT,\n" +
            " FOREIGN KEY( " + ID_CAR + ") REFERENCES " + TABLE_CAR + "(" + ID_CAR + ") ON DELETE CASCADE,\n" +
            " FOREIGN KEY( " + ID_TYPE_WORK + ") REFERENCES " + NAME_TYPE_WORK + "(" + ID_TYPE_WORK + ") ON DELETE CASCADE\n" +
            ");";
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE " + TABLE_WORK_AND_SPARE_PART_ITEM + " (\n" +
            ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ID_SERVICE_ART + " INTEGER,\n" +
            ID_TYPE_ITEM + " INTEGER,\n" +
            CODE_SPARE_PART_ITEM + " TEXT,\n" +
            NAME_ITEM + " TEXT,\n" +
            COUNT_ITEM + " INTEGER,\n" +
            PRICE_SPARE_PART_ITEM + " INTEGER,\n" +
            PRICE_WORK_ITEM + " INTEGER,\n" +
            " FOREIGN KEY(" + ID_SERVICE_ART + ") REFERENCES " + TABLE_SERVICE_MAINTENANCE_ACT + "(" + ID_SERVICE_ART + ") ON DELETE CASCADE\n" +
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
                MARK_CAR + ",\n" +
                MODEL_CAR + ",\n" +
                MILEAGE_CAR + ",\n" +
                YEAR_CAR + ",\n" +
                REG_CERTIFICATE_CAR + ",\n" +
                GOS_REG_NUMBER_CAR + "\n" +
                "FROM " + TABLE_CAR + "\n";

        try {
            cursorCar = db.rawQuery(GET_CAR_QUERY, null);
            if (cursorCar.moveToFirst()){

                do {
                    int id_car_column = cursorCar.getColumnIndex(ID_CAR);
                    int mark_column = cursorCar.getColumnIndex(MARK_CAR);
                    int model_column = cursorCar.getColumnIndex(MODEL_CAR);
                    int mileage_column = cursorCar.getColumnIndex(MILEAGE_CAR);
                    int year_column = cursorCar.getColumnIndex(YEAR_CAR);
                    int reg_cert_column = cursorCar.getColumnIndex(REG_CERTIFICATE_CAR);
                    int gos_num_column = cursorCar.getColumnIndex(GOS_REG_NUMBER_CAR);
                    Car car = new Car(
                            cursorCar.getInt(id_car_column),
                            cursorCar.getString(mark_column),
                            cursorCar.getString(model_column),
                            cursorCar.getInt(mileage_column),
                            cursorCar.getInt(year_column),
                            cursorCar.getString(reg_cert_column),
                            cursorCar.getString(gos_num_column)
                    );

                    car.getListServiceMaintenance().addAll(getListServiceMaintenance(cursorCar.getInt(id_car_column)));
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

    private ArrayList<ServiceMaintenanceCar> getListServiceMaintenance(int id_car){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorCarServiceMaintenance = null;
        ArrayList<ServiceMaintenanceCar> list_sto = new ArrayList<>();
        String GET_CAR_SERVICE_MAINTENANCE_QUERY = "SELECT \n" +
                ID_SERVICE_ART + ",\n" +
                ID_TYPE_WORK + ",\n" +
                DATE_OF_SERVICE_ACT + ",\n" +
                MILEAGE_MOMENT_SERVICE + ",\n" +
                NAME_COMPANY_SERVICE + ",\n" +
                DESCRIPTION_SERVICE_ACT + "\n" +
                "FROM " + TABLE_SERVICE_MAINTENANCE_ACT + "\n" +
                "WHERE " + ID_CAR + " = " + id_car;
        try {
             cursorCarServiceMaintenance = db.rawQuery(GET_CAR_SERVICE_MAINTENANCE_QUERY, null);
             if(cursorCarServiceMaintenance.moveToFirst()){
                 do{
                     int id_service_maintenance_column = cursorCarServiceMaintenance.getColumnIndex(ID_SERVICE_ART);
                     int id_work_column = cursorCarServiceMaintenance.getColumnIndex(ID_TYPE_WORK);
                     int date_column = cursorCarServiceMaintenance.getColumnIndex(DATE_OF_SERVICE_ACT);
                     int mileage_now_column = cursorCarServiceMaintenance.getColumnIndex(MILEAGE_MOMENT_SERVICE);
                     int name_company_column = cursorCarServiceMaintenance.getColumnIndex(NAME_COMPANY_SERVICE);
                     int text_description_column = cursorCarServiceMaintenance.getColumnIndex(DESCRIPTION_SERVICE_ACT);

                     ServiceMaintenanceCar serviceMaintenanceCar = new ServiceMaintenanceCar(
                             cursorCarServiceMaintenance.getInt(id_service_maintenance_column),
                             cursorCarServiceMaintenance.getInt(id_work_column),
                             cursorCarServiceMaintenance.getString(date_column),
                             cursorCarServiceMaintenance.getInt(mileage_now_column),
                             cursorCarServiceMaintenance.getString(name_company_column),
                             cursorCarServiceMaintenance.getString(text_description_column)
                     );
                     serviceMaintenanceCar.getListItemServiceMaintenance().addAll(getListItemServiceMaintenance(cursorCarServiceMaintenance.getInt(id_service_maintenance_column)));
                     list_sto.add(serviceMaintenanceCar);
                 }
                 while (cursorCarServiceMaintenance.moveToNext());
             }
             else {
                 //row 0
             }
        }
        catch (Exception ex){

        }
        finally {
            if (cursorCarServiceMaintenance != null && !cursorCarServiceMaintenance.isClosed()) {
                cursorCarServiceMaintenance.close();
            }
        }
        return list_sto;
    }

    private ArrayList<ItemServiceMaintenanceCar> getListItemServiceMaintenance(int id_sto){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorCarServiceMaintenanceItem = null;
        ArrayList<ItemServiceMaintenanceCar> list_item = new ArrayList<>();
        String GET_CAR_STO_ITEM_QUERY = "SELECT \n" +
                ID_ITEM + ",\n" +
                ID_TYPE_ITEM + ",\n" +
                CODE_SPARE_PART_ITEM + ",\n" +
                NAME_ITEM + ",\n" +
                COUNT_ITEM + ",\n" +
                PRICE_SPARE_PART_ITEM + ",\n" +
                PRICE_WORK_ITEM + "\n" +
                "FROM " + TABLE_WORK_AND_SPARE_PART_ITEM + "\n" +
                "WHERE " + ID_SERVICE_ART + " = " + id_sto;

        try{
            cursorCarServiceMaintenanceItem = db.rawQuery(GET_CAR_STO_ITEM_QUERY, null);
            if(cursorCarServiceMaintenanceItem.moveToFirst()) {
                do{
                    int id_itemColumn = cursorCarServiceMaintenanceItem.getColumnIndex(ID_ITEM);
                    int id_typeWorkItemColumn = cursorCarServiceMaintenanceItem.getColumnIndex(ID_TYPE_ITEM);
                    int codeColumn = cursorCarServiceMaintenanceItem.getColumnIndex(CODE_SPARE_PART_ITEM);
                    int nameColumn = cursorCarServiceMaintenanceItem.getColumnIndex(NAME_ITEM);
                    int countColumn = cursorCarServiceMaintenanceItem.getColumnIndex(COUNT_ITEM);
                    int priceItemColumn = cursorCarServiceMaintenanceItem.getColumnIndex(PRICE_SPARE_PART_ITEM);
                    int priceWorkColumn = cursorCarServiceMaintenanceItem.getColumnIndex(PRICE_WORK_ITEM);


                    ItemServiceMaintenanceCar itemServiceMaintenanceCar = new ItemServiceMaintenanceCar(
                            cursorCarServiceMaintenanceItem.getInt(id_itemColumn),
                            cursorCarServiceMaintenanceItem.getInt(id_typeWorkItemColumn),
                            cursorCarServiceMaintenanceItem.getString(codeColumn),
                            cursorCarServiceMaintenanceItem.getString(nameColumn),
                            cursorCarServiceMaintenanceItem.getInt(countColumn),
                            cursorCarServiceMaintenanceItem.getInt(priceItemColumn),
                            cursorCarServiceMaintenanceItem.getInt(priceWorkColumn)
                    );
                    list_item.add(itemServiceMaintenanceCar);

                }
                while (cursorCarServiceMaintenanceItem.moveToNext());
            }
            else {
                // row 0
            }
        }
        catch (Exception e){

        }
        finally {
            if (cursorCarServiceMaintenanceItem != null && !cursorCarServiceMaintenanceItem.isClosed()) {
                cursorCarServiceMaintenanceItem.close();
            }
        }


        return list_item;
    }

    @Override
    public void addCar(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        String INSERT_CAR_QUERY = "INSERT INTO " + TABLE_CAR +
                " (" +
                MARK_CAR + "," +
                MODEL_CAR + "," +
                MILEAGE_CAR + "," +
                YEAR_CAR + "," +
                REG_CERTIFICATE_CAR + "," +
                GOS_REG_NUMBER_CAR +
                ") " +
                "VALUES ('" +
                car.getMark() + "', '" +
                car.getModel() + "', " +
                car.getMileageCar() + ", " +
                car.getYearCar() + ", '" +
                car.getRegCertificate() + "', '" +
                car.getGosNumber() +
                "')";
        db.execSQL(INSERT_CAR_QUERY);
    }

    @Override
    public void addServiceMaintenance(int id_car, ServiceMaintenanceCar serviceMaintenanceCar ) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_SERVICE_MAINTENANCE_ACT +
                " (" +
                ID_CAR + ", " +
                ID_TYPE_WORK + ", " +
                DATE_OF_SERVICE_ACT + ", " +
                MILEAGE_MOMENT_SERVICE + ", " +
                NAME_COMPANY_SERVICE + ", " +
                DESCRIPTION_SERVICE_ACT +
                ") " +
                "VALUES (" +
                id_car + ", " +
                serviceMaintenanceCar.getIDTypeOfWork() + ", '" +
                serviceMaintenanceCar.getDate() + "', " +
                serviceMaintenanceCar.getMileageNow() + ", '" +
                serviceMaintenanceCar.getNameCompany() + "', '" +
                serviceMaintenanceCar.getTextDescription() +
                "')"
        );
    }

    @Override
    public void addSparePartAndWorkItem(int id_service_maintenance, ItemServiceMaintenanceCar itemServiceMaintenanceCar) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_WORK_AND_SPARE_PART_ITEM +
                " (" + ID_SERVICE_ART + ", " +
                ID_TYPE_ITEM + ", " +
                CODE_SPARE_PART_ITEM + ", " +
                NAME_ITEM + ", " +
                COUNT_ITEM + ", " +
                PRICE_SPARE_PART_ITEM + ", " +
                PRICE_WORK_ITEM +
                ") " +
                "VALUES (" +
                id_service_maintenance + ", " +
                itemServiceMaintenanceCar.getId_type_item() + ", '" +
                itemServiceMaintenanceCar.getCodeItem() + "', '" +
                itemServiceMaintenanceCar.getName() + "', " +
                itemServiceMaintenanceCar.getCount() + ", " +
                itemServiceMaintenanceCar.getPriceItem() + ", " +
                itemServiceMaintenanceCar.getPriceWork() +
                ")"
        );
    }

    @Override
    public void addPolicy() {

    }

    @Override
    public void updateCar(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_CAR + " SET\n" +
                MARK_CAR + " = '" + car.getMark() + "',\n" +
                MODEL_CAR + " = '" + car.getModel() + "',\n" +
                MILEAGE_CAR + " = " + car.getMileageCar() + ",\n" +
                YEAR_CAR + " = " + car.getYearCar() + ",\n" +
                REG_CERTIFICATE_CAR + " = '" + car.getRegCertificate() + "',\n" +
                GOS_REG_NUMBER_CAR + " = '" + car.getGosNumber() + "'\n" +
                "WHERE " + ID_CAR + " = " + car.getId()
        );
    }

    @Override
    public void updateServiceMaintenance(ServiceMaintenanceCar serviceMaintenanceCar) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_SERVICE_MAINTENANCE_ACT + " SET\n" +
                 ID_TYPE_WORK + " = " + serviceMaintenanceCar.getIdServiceMaintenance() + ",\n" +
                 DATE_OF_SERVICE_ACT + " = '" + serviceMaintenanceCar.getDate() + "',\n" +
                 MILEAGE_MOMENT_SERVICE + " = " + serviceMaintenanceCar.getMileageNow() + ",\n" +
                 NAME_COMPANY_SERVICE + " = '" + serviceMaintenanceCar.getNameCompany() + "',\n" +
                 DESCRIPTION_SERVICE_ACT + " = '" + serviceMaintenanceCar.getTextDescription() + "'\n" +
                "WHERE " + ID_SERVICE_ART + " = " + serviceMaintenanceCar.getIdServiceMaintenance()
        );
    }

    @Override
    public void updateSparePartAndWorkItem(ItemServiceMaintenanceCar itemServiceMaintenanceCar) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_WORK_AND_SPARE_PART_ITEM + " SET\n" +
                ID_TYPE_ITEM + " = " + itemServiceMaintenanceCar.getId_type_item() + ",\n" +
                CODE_SPARE_PART_ITEM + " = '" + itemServiceMaintenanceCar.getCodeItem() + "',\n" +
                NAME_ITEM + " = '" + itemServiceMaintenanceCar.getName() + "',\n" +
                COUNT_ITEM + " = " + itemServiceMaintenanceCar.getCount() + ",\n" +
                PRICE_SPARE_PART_ITEM + " = " + itemServiceMaintenanceCar.getPriceItem() + ",\n" +
                PRICE_WORK_ITEM + " = " + itemServiceMaintenanceCar.getPriceWork() + "\n" +
                "WHERE " + ID_ITEM + " = " + itemServiceMaintenanceCar.getId_item()
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
    public void deleteServiceMaintenance(int id_service_maintenance) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SERVICE_MAINTENANCE_ACT + " \n" +
                "WHERE " + ID_SERVICE_ART + " = " + id_service_maintenance
        );
    }

    @Override
    public void deleteSparePartAndWorkItem(int id_item) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORK_AND_SPARE_PART_ITEM + "\n" +
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
        db.execSQL("ALTER TABLE " + TABLE_TYPE_ITEM +
                " ADD COLUMN " + ID_TYPE_WORK + ";"
        );
    }
}
