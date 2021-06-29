/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northarrow;

/**
 *
 * @author Richard
 */
public class LatestMark
{
     private String Username;
    private int homel, addl , math, ch1, ch2, ch3,avg;

    public LatestMark(String inUsername, int inHomel, int inAddl, int inMath, int inCh1, int inCh2, int inCh3,int avg)
    {
        this.Username=inUsername;
        this.homel = inHomel;
        this.addl = inAddl;
        this.math = inMath;
        this.ch1 = inCh1;
        this.ch2 = inCh2;
        this.ch3 = inCh3;
        this.avg = avg;
    }

    public int getHomel()
    {
        return homel;
    }
    public int getUCTHomel()
    {
        if (homel<40)
        {
            return 0;
        }
        else
        {
             return homel;
        }       
    }

    public int getAddl()
    {
        return addl;
    }
    public int getUCTAddl()
    {
        if (addl<40)
        {
            return 0;
        }
        else
        {
             return addl;
        }       
    }

    public int getMath()
    {
        return math;
    }
    public int getUCTMath()
    {
        if (math<40)
        {
            return 0;
        }
        else
        {
             return math;
        }       
    }

    public int getCh1()
    {
        return ch1;
    }
    public int getUCTCh1()
    {
        if (ch1<40)
        {
            return 0;
        }
        else
        {
             return ch1;
        }       
    }

    public int getCh2()
    {
        return ch2;
    }
    public int getUCTCh2()
    {
        if (ch2<40)
        {
            return 0;
        }
        else
        {
             return ch2;
        }       
    }

    public int getCh3()
    {
        return ch3;
    }
public int getUCTCh3()
    {
        if (ch3<40)
        {
            return 0;
        }
        else
        {
             return ch3;
        }       
    }

    public int getAvg()
    {
        return avg;
    }
    
}
