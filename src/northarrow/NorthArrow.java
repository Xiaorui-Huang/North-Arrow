/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northarrow;

import java.sql.SQLException;
import northarrow.GUI.LoginFrame;

/**
 *
 * @author HASEE
 */
public class NorthArrow
{
    public static void main(String[] args) throws SQLException
    {
        LoginFrame lf=new LoginFrame();//instantiate the login frame
        lf.setVisible(true);//set it visible
    }
}
