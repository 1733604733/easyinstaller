## EasyInstaller

I once wrote a project using tcl script language to simplify common server side software installation, like hadoop, solr cluster, mysql cluster etc. [https://github.com/jianglibo/easy-installer](https://github.com/jianglibo/easy-installer).

This project use Vaadin technology, web interface, so it's far more easy to use.

### Abstraction

Thinking of software install progress, The essential step involved:

1. upload some file to server.
2. upload execute environment file to server.
3. upload code file to server and run it, code can access environment file in step 2. code file may be wrote by any language, of course you start from bash.


### How to setup project

1. Clone project
2. Copy application.yaml.template to application.yaml in src/main/resources folder. Edit content if needed.
3. run .\gradlew eclipse
4. import into eclipse run the VaadinApplication class. Or in command line just type .\gradlew bootRun, then visit http://localhost

### How to write software installation script

Software object has a "runner" property, when you set this value to:

* Single value. for example "tclsh", "bash" etc. the final command execute on target box is: tclsh code_to_exec_uuid_filename -envfile code_to_exec_uuid_filename_env -action install
* With substitute variables. for example "powershell -File {code} {envfile} {action}", then final command is: powershell -File code_to_exec_uuid_filename code_to_exec_uuid_filename_env install

What does code_to_exec_uuid_filename_env like? It contains all information about group of servers and software it's self. [A JSON Example](https://github.com/jianglibo/easyinstaller/blob/master/softwares/zookeeper--centos7--3.4.5-powershell/test/envforcodeexec.json),
[A YAML Example](https://github.com/jianglibo/easyinstaller/blob/master/softwares/zookeeper--centos7--3.4.5-powershell/test/envforcodeexec.yaml),[A XML Example](https://github.com/jianglibo/easyinstaller/blob/master/softwares/zookeeper--centos7--3.4.5-powershell/test/envforcodeexec.xml)

code_to_exec_uuid_filename_env will upload to target server before script is invoked.

### Need Help?
* email: jianglibo@hotmail.com
* qq群: 418474680
