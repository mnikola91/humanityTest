package humanityUtils;

public class UtilsMain {

	public static void main(String[] args) {

		ExcelUtils ex = new ExcelUtils();
		ex.setExcell("utils-files\\radnici.xlsx");
		ex.setWorkSheet(0);

		System.out.println(ex.getRowNumber());

		for (int i = 1; i < ex.getRowNumber(); i++) {
			System.out.println(ex.getDataAt(i, 0) + " " + ex.getDataAt(i, 1) + " " + ex.getDataAt(i, 2));
		}

		ex.closeExcell();
	}
}
