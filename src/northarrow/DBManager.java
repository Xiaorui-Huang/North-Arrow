package northarrow;

import java.io.*;
import java.sql.*;

/**
 *
 * @author HASEE
 */
public class DBManager
{

    //setting up connection for database
    private Connection conn;
    private String filePath = (new File("NorthArrow.accdb")).getAbsolutePath();
    //constructor for the database manager

    public DBManager()
    {
        try
        {
            //connecting trough manager to database
            conn = DriverManager.getConnection("jdbc:ucanaccess://" + filePath);
            //System.out.println(filename); to chexk filepath
            Statement s = conn.createStatement();
            System.out.println("Connection successful");
        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex);
            ex.printStackTrace();
        }
    }

    public Statement connect() throws SQLException
    {
        //connecting trough manager to database
        conn = DriverManager.getConnection("jdbc:ucanaccess://" + filePath);
        //System.out.println(filename); to chexk filepath
        Statement s = conn.createStatement();
        return s;
    }

    public ResultSet getAllProgramme() throws SQLException
    {
        Statement s = connect();//initialize connection
        String query = "SELECT * "
                + "FROM tblProgrammes;";
        ResultSet rs = s.executeQuery(query);
        return rs;
    }

    public boolean isNewUser(String inUsername) throws SQLException
    {
        boolean re = true;
        Statement s = connect();//initialize connection
        String query = "SELECT * "
                + "FROM tblStudents;";
        //execute select query
        ResultSet rs = s.executeQuery(query);
        while (rs.next())
        {//ckeck each username in the db against the Input username
            if (rs.getString("Username").equals(inUsername))
            {
                re = false;
            }
        }
        return re;
    }

    public boolean isNewYearMark(String inUsername, int inGrade) throws SQLException
    {
        boolean re = true;
        Statement s = connect();//initialize connection
        String query = "SELECT * "
                + "FROM tblYearMark;";
        ResultSet rs = s.executeQuery(query);
        while (rs.next())
        {                //checking primary key  to see if it exist already          
            if (rs.getString("Username").equals(inUsername) && rs.getInt("Grade") == inGrade)
            {
                re = false;
            }
        }
        return re;
    }

    public boolean isThereNoYearMark(String inUsername) throws SQLException
    {
        boolean re = true;
        Statement s = connect();//initialize connection
        String query = "SELECT * "
                + "FROM tblYearMark;";
        ResultSet rs = s.executeQuery(query);
        while (rs.next())
        {                //checking the composite key to see if YearMark exist  
            if (rs.getString("Username").equals(inUsername))
            {
                re = false;
            }
        }
        return re;
    }

    public boolean isNewTermMark(String inUsername, int inGrade, int inTerm) throws SQLException
    {
        boolean re = true;
        Statement s = connect();//initialize connection
        String query = "SELECT * "
                + "FROM tblTermMark;";
        ResultSet rs = s.executeQuery(query);
        while (rs.next())
        {                //checking primary key  to see if it exist already   
            if (rs.getString("Username").equals(inUsername) && rs.getInt("Grade") == inGrade && rs.getInt("Term") == inTerm)
            {
                re = false;
            }
        }
        return re;
    }

    public boolean isThereNoTermMark(String inUsername) throws SQLException
    {
        boolean re = true;
        Statement s = connect();//initialize connection
        String query = "SELECT * "
                + "FROM tblTermMark;";
        ResultSet rs = s.executeQuery(query);
        while (rs.next())
        {                //checking the composite key to see if TermMark exist  
            if (rs.getString("Username").equals(inUsername))
            {
                re = false;
            }
        }
        return re;
    }

    public void insertNewUser(String inUsername, String inRace, String inHomeLang, String inAddLang, String inMath, String inCh1, String inCh2, String inCh3, int inAL, int inQL, int inMAT) throws SQLException
    {
        Statement s = connect();//initialize connection
        //declating and executing query
        String query
                = "INSERT INTO tblStudents "
                + "VALUES('" + inUsername + "','" + inRace + "','" + inHomeLang + "','" + inAddLang + "','" + inMath + "','" + inCh1 + "','" + inCh2 + "','" + inCh3 + "'," + inAL + "," + inQL + "," + inMAT + ");";
        System.out.println(query);
        s.execute(query);
    }

    public void updateStudentInfo(String inUsername, String inRace, String inHomeLang, String inAddLang, String inMath, String inCh1, String inCh2, String inCh3, int inAL, int inQL, int inMAT) throws SQLException
    {
        Statement s = connect();//initialize connection
        //declating and executing query
        String query
                = "UPDATE tblStudents "
                + "SET Race='"
                + inRace
                + "', HomeLanguage='"
                + inHomeLang
                + "',FirstAdditionalanguage='"
                + inAddLang
                + "',MathChoice='"
                + inMath
                + "',Choice1='"
                + inCh1
                + "',Choice2='"
                + inCh2
                + "',Choice3='"
                + inCh3
                + "',AL="
                + inAL
                + ",QL="
                + inQL
                + ",MAT="
                + inMAT
                + " WHERE Username='"
                + inUsername
                + "'; ";
        System.out.println(query);
        s.execute(query);
    }

    public void insertYearMark(String inUsername, int inGrade, int inHomeLang, int inAddLang, int inMath, int inCh1, int inCh2, int inCh3) throws SQLException
    {
        int avg = (int) Math.round(((double) (inHomeLang + inAddLang + inMath + inCh1 + inCh2 + inCh3) / 6));
        Statement s = connect();//initialize connection
        //declating and executing query
        String query
                = "INSERT INTO tblYearMark "
                + "VALUES('" + inUsername + "'," + inGrade + "," + inHomeLang + "," + inAddLang + "," + inMath + "," + inCh1 + "," + inCh2 + "," + inCh3 + "," + avg + ");";
        System.out.println(query);
        s.execute(query);
    }

    public void updateYearMark(String inUsername, int inGrade, int inHomeLang, int inAddLang, int inMath, int inCh1, int inCh2, int inCh3) throws SQLException
    {
        int avg = (int) Math.round(((double) (inHomeLang + inAddLang + inMath + inCh1 + inCh2 + inCh3) / 6));
        Statement s = connect();//initialize connection
        //declating and executing query
        String query
                = "UPDATE tblYearMark "
                + "SET HomeLanguage=" + inHomeLang
                + ",FirstAdditionalanguage=" + inAddLang
                + ",MathChoice=" + inMath
                + ",Choice1=" + inCh1
                + ",Choice2=" + inCh2
                + ",Choice3=" + inCh3
                + ",AvgMark=" + avg
                + " WHERE Username='" + inUsername
                + "' AND Grade=" + inGrade + "; ";
        System.out.println(query);
        s.execute(query);
    }

    public void insertTermMark(String inUsername, int inGrade, int inTerm, int inHomeLang, int inAddLang, int inMath, int inCh1, int inCh2, int inCh3) throws SQLException
    {
        int avg = (int) Math.round(((double) (inHomeLang + inAddLang + inMath + inCh1 + inCh2 + inCh3) / 6));
        Statement s = connect();//initialize connection
        //declating and executing query
        String query
                = "INSERT INTO tblTermMark "
                + "VALUES('" + inUsername + "'," + inGrade + "," + inTerm + "," + inHomeLang + "," + inAddLang + "," + inMath + "," + inCh1 + "," + inCh2 + "," + inCh3 + "," + avg + ");";
        System.out.println(query);
        s.execute(query);
    }

    public void updateTermMark(String inUsername, int inGrade, int inTerm, int inHomeLang, int inAddLang, int inMath, int inCh1, int inCh2, int inCh3) throws SQLException
    {
        int avg = (int) Math.round(((double) (inHomeLang + inAddLang + inMath + inCh1 + inCh2 + inCh3) / 6));
        Statement s = connect();//initialize connection
        //declating and executing query
        String query
                = "UPDATE tblTermMark "
                + "SET HomeLanguage=" + inHomeLang
                + ",FirstAdditionalanguage=" + inAddLang
                + ",MathChoice=" + inMath
                + ",Choice1=" + inCh1
                + ",Choice2=" + inCh2
                + ",Choice3=" + inCh3
                + ",AvgMark=" + avg
                + " WHERE Username='" + inUsername
                + "' AND Grade=" + inGrade
                + " AND Term=" + inTerm + "; ";
        System.out.println(query);
        s.execute(query);
    }

    public Student populateStudent(String inUsername) throws SQLException
    {
        Student re = null;
        String user = "", race = "", homel = "", addl = "", math = "", ch1 = "", ch2 = "", ch3 = "";
        int AL = 0, QL = 0, MAT = 0;
        if (!isNewUser(inUsername))
        {
            Statement s = connect();//initialize connection
            String query
                    = "SELECT * "
                    + "FROM tblStudents "
                    + "WHERE Username='"
                    + inUsername + "' ;";
            ResultSet rs = s.executeQuery(query);
            //execute query to get result
            while (rs.next())
            {//preparing data to populate a student obj
                user = rs.getString("Username");
                race = rs.getString("Race");
                homel = rs.getString("HomeLanguage");
                addl = rs.getString("FirstAdditionalanguage");
                math = rs.getString("MathChoice");
                ch1 = rs.getString("Choice1");
                ch2 = rs.getString("Choice2");
                ch3 = rs.getString("Choice3");
                AL = rs.getInt("AL");
                QL = rs.getInt("QL");
                MAT = rs.getInt("MAT");
            }
            re = new Student(user, race, homel, addl, math, ch1, ch2, ch3, AL, QL, MAT);
        } else
        {
            System.out.println("Didn't find user");
        }
        return re;
    }

    public YearMark populateYearMark(String inUsername, int inGrade) throws SQLException
    {
        YearMark re = null;
        String user = "";
        int grade = 0, homel = 0, addl = 0, math = 0, ch1 = 0, ch2 = 0, ch3 = 0;
        if (!this.isNewYearMark(inUsername, inGrade))//prevent error by checking primary key
        {
            Statement s = connect();//initialize connection
            String query
                    = "SELECT * "
                    + "FROM tblYearMark "
                    + "WHERE Username='"
                    + inUsername + "' AND Grade=" + inGrade + " ;";
            System.out.println(query);
            ResultSet rs = s.executeQuery(query);
            //execute query to get result
            while (rs.next())
            {//preparing data to populate a YearMark obj
                user = rs.getString("Username");
                grade = rs.getInt("Grade");
                homel = rs.getInt("HomeLanguage");
                addl = rs.getInt("FirstAdditionalanguage");
                math = rs.getInt("MathChoice");
                ch1 = rs.getInt("Choice1");
                ch2 = rs.getInt("Choice2");
                ch3 = rs.getInt("Choice3");
            }
            re = new YearMark(user, grade, homel, addl, math, ch1, ch2, ch3);
        } else
        {
            System.out.println("Didn't find Year Mark");
        }
        return re;
    }

    public YearMark[] populateAllYearMark(String inUsername) throws SQLException
    {
        YearMark[] re = new YearMark[3];//every student have a maxinum of three grade marks
        String user = "";
        int grade = 0, homel = 0, addl = 0, math = 0, ch1 = 0, ch2 = 0, ch3 = 0, counter = 0;;
        if (!this.isThereNoYearMark(inUsername))//prevent error by checking primary key
        {
            Statement s = connect();//initialize connection
            String query
                    = "SELECT * "
                    + "FROM tblYearMark "
                    + "WHERE Username='"
                    + inUsername + "' "
                    + "ORDER BY Grade;";
            System.out.println(query);
            ResultSet rs = s.executeQuery(query);
            //execute query to get result
            while (rs.next())
            {//preparing data to populate a YearMark obj array
                user = rs.getString("Username");
                grade = rs.getInt("Grade");
                homel = rs.getInt("HomeLanguage");
                addl = rs.getInt("FirstAdditionalanguage");
                math = rs.getInt("MathChoice");
                ch1 = rs.getInt("Choice1");
                ch2 = rs.getInt("Choice2");
                ch3 = rs.getInt("Choice3");
                re[counter] = new YearMark(user, grade, homel, addl, math, ch1, ch2, ch3);
                counter++;
            }
        } else
        {
            System.out.println("Didn't find Any Year Mark");
        }
        return re;
    }

    public TermMark populateTermMark(String inUsername, int inGrade, int inTerm) throws SQLException
    {
        TermMark re = null;
        String user = "";
        int grade = 0, term = 0, homel = 0, addl = 0, math = 0, ch1 = 0, ch2 = 0, ch3 = 0;
        if (!this.isNewTermMark(inUsername, inGrade, inTerm))//prevent error by checking primary key
        {
            Statement s = connect();//initialize connection
            String query
                    = "SELECT * "
                    + "FROM tblTermMark "
                    + "WHERE Username='"
                    + inUsername
                    + "'AND Grade=" + inGrade
                    + " AND Term=" + inTerm + ";";
            System.out.println(query);
            ResultSet rs = s.executeQuery(query);
            //execute query to get result
            while (rs.next())
            {//preparing data to populate a obj
                user = rs.getString("Username");
                grade = rs.getInt("Grade");
                homel = rs.getInt("HomeLanguage");
                addl = rs.getInt("FirstAdditionalanguage");
                math = rs.getInt("MathChoice");
                ch1 = rs.getInt("Choice1");
                ch2 = rs.getInt("Choice2");
                ch3 = rs.getInt("Choice3");
            }
            re = new TermMark(user, grade, term, homel, addl, math, ch1, ch2, ch3);
        } else
        {
            System.out.println("Didn't find Term Mark");
        }
        return re;
    }

    public TermMark[] populateAllTermMark(String inUsername) throws SQLException
    {
        TermMark[] re = new TermMark[9];//every student have a maxinum of 9 grade marks
        String user = "";
        int grade = 0, term = 0, homel = 0, addl = 0, math = 0, ch1 = 0, ch2 = 0, ch3 = 0, counter = 0;;
        if (!this.isThereNoTermMark(inUsername))//prevent error by checking primary key
        {
            Statement s = connect();//initialize connection
            String query
                    = "SELECT * "
                    + "FROM tblTermMark "
                    + "WHERE Username='"
                    + inUsername + "' "
                    + "ORDER BY Grade, Term;";
            System.out.println(query);
            ResultSet rs = s.executeQuery(query);
            //execute query to get result
            while (rs.next())
            {//preparing data to populate a obj
                user = rs.getString("Username");
                grade = rs.getInt("Grade");
                term = rs.getInt("Term");
                homel = rs.getInt("HomeLanguage");
                addl = rs.getInt("FirstAdditionalanguage");
                math = rs.getInt("MathChoice");
                ch1 = rs.getInt("Choice1");
                ch2 = rs.getInt("Choice2");
                ch3 = rs.getInt("Choice3");
                re[counter] = new TermMark(user, grade, term, homel, addl, math, ch1, ch2, ch3);
                counter++;
            }
        } else
        {
            System.out.println("Didn't find Any Term Mark");
        }
        return re;
    }
}
