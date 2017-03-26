Java-Beispiel mit Chef
=============================

Dieses Beispiel zeigt, wie Chef genutzt werden kann, um eine Java-Umgebung zu konfigurieren.

Das Vagrantfile kann genutzt werden, um eine Java-Anwendung in einer
virtuellen Maschine zu installieren. Diese virtuelle Maschine wird von
VirtualBox verwaltet. Siehe http://vagrantup.com/  Um die Anwendung zu
starten muss nur `vagrant up` genutzt werden.

Die Anwendung kann auch auf einem System mit Chef Solo installiert
werden. Dazu dient das Skript `install.sh`.

Die Anwendung findet sich im Verzeichnis `cookbooks/webapp/files/default` .

In der Datei `roles/tomcatserver.json` ist konfiguriert, welche Rezpte
in ablaufen sollen und wie sie konfiguriert sind. Die Cookbooks stammen
aus dem Opscode Open Source Repository.

Das Verzeichnis `.chef` enthält ein einfaches `knife.rb`, das
angepasst werden muss, um es mit knife zu nutzen und die Anwendung
dann in der Amazon-EC2-Cloud zu installieren. Anweisungen sind in dem File.

Um es zu benutzen:
* Installiere Knife - siehe https://downloads.chef.io/chef-dk/ ,
* Installiere das EC2-PlugIn - siehe dazu
  https://github.com/chef/knife-ec2#installation
* Lade die Cookbooks mit `knife cookbook upload –a` hoch
* Lade die Rolle mit `knife role from file roles/tomcatserver.json` hoch
* Jetzt kannst Du mit  `knife ec2 server create -r
  'role[tomcatserver]' --identity-file ~/.ssh/<schluessel>.pem`
  einen neuen Server installieren

