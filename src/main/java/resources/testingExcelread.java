package resources;

public class testingExcelread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
			 System.out.println(System.getProperty("user.dir"));
			ReadExcel eat = new ReadExcel("./Testcase-TransparenSee.xlsx");
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	        
	}

}