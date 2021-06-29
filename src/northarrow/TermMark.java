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
public class TermMark
{
    private String Username;
    private int grade, term, homel, addl , math, ch1, ch2, ch3,avg;

    public TermMark(String inUsername, int inGrade, int inTerm, int inHomel, int inAddl, int inMath, int inCh1, int inCh2, int inCh3)
    {
        this.Username = inUsername;
        this.grade = inGrade;
        this.term = inTerm;
        this.homel = inHomel;
        this.addl = inAddl;
        this.math = inMath;
        this.ch1 = inCh1;
        this.ch2 = inCh2;
        this.ch3 = inCh3;
        this.avg=(int) Math.round(((double)(inHomel+inAddl+inMath+inCh1+inCh2+inCh3)/6));//calculated field of whole number
    }

    public int getAvg()
    {
        return avg;
    }


    public String getUsername()
    {
        return Username;
    }

    public int getGrade()
    {
        return grade;
    }

    public int getTerm()
    {
        return term;
    }


    public int getHomel()
    {
        return homel;
    }

    public int getAddl()
    {
        return addl;
    }

    public int getMath()
    {
        return math;
    }

    public int getCh1()
    {
        return ch1;
    }


    public int getCh2()
    {
        return ch2;
    }


    public int getCh3()
    {
        return ch3;
    }

}
