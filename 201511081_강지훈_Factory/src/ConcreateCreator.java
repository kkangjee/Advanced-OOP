
public class ConcreateCreator extends Creator{
	private Product product = null;

	private volatile static ConcreateCreator inst = new ConcreateCreator();
	private ConcreateCreator() {};

	public static ConcreateCreator getInstance() {
		return inst;
	}
	
	
	public Product factoryMethod(String title, int x, int y, int width, int height) {
		if (title.equals("TextField Window")) {
			product = new TextFieldWindow(title,  x,  y,  width,  height);
		}
		else if (title.equals("Label Window")) {
			product = new LabelWindow(title,  x,  y,  width,  height);
		}
		return product;
	}
	
}
