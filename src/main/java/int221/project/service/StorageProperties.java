package int221.project.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "integrated.storage")
public class StorageProperties {
	
	private String location;

	public String getLocation() {
		System.out.println(location);
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
