NAME = simulator
PACKAGE_PATH = com/FortyTwo/avaj/simulator
ENTRY_POINT = com.FortyTwo.avaj.simulator.Simulator
SOURCES_FILE = sources.txt

JAVA = java
RM = rm -f

all: compile
	java $(ENTRY_POINT) scenario.txt && cat simulation.txt

compile:
	find * -name "*.java" > $(SOURCES_FILE)
	javac @$(SOURCES_FILE)


clean:
	find . -name "*.class" -delete
	$(RM) $(SOURCES_FILE)

fclean: clean
	$(RM) simulation.txt

re: fclean all

.PHONY: all compile clean fclean re run