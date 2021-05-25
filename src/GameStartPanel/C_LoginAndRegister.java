package GameStartPanel;

import Actors.Blood;
import DatabaseManager.OperateDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_LoginAndRegister {
    public static JFrame frame = new JFrame("LOGIN");
    private JPanel panel1 = new JPanel(null);//login界面
    private JPanel panel2 = new JPanel(null);//register界面
    private String loUserName;//login界面上用户在文本框内输入的用户名
    private String loPassWord;//login界面上用户在密码框内输入的密码
    private String reUserName;//register界面上用户在文本框内输入的用户名
    private String rePassWord1;//register界面上用户在密码框内输入的密码遍1
    private String rePassWord2;//register界面上用户在密码框内输入的密码遍2
    private String txtUName;//当前要进行匹配的用户名
    private String txtPWord;//当前要进行匹配的用户名对应密码
    private int txtUNameLength;//当前要进行匹配的用户名的字符长度
    private int txtPWordLength;//当前要进行匹配的用户名对应密码的字符长度

    public boolean loginOpen=true;
    public boolean registerOpen=false;
    public static String UserId;//当前用户名   存储到数据库从而传递给后端游戏的用户名
    public static String Password;//当前密码     存储到数据库从而传递给后端游戏的密码


    //DbUser myDbUser = new DbUser("Database/HeroIo.db");

    public C_LoginAndRegister()
    {
        frame.setAlwaysOnTop(true);
    }


    public void Create()
    {

        //login界面
        panel1.setBounds(-1,0,372,400);
        panel1.setOpaque(false);
        JLabel label1=new JLabel(new ImageIcon("Images/login&registerIF/loginBBBBG.png"));
        label1.setBounds(-1,0,372,400);
        panel1.add(label1);

        //register界面
        panel2.setBounds(-1,0,372,400);
        panel2.setOpaque(false);
        JLabel label2=new JLabel(new ImageIcon("Images/login&registerIF/registerBBBBG.png"));
        label2.setBounds(-1,0,372,400);
        panel2.add(label2);

        //Txt txt = new Txt();


        //login界面里的文本框及其提交按钮
        JTextField loTF = textfield(60,75,250,35);
        loTF.setOpaque(false);
        loTF.setForeground(Color.WHITE);
        label1.add(loTF);
        //login界面里的密码框及其提交按钮
        JPasswordField loPF = passfield(60,187,250,35);
        loPF.setOpaque(false);
        loPF.setForeground(Color.WHITE);
        label1.add(loPF);

        //login界面里的login单词按钮
        JButton loLoginBtn = imageButton(
                40, 250, 310, 60,
                "loginBtn",
                "Images/login&registerIF/login.png",
                "Images/login&registerIF/loginLight.png",
                "Images/login&registerIF/loginPressed.png");
        label1.add(loLoginBtn);
        loLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loUserName = loTF.getText();
                System.out.println("loUserName12: " + loUserName);
                loPassWord =  new String(loPF.getPassword());
                System.out.println("loPassWord: " + loPassWord);


                //这里是login
                //查找数据库里是否有对应的UserId
                int exist = OperateDatabase.judgeUserId(loUserName);//判断数据库中是否存在loUserName
                //对比文本框中的用户名和在数据库中提取出来的用户名
                if(exist==0){//如果不一致(该用户名不存在)
                    //消息对话框无返回, 仅做通知作用。警告信息
                    JOptionPane.showMessageDialog(
                            frame,
                            "The user doesn't exist! \r\n " +
                                    "Hint: Check the username is right.\r\n " +
                                    "Hint: If you haven't registered, please register first.",
                            "Notice",
                            JOptionPane.WARNING_MESSAGE);
                }
                //如果有一致的UserId，对比Password，
                else if(exist==1) {//该用户名存在
                    //获取数据库中该用户名所存储的密码
                    String pabd = OperateDatabase.getPassword(loUserName);
                    //如果密码一致，提示登陆成功，点击之后切换界面
                    if(loPassWord.equals(pabd)){
                        OperateDatabase.user = loUserName;
                        UserId = loUserName;//记录当前用户名及数据
                        Password = loPassWord;
                        int herotypeexist = OperateDatabase.judgeHeroType(UserId);
                        if(herotypeexist == 1)
                            Blood.getHeroInfo();
                        JOptionPane.showMessageDialog(
                                frame,
                                "Login Successfully!",
                                "Notice",
                                JOptionPane.INFORMATION_MESSAGE);

                        //判断当前用户有没有记录其HeroType
                        if(herotypeexist==0){//未选择游戏角色
                            //切换界面
                            A_Start.loginRegisterOpen =false;
                            A_Start.chaChooOpen=true;
                        }
                        else if(herotypeexist==1){//已选择游戏角色
                            A_Start.loginRegisterOpen =false;
                            A_Start.levelChooOpen=true;
                        }


                        frame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "Wrong Password!", "Notice",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }





                /*//读取出txt中的所有信息
                String str = txt.readTxt("C:/Users/Administrator/Desktop/WD/usersInfor.txt");
                //找到txt中用户名所在的下标
                int txtUNameIndex = str.indexOf(loUserName);//返回第一次出现的指定子字符串在此字符串中的索引
                System.out.println("txtUNameIndex: "+txtUNameIndex);
                if(txtUNameIndex!=-1){
                    int txtUNamePostIndex = str.indexOf(" ", txtUNameIndex);
                    txtUNameLength=txtUNamePostIndex-txtUNameIndex;
                    txtUName=str.substring(txtUNameIndex, txtUNameIndex+ txtUNameLength);
                    System.out.println("txtUName: "+txtUName);
                }
                //此处进行之前两个变量与用户档案中数据的匹配，共三种情况
                //①如果此次输入的用户名在txt中的没有对应的数据，则输出该用户名未注册的提示信息，并引导用户点击register按钮进行注册
                if(txtUNameIndex==-1 || (txtUNameIndex!=-1 && !txtUName.equals(loUserName))){//若指定字符串中没有该字符则系统返回-1
                    //消息对话框无返回, 仅做通知作用。警告信息
                    JOptionPane.showMessageDialog(frame,
                            "The user doesn't exist! \r\n " +
                                    "Hint: Check the username is right.\r\n " +
                                    "Hint: If you haven't registered, please register first.",
                            "消息标题",
                            JOptionPane.WARNING_MESSAGE);
                }
                else if((txtUNameIndex!=-1) && txtUName.equals(loUserName)){//此次输入的用户名在txt中的有对应的数据
                    int txtPWordIndex = txtUNameIndex+ txtUNameLength +4;//每行元素之间的间隔是四个空格
                    int txtPWordPostIndex = str.indexOf(" ", txtPWordIndex);
                    txtPWordLength=txtPWordPostIndex-txtPWordIndex;
                    txtPWord=str.substring(txtPWordIndex, txtPWordIndex+ txtPWordLength) ;//获取txt中该用户名对应的密码
                    System.out.println("txtPWard: "+txtPWord);
                    if(loPassWord.equals(txtPWord)){
                        //②如果此次输入的用户名在txt中的有对应的数据且密码也与txt中的数据相匹配，
                        // 则弹出登陆成功的对话框，点击确认后退出login界面
                        JOptionPane.showMessageDialog(frame, "Login Successfully!", "消息标题",
                                JOptionPane.INFORMATION_MESSAGE);
                        A_Start.loginRegisterOpen =false;
                        A_Start.chaChooOpen=true;
                        frame.dispose();
                        //System.exit(0);
                    } else{
                        //③如果此次输入的用户名在txt中的有对应的数据但密码跟在txt中的数据不匹配，
                        // 则弹出密码错误的对话框，点击确认后重新输入密码
                        JOptionPane.showMessageDialog(frame, "Wrong Password!", "消息标题",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }*/
            }
        });
        //login界面的regist单词按钮：点击切换到register界面
        JButton loRegistBtn = imageButton(
                40, 310, 310, 60,
                "registBtn",
                "Images/login&registerIF/regist.png",
                "Images/login&registerIF/registLight.png",
                "Images/login&registerIF/registPressed.png");
        label1.add(loRegistBtn);
        loRegistBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginOpen=false;
                registerOpen=true;
                frame.remove(panel1);
                frame.add(panel2);
                frame.validate();
                frame.repaint();
            }
        });


        //register界面里的文本框及其提交按钮
        JTextField reTF = textfield(60,75,250,35);
        reTF.setOpaque(false);
        reTF.setForeground(Color.WHITE);
        label2.add(reTF);
        //register界面里的密码框1及其提交按钮
        JPasswordField rePF1 = passfield(60,153,250,35);
        rePF1.setOpaque(false);
        rePF1.setForeground(Color.WHITE);
        label2.add(rePF1);
        //register界面里的密码框2及其提交按钮
        JPasswordField rePF2 = passfield(60,233,250,35);
        rePF2.setOpaque(false);
        rePF2.setForeground(Color.WHITE);
        label2.add(rePF2);
        //register界面的regist单词按钮
        JButton reRegistBtn = imageButton(
                40, 310, 310, 60,
                "registBtn",
                "Images/login&registerIF/regist.png",
                "Images/login&registerIF/registLight.png",
                "Images/login&registerIF/registPressed.png");
        label2.add(reRegistBtn);
        reRegistBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //记录文本框和密码狂框中的内容
                reUserName = reTF.getText();
                System.out.println("reUserName: " + reUserName);
                rePassWord1 = new String(rePF1.getPassword());  //char[] getPassword()
                System.out.println("rePassWord1: " + rePassWord1 );
                rePassWord2 = new String(rePF2.getPassword());
                System.out.println("rePassWord2: " + rePassWord2 );



                //这里是register
                //比较文字框和数据库中的用户名
                //ArrayList allIdDb = OperateDatabase.getUserId();//取出数据库中全部的UserId
                //System.out.println("re---allIdDb: "+allIdDb);
                //int idIndex= allIdDb.indexOf(loUserName);
                int exist = OperateDatabase.judgeUserId(reUserName);
                //如果有重复的用户名
                if(exist==1){
                    //提示该用户名已被注册
                    JOptionPane.showMessageDialog(frame, "The user already exists! \r\n ", "Notice",
                            JOptionPane.WARNING_MESSAGE);
                }
                //如果没有重复的用户名
                else if(exist==0){
                    //UserId = reUserName;//记录用户名
                    OperateDatabase.user = reUserName;//传递当前用户名给后端游戏部分
                    //2.1 比较密码1和密码2操作，如果密码1=密码2
                    if(rePassWord1.equals(rePassWord2)){
                        //Password = rePassWord1; //记录密码
                        OperateDatabase.setUserInfor1(reUserName, rePassWord1);//将用户名和密码存储到数据库
                        OperateDatabase.addMark(reUserName);
                        //提示注册成功
                        JOptionPane.showMessageDialog(frame, "Register Successfully!", "Notice",
                                JOptionPane.INFORMATION_MESSAGE);

                        //切换界面
                        loginOpen=true;
                        registerOpen=false;
                        frame.remove(panel2);
                        frame.add(panel1);
                        frame.validate();
                        frame.repaint();
                    }
                    //2.2 比较密码1和密码2操作，如果密码1！=密码2
                    else{
                        //提示密码不一致
                        JOptionPane.showMessageDialog(
                                frame,
                                "Password Mismatch! \r\n "
                                        + "Please reenter the password!",
                                "Notice",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

                /*if (rePassWord1.equals(rePassWord2)){
                   //记录用户名到文件
                    txt.writeTxt("C:/Users/Administrator/Desktop/WD/usersInfor.txt", reUserName,"    ");
                    //记录密码遍1
                    txt.writeTxt("C:/Users/Administrator/Desktop/WD/usersInfor.txt", rePassWord1,"    ");
                    //记录密码遍2
                    txt.writeTxt("C:/Users/Administrator/Desktop/WD/usersInfor.txt", rePassWord2,"\r\n");


                    JOptionPane.showMessageDialog(
                            frame,
                            "Register Successfully!",
                            "消息标题",
                            JOptionPane.INFORMATION_MESSAGE);

                    loginOpen=true;
                    registerOpen=false;
                    frame.remove(panel2);
                    frame.add(panel1);
                    frame.validate();
                    frame.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(frame,
                            "Password Mismatch! \r\n " +
                                    "Please reenter the password!",
                            "消息标题",
                            JOptionPane.ERROR_MESSAGE);
                }*/

            }
        });



        frame.add(panel1);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(320,130,370,400);
        frame.setResizable(false);// 窗口设置为不可改变大小
        frame.setVisible(true);
    }



    //methods creating some components
    private JTextField textfield(int x, int y, int width, int height){
        // 创建文本框，指定可见列数为12列
        JTextField textField = new JTextField(12);
        textField.setFont(new Font(null, Font.PLAIN, 20));
        textField.setBounds(x, y, width, height);//该位置为就窗体而言

        return textField;
    }

    private JPasswordField passfield(int x, int y, int width, int height){
        //创建密码框，指定可见列数为10列
        JPasswordField passwordField = new JPasswordField(8);
        passwordField.setFont(new Font(null, Font.PLAIN, 20));
        passwordField.setBounds(x, y, width, height);
        //passwordField.setForeground(Color.DARK_GRAY);

        return  passwordField;
    }

    private JButton imageButton(int x, int y, int width, int height, String a, String b, String c, String d){
        JButton imBtn = new JButton(a);
        imBtn.setBounds(x, y, width, height);

        setButIcon(imBtn, b);
        setButRolloverIcon(imBtn, c);
        setButPressedIcon(imBtn, d);
        setBtnTransparent(imBtn);

        return imBtn;
    }

    private static void setBtnTransparent(JButton b) {
        b.setBorder(null);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
    }

    private static void setButIcon(JButton iconButton, String file) {
        ImageIcon icon = new ImageIcon(file);
        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),
                iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);
        icon = new ImageIcon(temp);
        iconButton.setIcon(icon);
    }

    private static void setButRolloverIcon(JButton iconButton, String file) {
        ImageIcon icon = new ImageIcon(file);
        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),
                iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);
        icon = new ImageIcon(temp);
        iconButton.setRolloverIcon(icon);
    }

    private static void setButPressedIcon(JButton iconButton, String file) {
        ImageIcon icon = new ImageIcon(file);
        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),
                iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);
        icon = new ImageIcon(temp);
        iconButton.setPressedIcon(icon);
    }

}