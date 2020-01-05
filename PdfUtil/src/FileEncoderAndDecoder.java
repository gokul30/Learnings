import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

public class FileEncoderAndDecoder {

	public static void fileToEncodedByteString(String filePath) throws Exception{
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		byte fileData[] = new byte[(int) file.length()];
		fis.read(fileData);
		String base64EncodedString = Base64.getEncoder().encodeToString(fileData);
		System.out.println(base64EncodedString);
	}
	
	public static void main(String[] args) throws Exception {
		FileEncoderAndDecoder.fileToEncodedByteString("C:\\Users\\gokul\\Desktop\\Gokul_Payslip_Nov2019.pdf");
	}
}
