package resources;

public class testingExcelread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
			ReadExcel eat = new ReadExcel("./Testcase-TransparenSee.xlsx");
			eat.getTestcases("Sheet1", "ID", 5, "id:36580");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	        
	}

}
