package com.science.earth.biogeochemistry.freshwaters.pandora.config;

public abstract class Variable implements VariableConfiguration {
    private boolean indexed = false;
    private int index;
  
    public int getIndex() {
	return this.index;
    }
    
    // only allow setting index once
    public void setIndex(int index) throws Exception {
	if (!indexed) {
	    this.index = index;
	    indexed = true;
	} else {
	    throw new Exception("This variable is already indexed");
	}
    }
}
