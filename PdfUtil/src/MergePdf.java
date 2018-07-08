import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;

public class MergePdf {
	public static void main(String[] args) throws IOException, COSVisitorException {

	      //Loading an existing PDF document
	      File file1 = new File("file1.pdf");
	      PDDocument doc1 = PDDocument.load(file1);
	       
	      File file2 = new File("file2.pdf");
	      PDDocument doc2 = PDDocument.load(file2);
	         
	      //Instantiating PDFMergerUtility class
	      PDFMergerUtility PDFmerger = new PDFMergerUtility();

	      //Setting the destination file
	      PDFmerger.setDestinationFileName("merged.pdf");

	      //adding the source files
	      PDFmerger.addSource(file1);
	      PDFmerger.addSource(file2);

	      //Merging the two documents
	      PDFmerger.mergeDocuments();

	      System.out.println("Documents merged");
	      //Closing the documents
	      doc1.close();
	      doc2.close();
	   }
}
