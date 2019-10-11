<<<<<<< HEAD
# Sberbook
### Правила 
[Правила](https://github.com/SBT2019/Sberbook/tree/master/documents/CONTRIBUTING.md).
Пожалуйста следуйте правилам при разработке, чтобы не возникло недоразумений. :shipit:
### Сервисы - Порты
1.	Профиль **9000**    
2.	Подписки **9100**
3.	Авторизация **9200**
4.	Регистрация **9300**
5.	Твиты **9400**
6.	Лента **9500**
7.	Рекомендации **9600**
8.	Load Balancer
9.	Service Register

![default](https://user-images.githubusercontent.com/22501063/53276168-0b971a80-370f-11e9-8fdb-96c52a82413b.png)

=======
# smart-home

Project Smart Home implements 'smart home''.
The house has sensors which are connected to a central server and send events in case of lights on/off doors open/close.
The system receives events of type SensorEvent.
<br/>
SmartHome - the home itself, contains rooms<br/>
Room - room, contains doors and lights<br/>
Door - door (interroom or entrance),<br/>
Light - source of light (lighbulb and etc)<br/>
SensorEvent - represents physical world event<br/>
SenserEventType - type of event, now there are 4 types<br/>
SensorCommand - command which allows to programmatically manage the physical world (turn on/off lights, open/close doorlocks)<br/>
CommandType - type of command, now only 1 type (turn ligths off)<br/>
>>>>>>> Smart/master
