package GameDriver;

/*
This class is used to update the pack file of the program
Including use, pick up and buy.
 */

import java.io.*;

public class PackFileModifier
{
    private static String path ="EquipmentsDescription/packTest.txt"; //目标文件路径
    private static File file = new File(path);

    //This method aims to determine whether the item exists in the package.
    //TY Use***
    public static boolean whetherExists(int type)
    {
        boolean isExisted = false;
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file)));
            String txtline = null;
            while((txtline = br.readLine()) != null)
            {
                int myType = Integer.parseInt(txtline.split(",")[0]);
                if(myType == type)
                    isExisted = true;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isExisted;
    }

    //This method aims to update the pack file, if there exits the item, add or reduce one.
    //LJY Use***, operaType: Add / Use "Use"
    //Use: Use an item in the bag
    //type: which kind of item is used.
    public static void update(String operaType, int type)
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            File newFile = new File("EquipmentsDescription/bufferPack");
            if (!newFile.exists()){
                newFile.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(newFile,true)));
            String string = null;
            while ((string = br.readLine()) != null)
            {
                int itemType = Integer.parseInt(string.split(",")[0]);
                int itemNumber = Integer.parseInt(string.split(",")[1]);
                boolean isOut = false;
                if(itemType == type)
                {
                    if(operaType == "Add")
                    {
                        itemNumber++;
                        string = itemType + "," + itemNumber;
                    }
                    else if(operaType == "Use")
                    {
                        itemNumber--;
                        string = itemType + "," + itemNumber;
                    }
                    if(itemNumber <= 0)
                    {
                        isOut = true;
                        string = "";
                    }
                }
                bw.write(string);
                if(!isOut)
                    bw.newLine();
            }
            br.close();
            bw.close();
            String filePath = file.getPath();
            file.delete();
            newFile.renameTo(new File(filePath));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //If the package does not exist the item, add a new one.
    //TY Use***
    public static void writeIntoFile(String input)
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            File newFile = new File("EquipmentsDescription/bufferPack");
            if (!newFile.exists()){
                newFile.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile,true)));
            String content = "";
            String txtline = null;
            while ((txtline = br.readLine()) != null)
                content = content + txtline + "\n";
            content = content + input;
            bw.write(content);
            bw.newLine();
            br.close();
            bw.close();
            String filePath = file.getPath();
            file.delete();
            newFile.renameTo(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Read the file contents, used in drawing the items in th pack bag.
    //LJY Use
    //Return the info of carried items
    //Each line refers to one blank in the pack bag.
    public static String readFile()
    {
        String contents = "";
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file)));
            String txtline = null;
            while((txtline = br.readLine()) != null)
                contents = contents + txtline + "\n";
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }
}
