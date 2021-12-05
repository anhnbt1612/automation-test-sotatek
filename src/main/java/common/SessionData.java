package common;

import java.util.*;

public class SessionData {
    
    private static LinkedHashMap<Integer, List<String>> loadDataTable(List<List<String>> _dataTable) {
        LinkedHashMap<Integer, List<String>> hashMap = new LinkedHashMap<Integer, List<String>>();
        // Add
        for (int i = 0; i < _dataTable.size(); i++) {
            hashMap.put(i, _dataTable.get(i));
        }
        System.out.println("***** INFO ***** Load data successfully!");
        return hashMap;
    }

    public static LinkedHashMap<Integer, List<String>> getDataTbRowsNoHeader(List<List<String>> dataTable) {
        LinkedHashMap<Integer, List<String>> ret = loadDataTable(dataTable);
        ret.remove(0);
        return ret;
    }
    
    public static String getDataTbVal(List<List<String>> dataTable, int _rowIndex, String _colName) {
        String ret = "";
        int colIndex = dataTable.get(0).indexOf(_colName);
        if (colIndex == -1) {
            System.out.println("***WARNING*** Column [" + _colName + "] not found in Data Table.");
            return ret;
        } else {
            try {
                ret = dataTable.get(_rowIndex).get(colIndex);
            } catch (Exception e) {
                ret = "";
                System.out.println("***WARNING*** Row [" + _rowIndex + "] not found in Data Table.");
            }
        }
        return ret;
    }

}
