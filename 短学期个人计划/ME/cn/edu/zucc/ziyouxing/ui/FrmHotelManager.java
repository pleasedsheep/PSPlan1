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
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.model.BeanReaderType;
import cn.edu.zucc.booklib.util.BaseException;

public class FrmHotelManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加酒店");
	private Button btnModify = new Button("修改酒店信息");
	private Button btnDelete = new Button("删除酒店信息");
	private Object tblTitle[]={"酒店编号","所属类别","酒店名称","酒店介绍","酒店地址","酒店星级","酒店入住时间"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable readerTypeTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			List<BeanReaderType> types=(new ReaderManager()).loadAllReaderType();
			tblData =new Object[types.size()][7];
			for(int i=0;i<types.size();i++){
				tblData[i][0]=types.get(i).getReaderTypeId()+"";
				tblData[i][1]=types.get(i).getReaderTypeName();
				tblData[i][2]=types.get(i).getReaderTypeName();
				tblData[i][3]=types.get(i).getReaderTypeName();
				tblData[i][4]=types.get(i).getReaderTypeName();
				tblData[i][5]=types.get(i).getReaderTypeName();
				tblData[i][6]=types.get(i).getReaderTypeName();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.readerTypeTable.validate();
			this.readerTypeTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmHotelManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.readerTypeTable), BorderLayout.CENTER);
		
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
			FrmHotelManager_Addhotel dlg=new FrmHotelManager_Addhotel(this,"添加酒店",true);
			dlg.setVisible(true);
			if(dlg.getReadertype()!=null){//刷新表格
				this.reloadTable();
			}
		}
		else if(e.getSource()==this.btnModify){
			int i=this.readerTypeTable.getSelectedRow();
			
			//限制条件
			int n=Integer.parseInt(this.tblData[i][0].toString());
			BeanReaderType readertype=new BeanReaderType();
			readertype.setReaderTypeId(n);
			readertype.setReaderTypeName(this.tblData[i][1].toString());
			readertype.setLendBookLimitted(Integer.parseInt(this.tblData[i][2].toString()));
			FrmHotelManager_Modifyhotel dlg=new FrmHotelManager_Modifyhotel(this,"添加酒店",true,readertype);
			dlg.setVisible(true);
			if(dlg.getReadertype()!=null){//刷新表格
				this.reloadTable();
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.readerTypeTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请输入酒店编号","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除该酒店吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				int n=Integer.parseInt(this.tblData[i][0].toString());
				try {
					(new ReaderManager()).deleteReaderType(n);
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
