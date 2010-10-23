package com.ttdev.cs;

import javax.jws.WebService;

@WebService(endpointInterface = "com.ttdev.cs.ComputeService")
public class ComputeServiceImpl implements ComputeService {
	private ComplexLogic logic;

	public void setLogic(ComplexLogic logic) {
		this.logic = logic;
	}

	@Override
	public String compute(String parameters) {
		return logic.calc(parameters);
	}

}
 