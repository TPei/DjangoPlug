package de.fhflensburg.pd.group007.helper;


import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.fhflensburg.pd.group007.Activator;

public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	private final String TEST_ARGUMENTS = "testArgs";
	private final String SERVER_ADDRESS = "serverAddress";
	private final String SERVER_PORT = "serverPort";
	private final String DJANGO_DIRECTORY = "djangoDir";
	private final String PYTHON_DIRECTORY = "pythonDir";
	
	private final String DEFAULT_TEST_ARGS = "";
	private final String DEFAULT_SERVER_ADDRESS = "127.0.0.1";
	private final int DEFAULT_SERVER_PORT = 8000;
	private final String DEFAULT_DJANGO_DIRECTORY = "C:\\Users\\Thomas Peikert\\djangoproject";
	private final String DEFAULT_PYTHON_DIRECTORY = "C:\\python34\\python";
	
	public PreferencePage() {
		super(GRID);
	}
	
	@Override
	public void init(IWorkbench arg0) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		performDefaults();
	}
	
	/**
	 * save default values
	 */
	public void performDefaults() {
		IPreferenceStore store = getPreferenceStore();
		store.setValue(TEST_ARGUMENTS, DEFAULT_TEST_ARGS);
		store.setValue(SERVER_ADDRESS, DEFAULT_SERVER_ADDRESS);
		store.setValue(SERVER_PORT, DEFAULT_SERVER_PORT);
		store.setValue(DJANGO_DIRECTORY, DEFAULT_DJANGO_DIRECTORY);
		store.setValue(PYTHON_DIRECTORY, DEFAULT_PYTHON_DIRECTORY);
	}

	@Override
	protected void createFieldEditors() {
		addField(new StringFieldEditor(TEST_ARGUMENTS, "Set Test arguments (for example \"car\" to test the class car)", getFieldEditorParent()));
		addField(new StringFieldEditor(SERVER_ADDRESS, "Set server address (default is 127.0.0.1)", getFieldEditorParent()));
		IntegerFieldEditor portField = new IntegerFieldEditor(SERVER_PORT, "Set server port (default is 8000)", getFieldEditorParent());
		portField.setValidRange(1000, 9999);
		addField(portField);
		addField(new DirectoryFieldEditor(DJANGO_DIRECTORY, "Directory for Django Installation", getFieldEditorParent()));
		addField(new DirectoryFieldEditor(PYTHON_DIRECTORY, "Directory for Python Installation", getFieldEditorParent()));
	}
	
}
