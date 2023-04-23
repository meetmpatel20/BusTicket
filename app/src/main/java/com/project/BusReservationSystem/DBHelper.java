package com.project.BusReservationSystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static String DBName = "";
    SQLiteDatabase db;
    String DATABASE_TABLE = "Bus";


    public DBHelper(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creating Database table if not already exists
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `BUS`(\n" +
                "\t`BUSID`\tINTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
                "\t`SOURCE`\tTEXT,\n" +
                "\t`DESTINATION`\tTEXT,\n" +
                "\t`PRICE`\tDOUBLE,\n"+
                "\t`DEPTTIME`\tTEXT,\n"+
                "\t`ARRTIME`\tTEXT\n"+");");

        //sqLiteDatabase.execSQL("UPDATE SQLITE_SEQUENCE SET seq = 1000 WHERE name = 'BUS'");


        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `TICKET`(\n" +
                "\t`TICKETID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`BUSID`\tINTEGER,\n" +
                "\t`DATE`\tTEXT,\n"+
                "\t`SEATNO`\tINTEGER\n"+");");

        //sqLiteDatabase.execSQL("UPDATE SQLITE_SEQUENCE SET seq = 10000 WHERE name = 'TICKET'");
        //mydb =this.getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO `BUS` (`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Patan','Mehsana',48.00,'12:00PM','02:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Patan','Ahmedabad',76.00,'10:00AM','12:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Deesa','Patan',40.00,'04:00PM','06:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Deesa','Mehsana',60.00,'08:00PM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Deesa','Ahmedabad',105.00,'05:00PM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Patan','Surat',200.00,'06:00PM','11:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Mehsana','Junagadh',250.00,'07:00PM','11:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Diyodar','Ahmedabad',150.00,'08:00PM','12:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Palanpur','Ahmedabad',15.00,'09:00PM','01:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Palanpur','Gandhinagar',82.00,'10:00PM','04:00AM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Deesa','Gandhinagar',48.00,'11:00PM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Mehsana','Gandhinagar',47.00,'12:00AM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Unava','Mehsansa',23.00,'01:00AM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Patan','Gandhinagar',76.00,'02:00PM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Unjha','Gandhinagar',67.00,'03:00PM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Ahmedabad','Patan',74.00,'04:00AM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Ahmedabad','Surat',120.00,'05:00AM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Surat','Patan',200.00,'06:00AM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Patan','Unjha',28.00,'07:00AM','10:00AM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Patan','Deesa',40.00,'08:00AM','10:00AM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Patan','Gandhinagar',91.00,'09:00AM','10:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Siddhpur','Unava',51.00,'10:00AM','03:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Bokarvada','Gandhinagar',74.00,'11:00AM','04:00PM');\n");
        sqLiteDatabase.execSQL("INSERT INTO `BUS`(`SOURCE`,`DESTINATION`,`PRICE`,`DEPTTIME`,`ARRTIME`) VALUES ('Gandhinagar','Patan',89.00,'12:00AM','08:00PM');\n");

        //sqLiteDatabase.execSQL("INSERT INTO `TICKET`(`TICKETID`,`BUSID`,`DATE`,`SEATNO`) VALUES (1000,10,'08/29/2019',24);\n");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertBusData(){

    }

    public ArrayList<String> retriveSOURCE(){
        ArrayList<String> allSource = new ArrayList<>();

        db = this.getReadableDatabase();
        String qry = "SELECT DISTINCT SOURCE FROM BUS";
        Cursor c = db.rawQuery(qry,null);
        c.moveToFirst();
        for(int i = 0;i<c.getCount();i++){
            for(int j=0;j<c.getColumnCount();j++){
                allSource.add(c.getString(j));
            }

            c.moveToNext();
        }
        return allSource;
    }

    public ArrayList<String> retriveDest(String source){
        ArrayList<String> allDest = new ArrayList<>();

        db = this.getReadableDatabase();
        String qry = "SELECT DISTINCT DESTINATION FROM BUS WHERE SOURCE ="+"'"+source+"'";
        Cursor c = db.rawQuery(qry,null);
        c.moveToFirst();
        for(int i = 0;i<c.getCount();i++){
            for(int j=0;j<c.getColumnCount();j++){
                allDest.add(c.getString(j));
            }

            c.moveToNext();
        }
        return allDest;
    }

    public Cursor getRoutes(String source, String dest) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM BUS WHERE SOURCE ="+"'"+source+"' and Destination="+"'"+dest+"'",null);
        return res;
    }

    public Cursor getTicket(int ticketid){
        Cursor res = db.rawQuery("SELECT * FROM TICKET WHERE TICKETID ="+ticketid,null);
        return res;
    }

    public Integer cancelTicket (String ticketid) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("TICKET", "TICKETID = ?",new String[] {ticketid});

    }

    public ArrayList<Integer> bTickets(int busID, String date){
        db = this.getReadableDatabase();
        ArrayList<Integer> bSeats = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT SEATNO FROM TICKET WHERE BUSID="+busID +" AND DATE ="+"'"+date+"'",null);
        c.moveToFirst();
        for(int i = 0;i<c.getCount();i++){
            for(int j=0;j<c.getColumnCount();j++){
                bSeats.add(c.getInt(j));
            }

            c.moveToNext();
        }



        return bSeats;


    }

    public int bookTicket(int busId, String date,int seatNo){
        db = this.getWritableDatabase();
        String qry = "INSERT INTO TICKET(BUSID,DATE,SEATNO) VALUES ("+busId+",'"+date+"',"+seatNo+")";
        db.execSQL(qry);
        db = this.getReadableDatabase();
        int ticketID = 0;
        Cursor c = db.rawQuery("SELECT TICKETID FROM TICKET WHERE BUSID="+busId+" AND DATE='"+date+"' AND SEATNO="+seatNo,null);
        while(c.moveToNext()){
            ticketID = c.getInt(0);
        }
        return ticketID;
    }

    public TicketDetailsObj retriveTicketDetails(int ticketID){
        TicketDetailsObj ticketObj = new TicketDetailsObj();
        db = this.getReadableDatabase();
        String qry = "SELECT T.*,B.* FROM BUS B, TICKET T WHERE T.TICKETID="+ticketID+" AND T.BUSID=B.BUSID";
        Cursor c = db.rawQuery(qry,null);
        while(c.moveToNext()){
            ticketObj.ticketID = c.getInt(0);
            ticketObj.busId = c.getInt(1);
            ticketObj.date = c.getString(2);
            ticketObj.seatno = c.getInt(3);
            ticketObj.source = c.getString(5);
            ticketObj.dest = c.getString(6);
            ticketObj.price = c.getDouble(7);
            ticketObj.depttime = c.getString(8);
            ticketObj.arrtime = c.getString(9);

        }



        return ticketObj;
    }

    public ArrayList<TicketDetailsObj> retriveAllTickets() {
        TicketDetailsObj ticketObj = new TicketDetailsObj();
        ArrayList<TicketDetailsObj> ticketArray = new ArrayList<>();
        db = this.getReadableDatabase();
        String qry = "SELECT T.*,B.* FROM BUS B, TICKET T WHERE T.BUSID=B.BUSID";
        Cursor c = db.rawQuery(qry, null);
        c.moveToFirst();
        for(int i = 0;i<c.getCount();i++){
            for(int j=0;j<c.getColumnCount();j++) {
                ticketObj.ticketID = c.getInt(0);
                ticketObj.busId = c.getInt(1);
                ticketObj.date = c.getString(2);
                ticketObj.seatno = c.getInt(3);
                ticketObj.source = c.getString(5);
                ticketObj.dest = c.getString(6);
                ticketObj.price = c.getDouble(7);
                ticketObj.depttime = c.getString(8);
                ticketObj.arrtime = c.getString(9);


            }
            c.moveToNext();
            ticketArray.add(ticketObj);
            ticketObj = new TicketDetailsObj();
        }
        return ticketArray;
    }
}