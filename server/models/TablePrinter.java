package server.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class TablePrinter {
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private ArrayList<String[]> info = null;


    public TablePrinter(ResultSet rs) throws DLException {
        try {
            this.rs = rs;
            rsmd = rs.getMetaData();
        } catch (Exception e) {
            throw new DLException(e, "Could not construct DBPrinter");
        }

    }

    TablePrinter(ResultSet rs, ArrayList<String[]> info) throws DLException {
        try {
            this.rs = rs;
            rsmd = rs.getMetaData();
            this.info = info;
        } catch (Exception e) {
            throw new DLException(e, "Could not construct DBPrinter");
        }

    }

    private int getRowCount() throws DLException {

        try {
            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();

            return count;
        } catch (Exception e) {
            //throw new DLException sending error message and relevant information
            throw new DLException(e, "Could Not print meta data in table");

        }
    }

    private void printColumnNames() throws DLException {
        try {
            int numCol = rsmd.getColumnCount();
            String colName;
            rs.beforeFirst();
            System.out.print("|");

            for (int j = 1; j <= numCol; j++) {

                colName = rsmd.getColumnName(j);
                int space = colName.length();
                System.out.printf("%" + space + "s %s", colName, "|");

            }


        } catch (Exception e) {
            //throw new DLException sending error message and relevant information
            throw new DLException(e, "Could Not print meta data in table");

        }


    }

    private void printDivider() throws DLException {
        try {
            int numCol = rsmd.getColumnCount();
            for (int i = 0; i < numCol; i++) {
                System.out.print("+");

                int longest = findLongest(i + 1);

                for (int j = 0; j <= longest; j++) {

                    System.out.print("-");

                }
            }
            System.out.print("+");
        } catch (Exception e) {
            //throw new DLException sending error message and relevant information
            throw new DLException(e, "Could Not print divider for table");

        }

    }

    void printData() throws DLException {
        try {
            int numCol = rsmd.getColumnCount();
            String colName;
            String data;
            rs.beforeFirst();

            printDivider();
            System.out.println();
            printColumnNames();
            System.out.println();
            printDivider();
            while (rs.next()) {


                System.out.println();
                System.out.print("|");
                for (int j = 1; j <= numCol; j++) {

                    data = rs.getString(j);
                    colName = rsmd.getColumnName(j);
                    int space = colName.length();
                    System.out.printf("%" + space + "s %s", data, "|");

                }


            }
            System.out.println();
            printDivider();
        } catch (Exception e) {
            //throw new DLException sending error message and relevant information
            throw new DLException(e, "Could Not print meta data in table");

        }


    }

    private int findLongest(int colNum) throws DLException {

        try {

            int numRows = getRowCount();
            String[] row;
            String field;
            String colName = rsmd.getColumnName(colNum);
            int longest = colName.length();


            for (int i = 0; i < numRows; i++) {

                row = info.get(i);


                field = row[colNum - 1];

                if (field.length() > longest) {
                    longest = field.length();

                }


            }

            return longest;
        } catch (Exception e) {
            //throw new DLException sending error message and relevant information
            throw new DLException(e, "Could Not print divider for table");

        }


    }

    void printTypes() throws DLException {

        try {
            int numCol = rsmd.getColumnCount();
            String colName;
            String colType;
            int colSize;
            String field = null;
            System.out.println();
            System.out.printf("%-30.30s  %-30.30s%n", "..Column Name..", "..Column Type..");

            for (int i = 1; i <= numCol; i++) {
                colName = rsmd.getColumnName(i);
                colType = rsmd.getColumnTypeName(i);
                colSize = rsmd.getPrecision(i);

                System.out.printf("%-30.30s  %-30.30s%n", colName, colType + "(" + colSize + ")");

            }


        } catch (Exception e) {
            //throw new DLException sending error message and relevant information
            throw new DLException(e, "Could Not print meta data in table");

        }


    }


}