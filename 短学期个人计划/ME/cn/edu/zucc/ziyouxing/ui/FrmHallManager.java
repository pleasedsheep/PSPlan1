package cn.edu.zucc.ziyouxing.ui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.control.SystemUserManager;
import cn.edu.zucc.booklib.model.BeanReader;
import cn.edu.zucc.booklib.model.BeanReaderType;
import cn.edu.zucc.booklib.util.BaseException;

public class FrmHallManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加餐厅");
	private Button btnModify = new Button("修改餐厅信息");
	private Button btnDelete = new Button("删除餐厅");
	private Map<String,BeanReaderType> readerTypeMap_name=new HashMap<String,BeanReaderType>();
	private Map<Integer,BeanReaderType> readerTypeMap_id=new HashMap<Integer,BeanReaderType>();
	private JComboBox cmbReadertype=null;
	
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询");
	
	private Object tblTitle[]={"餐厅编号","所属区域","餐厅名称","餐厅介绍","特色菜品","人均消费"};
	private Object tblData[][];
	List<BeanReader> readers=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable readerTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			int n=this.cmbReadertype.getSelectedIndex();
			int rtId=0;
			if(n>=0){
				String rtname=this.cmbReadertype.getSelectedItem().toString();
				BeanReaderType rt=this.readerTypeMap_name.get(rtname);
				if(rt!=null) rtId=rt.getReaderTypeId();
			}
			readers=(new ReaderManager()).searchReader(this.edtKeyword.getText(), rtId);
			tblData =new Object[readers.size()][6];
			for(int i=0;i<readers.size();i++){
				tblData[i][0]=readers.get(i).getReaderid();
				tblData[i][1]=readers.get(i).getReaderName();
				tblData[i][2]=readers.get(i).getReaderid();
				tblData[i][3]=readers.get(i).getReaderName();
				tblData[i][4]=readers.get(i).getReaderid();
				tblData[i][5]=readers.get(i).getReaderName();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.readerTable.validate();
			this.readerTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmHallManager(Frame f, String s, boolean b) {
		super(f, s, b);
		//提取读者类别信息
		List<BeanReaderType> types=null;
		try {
			types = (new ReaderManager()).loadAllReaderType();
			String[] strTypes=new String[types.size()+1];
			strTypes[0]="";
			for(int i=0;i<types.size();i++) {
				readerTypeMap_name.put(types.get(i).getReaderTypeName(),types.get(i));
				readerTypeMap_id.put(types.get(i).getReaderTypeId(), types.get(i));
				strTypes[i+1]=types.get(i).getReaderTypeName();
			}
			cmbReadertype=new JComboBox(strTypes);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(cmbReadertype);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.readerTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmHallManager_Addhall dlg=new FrmHallManager_Addhall(this,"添加餐厅",true,this.readerTypeMap_name);
			dlg.setVisible(true);
			if(dlg.getReader()!=null){//刷新表格
				this.reloadTable();
			}
		}
		else if(e.getSource()==this.btnModify){
			int i=this.readerTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择餐厅,"提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanReader reader=this.readers.get(i);
			
			FrmHallManager_Modifyhall dlg=new FrmHallManager_Modifyhall(this,"修改餐厅",true,this.readerTypeMap_name,reader);
			dlg.setVisible(true);
			if(dlg.getReader()!=null){//刷新表格
				this.reloadTable();
			}
		}
		
		else if(e.getSource()==this.btnDelete){
			int i=this.readerTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择餐厅","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanReader reader=this.readers.get(i);
			if(JOptionPane.showConfirmDialog(this,"确定删除该餐厅吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					(new ReaderManager()).removeReader(reader.getReaderid(), SystemUserManager.currentUser.getUserid());
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}
	}
}
