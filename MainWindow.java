import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Rectangle;

public class MainWindow {

	protected Shell shlPasswordKeeper;
	databasebuilder data = new databasebuilder();
	private Text text;
	private Label lblNewLabel_1;
	private Text text_1;
	private Label lblNewLabel_2;
	private Text text_2;
	private Button btnNewButton_1;
	String modtext1,modtext2,modtext3,modtext4;
	private Text text_3;
	private Button btnNewButton_3;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void open() throws SQLException, ClassNotFoundException {
		Display display = Display.getDefault();
		createContents();
		shlPasswordKeeper.open();
		shlPasswordKeeper.layout();
		while (!shlPasswordKeeper.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	protected void createContents() throws SQLException, ClassNotFoundException {
		data.connect();
		shlPasswordKeeper = new Shell();
		shlPasswordKeeper.setSize(485, 349);
		shlPasswordKeeper.setText("Password Keeper");
		Display display = Display.getDefault();
		 Monitor primary = display.getPrimaryMonitor();
		    Rectangle bounds = primary.getBounds();
		    Rectangle rect = shlPasswordKeeper.getBounds();
		    
		    int x = bounds.x + (bounds.width - rect.width) / 2;
		    int y = bounds.y + (bounds.height - rect.height) / 2;
		    
		    shlPasswordKeeper.setLocation(x, y);
		text = new Text(shlPasswordKeeper, SWT.BORDER);
		text.setBounds(181, 10, 102, 22);
		Label lblNewLabel = new Label(shlPasswordKeeper, SWT.NONE);
		lblNewLabel.setBounds(97, 10, 59, 14);
		lblNewLabel.setText("User Name");
		
		lblNewLabel_1 = new Label(shlPasswordKeeper, SWT.NONE);
		lblNewLabel_1.setBounds(97, 57, 59, 14);
		lblNewLabel_1.setText("Password");
		
		text_1 = new Text(shlPasswordKeeper, SWT.BORDER);
		text_1.setBounds(181, 54, 102, 22);
		
		lblNewLabel_2 = new Label(shlPasswordKeeper, SWT.NONE);
		lblNewLabel_2.setBounds(97, 101, 59, 14);
		lblNewLabel_2.setText("URL");
		
		text_2 = new Text(shlPasswordKeeper, SWT.BORDER);
		text_2.setBounds(181, 98, 102, 22);
		
		text_3 = new Text(shlPasswordKeeper, SWT.BORDER);
		text_3.setBounds(327, 10, 140, 68);
		
		
		List list = new List(shlPasswordKeeper, SWT.BORDER);
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(list.getSelectionIndex()>=0)
					{
					text.setText(data.select("UserName", list.getItem(list.getSelectionIndex())));
					text_1.setText(data.select("password", list.getItem(list.getSelectionIndex())));
					text_2.setText(data.select("LoginUrl", list.getItem(list.getSelectionIndex())));
					text_3.setText(data.select("Notes", list.getItem(list.getSelectionIndex())));
					modtext1 = text.getText();
					modtext2=text_1.getText();
					modtext3=text_2.getText();
					modtext4=text_3.getText();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		list.setBounds(0, 0, 79, 263);
		list.setItems(data.Populate("WebsiteName"));
		if(list.getItemCount()>0)
		{
		list.setSelection(0);
		text.setText(data.select("UserName", list.getItem(list.getSelectionIndex())));
		text_1.setText(data.select("password", list.getItem(list.getSelectionIndex())));
		text_2.setText(data.select("LoginUrl", list.getItem(list.getSelectionIndex())));
		text_3.setText(data.select("Notes", list.getItem(list.getSelectionIndex())));
		modtext1 = text.getText();
		modtext2=text_1.getText();
		modtext3=text_2.getText();
		modtext4=text_3.getText();
		}
		
		
		Button btnNewButton = new Button(shlPasswordKeeper, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CreateNew create = new CreateNew(shlPasswordKeeper, shlPasswordKeeper.getStyle());
				create.open();
				try {
					list.setItems(data.Populate("WebsiteName"));
					list.setSelection(0);
					text.setText(data.select("UserName", list.getItem(list.getSelectionIndex())));
					text_1.setText(data.select("password", list.getItem(list.getSelectionIndex())));
					text_2.setText(data.select("LoginUrl", list.getItem(list.getSelectionIndex())));
					text_3.setText(data.select("Notes", list.getItem(list.getSelectionIndex())));
					modtext1 = text.getText();
					modtext2=text_1.getText();
					modtext3=text_2.getText();
					modtext4=text_3.getText();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(100, 141, 80, 25);
		btnNewButton.setText("New");
		
		btnNewButton_1 = new Button(shlPasswordKeeper, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
					try {
						connections conn = new connections(text.getText(),text_1.getText(),text_2.getText());
						if(conn.error==true)
						{
							Error error = new Error(shlPasswordKeeper, shlPasswordKeeper.getStyle());
							error.open("You need Firefox to use this feature");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		btnNewButton_1.setBounds(291, 141, 83, 24);
		btnNewButton_1.setText("Connect");
		
		Button btnNewButton_2 = new Button(shlPasswordKeeper, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(list.getSelectionIndex()<0)
				{
					return;
				}
				if(text.getText().equals(modtext1)==false)
				{
					System.out.println("here");
					try {
						data.update(list.getItem(list.getSelectionIndex()), "UserName",text.getText());
						System.out.println("changed");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
						}
				if(text_2.getText().equals(modtext3)==false)
				{
					try {
						data.update(list.getItem(list.getSelectionIndex()), "LoginUrl",text_2.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				if(text_1.getText().equals(modtext2)==false)
				{
					try {
						data.update(list.getItem(list.getSelectionIndex()), "password",text_1.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(text_3.getText().equals(modtext3)==false)
				{
					try {
						data.update(list.getItem(list.getSelectionIndex()), "Notes",text_3.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
			}
		});
		btnNewButton_2.setBounds(193, 141, 83, 25);
		btnNewButton_2.setText("Update");
		
		
		
		btnNewButton_3 = new Button(shlPasswordKeeper, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(list.getSelectionIndex()>=0)
				{   confirm conf = new confirm(shlPasswordKeeper, shlPasswordKeeper.getStyle());
				    conf.open("Are you sure you want to delete this?");
				    if(conf.d.data=="yes")
				    {
		
					data.delete(list.getItem(list.getSelectionIndex()));
					try {
						list.setItems(data.Populate("WebsiteName"));
						list.setSelection(0);
						text.setText(data.select("UserName", list.getItem(list.getSelectionIndex())));
						text_1.setText(data.select("password", list.getItem(list.getSelectionIndex())));
						text_2.setText(data.select("LoginUrl", list.getItem(list.getSelectionIndex())));
						text_3.setText(data.select("Notes", list.getItem(list.getSelectionIndex())));
						modtext1 = text.getText();
						modtext2=text_1.getText();
						modtext3=text_2.getText();
						modtext4=text_3.getText();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    }
				    if(conf.d.data=="no")
				    {
				    	return;
				    }
				}
			}
			
		});
		btnNewButton_3.setBounds(99, 186, 81, 23);
		btnNewButton_3.setText("Delete");
		
		Button btnOptions = new Button(shlPasswordKeeper, SWT.NONE);
		btnOptions.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Settings set = new Settings(shlPasswordKeeper,shlPasswordKeeper.getStyle());
				set.open();
			}
		});
		btnOptions.setBounds(97, 227, 75, 25);
		btnOptions.setText("Options");
		
		
		
		

	}
}
