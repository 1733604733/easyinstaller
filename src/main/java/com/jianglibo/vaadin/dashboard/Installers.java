package com.jianglibo.vaadin.dashboard;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.jianglibo.vaadin.dashboard.annotation.SoftwareInstaller;
import com.jianglibo.vaadin.dashboard.domain.Software;
import com.jianglibo.vaadin.dashboard.installer.Installer;
import com.jianglibo.vaadin.dashboard.repositories.SoftwareRepository;

/**
 * First checkout all class annotated by @SoftwareInstaller.
 * 
 * @author jianglibo@gmail.com
 *
 */
@Component
public class Installers {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Installers.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SoftwareRepository softwareRepository;
	
	@Autowired
	private GlobalComboOptions comboOptions;
	
	private Map<String, Installer> installers = Maps.newHashMap();
	
	@PostConstruct
	public void post() {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(SoftwareInstaller.class);
		
		for(Object o : beans.values()) {
			SoftwareInstaller swi = o.getClass().getAnnotation(SoftwareInstaller.class);
			if (softwareRepository.findOneByNameAndOstype(swi.name(), swi.ostype()) == null) {
				Software sf = new Software(swi.name(), swi.ostype());
				softwareRepository.save(sf);
			}
			if (o instanceof Installer) {
				installers.put(swi.name() + swi.ostype(), (Installer) o);
				comboOptions.getInstallerNames().add(swi.name());
				comboOptions.getOstypes().add(swi.ostype());
			} else {
				LOGGER.error("Class: {} annotated by @SoftwareInstaller, But not implements Installer interface.", o.getClass());
			}
		}
	}
}
