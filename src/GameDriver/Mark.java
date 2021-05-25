package GameDriver;

import java.sql.Timestamp;

public class Mark {
    public Timestamp startTime;
    public Timestamp finishTime;
    public int star;
    public int unFinishedMission;
    public int numberOfDeath;
    public int mark=0;
    public float totalMinutes;
    public boolean isMark;
    public Mark(Timestamp startTime)
    {
        isMark=false;
        this.startTime=startTime;
    }

    public int markMethod()
    {
        long totalTime = finishTime.getTime()-startTime.getTime();
        totalMinutes = (float) totalTime / (1000*60);
        if(totalMinutes<4)
        {
            mark+=3;

        }
        else if(totalMinutes<6&&totalMinutes>4)
        {
            mark+=2;
        }
        else if(totalMinutes>6)
        {
            mark+=1;
        }

        if(numberOfDeath<=2)
        {
            mark+=3;
        }
        else if(numberOfDeath>2&&numberOfDeath<=4)
        {
            mark+=2;
        }
        else
        {
            mark+=1;
        }

        if(unFinishedMission==1)
        {
            mark-=1;
        }
        else if(unFinishedMission==2)
        {
            mark-=2;
        }
        else if(unFinishedMission==3)
        {
            mark-=3;
        }
        else{
            mark+=3;
        }
        star=calculateStar(mark);
        return star;
    }

    private int calculateStar(int mark) {
        if(mark>=9&&mark<=10)
        {
            star=5;
        }
        else if(mark>=7&&mark<=8)
        {
            star=4;
        }
        else if(mark>=5&&mark<=6)
        {
            star=3;
        }
        else if(mark>=3&&mark<=4)
        {
            star=2;
        }
        else if(mark>=1&&mark<=2)
        {
            star=1;
        }
        return star;
    }

}
