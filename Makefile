# Directorios y archivos fuente existentes
SRC_DIRS := FONTS
SRC_FILES := $(shell find $(SRC_DIRS) -name '*.java')

# Añadir el directorio de pruebas
TEST_DIR := FONTS/tests
TEST_FILES := $(shell find $(TEST_DIR) -name '*.java')

# Generar los archivos .class en sus respectivos directorios
CLASS_FILES := $(patsubst %.java, %.class, $(SRC_FILES))
TEST_CLASS_FILES := $(patsubst %.java, %.class, $(TEST_FILES))

# Capa de persistencia
PERSISTENCIA_DIR := FONTS/persistencia
PERSISTENCIA_SRC := $(shell find $(PERSISTENCIA_DIR) -name '*.java')
PERSISTENCIA_CLASS := $(patsubst %.java, %.class, $(PERSISTENCIA_SRC))

# Configuración de compilación
JAVAC := javac
JAVA := java
JUNIT_LIBS := libs/junit-4.13.2.jar:libs/hamcrest-core-1.3.jar
GSON_LIB := libs/gson-2.10.1.jar

# Objetivo principal: compilar todo (código fuente y pruebas)
all: $(CLASS_FILES) $(TEST_CLASS_FILES) $(PERSISTENCIA_CLASS)

# Regla para compilar archivos .java en .class en los mismos directorios
%.class: %.java
	$(JAVAC) -cp $(JUNIT_LIBS):$(GSON_LIB):. $<

# Regla para compilar la capa de persistencia
$(PERSISTENCIA_CLASS): $(PERSISTENCIA_SRC)
	$(JAVAC) -cp $(GSON_LIB):$(JUNIT_LIBS):. $^

# Objetivo para ejecutar las pruebas
test: $(TEST_CLASS_FILES)
	$(JAVA) -cp "$(JUNIT_LIBS):$(GSON_LIB):." org.junit.runner.JUnitCore $(subst /,.,$(patsubst %.java, %,$(TEST_FILES)))

# Limpiar archivos compilados (eliminar los .class generados en los directorios)
clean:
	find $(SRC_DIRS) -name '*.class' -delete
	find $(TEST_DIR) -name '*.class' -delete
	find $(PERSISTENCIA_DIR) -name '*.class' -delete

# Ver qué archivos serán compilados
print:
	@echo "Archivos fuente: $(SRC_FILES)"
	@echo "Archivos de prueba: $(TEST_FILES)"
	@echo "Archivos .class generados: $(CLASS_FILES)"
	@echo "Archivos .class de pruebas generados: $(TEST_CLASS_FILES)"
