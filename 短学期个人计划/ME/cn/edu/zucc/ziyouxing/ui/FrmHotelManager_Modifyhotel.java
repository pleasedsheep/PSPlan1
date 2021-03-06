package cn.edu.zucc.ziyouxing.ui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.model.BeanReaderType;
import cn.edu.zucc.booklib.util.BaseException;

public class FrmHotelManager_Modifyhotel extends JDialog implements ActionListener {
	private BeanReaderType readertype=null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelid = new JLabel("�Ƶ��ţ�");
	private JLabel labelarea_id = new JLabel("��������");
	private JLabel labelName = new JLabel("�Ƶ����ƣ�");
	private JLabel labelintro = new JLabel("�Ƶ���ܣ�");
	private JLabel labeladd = new JLabel("�Ƶ��ַ��");
	private JLabel labelstar = new JLabel("�Ƶ��Ǽ���");
	private JLabel labelsttime = new JLabel("�Ƶ���סʱ�䣺");
	
	private JTextField edtid = new JTextField(10);
	private JTextField edtarea_id = new JTextField(10);
	private JTextField edtName = new JTextField(20);
	private JTextField edtintro = new JTextField(30);
	private JTextField edtadd = new JTextField(20);
	private JTextField edtstar = new JTextField(10);
	private JTextField edtsttime = new JTextField(20);
	
	public FrmHotelManager_Modifyhotel(JDialog f, String s, boolean b,BeanReaderType rt) {
		super(f, s, b);
		this.readertype=rt;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelid);
		this.edtName.setText(rt.getReaderTypeName());
		workPane.add(edtid);
		workPane.add(labelarea_id);
		this.edtName.setText(rt.getReaderTypeName());
		workPane.add(edtarea_id);
		workPane.add(labelName);
		this.edtName.setText(rt.getReaderTypeName());
		workPane.add(edtName);
		workPane.add(labelintro);
		this.edtName.setText(rt.getReaderTypeName());
		workPane.add(edtintro);
		workPane.add(labeladd);
		this.edtName.setText(rt.getReaderTypeName());
		workPane.add(edtadd);
		workPane.add(labelstar);
		this.edtName.setText(rt.getReaderTypeName());
		workPane.add(edtstar);
		workPane.add(labelsttime);
		this.edtName.setText(rt.getReaderTypeName());
		workPane.add(edtsttime);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(360, 140);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmHotelManager_Modifyhotel.this.readertype=null;
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.readertype=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			String name=this.edtName.getText();
			int n=0;
			
			//������Ϣ
			
			this.readertype.setLendBookLimitted(n);
			this.readertype.setReaderTypeName(name);
			try {
				(new ReaderManager()).modifyReaderType(this.readertype);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.readertype=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public BeanReaderType getReadertype() {
		return readertype;
	}
	
}
