package GUI;

/*
This class aims to load contents between different NPCs and player.
All the content are stored in a txt file.
 */

import java.io.*;

public class dialogTxtReader
{
    //The speaking contents of player & NPCs
    private String[] communication;
    //Level and number of NPCs
    private int level;
    private int number;
    private String npcName;
    private String playerName;

    public void readContent(int level, int number)
    {
        String path = System.getProperty("user.dir");
        File ctoFile = new File(path + "/DialogTxt/Level" + level + ".txt");
        InputStreamReader contents = null;
        try {
            contents = new InputStreamReader(new FileInputStream(ctoFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(contents);
        String txtline = null;
        //Decide which NPC is selected.
        decideNPC(level, number);
        //Decide if the content is this NPC
        boolean select = false;
        //Index of player and npc.
        int comFlag = 0;
        try {
            while ((txtline = buffer.readLine()) != null)
            {
                if(txtline.equals("#NPC" + number))
                {
                    select = true;
                    continue;
                }
                //If content is end->Not content, then stop reading.
                if(txtline.equals("@") && select)
                {
                    comFlag = 0;
                    break;
                }

                if(select == true)
                {
                    if(txtline.substring(0, 1).equals("N") || txtline.substring(0, 1).equals("P")
                            || txtline.substring(0, 1).equals("S"))
                    {
                        this.communication[comFlag] = txtline;
                        comFlag++;
                    }
                }

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addHeader();
    }

    /*Decide the size of dialog contents->Number of dialogs
    @level: level number
    @para: number: which npc in this level
    @para: communication: length of the communication between player and this npc
     */
    public void decideNPC(int level, int number)
    {
        if(level == 1)
        {
            if(number == 1)
            {

                this.communication = new String[10];
                this.npcName = "Little Girl: ";
            }
            if(number == 2)
            {
                this.communication = new String[10];
                this.npcName = "Beautiful Girl: ";
            }
            if(number == 3)
            {
                this.communication = new String[11];
                this.npcName = "Green Sprite: ";
            }
        }
        else if(level == 3)
        {
            if(number == 1)
            {
                this.communication = new String[11];
                this.npcName = "Blue Sprite: ";
            }
            if(number == 2)
            {
                this.communication = new String[11];
                this.npcName = "Man: ";
            }
        }
        else if(level == 4)
        {
            if(number == 1)
            {
                this.communication = new String[11];
                this.npcName = "Umbrella Girl: ";
            }
            if(number == 2)
            {
                this.communication = new String[11];
                this.npcName = "Princess: ";
            }
        }
        else if(level == 5)
        {
            if (number == 1) {
                this.communication = new String[10];
                this.npcName = "Green Sprite: ";
            }
            if (number == 2) {
                this.communication = new String[10];
                this.npcName = "Blue Sprite: ";
            }
        }
        else if(level == 7)
        {
            if (number == 1) {
                this.communication = new String[10];
                this.npcName = "Sword Solider: ";
            }
            if (number == 2) {
                this.communication = new String[10];
                this.npcName = "Rich Girl: ";
            }
        }
        else if(level == 8)
        {
            if(number == 1)
            {
                this.communication = new String[10];
                this.npcName = "Blue Sprite: ";
            }
            if(number == 2)
            {
                this.communication = new String[10];
                this.npcName = "Sword Man: ";
            }
        }
        else if(level == 9)
        {
            if(number == 1)
            {
                this.communication = new String[10];
                this.npcName = "Saber: ";
            }
            if(number == 2)
            {
                this.communication = new String[10];
                this.npcName = "Beautiful Girl: ";
            }
        }
        else if(level == 10)
        {
            if(number == 1)
            {
                this.communication = new String[10];
                this.npcName = "Caster: ";
            }
            if(number == 2)
            {
                this.communication = new String[10];
                this.npcName = "Lancer: ";
            }
        }
    }

    //Add headers for NPC and player.
    public void addHeader()
    {
        for (int i = 0; i < this.communication.length; i++)
        {
            if(this.communication[i].substring(0, 1).equals("P"))
            {
                this.communication[i] = "Player: " + this.communication[i].substring(3, communication[i].length());
            }
            else if(this.communication[i].substring(0, 1).equals("N"))
            {
                this.communication[i] = this.npcName + this.communication[i].substring(3, communication[i].length());
            }
        }
    }

    //Get the final contents of player and current NPC.
    public String[] getCommunication()
    {
        return this.communication;
    }

}
