import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class confirm extends Dialog {

	protected Object result;
	protected Shell shell;
	public SelectionEvent d;
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public confirm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String text) {
		createContents(text);
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents(String text) {
		String no;
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 136);
		shell.setText(getText());
		shell.setLocation(getParent().getBounds().x,(getParent().getBounds().y));
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(127, 27, 332, 40);
		lblNewLabel.setText(text);
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.data="yes";
				d=e;

				shell.close();			}
		});
		btnOk.setBounds(258, 73, 68, 23);
		btnOk.setText("Ok");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		
		btnCancel.addSelectionListener(new SelectionAdapter() {
		
			String temp;
			public void widgetSelected(SelectionEvent e) {
				e.data="no";
				d=e;
				shell.close();
			}
		});
		btnCancel.setBounds(82, 73, 68, 23);
		btnCancel.setText("Cancel");
		
       
	}

}
