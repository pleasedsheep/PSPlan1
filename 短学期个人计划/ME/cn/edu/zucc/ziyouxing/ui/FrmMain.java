package cn.edu.zucc.ziyouxing.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//import cn.edu.zucc.booklib.control.SystemUserManager;

public class FrmMain extends JFrame implements ActionListener {
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("ϵͳ����");
    private JMenu menu_lend=new JMenu("��Ʒ����");
    private JMenu menu_search=new JMenu("����ͳ��");
    private JMenuItem  menuItem_UserManager=new JMenuItem("�û�����");
    private JMenuItem  menuItem_hotelManager=new JMenuItem("�Ƶ����");
    private JMenuItem  menuItem_hallManager=new JMenuItem("��������");
    private JMenuItem  menuItem_areaManager=new JMenuItem("�������");
    private JMenuItem  menuItem_lineManager=new JMenuItem("��·����");
    private JMenuItem  menuItem_spotManager=new JMenuItem("�������");

    private JMenuItem  menuItem_buy=new JMenuItem("��Ʊ");
    private JMenuItem  menuItem_Return=new JMenuItem("��Ʊ");
    
    private JMenuItem  menuItem_linebuySearch=new JMenuItem("��Ʒ���������ѯ");
    private JMenuItem  menuItem_userpriSearch=new JMenuItem("�û����������ѯ");
    
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("ͼ�����ϵͳ");
		dlgLogin=new FrmLogin(this,"��½",true);
		dlgLogin.setVisible(true);
	    //�˵�
	    /*if("����Ա".equals(SystemUserManager.currentUser.getUsertype())){
	    	menu_Manager.add(menuItem_UserManager);
	    	menuItem_UserManager.addActionListener(this);
	    	menu_Manager.add(menuItem_hotelManager);
	    	menuItem_hotelManager.addActionListener(this);
	    	menu_Manager.add(menuItem_hallManager);
	    	menuItem_hallManager.addActionListener(this);
	    	menu_Manager.add(menuItem_areaManager);
	    	menuItem_areaManager.addActionListener(this);
	    	menu_Manager.add(menuItem_lineManager);
	    	menuItem_lineManager.addActionListener(this);
	    	menubar.add(menu_Manager);
	    }*/
	    menu_lend.add(this.menuItem_buy);
	    menuItem_buy.addActionListener(this);
	    menu_lend.add(this.menuItem_Return);
	    menuItem_Return.addActionListener(this);
	    menubar.add(menu_lend);
	    menu_search.add(this.menuItem_linebuySearch);
	    menuItem_linebuySearch.addActionListener(this);
	    menu_search.add(this.menuItem_userpriSearch);
	    menuItem_userpriSearch.addActionListener(this);
	    menubar.add(this.menu_search);
	    this.setJMenuBar(menubar);
	    //״̬��
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("����!"/*+SystemUserManager.currentUser.getUsername()*/);
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.menuItem_UserManager){
			FrmUserManager dlg=new FrmUserManager(this,"�û�����",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_hotelManager){
			FrmReaderTypeManager dlg=new FrmReaderTypeManager(this,"�Ƶ����",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_hallManager){
			FrmReaderManager dlg=new FrmReaderManager(this,"��������",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_areaManager){
			FrmPublisherManager dlg=new FrmPublisherManager(this,"�������",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_lineManager){
			FrmBookManager dlg=new FrmBookManager(this,"��·����",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_spotManager){
			FrmBookManager dlg=new FrmBookManager(this,"�������",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_buy){
			FrmLend dlg=new FrmLend(this,"��Ʊ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_Return){
			FrmReturn dlg=new FrmReturn(this,"��Ʊ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_linebuySearch){
			FrmBookLendSearch dlg=new FrmBookLendSearch(this,"ͼ����������ѯ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_userpriSearch){
			FrmReaderLendSearch dlg=new FrmReaderLendSearch(this,"���߽��������ѯ",true);
			dlg.setVisible(true);
		}
	}
}
