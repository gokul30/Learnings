/*
This will extract the images available in the PDF using PDFBox
*/
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectForm;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

public class ExtractImageFromPdf {

	public static BufferedImage convertToImage(String PDFPath) throws IOException
	{		
		BufferedImage image=null;
		try {
			PDDocument	document = PDDocument.load(PDFPath);
			PDDocumentInformation info1 = document.getDocumentInformation();
			System.out.println(info1.getTitle());
			System.out.println(info1.getCOSObject());
			final List pages = document.getDocumentCatalog().getAllPages();
			final Iterator pageIterator = pages.iterator();
			while (pageIterator.hasNext()) {
				PDPage   thisPage = (PDPage) pageIterator.next();

				Map pageImage = thisPage.getResources().getXObjects();
				if(pageImage != null){
					Iterator imageIter = pageImage.keySet().iterator();
					Long count = 1l;
					while(imageIter.hasNext()){
						String key = (String) imageIter.next();
						System.out.println(key);
						PDXObject xObject = (PDXObject) pageImage.get(key);
						System.out.println("1 ==> "+xObject);
						System.out.println();
						
						if (xObject instanceof PDXObjectImage){
							PDXObjectImage pdxObjectImage = (PDXObjectImage) pageImage.get(key);
							pdxObjectImage.write2file("C:/Users/"+count);
							count += 1;
						}
						else if (xObject instanceof PDXObjectForm){
							PDXObjectForm pdxObjectForm = (PDXObjectForm) pageImage.get(key);
							//System.out.println(pdxObjectForm.getCOSStream());
							Collection<COSBase> cmap = pdxObjectForm.getCOSStream().getValues();
							System.out.println(cmap);
							for(COSBase base : cmap){
								//System.out.println(base.getCOSObject());
								System.out.println(ToStringBuilder.reflectionToString(base.getCOSObject()));
							}
							System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
							System.out.println(pdxObjectForm.getResources().getXObjects());
							Map pageImage2 = pdxObjectForm.getResources().getXObjects();
							if(pageImage2 != null){
								Iterator imageIter1 = pageImage2.keySet().iterator();
								while(imageIter1.hasNext()){
									String key1 = (String) imageIter1.next();
									System.out.println(key1);
									PDXObject xObject1 = pdxObjectForm.getResources().getXObjects().get(key1);
									System.out.println(xObject1);
									if (xObject1 instanceof PDJpeg){
										PDJpeg img = (PDJpeg) xObject1;
										System.out.println("NAME ==>"+img.getPDStream().getStream().getNameAsString(COSName.NAME));
										img.write2file("C:/Users/"+key1+count);
										count += 1;
									}
								}
							}
						}else if(xObject instanceof PDPixelMap){
							PDPixelMap pdPixelMap = (PDPixelMap) pageImage.get(key);;
							System.out.println(pdPixelMap.getCOSObject());
						}
					}
				}
			}
			document.close();	   
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static void main(String[] args) throws IOException {
		PdfHelper.convertToImage("freeformatter-decoded.pdf");
	}
}
