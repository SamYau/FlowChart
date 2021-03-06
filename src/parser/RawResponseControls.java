package parser;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RawResponseControls {

	public static void exportCSVAction() throws Exception {
		// Prompt user path to save CSV file
		@SuppressWarnings("serial")
		JFileChooser fc = new JFileChooser() {
			@Override
			public void approveSelection() {
				File f = getSelectedFile();
				if (f.exists() && getDialogType() == SAVE_DIALOG) {
					int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
					switch (result) {
					case JOptionPane.YES_OPTION:
						super.approveSelection();
						return;
					case JOptionPane.NO_OPTION:
						return;
					case JOptionPane.CLOSED_OPTION:
						return;
					case JOptionPane.CANCEL_OPTION:
						cancelSelection();
						return;
					}
				}
				super.approveSelection();
			}
		};
		fc.setFileFilter(new FileNameExtensionFilter("Spreadsheet File", "csv", "xls"));
		fc.setDialogTitle("Export Session to Spreadsheet");
		int returnVal = fc.showSaveDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			RawResponseCSV.exportCSV(fc.getSelectedFile().getCanonicalPath());
		} else {
			// Cancel CSV export
			return;
		}
	}
}
