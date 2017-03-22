package qi.yue.dto;

public class PageDTO {
	private Integer id;
	private Integer size = 20;
	private Integer currentNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

	public void setCurrentNumberFromPages(Integer pages) {
		this.currentNumber = size * pages;
	}
}
