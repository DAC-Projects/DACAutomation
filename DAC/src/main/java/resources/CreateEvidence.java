package resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

public class CreateEvidence {
	
	 static String cTAbstractNumBulletXML = 
			  "<w:abstractNum xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:abstractNumId=\"0\">"
			+ "<w:multiLevelType w:val=\"hybridMultilevel\"/>"
			+ "<w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"720\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Wingdings\" w:hAnsi=\"Wingdings\" w:hint=\"default\"/></w:rPr></w:lvl>"
			+ "<w:lvl w:ilvl=\"1\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"-\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"1440\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Courier New\" w:hAnsi=\"Courier New\" w:cs=\"Courier New\" w:hint=\"default\"/></w:rPr></w:lvl>"
			+ "<w:lvl w:ilvl=\"2\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"2160\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Symbol\" w:hAnsi=\"Symbol\" w:hint=\"default\"/></w:rPr></w:lvl>"
			+ "</w:abstractNum>";

	static String cTAbstractNumDecimalXML = 
			  "<w:abstractNum xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:abstractNumId=\"1\">"
			+ "<w:multiLevelType w:val=\"hybridMultilevel\"/>"
			+ "<w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"720\" w:hanging=\"360\"/></w:pPr></w:lvl>"
			+ "<w:lvl w:ilvl=\"1\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1.%2\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"1440\" w:hanging=\"360\"/></w:pPr></w:lvl>"
			+ "<w:lvl w:ilvl=\"2\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1.%2.%3\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"2160\" w:hanging=\"360\"/></w:pPr></w:lvl>"
			+ "</w:abstractNum>";

	private static final String callerClassName = new Exception().getStackTrace()[1].getClassName();
	private static final String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH_mm")
			.format(Calendar.getInstance().getTime());
	
	XWPFDocument doc = new XWPFDocument();

	// To DO write a method to read image file names from excel and call the method
	// from function below to get Image names
	public void creatDoc(ArrayList<String[]> arraySteps) throws IOException, InvalidFormatException, XmlException {

		

		
		
		ArrayList<String> imgnames = new ArrayList<String>();
		int counter=0;
		int counter2= -1;
		
		// create an array with screenshot names
		for (String[] step: arraySteps)
		{	if (step[2].equalsIgnoreCase("yes"))
			{
			counter += 1;
			imgnames.add("Test evidence_"+counter);
			System.out.println("image array: "+imgnames.toString());
			
			}
		}
		
		
		
		for (String[] step: arraySteps)
		{
			 String TestStep =  step[0].toString();
			 String ExpctdRsult = step[1].toString();
			 String ScrnshtRequird = step[2].toString();
			 
			 
				
			
			 XWPFParagraph p = doc.createParagraph();
				p.setNumID(orderBy("No"));
				
				
				XWPFRun r = p.createRun();
		
			if (TestStep.trim().matches(".*\\w.*"))
			{	
				
				r.setText(TestStep.trim());
			}
			p.setSpacingAfter(0);
			
			
			
		
			if (ExpctdRsult.trim().matches(".*\\w.*") )
				
			{	XWPFParagraph p2 = doc.createParagraph();
				p2.setNumID(orderBy("No"));
				p2.getCTP().getPPr().getNumPr().addNewIlvl().setVal(BigInteger.valueOf(1));
				p2.setWordWrapped(true);
				XWPFRun r2= p2.createRun();
				r2.setText(ExpctdRsult.trim());
			}
		
			p.setSpacingAfter(0);
		if (ScrnshtRequird.equalsIgnoreCase("yes"))
		{	counter2 +=1;
			String screenshotName=imgnames.get(counter2).toString(); 
			System.out.println(screenshotName);
			File img1 = new File("Screenshot/" + screenshotName + ".png");
			if(img1.exists()){
		        System.out.println("image found");
		        BufferedImage bimg1 = ImageIO.read(img1);
				int height1 = bimg1.getHeight();
				String imgFile1 = img1.getName();
				int imgFormat1 = getImageFormat(imgFile1);
				r.addBreak();
				r.addPicture(new FileInputStream(img1), imgFormat1, imgFile1, Units.toEMU(500), Units.toEMU(200));
				r.addBreak(BreakType.TEXT_WRAPPING);
		}else
	    {
	    	System.out.println("image not found.");
	    	r.setText("*******"+"Error encountered. Please refer report for details"+"*******" );
			r.addBreak(BreakType.TEXT_WRAPPING);
	    }
		}
		
		

			// BufferedImage bimg2 = ImageIO.read(img2);
			// int width2 = bimg2.getWidth();
			// int height2 = bimg2.getHeight();

			
			// String imgFile2 = img2.getName();

			
			// int imgFormat2 = getImageFormat(imgFile2);

			

			// r.setText(imgFile2);
			// r.addBreak();
			// r.addPicture(new FileInputStream(img2), imgFormat2, imgFile2,
			// Units.toEMU(500), Units.toEMU(200));

		}
		String callerClassName = new Exception().getStackTrace()[1].getClassName();
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HH_mm").format(Calendar.getInstance().getTime());

		FileOutputStream out = new FileOutputStream("testevidence/" + callerClassName + "-" + timeStamp + ".docx");
		doc.write(out);

		out.close();
		// doc.close();

	}

	public static int getImageFormat(String imgFile) {
		int format;
		if (imgFile.endsWith(".emf"))
			format = XWPFDocument.PICTURE_TYPE_EMF;
		else if (imgFile.endsWith(".wmf"))
			format = XWPFDocument.PICTURE_TYPE_WMF;
		else if (imgFile.endsWith(".pict"))
			format = XWPFDocument.PICTURE_TYPE_PICT;
		else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
			format = XWPFDocument.PICTURE_TYPE_JPEG;
		else if (imgFile.endsWith(".png"))
			format = XWPFDocument.PICTURE_TYPE_PNG;
		else if (imgFile.endsWith(".dib"))
			format = XWPFDocument.PICTURE_TYPE_DIB;
		else if (imgFile.endsWith(".gif"))
			format = XWPFDocument.PICTURE_TYPE_GIF;
		else if (imgFile.endsWith(".tiff"))
			format = XWPFDocument.PICTURE_TYPE_TIFF;
		else if (imgFile.endsWith(".eps"))
			format = XWPFDocument.PICTURE_TYPE_EPS;
		else if (imgFile.endsWith(".bmp"))
			format = XWPFDocument.PICTURE_TYPE_BMP;
		else if (imgFile.endsWith(".wpg"))
			format = XWPFDocument.PICTURE_TYPE_WPG;
		else {
			return 0;
		}
		return format;
	}

	
	public BigInteger orderBy(String s) throws XmlException
	{

		 CTNumbering cTNumbering = CTNumbering.Factory.parse(cTAbstractNumBulletXML);
		  CTAbstractNum cTAbstractNum = cTNumbering.getAbstractNumArray(0);
		  XWPFAbstractNum abstractNum1 = new XWPFAbstractNum(cTAbstractNum);


		  cTNumbering = CTNumbering.Factory.parse(cTAbstractNumDecimalXML);
		  cTAbstractNum = cTNumbering.getAbstractNumArray(0);
		  XWPFAbstractNum abstractNum2 = new XWPFAbstractNum(cTAbstractNum);

		  XWPFNumbering numbering = doc.createNumbering();

		  BigInteger abstractNumID1 = numbering.addAbstractNum(abstractNum1);
		  BigInteger numID1 = numbering.addNum(abstractNumID1);

		  BigInteger abstractNumID2 = numbering.addAbstractNum(abstractNum2);
		  BigInteger numID2 = numbering.addNum(abstractNumID2);
		  
		  if(s.equals("No")) return numID2;
		  else return numID2;
		 
		 

		 
	}
	
	
}
