package org.pdfsam.module;

public class ModuleDescriptorBuilderData {
	public ModuleCategory category;
	public ModuleInputOutputType[] inputTypes;
	public String name;
	public String description;
	public int priority;
	public String supportURL;

	public ModuleDescriptorBuilderData(int priority) {
		this.priority = priority;
	}
}