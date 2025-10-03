package com.jimchrist.clanmanager;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.awt.Desktop;

public class chartGenerator {
    public chartGenerator(){}

    public static boolean saveAndOpenInBrowser(String filePath, String html){
        try {
            File file = new File(filePath);
            try (FileWriter fileWrite = new FileWriter(file)) {
                fileWrite.write(html);
            }
            Desktop.getDesktop().open(file);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //temporary example function to showcase and test it with ease
    public static String returnHTMLChartOfMembersValues(ClanInfo data,String methodName){
        return simpleHTMLChartGenerator(returnListMemberNameValues(data,methodName)[0],returnListMemberNameValues(data,methodName)[1],"pie");
    }


    //sample of gathering lists because the object orientation of the code is too fucked for such function to be created in a lazy way
    public static String[][] returnListMemberNameValues(ClanInfo data,String methodName){
        //List<List<String>> result = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();

        for (int i=0;i<data.getMembers();i++)
            nameList.add(data.getMember(i).getName());

        switch(methodName){
            case "getExpLevel":
                for (int i=0;i<data.getMembers();i++)
                    valueList.add( Integer.toString( data.getMember(i).getExpLevel() ) );
                break;
            case "getTrophies":
                for (int i=0;i<data.getMembers();i++)
                    valueList.add( Integer.toString( data.getMember(i).getTrophies() ) );
                break;
            case "getDonations":
                for (int i=0;i<data.getMembers();i++)
                    valueList.add( Integer.toString( data.getMember(i).getDonations() ) );
                break;
            case "getDonationsReceived":
                for (int i=0;i<data.getMembers();i++)
                    valueList.add( Integer.toString( data.getMember(i).getDonationsReceived() ) );
                break;
            case "getClanChestPoints":
                for (int i=0;i<data.getMembers();i++)
                    valueList.add( Integer.toString( data.getMember(i).getClanChestPoints() ) );
                break;
        }
        //result.add(nameList);
        //result.add(valueList);

        String[][] result = new String[2][];
        result[0] = nameList.toArray(new String[0]);
        result[1] = valueList.toArray(new String[0]);

        return result;
    }

    public static String simpleHTMLChartGenerator(String[] labels, String[] values, String chartType){
        // chart type changes the style of the chart
        // chartType options: "line", "bar", "pie", "doughnut", "polarArea", "radar", "scatter", "bubble"
        if (labels.length != values.length)
            return "Label and value list length should be equal because they reference eachother, right now they are inequal. Make sure they are equal. Right now they differ by" +labels.length + "-" +values.length;
        String labelsGenString="";
        String valuesGenString="";
        for (int i = 0; i < labels.length; i++) {
            labelsGenString = labelsGenString + "'" + labels[i] + "'";
            valuesGenString = valuesGenString + values[i];

            if (i+1 < labels.length) {
                labelsGenString = labelsGenString + ",";
                valuesGenString = valuesGenString + ",";
            }
        }

        String template="<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n" +
                "<canvas id=\"c\"></canvas>\n" +
                "<script>\n" +
                "    new Chart(document.getElementById('c'), {\n" +
                "        type: '"+chartType+"',\n" +
                "        data: {\n" +
                "            labels: ["+labelsGenString+"],\n" +
                "            datasets: [{\n" +
                "                data: ["+valuesGenString+"]\n" +
                "            }]\n" +
                "        }\n" +
                "    });\n" +
                "</script>";


        return template;
    }
}
