/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northarrow.GUI;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Richard
 */
public class GUIManager
{

    public GUIManager()
    {
    }

    //key binding method
    public static void addKeyBind(JComponent panel, String key, Action action)
    {//advanced code 
        InputMap iMap = panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap aMap = panel.getActionMap();
        iMap.put(KeyStroke.getKeyStroke(key), "keybind" + key);
        aMap.put("keybind" + key, action);
        panel.requestFocus();
    }

    //overloaded a key bind with Alt modifier + Key
    public static void addKeyBind(JComponent panel, int keycode, Action action)
    {
        InputMap iMap = panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap aMap = panel.getActionMap();
        iMap.put(KeyStroke.getKeyStroke(keycode, InputEvent.ALT_MASK), "keybind" + keycode);
        aMap.put("keybind" + keycode, action);
        panel.requestFocus();
    }

    public static void messageBox(String msg)
    {
        //String example in html"<html><p align=\"center\">fisrt line<br>second line</html>"
        MessageFrame mf = new MessageFrame(msg);//inject html into jLabel for dynamic text
        mf.setLocationRelativeTo(null);//set message box in the centre of the screen
        mf.setVisible(true);//custom show message dialogue
    }

    public static void helpFrame(String help)
    {
        //String example in html"<html><p align=\"center\">fisrt line<br>second line</html>"
        HelpFrame hf = new HelpFrame(help);//inject html into jLabel for dynamic text
        hf.setLocationRelativeTo(null);//set message box in the centre of the screen
        hf.setVisible(true);//custom show message dialogue
    }

    public static Cursor cursor()//custom cursor code
    {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Image img = tool.getImage("src/northarrow/Images/cursor.png");//get icon
        Point point = new Point(0, 0);//set point of reponse of the mouse
        Cursor cur = tool.createCustomCursor(img, point, "Cursor");
        return cur;
    }

    public static boolean isUniqueUsername(String un)
    {//check if username if unique sing scanners
        String usern;
        boolean re = true;
        try
        {
            Scanner sc = new Scanner(new File("Password.txt"));
            while (sc.hasNextLine())
            {
                Scanner line = new Scanner(sc.nextLine()).useDelimiter("#");
                usern = line.next();
                if (usern.equals(un))
                {
                    re = false;//if it already existed =false;
                }
                line.close();
            }
            sc.close();
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return re;
    }

    public static boolean isNull(String un)
    {//check if a string is empty
        boolean re = false;
        if (un.equals(""))
        {
            re = true;
        }
        return re;
    }

    public static boolean isShorterThan8Char(String un)
    {//checking length of passwords
        boolean re = false;
        if (un.length() < 8)
        {
            re = true;
        }
        return re;
    }

    public static boolean checkActivationCode(String code)
    {//checking activation code 2018
        boolean re = false;
        if (code.equals(LoginFrame.Acode))
        {
            re = true;
        }
        return re;
    }

    public static void writeUserInfo(String username, String password)
    {// if all code passes than recored into the text file! encrypted
        String userinfo;
        try
        {//using a print writer
            FileWriter fw = new FileWriter("Password.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter line = new PrintWriter(bw);
            userinfo = username + "#" + (encryption(password));//encryption
            line.println(userinfo);
            line.close();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String encryption(String dec)//encryption algorithm
    {//not going to disclose exactly how it works
        String enc = "", temp = "", ch = "";
        for (int i = 0; i < dec.length(); i++)
        {
            if (((int) dec.charAt(i) + "").length() == 2)//if ascii code is only 2 digit
            {
                enc += "0" + (int) dec.charAt(i);
            } else
            {
                enc += (int) dec.charAt(i);
            }

        }
        for (int i = enc.length() - 1; i >= 0; i--)
        {
            temp += enc.charAt(i);
        }
        temp = temp.replaceAll("0", ".");
        return temp;
    }

    private static String decryption(String enc)//decryption algorithm
    {//simple character handling, complexed ones gives me too much problem
        enc = enc.replaceAll("\\.", "0");
        String dec = "", temp = "";
        for (int i = enc.length() - 1; i >= 0; i--)
        {
            dec += enc.charAt(i);
        }
        for (int i = 0; i < dec.length() - 1; i += 3)
        {
            temp += (char) Integer.parseInt(dec.substring(i, i + 3));
        }
        return temp;
    }

    public static String findRelativePassword(String username)
    {// find the relative password to a username using scanners
        String re = "", usern;
        try
        {
            Scanner sc = new Scanner(new File("Password.txt"));
            while (sc.hasNextLine())
            {
                Scanner line = new Scanner(sc.nextLine()).useDelimiter("#");
                usern = line.next();
                if (usern.equals(username))
                {
                    re = line.next();
                }
                line.close();
            }
            sc.close();
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return decryption(re);
    }

    public static int countObjects(Object[] o)
    {//count the number of objects that is not null in an object array
        int count = 0;
        for (int i = 0; i < o.length; i++)
        {
            if (!(o[i] == null))
            {
                count++;
            }
        }
        return count;
    }

}
