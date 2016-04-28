package nl.cwi.md;

public class Cell<T> {
	private T value;

	public Cell(){
	}
	
	public Cell(T value){
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
