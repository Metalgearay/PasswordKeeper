import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CreateNew extends Dialog {

	protected Object result;
	protected Shell shlEntryCreation;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Label lblWebsiteName;
	private Label lblUserName;
	private Label lblPassword;
	private Label lblLoginUrl;
	private Text text_5;
	private Label lblNotes;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CreateNew(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlEntryCreation.open();
		shlEntryCreation.layout();
		Display display = getParent().getDisplay();
		while (!shlEntryCreation.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlEntryCreation = new Shell(getParent(), getStyle());
		shlEntryCreation.setSize(418, 318);
		shlEntryCreation.setText("Entry Creation");
		shlEntryCreation.setLocation(getParent().getBounds().x,(getParent().getBounds().y));
		text_1 = new Text(shlEntryCreation, SWT.BORDER);
		text_1.setBounds(127, 24, 139, 22);
		
		text_2 = new Text(shlEntryCreation, SWT.BORDER);
		text_2.setBounds(127, 66, 139, 22);
		
		text_3 = new Text(shlEntryCreation, SWT.BORDER);
		text_3.setBounds(127, 108, 139, 22);
		
		Button btnNewButton = new Button(shlEntryCreation, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Error err = new Error(shlEntryCreation, getStyle());
				if(text_1.getText()=="")
				{
					err.open("You Must Enter A Website Name");
					return;
				}
				databasebuilder data = new databasebuilder();
				try {
					data.insert(text_1.getText(), text_2.getText(), text_3.getText(), text_4.getText(),text_5.getText());
					shlEntryCreation.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		});
		btnNewButton.setBounds(248, 241, 80, 25);
		btnNewButton.setText("Ok");
		
		text_4 = new Text(shlEntryCreation, SWT.BORDER);
		text_4.setBounds(127, 152, 139, 22);
		
		Button btnCancel = new Button(shlEntryCreation, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlEntryCreation.close();
			}
		});
		btnCancel.setBounds(96, 242, 80, 23);
		btnCancel.setText("Cancel");
		
		lblWebsiteName = new Label(shlEntryCreation, SWT.NONE);
		lblWebsiteName.setBounds(41, 27, 80, 13);
		lblWebsiteName.setText("Website Name");
		
		lblUserName = new Label(shlEntryCreation, SWT.NONE);
		lblUserName.setBounds(52, 69, 69, 13);
		lblUserName.setText("User Name");
		
		lblPassword = new Label(shlEntryCreation, SWT.NONE);
		lblPassword.setBounds(52, 108, 49, 13);
		lblPassword.setText("Password");
		
		lblLoginUrl = new Label(shlEntryCreation, SWT.NONE);
		lblLoginUrl.setBounds(52, 152, 49, 13);
		lblLoginUrl.setText("Login Url");
		
		text_5 = new Text(shlEntryCreation, SWT.BORDER);
		text_5.setBounds(117, 192, 177, 43);
		
		lblNotes = new Label(shlEntryCreation, SWT.NONE);
		lblNotes.setBounds(52, 203, 49, 13);
		lblNotes.setText("Notes");
		

	}
}
