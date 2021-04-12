## Интеграция с ReportPortal

1. Необходимо установить [Docker](https://www.docker.com/community-edition)
1. В настройках системы установить значение vm.max_map_count по инструкции [1](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/docker.html#docker-cli-run-prod-mode) или [2](https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html#_set_vm_max_map_count_to_at_least_262144)
1. Создать папку data в папке проекта и выставить разрешения:
```
    mkdir -p data/elasticsearch
    chmod 777 data/elasticsearch
    chgrp 1000 data/elasticsearch
``` 
4. Запустить создание контейнера `docker-compose -p reportportal up -d --force-recreate`
5. Подготовить проект к интеграции по инструкции для [Junit5](https://github.com/reportportal/agent-java-junit5)
6. Зайти на страницу [ReportPortal](http://localhost:8080/) и авторизоваться под админом `superadmin/erebus`
7. На [странице проектов](http://localhost:8080/ui/#administrate/projects) добавить новый проект
8. На [странице пользователей](http://localhost:8080/ui/#administrate/users) добавить пользователя и привязать его к проекту
9. Зайти в систему как новый пользователь и на [странице профиля](http://localhost:8080/ui/#user-profile) скопировать данные для вставки в REPORTPORTAL.PROPERTIES
10. Включить расширение ReportPortal в проекте либо аннотацией `@ExtendWith(ReportPortalExtension.class)` в тестовых классах, либо записью `com.epam.reportportal.junit5.ReportPortalExtension` в файле `org.junit.jupiter.api.extension.Extension`
11. Зарегистрировать агент ReportPortal одним из способов инструкции.
Для Gradle:
```
test {
    testLogging.showStandardStreams = true
    useJUnitPlatform()
    systemProperty 'junit.jupiter.extensions.autodetection.enabled', true
} 
```

[![Build status](https://ci.appveyor.com/api/projects/status/29rh0nwwp5029yki?svg=true)](https://ci.appveyor.com/project/Warlokk/aqa4-1-2)
