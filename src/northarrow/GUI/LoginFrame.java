/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northarrow.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import keeptoo.Drag;
import mdlaf.MaterialLookAndFeel;

/**
 *
 * @author Richard
 */
public class LoginFrame extends javax.swing.JFrame
{

    public static final String Acode = "2018";

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame()
    {
        try//impletation of material ui look and feel jar file
        {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        initComponents();
        GUIManager.addKeyBind(jpLogin, "ENTER", loginEnter);//inject key binding aka hot keys
        GUIManager.addKeyBind(jpSignUp, "ENTER", signUpEnter);
        GUIManager.addKeyBind(jpLogin, KeyEvent.VK_H, loginHelpH);
        GUIManager.addKeyBind(jpSignUp, KeyEvent.VK_H, signUpHelpH);
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));//set background to tranparent for drop shadow effect
        setLocationRelativeTo(null);//set frame in the centre of screen
        setCursor(GUIManager.cursor());//sset custom cursor
    }
    //key bind actions
    private Action loginEnter = new AbstractAction() //key binding action
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                loginNextCode();
            } catch (SQLException ex)
            {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    private Action signUpEnter = new AbstractAction() //key binding action
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            signUpNextCode();
        }
    };
    private Action loginHelpH = new AbstractAction() //key binding action
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            loginHelpCode();
        }
    };
    private Action signUpHelpH = new AbstractAction() //key binding action
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            signUpHelpCode();
        }
    };

    //action code to be reused in differnt places
    private void signUpNextCode()
    {
        if (!GUIManager.isUniqueUsername(txfSignUsername.getText()))//data validation
        {
            txfSignUsername.setText("");
            GUIManager.messageBox("<html><p align=\"center\">Username already exist<br>Please choose another username</html>");
        } else if (GUIManager.isNull(txfSignUsername.getText()))
        {
            txfSignUsername.setText("");
            GUIManager.messageBox("<html><p align=\"center\">Username cannot be empty<br>Please enter an username</html>");
        } else if (GUIManager.isNull(txfSignPassword.getText()))
        {
            txfSignPassword.setText("");
            txfSignCPassword.setText("");
            GUIManager.messageBox("<html><p align=\"center\">Password cannot be empty<br>Please enter a password</html>");
        } else if (GUIManager.isShorterThan8Char(txfSignPassword.getText()))
        {
            txfSignPassword.setText("");
            txfSignCPassword.setText("");
            GUIManager.messageBox("<html><p align=\"center\">Password is too short<br>A mininium of 8 characters are required<br><br>Please enter a new password</html>");
        } else if (!txfSignPassword.getText().equals(txfSignCPassword.getText()))
        {
            txfSignCPassword.setText("");
            GUIManager.messageBox("<html><p align=\"center\">Confirm Password does not match<br>Please enter the correct password</html>");
        } else if (GUIManager.isNull(txfActivation.getText()))
        {
            txfActivation.setText("");
            GUIManager.messageBox("<html><p align=\"center\">Please enter your Activation Code</html>");
        } else if (!GUIManager.checkActivationCode(txfActivation.getText()))
        {
            try
            {
                txfActivation.setText("");
                GUIManager.messageBox("<html><p align=\"center\">Activation failed<br>     Incorrect Activation Code</html>");
            } catch (NumberFormatException ex)
            {
                System.out.println("error");
            }

        } else//ater user passes all data validation
        {
            GUIManager.messageBox("<html><p align=\"center\">You have successfully signed up<br><br>Please login through the login page</html>");
            String user = txfSignUsername.getText();
            String password = txfSignPassword.getText();
            GUIManager.writeUserInfo(user, password);//record username and passoword
            jpLogin.setVisible(true);//send user to login
            jpSignUp.setVisible(false);//close signup
            txfSignUsername.setText("");//clear input in sign up
            txfSignPassword.setText("");
            txfSignCPassword.setText("");
            txfActivation.setText("");
            txfLoginUsername.setText("");
            txfLoginPassword.setText("");
        }

    }

    private void loginNextCode() throws SQLException
    {
        if (GUIManager.isNull(txfLoginUsername.getText()))//data validation, no input
        {
            txfLoginUsername.setText("");
            GUIManager.messageBox("<html><p align=\"center\">Username cannot be empty<br>Please enter your username</html>");
        } else if (GUIManager.isNull(txfLoginPassword.getText()))
        {
            txfLoginPassword.setText("");
            GUIManager.messageBox("<html><p align=\"center\">Password cannot be empty<br>Please enter your password</html>");
        } else if (GUIManager.isUniqueUsername(txfLoginUsername.getText()))//means that the user name does not exist
        {
            GUIManager.messageBox("<html><p align=\"center\">Username does not exist<br>Or incorrect password</html>");
        } else if (!GUIManager.findRelativePassword(txfLoginUsername.getText()).equals(txfLoginPassword.getText()))//if username does not match the relative password
        {
            GUIManager.messageBox("<html><p align=\"center\">Username does not exist<br>Or incorrect password</html>");
        } else//passes and execute code
        {
            MainFrame mf = new MainFrame(txfLoginUsername.getText());//push the username to the main frame for data validation and insert etc.
            dispose();//close login frame
            mf.setVisible(true);// opens the main frame         
        }
    }

    private void loginHelpCode()
    {//call a help page
        GUIManager.helpFrame("<html><p align=\"center\"><h2>This Program is only suited for NSC/IEB students<br>Some less common subjects may not be included<br><br>Currently This program only supports UCT, UP, Wits <br>and Stellenbosch<br></h2>"
                + "<ul><strong>Hot Key <br></strong>Help page: <strong>Alt+H</strong><br>"
                + "<li>Please sign up though the sign up page if you are a <strong>first time</strong> user by clicking<br> "
                + "<img src=\""
                + HelpFrame.class.getResource("/northarrow/Images/signup.png")
                + "\"></li>"
                + "<li>For <strong>security reasons</strong> password typed is in <strong>•</strong> form. if you wish to see the typed password please click<br>"
                + "<img src=\""
                + HelpFrame.class.getResource("/northarrow/Images/showpassword.png")
                + "\"></li>"
                + "<li>Login by clicking on<br>"
                + "<img src=\""
                + HelpFrame.class.getResource("/northarrow/Images/next_help.png")
                + "\"><br><strong>!  Provided you have the correct username and password</strong></li>"
                + "</ul></html>");
    }

    private void signUpHelpCode()
    {//call a help page
        GUIManager.helpFrame("<html><p align=\"center\"><h2>This Program is only suited for NSC/IEB students<br>Some less common subjects may not be included<br><br>Currently This program only supports UCT, UP, Wits<br>and Stellenbosch<br></h2>"
                + "<ul><strong>Hot Keys <br></strong>Help page: <strong>Alt+H</strong><br>"
                + "<br><li>Mininum lenth for passwords is <strong>8 characters</strong><br></li>"
                + "<li>For <strong>security reasons</strong> passwords typed is in <strong>•</strong> form. if you wish to see the typed password please click<br>"
                + "<img src=\""
                + HelpFrame.class.getResource("/northarrow/Images/showpassword.png")
                + "\"></li>"
                + "<li>If you don't have a <strong>Activation Code</strong>, please ask the person who provided you the software or Email to this Asian:<strong>18richardh@beaulieucollege.org</strong><br></li>"
                + "<li>Sign Up by clicking on<br>"
                + "<img src=\""
                + HelpFrame.class.getResource("/northarrow/Images/next_help.png")
                + "\"></li>"
                + "<li>Sign Up will take you to the Login in page for you to login<strong><br>You are not logged in immediately</strong><br></li>"
                + "</ul></html>");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jpParent = new javax.swing.JPanel();
        lbSignUp = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        Close = new javax.swing.JLabel();
        Minimize = new javax.swing.JLabel();
        DragBar = new javax.swing.JLabel();
        jpSide = new javax.swing.JPanel();
        btnLogin = new keeptoo.KButton();
        btnSignUp = new keeptoo.KButton();
        jpLogin = new javax.swing.JPanel();
        Login = new javax.swing.JLabel();
        loginHelp = new javax.swing.JLabel();
        loginNext = new javax.swing.JLabel();
        lbLoginUsername = new javax.swing.JLabel();
        lbLoginPassword = new javax.swing.JLabel();
        txfLoginUsername = new javax.swing.JTextField();
        txfLoginPassword = new javax.swing.JPasswordField();
        cbLoginShowPassword = new javax.swing.JCheckBox();
        jpSignUp = new javax.swing.JPanel();
        SignUp = new javax.swing.JLabel();
        signupHelp = new javax.swing.JLabel();
        signUpNext = new javax.swing.JLabel();
        lbSignUsername = new javax.swing.JLabel();
        txfSignUsername = new javax.swing.JTextField();
        lbActiviation = new javax.swing.JLabel();
        txfActivation = new javax.swing.JTextField();
        lbSignPassword = new javax.swing.JLabel();
        txfSignPassword = new javax.swing.JPasswordField();
        lbSignCPassword = new javax.swing.JLabel();
        txfSignCPassword = new javax.swing.JPasswordField();
        cbSignShowPassword = new javax.swing.JCheckBox();
        imgBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(550, 310));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jpParent.setOpaque(false);
        jpParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbSignUp.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lbSignUp.setForeground(new java.awt.Color(255, 255, 255));
        lbSignUp.setText("Sign Up");
        jpParent.add(lbSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 365, 80, 30));

        lbLogin.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(255, 255, 255));
        lbLogin.setText("Login");
        jpParent.add(lbLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 295, 60, 30));

        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/Close.png"))); // NOI18N
        Close.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                CloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                CloseMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                CloseMouseReleased(evt);
            }
        });
        jpParent.add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 32, 32));

        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/minimize.png"))); // NOI18N
        Minimize.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                MinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                MinimizeMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                MinimizeMouseReleased(evt);
            }
        });
        jpParent.add(Minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        DragBar.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        DragBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            public void mouseDragged(java.awt.event.MouseEvent evt)
            {
                DragBarMouseDragged(evt);
            }
        });
        DragBar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                DragBarMousePressed(evt);
            }
        });
        jpParent.add(DragBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 40));

        jpSide.setOpaque(false);
        jpSide.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogin.setMnemonic('L');
        btnLogin.setFocusable(false);
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnLogin.setkAllowTab(false);
        btnLogin.setkBackGroundColor(new java.awt.Color(51, 153, 255));
        btnLogin.setkBorderRadius(0);
        btnLogin.setkEndColor(new java.awt.Color(25, 87, 186));
        btnLogin.setkHoverEndColor(new java.awt.Color(255, 255, 255));
        btnLogin.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnLogin.setkHoverStartColor(new java.awt.Color(102, 153, 255));
        btnLogin.setkPressedColor(new java.awt.Color(102, 153, 255));
        btnLogin.setkStartColor(new java.awt.Color(25, 87, 186));
        btnLogin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLoginActionPerformed(evt);
            }
        });
        jpSide.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 244, -1));

        btnSignUp.setMnemonic('S');
        btnSignUp.setFocusable(false);
        btnSignUp.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSignUp.setkAllowTab(false);
        btnSignUp.setkBackGroundColor(new java.awt.Color(51, 153, 255));
        btnSignUp.setkBorderRadius(0);
        btnSignUp.setkEndColor(new java.awt.Color(25, 87, 186));
        btnSignUp.setkHoverEndColor(new java.awt.Color(255, 255, 255));
        btnSignUp.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnSignUp.setkHoverStartColor(new java.awt.Color(102, 153, 255));
        btnSignUp.setkPressedColor(new java.awt.Color(102, 153, 255));
        btnSignUp.setkStartColor(new java.awt.Color(25, 87, 186));
        btnSignUp.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSignUpActionPerformed(evt);
            }
        });
        jpSide.add(btnSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 244, -1));

        jpParent.add(jpSide, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 244, 480));

        jpLogin.requestFocus();
        jpLogin.setOpaque(false);
        jpLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Login.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        Login.setForeground(new java.awt.Color(0, 102, 255));
        Login.setText("Login");
        jpLogin.add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        loginHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/help.png"))); // NOI18N
        loginHelp.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                loginHelpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                loginHelpMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                loginHelpMouseReleased(evt);
            }
        });
        jpLogin.add(loginHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 32, 32));

        loginNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/next.png"))); // NOI18N
        loginNext.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                loginNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                loginNextMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                loginNextMouseReleased(evt);
            }
        });
        jpLogin.add(loginNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, -1, -1));

        lbLoginUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbLoginUsername.setForeground(new java.awt.Color(51, 102, 255));
        lbLoginUsername.setText("Username");
        jpLogin.add(lbLoginUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, 30));

        lbLoginPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbLoginPassword.setForeground(new java.awt.Color(51, 102, 255));
        lbLoginPassword.setText("Password");
        jpLogin.add(lbLoginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 30));

        txfLoginUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfLoginUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 255)));
        txfLoginUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txfLoginUsername.setOpaque(false);
        jpLogin.add(txfLoginUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 230, 30));
        txfLoginUsername.setBackground(new Color(0, 0, 0, 0));

        txfLoginPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfLoginPassword.setAutoscrolls(false);
        txfLoginPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 255)));
        txfLoginPassword.setOpaque(false);
        jpLogin.add(txfLoginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 230, 30));
        txfLoginPassword.setBackground(new Color(0, 0, 0, 0));

        cbLoginShowPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbLoginShowPassword.setForeground(new java.awt.Color(102, 153, 255));
        cbLoginShowPassword.setText("Show Password");
        cbLoginShowPassword.setToolTipText("");
        cbLoginShowPassword.setBorder(null);
        cbLoginShowPassword.setContentAreaFilled(false);
        cbLoginShowPassword.setFocusPainted(false);
        cbLoginShowPassword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbLoginShowPasswordActionPerformed(evt);
            }
        });
        jpLogin.add(cbLoginShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

        jpParent.add(jpLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 0, 396, 480));

        jpSignUp.setAutoscrolls(true);
        jpSignUp.setOpaque(false);
        jpSignUp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SignUp.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        SignUp.setForeground(new java.awt.Color(0, 102, 255));
        SignUp.setText("Sign Up");
        jpSignUp.add(SignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        signupHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/help.png"))); // NOI18N
        signupHelp.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                signupHelpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                signupHelpMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                signupHelpMouseReleased(evt);
            }
        });
        jpSignUp.add(signupHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 32, 32));

        signUpNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/next.png"))); // NOI18N
        signUpNext.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                signUpNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                signUpNextMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                signUpNextMouseReleased(evt);
            }
        });
        jpSignUp.add(signUpNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, -1, -1));

        lbSignUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSignUsername.setForeground(new java.awt.Color(51, 102, 255));
        lbSignUsername.setText("Username");
        jpSignUp.add(lbSignUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 30));

        txfSignUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSignUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 255)));
        txfSignUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txfSignUsername.setOpaque(false);
        jpSignUp.add(txfSignUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 230, 30));
        txfSignUsername.setBackground(new Color(0, 0, 0, 0));

        lbActiviation.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbActiviation.setForeground(new java.awt.Color(51, 102, 255));
        lbActiviation.setText("<html><p align=\"right\">Activation<br>Code</html>");
        jpSignUp.add(lbActiviation, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 40));

        txfActivation.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfActivation.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 255)));
        txfActivation.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txfActivation.setOpaque(false);
        jpSignUp.add(txfActivation, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 230, 30));
        txfActivation.setBackground(new Color(0, 0, 0, 0));

        lbSignPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSignPassword.setForeground(new java.awt.Color(51, 102, 255));
        lbSignPassword.setText("Password");
        jpSignUp.add(lbSignPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, 30));

        txfSignPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSignPassword.setAutoscrolls(false);
        txfSignPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 255)));
        txfSignPassword.setOpaque(false);
        jpSignUp.add(txfSignPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 230, 30));
        txfSignPassword.setBackground(new Color(0, 0, 0, 0));

        lbSignCPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSignCPassword.setForeground(new java.awt.Color(51, 102, 255));
        lbSignCPassword.setText("<html><p align=\"right\">Confirm<br>Password</html>");
        jpSignUp.add(lbSignCPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, 40));

        txfSignCPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSignCPassword.setAutoscrolls(false);
        txfSignCPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 255)));
        txfSignCPassword.setOpaque(false);
        jpSignUp.add(txfSignCPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 230, 30));
        txfSignCPassword.setBackground(new Color(0, 0, 0, 0));

        cbSignShowPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbSignShowPassword.setForeground(new java.awt.Color(102, 153, 255));
        cbSignShowPassword.setText("Show Password");
        cbSignShowPassword.setToolTipText("");
        cbSignShowPassword.setBorder(null);
        cbSignShowPassword.setContentAreaFilled(false);
        cbSignShowPassword.setFocusPainted(false);
        cbSignShowPassword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbSignShowPasswordActionPerformed(evt);
            }
        });
        jpSignUp.add(cbSignShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, -1, -1));

        jpParent.add(jpSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 0, 396, 480));
        jpSignUp.setVisible(false);

        imgBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/loginbg.png"))); // NOI18N
        jpParent.add(imgBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-11, -10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void cbLoginShowPasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbLoginShowPasswordActionPerformed
    {//GEN-HEADEREND:event_cbLoginShowPasswordActionPerformed
        if (cbLoginShowPassword.isSelected())//password showing toggle
        {
            txfLoginPassword.setEchoChar((char) 0);//set password visible
        } else//not selected 
        {//set password coded
            txfLoginPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_cbLoginShowPasswordActionPerformed

    private void DragBarMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_DragBarMousePressed
    {//GEN-HEADEREND:event_DragBarMousePressed
        new Drag(DragBar).onPress(evt);
        //drag function without default boaders, able to move window
    }//GEN-LAST:event_DragBarMousePressed

    private void DragBarMouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_DragBarMouseDragged
    {//GEN-HEADEREND:event_DragBarMouseDragged
        new Drag(DragBar).moveWindow(evt);
        //drag function without default boaders
    }//GEN-LAST:event_DragBarMouseDragged

    //hover effects-before i know that roll over icon existed
    private void loginHelpMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_loginHelpMouseEntered
    {//GEN-HEADEREND:event_loginHelpMouseEntered
        loginHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/help hover.png")));
    }//GEN-LAST:event_loginHelpMouseEntered

    private void loginHelpMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_loginHelpMouseExited
    {//GEN-HEADEREND:event_loginHelpMouseExited
        loginHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/help.png")));
    }//GEN-LAST:event_loginHelpMouseExited

    private void MinimizeMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_MinimizeMouseEntered
    {//GEN-HEADEREND:event_MinimizeMouseEntered
        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/minimize hover.png")));
    }//GEN-LAST:event_MinimizeMouseEntered

    private void MinimizeMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_MinimizeMouseExited
    {//GEN-HEADEREND:event_MinimizeMouseExited
        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/minimize.png")));
    }//GEN-LAST:event_MinimizeMouseExited

    private void CloseMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_CloseMouseEntered
    {//GEN-HEADEREND:event_CloseMouseEntered
        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/close hover.png")));
    }//GEN-LAST:event_CloseMouseEntered

    private void CloseMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_CloseMouseExited
    {//GEN-HEADEREND:event_CloseMouseExited
        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/close.png")));
    }//GEN-LAST:event_CloseMouseExited

    private void loginNextMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_loginNextMouseEntered
    {//GEN-HEADEREND:event_loginNextMouseEntered
        loginNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/next hover.png")));
    }//GEN-LAST:event_loginNextMouseEntered

    private void loginNextMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_loginNextMouseExited
    {//GEN-HEADEREND:event_loginNextMouseExited
        loginNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/next.png")));
    }//GEN-LAST:event_loginNextMouseExited

    private void signupHelpMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_signupHelpMouseEntered
    {//GEN-HEADEREND:event_signupHelpMouseEntered
        signupHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/help hover.png")));
    }//GEN-LAST:event_signupHelpMouseEntered

    private void signupHelpMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_signupHelpMouseExited
    {//GEN-HEADEREND:event_signupHelpMouseExited
        signupHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/help.png")));
    }//GEN-LAST:event_signupHelpMouseExited

    private void signUpNextMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_signUpNextMouseEntered
    {//GEN-HEADEREND:event_signUpNextMouseEntered
        signUpNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/next hover.png")));
    }//GEN-LAST:event_signUpNextMouseEntered

    private void signUpNextMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_signUpNextMouseExited
    {//GEN-HEADEREND:event_signUpNextMouseExited
        signUpNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/next.png")));
    }//GEN-LAST:event_signUpNextMouseExited
//hover effect end
    
    private void cbSignShowPasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbSignShowPasswordActionPerformed
    {//GEN-HEADEREND:event_cbSignShowPasswordActionPerformed
        if (cbSignShowPassword.isSelected())//password showing toggle
        {
            //set password visible
            txfSignPassword.setEchoChar((char) 0);
            txfSignCPassword.setEchoChar((char) 0);
        } else//not selected 
        //set password coded
        {
            txfSignPassword.setEchoChar('*');
            txfSignCPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_cbSignShowPasswordActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSignUpActionPerformed
    {//GEN-HEADEREND:event_btnSignUpActionPerformed
        jpSignUp.setVisible(true);//open sign up
        jpLogin.setVisible(false);//close login
        jpSignUp.requestFocus();
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLoginActionPerformed
    {//GEN-HEADEREND:event_btnLoginActionPerformed
        jpLogin.setVisible(true);//open login
        jpSignUp.setVisible(false);//close sign up
        jpLogin.requestFocus();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void CloseMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_CloseMouseReleased
    {//GEN-HEADEREND:event_CloseMouseReleased
        System.exit(0);//exit program
    }//GEN-LAST:event_CloseMouseReleased

    private void MinimizeMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_MinimizeMouseReleased
    {//GEN-HEADEREND:event_MinimizeMouseReleased
        this.setState(LoginFrame.ICONIFIED);//minimize the program
    }//GEN-LAST:event_MinimizeMouseReleased

    private void loginNextMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_loginNextMouseReleased
    {//GEN-HEADEREND:event_loginNextMouseReleased
        try
        {
            loginNextCode();//run login code
        } catch (SQLException ex)
        {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loginNextMouseReleased

    private void signUpNextMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_signUpNextMouseReleased
    {//GEN-HEADEREND:event_signUpNextMouseReleased
        signUpNextCode();//run sign up code
    }//GEN-LAST:event_signUpNextMouseReleased

    private void loginHelpMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_loginHelpMouseReleased
    {//GEN-HEADEREND:event_loginHelpMouseReleased
        loginHelpCode();//get help page
    }//GEN-LAST:event_loginHelpMouseReleased

    private void signupHelpMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_signupHelpMouseReleased
    {//GEN-HEADEREND:event_signupHelpMouseReleased
        signUpHelpCode();//get help page
    }//GEN-LAST:event_signupHelpMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Close;
    private javax.swing.JLabel DragBar;
    private javax.swing.JLabel Login;
    private javax.swing.JLabel Minimize;
    private javax.swing.JLabel SignUp;
    private keeptoo.KButton btnLogin;
    private keeptoo.KButton btnSignUp;
    private javax.swing.JCheckBox cbLoginShowPassword;
    private javax.swing.JCheckBox cbSignShowPassword;
    private javax.swing.JLabel imgBackground;
    private javax.swing.JPanel jpLogin;
    private javax.swing.JPanel jpParent;
    private javax.swing.JPanel jpSide;
    private javax.swing.JPanel jpSignUp;
    private javax.swing.JLabel lbActiviation;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbLoginPassword;
    private javax.swing.JLabel lbLoginUsername;
    private javax.swing.JLabel lbSignCPassword;
    private javax.swing.JLabel lbSignPassword;
    private javax.swing.JLabel lbSignUp;
    private javax.swing.JLabel lbSignUsername;
    private javax.swing.JLabel loginHelp;
    private javax.swing.JLabel loginNext;
    private javax.swing.JLabel signUpNext;
    private javax.swing.JLabel signupHelp;
    private javax.swing.JTextField txfActivation;
    private javax.swing.JPasswordField txfLoginPassword;
    private javax.swing.JTextField txfLoginUsername;
    private javax.swing.JPasswordField txfSignCPassword;
    private javax.swing.JPasswordField txfSignPassword;
    private javax.swing.JTextField txfSignUsername;
    // End of variables declaration//GEN-END:variables
}
