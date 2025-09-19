Para ejecutar los tests unitarios de cada clase recomendamos usar este comando en la terminal: java -cp .:libs/hamcrest-core-1.3.jar:libs/junit-4.13.2.jar: org.junit.runner.JUnitCore  FONTS.tests.??    donde los interrogantes son el test que se desee ejecutar

Para compilar los .java de persistencia se debe usar este comando:
javac -d . -cp .:libs/gson-2.10.1.jar FONTS/persistencia/*.java
Para ejecutar los .class de persistencia se debe usar este comando:
java -cp .:libs/gson-2.10.1.jar FONTS.persistencia."archivo.class"
