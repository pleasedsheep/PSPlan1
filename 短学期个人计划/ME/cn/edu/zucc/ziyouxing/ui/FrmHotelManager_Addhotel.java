package cn.edu.zucc.ziyouxing.ui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.model.BeanReaderType;
import cn.edu.zucc.booklib.util.BaseException;

public class FrmHotelManager_Addhotel extends JDialog implements ActionListener {
	private BeanReaderType readertype=null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelid = new JLabel("酒店编号：");
	private JLabel labelarea_id = new JLabel("所属区域：");
	private JLabel labelName = new JLabel("酒店名称：");
	private JLabel labelintro = new JLabel("酒店介绍：");
	private JLabel labeladd = new JLabel("酒店地址：");
	private JLabel labelstar = new JLabel("酒店星级：");
	private JLabel labelsttime = new JLabel("酒店入住时间：");
	
	
	

	private JTextField edtid = new JTextField(10);
	private JTextField edtarea_id = new JTextField(10);
	private JTextField edtName = new JTextField(20);
	private JTextField edtintro = new JTextField(30);
	private JTextField edtadd = new JTextField(20);
	private JTextField edtstar = new JTextField(10);
	private JTextField edtsttime = new JTextField(20);

	public FrmHotelManager_Addhotel(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelid);
		workPane.add(edtid);
		workPane.add(labelarea_id);
		workPane.add(edtarea_id);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelintro);
		workPane.add(edtintro);
		workPane.add(labeladd);
		workPane.add(edtadd);
		workPane.add(labelstar);
		workPane.add(edtstar);
		workPane.add(labelsttime);
		workPane.add(edtsttime);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(360, 140);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			
			String name=this.edtName.getText();
			int n=0;
			
			//限制条件
			
			this.readertype=new BeanReaderType();
			this.readertype.setLendBookLimitted(n);
			this.readertype.setReaderTypeName(name);
			try {
				(new ReaderManager()).createReaderType(this.readertype);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.readertype=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public BeanReaderType getReadertype() {
		return readertype;
	}
	
}
