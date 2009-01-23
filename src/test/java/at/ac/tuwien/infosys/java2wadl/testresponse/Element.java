package at.ac.tuwien.infosys.java2wadl.testresponse;

import java.util.List;

public class Element {

	public Long id;
	public String name;
	public List<Long> subElementIds;

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
