package faultfinder.views;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

public class StatementsMap 
{
	public List<StatementInfo> Statements = new ArrayList<StatementInfo>();
	
	public String m_basePath = "";

	public void NavigateToFile(StatementInfo info)
	{
		String path = m_basePath + "/" + info.FileName;
		NavigateToFile(path, info.LineNumber - 1);
	}
	
	private void NavigateToFile(String file, int line)
	{
		File fileToOpen = new File(file);

		if (fileToOpen.exists() && fileToOpen.isFile()) 
		{
			IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

			try 
			{
				IEditorPart p = IDE.openEditorOnFileStore( page, fileStore );
				if( p instanceof ITextEditor )
				{
					ITextEditor editor = (ITextEditor)p;
					IDocument document = editor.getDocumentProvider().getDocument(p.getEditorInput());
					try
					{
						editor.selectAndReveal(document.getLineOffset(line),document.getLineLength(line));
					}
					catch (BadLocationException e)
					{
						e.printStackTrace();
					}
				}
				// IJavaElement editorCU=EditorUtility.getEditorInputJavaElement(editor, false);
			} 
			catch ( PartInitException e ) 
			{
				//Put your exception handler here if you wish to
			}
		} 
		else 
		{
			//Do something if the file does not exist
		}
	}
	

}
