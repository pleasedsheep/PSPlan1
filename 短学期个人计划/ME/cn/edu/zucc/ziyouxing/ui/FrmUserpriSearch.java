package cn.edu.zucc.ziyouxing.ui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.booklib.control.BookLendManager;
import cn.edu.zucc.booklib.control.BookManager;
import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.model.BeanBook;
import cn.edu.zucc.booklib.model.BeanBookLendRecord;
import cn.edu.zucc.booklib.model.BeanReader;
import cn.edu.zucc.booklib.util.BaseException;
import cn.edu.zucc.booklib.util.DbException;

public class FrmUserpriSearch extends JDialog {
	private JPanel toolBar = new JPanel();
	private JLabel lableReader = new JLabel("用户：");
	private JTextField edtReaderId = new JTextField(10);
	private JLabel lableReaderName = new JLabel("");
	private JLabel lableReaderState = new JLabel("");
	private JLabel lableMessage = new JLabel("");
	
	private Object tblTitle[]={"用户编号","金额","评价","总优惠","明确信息"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private final java.text.SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private void reloadTable(){
		if("".equals(this.lableReaderName.getText())){
			tblData =new Object[0][5];
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
			return;
		}
		try {
			List<BeanBookLendRecord> records=(new BookLendManager()).loadReaderAllRecode(this.edtReaderId.getText());
			tblData =new Object[records.size()][5];
			for(int i=0;i<records.size();i++){
				tblData[i][0]=records.get(i).getBookBarcode();
				tblData[i][1]=records.get(i).getBookBarcode();
				tblData[i][2]=records.get(i).getBookBarcode();
				tblData[i][3]=records.get(i).getBookBarcode();
				tblData[i][4]=records.get(i).getBookBarcode();
				
			}
			
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void reloadReaderInfo(){
		String s = edtReaderId.getText().trim();
        BeanReader r=null;
        if(!"".equals(s))
			try {
				r=(new ReaderManager()).loadReader(s);
			} catch (DbException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        this.lableReaderName.setText("");
        this.lableReaderState.setText("");
        if(r!=null){
        	this.lableReaderName.setText(r.getReaderName());
        	this.lableReaderState.setText(r.getRemoveDate()!=null?"已注销":(r.getStopDate()==null?"":"已挂失"));
        }
        this.reloadTable();
	}
	public FrmUserpriSearch(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.lableReaderState.setForeground(Color.red);
		toolBar.add(lableReader);
		toolBar.add(edtReaderId);
		toolBar.add(lableReaderName);
		toolBar.add(lableReaderState);
		toolBar.add(lableMessage);
		
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.edtReaderId.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
		    public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
		    	FrmUserpriSearch.this.reloadReaderInfo();
		  	}
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				FrmUserpriSearch.this.reloadReaderInfo();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				FrmUserpriSearch.this.reloadReaderInfo();
			}
		});
	}
}
