package com.jianglibo.vaadin.dashboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.jianglibo.vaadin.dashboard.annotation.VaadinTable;
import com.vaadin.ui.themes.ValoTheme;

@Entity
@VaadinTable(multiSelect = true, messagePrefix = "domain.installstep.", footerVisible = true, styleNames = {
		ValoTheme.TABLE_BORDERLESS, ValoTheme.TABLE_NO_HORIZONTAL_LINES,
		ValoTheme.TABLE_COMPACT }, selectable = true, fullSize = true)
@Table(name = "installstep", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "ostype" }) })
public class InstallStep extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;

	private String runner;

	@NotNull
	private String ostype;

	@Lob
	@Column(length = 64000)
	private String codeContent;

	@Lob
	@Column(length = 64000)
	private String kvpairs;

	private boolean ifSuccessSkipNext;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public String getCodeContent() {
		return codeContent;
	}

	public void setCodeContent(String codeContent) {
		this.codeContent = codeContent;
	}

	public String getKvpairs() {
		return kvpairs;
	}

	public void setKvpairs(String kvpairs) {
		this.kvpairs = kvpairs;
	}

	@Override
	public String getDisplayName() {
		return null;
	}

	public boolean isIfSuccessSkipNext() {
		return ifSuccessSkipNext;
	}

	public void setIfSuccessSkipNext(boolean ifSuccessSkipNext) {
		this.ifSuccessSkipNext = ifSuccessSkipNext;
	}

	public String getRunner() {
		return runner;
	}

	public void setRunner(String runner) {
		this.runner = runner;
	}
}
