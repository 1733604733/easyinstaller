package com.jianglibo.vaadin.dashboard.sshrunner;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.jianglibo.vaadin.dashboard.annotation.Runner;
import com.jianglibo.vaadin.dashboard.domain.JschExecuteResult;
import com.jianglibo.vaadin.dashboard.domain.StepRun;
import com.jianglibo.vaadin.dashboard.repositories.StepRunRepository;
import com.jianglibo.vaadin.dashboard.ssh.CodeSubstitudeUtil;
import com.jianglibo.vaadin.dashboard.ssh.JschSession;

/**
 * It must upload execute content to server before execute.
 * A working folder to hold uploaded code file is needed.
 * 
 * It's design to run a batch of command in a file.
 * 
 * @author jianglibo@gmail.com
 *
 */
@Runner(SshExecRunner.UNIQUE_NAME)
@Component(SshExecRunner.UNIQUE_NAME)
public class SshExecRunner implements BaseRunner {
	
	public static final String UNIQUE_NAME = "SshExecRunner";
	
	@Autowired
	private StepRunRepository stepRunRepository;

	@Override
	public JschExecuteResult run(JschSession jsession, StepRun stepRun) {
		copyCodeToServer(jsession, stepRun);
		return null;
	}
	
	private void copyCodeToServer(JschSession jsession, StepRun stepRun) {
		String code = CodeSubstitudeUtil.process(stepRun);
		ChannelSftp sftp = null;
		String cmdFile = "/opt/web-easyinstall/" + stepRun.getUniqueFileName();
		try {
			sftp = jsession.getSftpCh();
			try {
				OutputStream os = sftp.put(cmdFile, ChannelSftp.OVERWRITE);
				os.write(code.getBytes(Charsets.UTF_8));
				os.flush();
				os.close();
			} catch (SftpException | IOException e) {
				e.printStackTrace();
			}
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			if (sftp != null) {
				sftp.disconnect();
			}
		}
		String cmd = stepRun.getStepDefine().getRunner() + " " + cmdFile;
		JschExecuteResult jer = jsession.exec(cmd);
		stepRun.setResult(jer);
		stepRunRepository.save(stepRun);
	}

	@Override
	public String uniqueName() {
		return UNIQUE_NAME;
	}

}