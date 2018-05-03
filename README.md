# webapps-project-archetype

[![Build Status](https://travis-ci.org/DSI-Ville-Noumea/webapps-project-archetype.svg?branch=master)](https://travis-ci.org/DSI-Ville-Noumea/webapps-project-archetype) [![Dependency Status](https://www.versioneye.com/user/projects/595099750fb24f00407c3488/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/595099750fb24f00407c3488)  [![Coverage Status](https://coveralls.io/repos/github/DSI-Ville-Noumea/webapp-project-archetype/badge.svg)](https://coveralls.io/github/DSI-Ville-Noumea/webapp-project-archetype)

Archetype Maven pour projet web ZK Mairie


## Installation de l'archetype dans le repository maven local

```
>> git clone https://github.com/DSI-Ville-Noumea/webapps-project-archetype.git
>> cd webapps-project-archetype
>> mvn install
```

## Créer un nouveau projet à partir de l'archetype

```
>> cd ..
>> mvn archetype:generate -DarchetypeArtifactId=webapps-project-archetype -DarchetypeGroupId=nc.noumea.mairie -DarchetypeVersion=1.00.00 -DappName=myApp -DarchetypeCatalog=local
```