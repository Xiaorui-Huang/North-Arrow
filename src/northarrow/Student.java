/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northarrow;

import java.sql.SQLException;
import northarrow.GUI.GUIManager;

/**
 *
 * @author Richard
 */
public class Student
{

    private String username, race, homel, addl, math, ch1, ch2, ch3;
    private int AL, QL, MAT;

    public Student(String inUsername, String inRace, String inHomel, String inAddl, String inMath, String inCh1, String inCh2, String inCh3, int inAL, int inQL, int inMAT)
    {
        this.username = inUsername;
        this.race = inRace;
        this.homel = inHomel;
        this.addl = inAddl;
        this.math = inMath;
        this.ch1 = inCh1;
        this.ch2 = inCh2;
        this.ch3 = inCh3;
        this.AL = inAL;
        this.QL = inQL;
        this.MAT = inMAT;
    }

    public int getStellen() throws SQLException
    {//get university specific APS marks
        int re = 0;
        LatestMark lm = getLatestMark();
        re = lm.getAvg();
        return re;
    }

    private int sevenPointScaleWits(int m)
    {//calculation method for wits APS
        int re = 0;
        if (90 <= m && m <= 100)
        {
            re = 8;
        } else if (80 <= m && m < 90)
        {
            re = 7;
        } else if (70 <= m && m < 80)
        {
            re = 6;
        } else if (60 <= m && m < 70)
        {
            re = 5;
        } else if (50 <= m && m < 60)
        {
            re = 4;
        } else if (40 <= m && m < 50)
        {
            re = 3;
        } else
        {
            re = 0;
        }
        return re;
    }

    private int sevenPointScaleWitsEM(int m)
    {//calculation method for wits APS
        int re = 0;
        if (90 <= m && m <= 100)
        {
            re = 10;
        } else if (80 <= m && m < 90)
        {
            re = 9;
        } else if (70 <= m && m < 80)
        {
            re = 8;
        } else if (60 <= m && m < 70)
        {
            re = 7;
        } else if (50 <= m && m < 60)
        {
            re = 4;
        } else if (40 <= m && m < 50)
        {
            re = 3;
        } else
        {
            re = 0;
        }
        return re;
    }

    public int getWits() throws SQLException//used by UP
    {//get university specific APS marks
        int re = 0;
        LatestMark lm = getLatestMark();
        int h = this.sevenPointScaleWitsEM(lm.getHomel());
        int a = this.sevenPointScaleWits(lm.getAddl());
        int m = this.sevenPointScaleWitsEM(lm.getMath());
        int c1 = this.sevenPointScaleWits(lm.getCh1());
        int c2 = this.sevenPointScaleWits(lm.getCh2());
        int c3 = this.sevenPointScaleWits(lm.getCh3());
        re = h + a + m + c1 + c2 + c3 + 2;
        return re;
    }

    public int getNSC() throws SQLException//used by UP
    {//get university specific APS marks
        int re = 0;
        LatestMark lm = getLatestMark();
        int h = this.sevenPointScale(lm.getHomel());
        int a = this.sevenPointScale(lm.getAddl());
        int m = this.sevenPointScale(lm.getMath());
        int c1 = this.sevenPointScale(lm.getCh1());
        int c2 = this.sevenPointScale(lm.getCh2());
        int c3 = this.sevenPointScale(lm.getCh3());
        re = h + a + m + c1 + c2 + c3;
        return re;
    }

    private int sevenPointScale(int m)
    {//calculation method for wits APS
        int re = 0;
        if (80 <= m && m <= 100)
        {
            re = 7;
        } else if (70 <= m && m < 80)
        {
            re = 6;
        } else if (60 <= m && m < 70)
        {
            re = 5;
        } else if (50 <= m && m < 60)
        {
            re = 4;
        } else if (40 <= m && m < 50)
        {
            re = 3;
        } else if (30 <= m && m < 40)
        {
            re = 2;
        } else
        {
            re = 1;
        }
        return re;
    }

    public int getUCT_normFPS() throws SQLException//faculty from commence, engineering, build enviroment , huamnity and law
    {//get university andprogramme specific APS marks
        int re = 0;
        LatestMark m = getLatestMark();
        re = m.getUCTHomel() + m.getUCTAddl() + m.getUCTMath() + m.getUCTCh1() + m.getUCTCh2() + m.getUCTCh3();
        return re;
    }

    public int getUCT_HealthFPS() throws SQLException//faculty for health MBChB needs a PR
    {//get university andprogramme specific APS marks
        int re = 0;
        LatestMark m = getLatestMark();
        re = m.getUCTHomel() + m.getUCTAddl() + m.getUCTMath() + m.getUCTCh1() + m.getUCTCh2() + m.getUCTCh3() + AL + QL + MAT;
        return re;
    }

    public int getUCT_MBChB_FPS(int PR) throws SQLException//crazy surgent giving me a hard time to code...
    {//get university andprogramme specific APS marks
        int re = 0;
        LatestMark m = getLatestMark();
        re = m.getUCTHomel() + m.getUCTAddl() + m.getUCTMath() + m.getUCTCh1() + m.getUCTCh2() + m.getUCTCh3() + AL + QL + MAT + PR;
        return re;
    }

    public int getUCT_ScienceFPS() throws SQLException//faculty of science
    {//one of the choice subject will be physcial science as if not will not use this method, if its math lit will not use this either
        //also math will be core, otherwise will not use this.       
        //get university andprogramme specific APS marks
        LatestMark m = getLatestMark();
        int re = m.getUCTHomel() + m.getUCTAddl() + (m.getUCTMath() * 3) + m.getUCTCh1() + m.getUCTCh2() + m.getUCTCh3();
        if (this.identifyPhysicalScience().equals("1"))
        {
            re = m.getUCTHomel() + m.getUCTAddl() + (m.getUCTMath() * 2) + (m.getUCTCh1() * 2) + m.getUCTCh2() + m.getUCTCh3();
        } else if (this.identifyPhysicalScience().endsWith("2"))
        {
            re = m.getUCTHomel() + m.getUCTAddl() + (m.getUCTMath() * 2) + m.getUCTCh1() + (m.getUCTCh2() * 2) + m.getUCTCh3();
        } else if (this.identifyPhysicalScience().equals("3"))
        {
            re = m.getUCTHomel() + m.getUCTAddl() + (m.getUCTMath() * 2) + m.getUCTCh1() + m.getUCTCh2() + (m.getUCTCh3() * 2);
        } else
        {
            System.out.println("Error physics n/a");
        }
        return re;
    }

    public String identifyPhysicalScience() throws SQLException
    {//Identify which choice position(“1”, ”2”, ”3”) the Physical Science subject is in. if user does not take Physical science returns “0”
        String re = "0";
        if (ch1.equals("Physical Sciences"))
        {
            re = "1";
        } else if (ch2.equals("Physical Sciences"))
        {
            re = "2";
        } else if (ch3.equals("Physical Sciences"))
        {
            re = "3";
        } else
        {
            re="0";
        }
        return re;
    }

    public String identifyLifeScience() throws SQLException
    {//Identify which choice position(“1”, ”2”, ”3”) the Life Science subject is in. if user does not take Life Science returns “0”
        String re = "0";
        if (ch1.equals("Life Sciences"))
        {
            re = "1";
        } else if (ch2.equals("Life Sciences"))
        {
            re = "2";
        } else if (ch3.equals("Life Sciences"))
        {
            re = "3";
        } else
        {
            re = "0";
        }
        return re;
    }

    public String identifyMusic() throws SQLException
    {//Identify which choice position(“1”, ”2”, ”3”) the Music subject is in. if user does not take Music returns “0”
        String re = "0";
        if (ch1.equals("Music"))
        {
            re = "1";
        } else if (ch2.equals("Music"))
        {
            re = "2";
        } else if (ch3.equals("Music"))
        {
            re = "3";
        } else
        {
            re="0";
        }
        return re;
    }

    public LatestMark getLatestMark() throws SQLException
    {
        LatestMark re = null;
        if (isYearLater(getLatestYearMark(), getLatestTermMark()))
        {
            re = new LatestMark(username, getLatestYearMark().getHomel(), getLatestYearMark().getAddl(), getLatestYearMark().getMath(), getLatestYearMark().getCh1(), getLatestYearMark().getCh2(), getLatestYearMark().getCh3(), getLatestYearMark().getAvg());
        } else
        {
            re = new LatestMark(username, getLatestTermMark().getHomel(), getLatestTermMark().getAddl(), getLatestTermMark().getMath(), getLatestTermMark().getCh1(), getLatestTermMark().getCh2(), getLatestTermMark().getCh3(), getLatestTermMark().getAvg());
        }
        return re;
    }

    public YearMark getLatestYearMark() throws SQLException
    {//preperation method for getLatestMark and get the latest  year mark
        YearMark re = null;
        DBManager db = new DBManager();
        YearMark[] yms = db.populateAllYearMark(username);
        int latestYm = 0, yp = 0;

        if (!db.isThereNoYearMark(username))//only if there is a Yearmark, to prevent error
        {//by comparing which has the highest grade
            for (int i = 0; i < GUIManager.countObjects(yms); i++)
            {
                if (yms[i].getGrade() > latestYm)
                {
                    latestYm = yms[i].getGrade();//
                    yp = i;//get position of the latest mark
                }
            }
            re = yms[yp];//getting the latest mark object
        }
        return re;
    }

    public TermMark getLatestTermMark() throws SQLException
    {//preperation method for getLatestMark and get the latest  term mark
        TermMark re = null;
        DBManager db = new DBManager();
        TermMark[] tms = db.populateAllTermMark(username);
        int latestTm = 0, tp = 0;
        if (!db.isThereNoTermMark(username))//only if there is a termark, to prevent error
        {//by comparing which has the highest grade
            for (int i = 0; i < GUIManager.countObjects(tms); i++)
            {
                if (tms[i].getGrade() > latestTm)
                {
                    latestTm = tms[i].getGrade();//
                    tp = i;//get position of the latest mark
                }
            }
            re = tms[tp];//getting the latest mark object
        }
        return re;
    }

    public boolean isYearLater(YearMark ym, TermMark tm)//used with if statement if true than its Year obj, else term obj
    {//helping method to decide if year or term is later
        boolean re;
        if (tm == null)// will at least have one of tm or ym cause if user do not they can't access this function
        {
            re = true;
        } else if (ym == null)//if one of them is null then the other one is the only mark left
        {
            re = false;
        } else if (ym.getGrade() == tm.getGrade())//same grade year mark is the latest
        {
            re = true;
        } else if (tm.getGrade() > ym.getGrade())
        {
            re = false;
        } else//there is only five possibilities, last one if ym.grade>tm.grade
        {
            re = true;
        }
        return re;
    }

    public int getAL()
    {
        return AL;
    }

    public int getQL()
    {
        return QL;
    }

    public int getMAT()
    {
        return MAT;
    }

    public String getUsername()
    {
        return username;
    }

    public String getRace()
    {
        return race;
    }

    public String getHomel()
    {
        return homel;
    }


    public String getAddl()
    {
        return addl;
    }


    public String getMath()
    {
        return math;
    }

    public String getCh1()
    {
        return ch1;
    }

    public String getCh2()
    {
        return ch2;
    }

    public String getCh3()
    {
        return ch3;
    }


}
